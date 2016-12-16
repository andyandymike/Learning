function checkStorage(){
	clearDisplayArea('function_area1', 'display_area1', 'button_area');
	chrome.storage.local.get(null, function(result){
		for(var resultKey in result){
			for(var i = 0; i < result[resultKey].length; i++){
				createLine('display_area1', resultKey, result[resultKey][i]);
			}
		}
		
	});
}

function clearStorage(){
	chrome.storage.local.clear();
	chrome.extension.getBackgroundPage().openedTabsNum = 0;
	chrome.extension.getBackgroundPage().updateWindowId = 0;
	chrome.extension.getBackgroundPage().lastChangeKey = '';
	chrome.extension.getBackgroundPage().updatePageTabIds = [];
	chrome.extension.getBackgroundPage().oriPageInfo = [];
	chrome.extension.getBackgroundPage().tabsWaitingList = [];
	chrome.extension.getBackgroundPage().tabsWaitingListKeyList = [];
	chrome.extension.getBackgroundPage().tempURLs = [];
	checkStorage();
}

function createLine(tableId, key, content){
	var tableNode = document.getElementById(tableId);
	var tr = document.createElement('tr');
	var tdKey = document.createElement('td');
	var tdContent = document.createElement('td');
	var innerTextKey = document.createTextNode(key);
	var innerTextContent = document.createTextNode(content);
	
	tdKey.appendChild(innerTextKey);
	tdContent.appendChild(innerTextContent);
	tr.appendChild(tdKey);
	tr.appendChild(tdContent);
	tableNode.appendChild(tr);
}

function clearDisplayArea(functionAreaId, tableId, buttonAreaId){
	var formNode = document.getElementById(functionAreaId);
	var tableNode = document.getElementById(tableId);
	formNode.removeChild(tableNode);
	var newTableNode = document.createElement('table');
	newTableNode.id = tableId;
	var buttonAreaNode = document.getElementById(buttonAreaId);
	formNode.insertBefore(newTableNode, buttonAreaNode);
}


window.onload = function(){
	document.getElementById('check_url_array').onclick = checkStorage;
	document.getElementById('reset_all_parameter').onclick = clearStorage;
}