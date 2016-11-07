var selectedId = [];

chrome.tabs.onUpdated.addListener(function(tabId, props) {
	tabIdIndex = selectedId.indexOf(tabId);
	if (props.status == "complete" && selectedId.indexOf(tabId) > -1) {
	  chrome.tabs.get(selectedId[tabIdIndex], function(tab){
		  if(tab.url.match('statusUpdate') != 'statusUpdate'){
			  chrome.tabs.executeScript(tabId, {file: 'updateForm.js', allFrames: true, runAt: "document_start"}, function(){
				  if(tabIdIndex > -1){
					  selectedId.splice(tabIdIndex, 1);
				  }
			  });
		  }
		});
	};
});

function reDirect(tabId, url){
	selectedId.push(tabId);
	chrome.tabs.get(tabId, function(tab){
		chrome.tabs.executeScript(tabId, {code: 'window.location = "'+url+'"', allFrames: true, runAt: "document_start"}, function(){
				chrome.storage.local.get({updateURLs: []}, function (result) {
					var updateURLs = result.updateURLs;
					urlIndex = updateURLs.indexOf(url);
					if(urlIndex > -1){
						updateURLs.splice(urlIndex, 1);
					}
					chrome.storage.local.set({updateURLs: updateURLs});
			});
		});
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
          //alert('key: '+changeKey+' in: '+namespace+' changes! Old: '+storageChange.oldValue+' New: '+storageChange.newValue);
		  if(storageChange.oldValue ==  undefined){
			  if(storageChange.newValue != undefined){
				  for(var i = 0; i < storageChange.newValue.length; i++){
					  createReDirect(storageChange.newValue[i]);
					}
				}
			}
		  else{
			  if(storageChange.oldValue < storageChange.newValue){
				  for(var i = 0; i < storageChange.newValue.length; i++){
					  createReDirect(storageChange.newValue[i]);
					  }
				}
			}
	}
});