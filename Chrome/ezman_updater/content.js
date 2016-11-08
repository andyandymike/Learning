chrome.runtime.onMessage.addListener(function (msg, sender, sendResponse) {
	if (msg.text === 'body_loaded?') {
		var checkExist = setInterval(function() {
			if (waitForElement(document.body)) {
				clearInterval(checkExist);
				alert('here');
				sendResponse({text: 'body_loaded!'});
				}
			}, 100);
		}
});

function waitForElement(targetElement){
	if(targetElement != undefined){
		return true;
	}
}
