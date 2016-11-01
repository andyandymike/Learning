var selectedId = -1;
var test = -1;

chrome.tabs.onUpdated.addListener(function(tabId, props) {
  if (props.status == "complete" && tabId == test)
	  alert('complete');
});

chrome.tabs.onActivated.addListener(function(props) {
  selectedId = props.tabId;
});

chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
  selectedId = tabs[0].id;
});