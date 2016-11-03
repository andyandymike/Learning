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

chrome.runtime.onMessage.addListener(function(message, sender, sendResponse){
	if(message.count <= 2){
		reDirect(message.id, message.url);
		sendResponse({"count": ++message.count});
	}
});

