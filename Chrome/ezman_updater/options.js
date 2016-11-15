function checkStorage(){
	chrome.storage.local.get(null, function (result) {
		for(var resultKey in result){
			createLine('display_area1', resultKey, result[resultKey]);
			//alert(resultKey + ' : ' + result[resultKey]);
		}
		
	});
}

function clearStorage(){
	chrome.storage.local.clear();
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


window.onload = function() {
	document.getElementById('check_url_array').onclick = checkStorage;
	document.getElementById('clear_url_array').onclick = clearStorage;
}