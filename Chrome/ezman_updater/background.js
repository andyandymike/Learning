function updatePage(updatePageTabId, updateSuiteName) {
	var updatePageStatus = null;
	
	updatePage.prototype.addListener = function() {
		chrome.tabs.onUpdated.addListener(function(tabId, props) {
			if (tabId == updatePageTabId) {
				chrome.tabs.get(updatePageTabId, function(tab){
					if(tab.url.match('statusUpdate') != 'statusUpdate'){
						var tempURL = tab.url;
						chrome.tabs.sendMessage(updatePageTabId, {request: 'loaded?'}, function (response){
							updatePageStatus = response.status;
							if(updatePageStatus == 'timeout'){
								chrome.tabs.executeScript(updatePageTabId, {file: 'refresh.js', allFrames: true, runAt: "document_start"}, function(){});
								}
							if(updatePageStatus == 'loaded'){
								chrome.tabs.executeScript(updatePageTabId, {file: 'updateForm.js', allFrames: true, runAt: "document_start"}, function(){
									var obj = {};
									obj[updateSuiteName] = [];
									chrome.storage.local.get(obj, function (result) {
										var updateURLs = result[updateSuiteName];
										var urlIndex = updateURLs.indexOf(tempURL);
										if(urlIndex > -1){
											updateURLs.splice(urlIndex, 1);
											}
										obj[updateSuiteName] = updateURLs;
										chrome.storage.local.set(obj);
										});
									});
								}
							});
						}
					});
				}
				//if(props.url.indexOf('statusUpdate') > -1){
				//	chrome.tabs.remove(tabId);
				//}
			});
		};
}

function reDirect(tabId, url){
	chrome.tabs.get(tabId, function(tab){
		chrome.tabs.executeScript(tabId, {code: 'window.location = "'+url+'"', allFrames: true, runAt: "document_start"}, function(){});
    });
}

function createReDirect(url, updateSuiteName){
	chrome.tabs.create({url: url}, function(tab){
		var oUpdatePage = new updatePage(tab.id, updateSuiteName);
		oUpdatePage.addListener();
		reDirect(tab.id, url);
	});
}

chrome.storage.onChanged.addListener(function(changes, namespace) {
	for (var changeKey in changes) {
		var storageChange = changes[changeKey];
		if(storageChange.newValue[0] !=  undefined){
			createReDirect(storageChange.newValue[0], changeKey);
			}
		else{}
	}
});