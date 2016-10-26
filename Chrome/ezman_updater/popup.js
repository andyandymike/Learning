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
input_value = document.getElementById('version_num').value;
alert(input_value);
}

function getCheckedValue(){
checked_value = $("input[name='cases_status']:checked").val();
alert(checked_value);
}

window.onload = function() {
  document.getElementById('upgrade_page').onclick = updateForm;
  document.getElementById('test_button').onclick = getCheckedValue;
}