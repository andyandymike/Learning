function getVersionNum(){
	versionNum = document.getElementById('version_num').value;
	return versionNum.replace(/\s/g,'');
}

function getCheckedValue(){
	checkedValue = $("input[name='cases_status']:checked").val();
	return checkedValue;
}

function reDirect(url){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		chrome.tabs.executeScript(tabs[0].id, {code: 'window.location = "'+url+'"', allFrames: true, runAt: "document_start"});
    });
}

function refresh(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		chrome.tabs.executeScript(tabs[0].id, {file: 'refresh.js', allFrames: true, runAt: "document_start"});
    });
}

function updateForm(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		chrome.tabs.executeScript(tabs[0].id, {file: 'updateForm.js', allFrames: true, runAt: "document_start"});
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
	});
}

function getStatus(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		alert(tabs[0].status);
	});
}

function updatePage(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		tempURL = tabs[0].url;
		if(tempURL.match('statusUpdate') != 'statusUpdate'){
			if(getCheckedValue() != 'null'){
				if(getVersionNum() == ''){
					tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=null');
					tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus='+getCheckedValue());
					chrome.extension.getBackgroundPage().selectedId = tabs[0].id;
					reDirect(tempURL);
				}
				else{
					tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=null');
					tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus='+getCheckedValue());
					chrome.extension.getBackgroundPage().selectedId = tabs[0].id;
					chrome.runtime.sendMessage({'count': 1, 'id': tabs[0].id, 'url': tempURL}, function(response){
						alert('test');
						tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=D.'+getVersionNum());
						tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus=null');
						chrome.extension.getBackgroundPage().selectedId = tabs[0].id;
						chrome.runtime.sendMessage({'count': 2, 'id': tabs[0].id, 'url': tempURL}, function(response){alert(response);});
						});					
				}
			}
			else{
				if(getVersionNum() != ''){
					tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=D.'+getVersionNum());
					tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus=null');
					chrome.extension.getBackgroundPage().selectedId = tabs[0].id;
					reDirect(tempURL);
				}
			}
		}
		else{
			alert('Cannot update current page, please turn back to previous page!');
		}
	});
}

function testSend(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		chrome.runtime.sendMessage({'count': 1, 'id': tabs[0].id, 'url': 'http://www.baidu.com/'}, function(response){alert(response);});
	});
}


window.onload = function() {
  document.getElementById('upgrade_page').onclick = updatePage;
  document.getElementById('test').onclick = testSend;
}