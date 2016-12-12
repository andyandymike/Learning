var updatePageTabIds = [];
var oriPageInfo = [];
var tabsWaitingList = [];
var tabsWaitingListKeyList = [];
var maxTabsNum = 5;
var openedTabsNum = 0;

function getSuiteName(url){
	var suiteName = url.match(/suite=[^\&]*/);
	suiteName = suiteName[0].replace('suite=','');
	return suiteName;
}

var scope = {urls: ["*://*/ezman/TestCaseStatus.jsp*", "*://*/ezman/statusUpdate*"]};
chrome.webRequest.onErrorOccurred.addListener(function(details){
	if(details.error == 'net::ERR_INCOMPLETE_CHUNKED_ENCODING'){
		if(updatePageTabIds.indexOf(details.tabId) > -1){
			if(details.url.indexOf('TestCaseStatus.jsp') > -1){
				chrome.tabs.executeScript(details.tabId, {file: 'refresh.js', allFrames: true, runAt: "document_start"}, function(){});
			}
			if(details.url.indexOf('statusUpdate') > -1){
				chrome.tabs.remove(details.tabId);
			}
		}
	}
	}, scope);

function updatePage(updatePageTabId, updateSuiteName){
	var updatePageStatus = null;
	
	updatePage.prototype.addOnUpdateListener = function(){
		chrome.tabs.onUpdated.addListener(function(tabId, props){
			if(tabId == updatePageTabId){
				chrome.tabs.get(updatePageTabId, function(tab){
					if(tab.url.match('statusUpdate') != 'statusUpdate'){
						var tempURL = tab.url;
						chrome.tabs.sendMessage(updatePageTabId, {request: 'loaded?'}, function (response){
							updatePageStatus = response.status;
							if(updatePageStatus == 'timeout'){
								chrome.tabs.executeScript(updatePageTabId, {file: 'refresh.js', allFrames: true, runAt: "document_start"}, function(){});
								}
							if(updatePageStatus == 'loaded'){
								chrome.tabs.executeScript(updatePageTabId, {file: 'scrolldown.js', allFrames: true, runAt: "document_start"}, function(){
									chrome.tabs.executeScript(updatePageTabId, {file: 'updateForm.js', allFrames: true, runAt: "document_start"}, function(){
										var idIndex = updatePageTabIds.indexOf(updatePageTabId)
										if(idIndex > -1){
											updatePageTabIds.splice(idIndex, 1);
										}
										
										openedTabsNum--;
										var obj = {};
										obj[updateSuiteName] = [];
										chrome.storage.local.get(obj, function (result){
											var updateURLs = result[updateSuiteName];
											var urlIndex = updateURLs.indexOf(tempURL);
											if(urlIndex > -1){
												updateURLs.splice(urlIndex, 1);
												}
											obj[updateSuiteName] = updateURLs;
											chrome.storage.local.set(obj);
											});
										});
									});
								}
							});
						}
					});
				}
			if(props.url.indexOf('statusUpdate') > -1 && tabId == updatePageTabId){
				chrome.tabs.remove(tabId);
			}
		});
	};
}

function reDirect(tabId, url){
	updatePageTabIds.push(tabId);
	chrome.tabs.get(tabId, function(tab){
		chrome.tabs.executeScript(tabId, {code: 'window.location = "'+url+'"', allFrames: true, runAt: "document_start"}, function(){});
    });
}

function createReDirect(url){
	chrome.tabs.create({url: url}, function(tab){
		var updateSuiteName = getSuiteName(url);
		var oUpdatePage = new updatePage(tab.id, updateSuiteName);
		oUpdatePage.addOnUpdateListener();
		reDirect(tab.id, url);
	});
}

chrome.storage.onChanged.addListener(function(changes, namespace){
	for (var changeKey in changes){
		var storageChange = changes[changeKey];
		if(storageChange.newValue[0] !=  undefined){
			var obj = {};
			obj[changeKey] = storageChange.newValue[0];
			tabsWaitingList.push(obj);
			tabsWaitingListKeyList.push(changeKey);
			
			//createReDirect(storageChange.newValue[0], changeKey);
			if(openedTabsNum < maxTabsNum){
				openedTabsNum++;
				createReDirect(tabsWaitingList[0][tabsWaitingListKeyList[0]]);
				tabsWaitingList.shift();
				tabsWaitingListKeyList.shift();
			}
		}
		else{
			if(openedTabsNum < maxTabsNum){
				openedTabsNum++;
				createReDirect(tabsWaitingList[0][tabsWaitingListKeyList[0]]);
				tabsWaitingList.shift();
				tabsWaitingListKeyList.shift();
			}	
			
			for(var i = 0; i < oriPageInfo.length; i++){
				if(oriPageInfo[i][changeKey] != undefined){
					//chrome.tabs.remove(oriPageInfo[i][changeKey]);
				}
			}
		}
	}
});