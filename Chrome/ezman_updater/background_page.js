function changeSearchText(){
  chrome.windows.getCurrent(function (currentWindow) {
    chrome.tabs.query({active: true, windowId: currentWindow.id},
                      function(activeTabs) {
		chrome.tabs.executeScript(
        activeTabs[0].id, {file: 'change_baidu.js', allFrames: true});
    });
  });
}

window.onload = function() {
  document.getElementById('my_button').onclick = changeSearchText;
}