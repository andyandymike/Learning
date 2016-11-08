function getVersionNum(){
	versionNum = document.getElementById('version_num').value;
	return versionNum.replace(/\s/g,'');
}

function getCheckedValue(){
	checkedValue = $("input[name='cases_status']:checked").val();
	return checkedValue;
}

function updatePage(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		tempURL = tabs[0].url;
		if(tempURL.match('statusUpdate') != 'statusUpdate'){
			if(getCheckedValue() != 'null'){
				if(getVersionNum() == ''){
					tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=null');
					tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus='+getCheckedValue());
					addURL(tempURL);
				}
				else{
					var tempURLs = [];
					tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=null');
					tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus='+getCheckedValue());
					tempURLs.push(tempURL);
					tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=D.'+getVersionNum());
					tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus=null');
					tempURLs.push(tempURL);
					addURL(tempURLs);					
				}
			}
			else{
				if(getVersionNum() != ''){
					tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=D.'+getVersionNum());
					tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus=null');
					addURL(tempURL);
				}
			}
		}
		else{
			alert('Cannot update current page, please turn back to previous page!');
		}
	});
}

function addURL(url){
	chrome.storage.local.get({updateURLs: []}, function (result) {
		var updateURLs = result.updateURLs;
		if(typeof url === 'object'){
			for(var i = 0; i < url.length; i++){
				updateURLs.push(url[i]);
			}
		}
		else{
			updateURLs.push(url);
		}
		chrome.storage.local.set({updateURLs: updateURLs});
	});
}

function test(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		tabId = tabs[0].id
		chrome.tabs.executeScript(tabId, {file: 'refresh.js', allFrames: true, runAt: "document_start"}, function(){
			chrome.tabs.sendMessage(tabId, {text: 'get_body'}, function (targetElement){});
		});
	});
}

window.onload = function() {
	document.getElementById('upgrade_page').onclick = updatePage;
	document.getElementById('test').onclick = test;
}