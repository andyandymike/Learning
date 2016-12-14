var testunitUpdateTimes = 1;

function getVersionNum(){
	var versionNum = document.getElementById('version_num').value;
	return versionNum.replace(/\s/g,'');
}

function getCheckedValue(){
	var checkedValue = $("input[name='cases_status']:checked").val();
	return checkedValue;
}

function getSuiteName(url){
	var suiteName = url.match(/suite=[^\&]*/);
	suiteName = suiteName[0].replace('suite=','');
	return suiteName;
}

function uniqueArrary(array){
	var sortedArr = array.sort(); 
	var results = [];
	for(var i = 0; i < sortedArr.length; i++){
		if(sortedArr[i + 1] == sortedArr[i]){
			sortedArr.splice(i + 1, 1);
			i--;
		}
		else{
			results.push(sortedArr[i])
		}
	}
	return results;
}

function updatePage(){	
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		var tempURL = tabs[0].url;
		var tempId = tabs[0].id;
		var obj = {};
		var tempSuiteName = getSuiteName(tempURL);
		obj[tempSuiteName] = tempId;
		if(tempURL.match('statusUpdate') != 'statusUpdate' && tempURL.match('TestCaseStatus') == 'TestCaseStatus'){			
			if(getCheckedValue() != 'null'){
				if(getVersionNum() == ''){
					tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=null');
					tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus='+getCheckedValue());
					chrome.extension.getBackgroundPage().oriPageInfo.push(obj);
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
					chrome.extension.getBackgroundPage().oriPageInfo.push(obj);
					addURL(tempURLs);					
				}
			}
			else{
				if(getVersionNum() != ''){
					tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=D.'+getVersionNum());
					tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus=null');
					chrome.extension.getBackgroundPage().oriPageInfo.push(obj);
					addURL(tempURL);
				}
			}
		}
		else{
			//console.log('Cannot update current page!');
		}
	});
}

function updateAllPages(){
	chrome.tabs.query({},function(tabs){
		var tempURLs = []; 
		for(var i = 0; i < tabs.length; i++){
			var tempURL = tabs[i].url;
			var tempId = tabs[i].id;
			if(tempURL.match('statusUpdate') != 'statusUpdate' && tempURL.match('TestCaseStatus') == 'TestCaseStatus'){				
				var obj = {};
				var tempSuiteName = getSuiteName(tempURL);
				obj[tempSuiteName] = tempId;
				if(getCheckedValue() != 'null'){
					if(getVersionNum() == ''){
						tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=null');
						tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus='+getCheckedValue());
						chrome.extension.getBackgroundPage().oriPageInfo.push(obj);
						tempURLs.push(tempURL);	
					}
					else{
						tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=null');
						tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus='+getCheckedValue());
						tempURLs.push(tempURL);
						tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=D.'+getVersionNum());
						tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus=null');
						chrome.extension.getBackgroundPage().oriPageInfo.push(obj);
						tempURLs.push(tempURL);				
					}
				}
				else{
					if(getVersionNum() != ''){
						tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=D.'+getVersionNum());
						tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus=null');
						chrome.extension.getBackgroundPage().oriPageInfo.push(obj);
						tempURLs.push(tempURL);	
					}
				}
			}
			else{
				//alert('Cannot update current page!');
			}
		}
		addURL(uniqueArrary(tempURLs));
	});
}

function addURL(url){
	if(chrome.extension.getBackgroundPage().updateWindowId == 0){
		chrome.windows.create({state: 'minimized'}, function(windows){
			chrome.extension.getBackgroundPage().updateWindowId = windows.id;
			});
	}
	
	var suiteName = '';
	var obj = {};
	if(typeof url === 'object'){
		for(var i = 0; i < url.length; i++){
			suiteName = getSuiteName(url[i]);
			if(obj[suiteName] == undefined){
				obj[suiteName] = [];
			}
			for(var j = 0; j < testunitUpdateTimes; j++){
				obj[suiteName].push(url[i]);
			}
		}
	}
	else{
		suiteName = getSuiteName(url);
		obj[suiteName] = [];
		for(var j = 0; j < testunitUpdateTimes; j++){
			obj[suiteName].push(url);
		}
	}
	chrome.storage.local.set(obj);
}

function test(){
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
		chrome.tabs.executeScript(tabs[0].id, {file: 'scrolldown.js', allFrames: true, runAt: "document_start"}, function(){});
	});
}

window.onload = function(){
	document.getElementById('upgrade_page').onclick = updatePage;
	document.getElementById('upgrade_all_pages').onclick = updateAllPages;
	document.getElementById('test').onclick = test;
}