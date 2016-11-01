function updateForm(){
  chrome.windows.getCurrent(function (currentWindow) {
    chrome.tabs.query({active: true, windowId: currentWindow.id},
                      function(activeTabs) {
		chrome.tabs.executeScript(
        activeTabs[0].id, {file: 'updateForm.js', allFrames: true});
    });
  });
}

function getInputValue(){
inputValue = document.getElementById('version_num').value;
return inputValue;
}

function getCheckedValue(){
checkedValue = $("input[name='cases_status']:checked").val();
return checkedValue;
}

function getURL(){
  chrome.windows.getCurrent(function (currentWindow) {
    chrome.tabs.query({active: true, windowId: currentWindow.id},
                      function(activeTabs) {
						  tempURL = activeTabs[0].url;
						  tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=D.'+getInputValue());
						  tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus='+getCheckedValue());
						  alert(tempURL);
    });
  });
}

function getTabStatus(){
  chrome.windows.getCurrent(function (currentWindow) {
    chrome.tabs.query({active: true, windowId: currentWindow.id},
                      function(activeTabs) {
						  alert(activeTabs[0].status);
    });
  });
}

function reDirect(){
  chrome.windows.getCurrent(function (currentWindow) {
    chrome.tabs.query({active: true, windowId: currentWindow.id},
                      function(activeTabs) {
						  tempURL = activeTabs[0].url;
						  tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=D.'+getInputValue());
						  tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus='+getCheckedValue());
						  chrome.tabs.executeScript(activeTabs[0].id, {code: 'window.location = "'+tempURL+'"', allFrames: true});
    });
  });
}

function refresh(){
  chrome.windows.getCurrent(function (currentWindow) {
    chrome.tabs.query({active: true, windowId: currentWindow.id},
                      function(activeTabs) {
						  chrome.tabs.executeScript(activeTabs[0].id, {code: 'window.location.reload(true)', allFrames: true});
    });
  });
}

function closeOpen(){
  chrome.windows.getCurrent(function (currentWindow) {
    chrome.tabs.query({active: true, windowId: currentWindow.id},
                      function(activeTabs) {
						  alert(activeTabs[0].url)
						  chrome.tabs.duplicate(activeTabs[0].id);
						  chrome.tabs.remove(activeTabs[0].id);
    });
  });
}

window.onload = function() {
  document.getElementById('upgrade_page').onclick = updateForm;
  document.getElementById('test_button').onclick = closeOpen;
}