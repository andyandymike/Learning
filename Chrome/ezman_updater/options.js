function checkStorage(){
	chrome.storage.local.get(null, function (result) {
		for(var resultKey in result){
			alert(resultKey + ' : ' + result[resultKey]);
		}
		
	});
}

function clearStorage(){
	chrome.storage.local.clear();
}


window.onload = function() {
	document.getElementById('check_url_array').onclick = checkStorage;
	document.getElementById('clear_url_array').onclick = clearStorage;
}