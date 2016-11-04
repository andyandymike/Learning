var selectedId = -1;

chrome.tabs.onUpdated.addListener(function(tabId, props) {
  if (props.status == "complete" && tabId == selectedId) {
	  chrome.tabs.get(selectedId, function(tab){
		  if(tab.url.match('statusUpdate') != 'statusUpdate'){
			  chrome.tabs.executeScript(tabId, {file: 'updateForm.js', allFrames: true, runAt: "document_start"});
			  selectedId = -1;
		  }
		});
	};
});

function reDirect(tabId, url){
	chrome.tabs.get(tabId, function(tab){
		chrome.tabs.executeScript(tabId, {code: 'window.location = "'+url+'"', allFrames: true, runAt: "document_start"});
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
			results.push(sortedArr[i]);
			sortedArr.splice(i + 1);
			i = i - 1;
		}
		else{
			results.push(sortedArr[i])
		}
	}
	return results;
}

chrome.runtime.onMessage.addListener(function(message, sender, sendResponse){
	if(message.count <= 2){
		reDirect(message.id, message.url);
		sendResponse({"count": ++message.count});
	}
});

chrome.storage.onChanged.addListener(function(changes, namespace) {
	for (changeKey in changes) {
          var storageChange = changes[changeKey];
          //alert('key: '+changeKey+' in: '+namespace+' cahnges! Old: '+storageChange.oldValue+' New: '+storageChange.newValue);
		  alert(uniqueArrary(storageChange.newValue));
        }
});