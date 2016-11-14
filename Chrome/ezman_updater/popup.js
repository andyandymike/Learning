function getVersionNum(){
	var versionNum = document.getElementById('version_num').value;
	return versionNum.replace(/\s/g,'');
}

function getCheckedValue(){
	var checkedValue = $("input[name='cases_status']:checked").val();
	return checkedValue;
}

function uniqueArrary(array){
	var sortedArr = array.sort(); 
	var results = [];
	for (var i = 0; i < sortedArr.length; i++) {
		if (sortedArr[i + 1] == sortedArr[i]) {
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
		if(tempURL.match('statusUpdate') != 'statusUpdate' && tempURL.match('TestCaseStatus') == 'TestCaseStatus'){
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
			//alert('Cannot update current page!');
		}
	});
}

function updateAllPages(){
	chrome.tabs.query({},function(tabs){
		var tempURLs = []; 
		for(var i = 0; i < tabs.length; i++){
			var tempURL = tabs[i].url;
			if(tempURL.match('statusUpdate') != 'statusUpdate' && tempURL.match('TestCaseStatus') == 'TestCaseStatus'){
				if(getCheckedValue() != 'null'){
					if(getVersionNum() == ''){
						tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=null');
						tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus='+getCheckedValue());
						tempURLs.push(tempURL);	
					}
					else{
						tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=null');
						tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus='+getCheckedValue());
						tempURLs.push(tempURL);
						tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=D.'+getVersionNum());
						tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus=null');
						tempURLs.push(tempURL);				
					}
				}
				else{
					if(getVersionNum() != ''){
						tempURL = tempURL.replace(/sbuild=\w*[\.]*\w*/,'sbuild=D.'+getVersionNum());
						tempURL = tempURL.replace(/sstatus=\w*[\.]*\w*/,'sstatus=null');
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
	console.log('test');
}

window.onload = function() {
	document.getElementById('upgrade_page').onclick = updatePage;
	document.getElementById('test').onclick = updateAllPages;
}