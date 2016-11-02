var selectedId = -1;
var tempId = -1;

chrome.tabs.onUpdated.addListener(function(tabId, props) {
  if (props.status == "complete" && tabId == selectedId) {
	  chrome.tabs.executeScript(tabId, {file: 'updateForm.js', allFrames: true});
  };
});