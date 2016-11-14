function updatePage(aUpdatePageTabId) {
	var updatePageStatus = null;
	
	updatePage.prototype.addListener = function() {
		chrome.tabs.onUpdated.addListener(function(tabId, props) {
			if (tabId == aUpdatePageTabId) {
				chrome.tabs.get(aUpdatePageTabId, function(tab){
					if(tab.url.match('statusUpdate') != 'statusUpdate'){
						var tempURL = tab.url;
						chrome.tabs.sendMessage(aUpdatePageTabId, {request: 'loaded?'}, function (response){
							updatePageStatus = response.status;
							if(updatePageStatus == 'timeout'){
								chrome.tabs.executeScript(aUpdatePageTabId, {file: 'refresh.js', allFrames: true, runAt: "document_start"}, function(){});
								}
							if(updatePageStatus == 'loaded'){
								chrome.tabs.executeScript(aUpdatePageTabId, {file: 'updateForm.js', allFrames: true, runAt: "document_start"}, function(){
									chrome.storage.local.get({updateURLs: []}, function (result) {
										var updateURLs = result.updateURLs;
										var urlIndex = updateURLs.indexOf(tempURL);
										if(urlIndex > -1){
											updateURLs.splice(urlIndex, 1);
											}
										chrome.storage.local.set({updateURLs: updateURLs});
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

function createReDirect(url){
	chrome.tabs.create({url: url}, function(tab){
		var oUpdatePage = new updatePage(tab.id);
		oUpdatePage.addListener();
		reDirect(tab.id, url);
	});
}

function uniqueArrary(array){
	var sortedArr = array.sort(); 
	var results = [];
	for (var i = 0; i < sortedArr.length; i++) {
		if (sortedArr[i + 1] == sortedArr[i]) {
			sortedArr.splice(i + 1, 1);
			i--;
		}
		else{
			results.push(sortedArr[i])
		}
	}
	return results;
}

chrome.storage.onChanged.addListener(function(changes, namespace) {
	for (var changeKey in changes) {
          var storageChange = changes[changeKey];
		  if(storageChange.newValue[0] !=  undefined){
			  createReDirect(storageChange.newValue[0]);
		  }
		  else{
		  }
	}
});