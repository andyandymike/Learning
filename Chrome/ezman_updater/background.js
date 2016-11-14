var selectedId = [];
var tabIdIndex = null;
var updatePageStatus = null;
				
chrome.tabs.onUpdated.addListener(function(tabId, props) {
	tabIdIndex = selectedId.indexOf(tabId);
	if (selectedId.indexOf(tabId) > -1) {
		chrome.tabs.get(selectedId[tabIdIndex], function(tab){
			if(tab.url.match('statusUpdate') != 'statusUpdate'){
				tempURL = tab.url;
				chrome.tabs.sendMessage(selectedId[tabIdIndex], {request: 'loaded?'}, function (response){
					updatePageStatus = response.status;
					if(updatePageStatus == 'timeout'){
						chrome.tabs.executeScript(selectedId[tabIdIndex], {file: 'refresh.js', allFrames: true, runAt: "document_start"}, function(){});
						}
					if(updatePageStatus == 'loaded'){
						chrome.tabs.executeScript(tabId, {file: 'updateForm.js', allFrames: true, runAt: "document_start"}, function(){
							if(tabIdIndex > -1){
								selectedId.splice(tabIdIndex, 1);
								}
								chrome.storage.local.get({updateURLs: []}, function (result) {
									var updateURLs = result.updateURLs;
									urlIndex = updateURLs.indexOf(tempURL);
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
	if(props.url.indexOf('statusUpdate') > -1){
		chrome.tabs.remove(tabId);
	}
});

function reDirect(tabId, url){
	selectedId.push(tabId);
	chrome.tabs.get(tabId, function(tab){
		chrome.tabs.executeScript(tabId, {code: 'window.location = "'+url+'"', allFrames: true, runAt: "document_start"}, function(){});
    });
}

function createReDirect(url){
	chrome.tabs.create({url: url}, function(tab){
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
	for (changeKey in changes) {
          var storageChange = changes[changeKey];
		  if(storageChange.newValue[0] !=  undefined){
			  createReDirect(storageChange.newValue[0]);
		  }
	}
});