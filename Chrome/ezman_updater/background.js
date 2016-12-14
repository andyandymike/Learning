var updatePageTabIds = [];
var oriPageInfo = [];
var tabsWaitingList = [];
var tabsWaitingListKeyList = [];
var tempURLs = [];
var updateWindowId = 0;
var openedTabsNum = 0;
var lastChangeKey = '';
var maxTabsNum = 5;

function getSuiteName(url){
	var suiteName = url.match(/suite=[^\&]*/);
	suiteName = suiteName[0].replace('suite=','');
	return suiteName;
}

function getStatus(url){
	var status = url.match(/sstatus=[^\&]*/);
	status = status[0].replace('sstatus=','');
	return status;
}

function getBuildNum(url){
	var buildNum = url.match(/sbuild=[^\&]*/);
	buildNum = buildNum[0].replace('sbuild=','');
	return buildNum;
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

chrome.windows.onRemoved.addListener(function(windowId){
	if(windowId == updateWindowId){
		updatePageTabIds = [];
		oriPageInfo = [];
		tabsWaitingList = [];
		tabsWaitingListKeyList = [];
		tempURLs = [];
		updateWindowId = 0;
		openedTabsNum = 0;
		lastChangeKey = '';
	}
});

function updatePage(updatePageTabId, updateSuiteName, updateStatus, updateBuildNum){
	var updatePageStatus = null;
	
	updatePage.prototype.addOnUpdateListener = function(){
		chrome.tabs.onUpdated.addListener(function(tabId, changeInfo, tab){
			if(tabId == updatePageTabId){
				if(tab.url.match('statusUpdate') != 'statusUpdate'){
					var obj = {};
					obj[updatePageTabId] = tab.url;
					tempURLs.push(obj);
					
					if(changeInfo.discarded){
						chrome.tabs.executeScript(updatePageTabId, {file: 'refresh.js', allFrames: true, runAt: "document_start"}, function(){});
					}
					
					if(changeInfo.status ==  'complete'){
						chrome.tabs.sendMessage(updatePageTabId, {post: 'complete'}, function (response){});
					}
					
					chrome.tabs.sendMessage(updatePageTabId, {request: 'loaded?', updateStatus: updateStatus, updateBuildNum: updateBuildNum}, function (response){
						updatePageStatus = response.status;
						if(updatePageStatus == 'timeout' || updatePageStatus == 'refresh'){
							chrome.tabs.executeScript(updatePageTabId, {file: 'refresh.js', allFrames: true, runAt: "document_start"}, function(){});
						}
						if(updatePageStatus == 'loaded'){
							chrome.tabs.executeScript(updatePageTabId, {file: 'scrolldown.js', allFrames: true, runAt: "document_start"}, function(){
								chrome.tabs.executeScript(updatePageTabId, {file: 'updateForm.js', allFrames: true, runAt: "document_start"}, function(){
									var idIndex = updatePageTabIds.indexOf(updatePageTabId)
									if(idIndex > -1){
										updatePageTabIds.splice(idIndex, 1);
									}
								});
							});
						}
					});
				}
			}
			if(changeInfo.url.indexOf('statusUpdate') > -1 && tabId == updatePageTabId){
				openedTabsNum--;
				var tempURL = '';
				for(var i = 0; i < tempURLs.length; i++){
					tempURL = tempURLs[i][updatePageTabId];
					if(tempURL != undefined){
						break;
					}
				}
				
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
				chrome.tabs.remove(updatePageTabId);
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
	//alert('url: '+url+', windowId: '+updateWindowId);
	chrome.tabs.create({url: url, windowId: updateWindowId}, function(tab){
		var updateSuiteName = getSuiteName(url);
		var updateStatus = getStatus(url);
		var updateBuildNum = getBuildNum(url);
		var oUpdatePage = new updatePage(tab.id, updateSuiteName, updateStatus, updateBuildNum);
		oUpdatePage.addOnUpdateListener();
		reDirect(tab.id, url);
	});
}

chrome.storage.onChanged.addListener(function(changes, namespace){	
	for(var changeKey in changes){
		var storageChange = changes[changeKey];
		if(storageChange.newValue[0] !=  undefined){
			var obj = {};
			obj[changeKey] = storageChange.newValue[0];
			tabsWaitingList.push(obj);
			tabsWaitingListKeyList.push(changeKey);
			
			//createReDirect(storageChange.newValue[0], changeKey);
			while(openedTabsNum < maxTabsNum && openedTabsNum < tabsWaitingListKeyList.length){
				openedTabsNum++;
				var tempChangeKey = changeKey;
				var waitingListIndex = 0;
				for(var i = 0; lastChangeKey == tempChangeKey && i < tabsWaitingListKeyList.length; i++){
					tempChangeKey = tabsWaitingListKeyList[i];
					waitingListIndex = i;
				}
				lastChangeKey = tempChangeKey;
				createReDirect(tabsWaitingList[waitingListIndex][tabsWaitingListKeyList[waitingListIndex]]);
				tabsWaitingList.splice(waitingListIndex, 1);
				tabsWaitingListKeyList.splice(waitingListIndex, 1);
			}
		}
		else{
			chrome.tabs.query({windowId: updateWindowId, url: 'chrome://newtab/'}, function(tabs){
				chrome.tabs.remove(tabs[0].id);
			});
			
			while(openedTabsNum < maxTabsNum && openedTabsNum < tabsWaitingListKeyList.length){
				openedTabsNum++;
				var tempChangeKey = changeKey;
				var waitingListIndex = 0;
				for(var i = 0; lastChangeKey == tempChangeKey && i < tabsWaitingListKeyList.length; i++){
					tempChangeKey = tabsWaitingListKeyList[i];
					waitingListIndex = i;
				}
				lastChangeKey = tempChangeKey;
				createReDirect(tabsWaitingList[waitingListIndex][tabsWaitingListKeyList[waitingListIndex]]);
				tabsWaitingList.splice(waitingListIndex, 1);
				tabsWaitingListKeyList.splice(waitingListIndex, 1);
			}
			
			for(var i = 0; i < oriPageInfo.length; i++){
				if(oriPageInfo[i][changeKey] != undefined){
					//chrome.tabs.remove(oriPageInfo[i][changeKey]);
				}
			}
		}
	}
});