function getInputValue(){
	inputValue = document.getElementById('version_num').value;
	return inputValue;
}

function getCheckedValue(){
	checkedValue = $("input[name='cases_status']:checked").val();
	return checkedValue;
}

function reDirect(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
						  tempURL = tabs[0].url;
						  tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=D.'+getInputValue());
						  tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus='+getCheckedValue());
						  chrome.tabs.executeScript(tabs[0].id, {code: 'window.location = "'+tempURL+'"', allFrames: true});
    });
}

function refresh(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		chrome.tabs.executeScript(tabs[0].id, {file: 'refresh.js', allFrames: true, runAt: "document_start"});
    });
}

function closeOpen(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		chrome.tabs.duplicate(tabs[0].id, function(tab){
			chrome.extension.getBackgroundPage().selectedId = tab.id;
		});
		chrome.tabs.remove(tabs[0].id);
	});
}

function sendCurrentTabId(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		chrome.extension.getBackgroundPage().selectedId = tabs[0].id;
		alert(tempId);
	});
}

function getStatus(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		alert(tabs[0].status);
	});
}

function test(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		chrome.tabs.update(tabs[0].id, {url:'javascript:void window.stop();'});
	});
}

window.onload = function() {
  document.getElementById('upgrade_page').onclick = closeOpen;
  document.getElementById('test').onclick = refresh;
}