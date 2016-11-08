function checkStorage(){
	chrome.storage.local.get({updateURLs: []}, function (result) {
		alert(result.updateURLs);
	});
}

function clearStorage(){
	chrome.storage.local.clear();
}


window.onload = function() {
	document.getElementById('check_url_array').onclick = checkStorage;
	document.getElementById('clear_url_array').onclick = clearStorage;
}