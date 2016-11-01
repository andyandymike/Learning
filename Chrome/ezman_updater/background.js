var selectedId = -1;

chrome.tabs.onUpdated.addListener(function(tabId, props) {
  if (props.status == "complete")
	  alert('onUpdated');
});

chrome.tabs.onActivated.addListener(function(tabId) {
  selectedId = tabId;
});

chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
  selectedId = tabs[0].id;
  alert('query');
});