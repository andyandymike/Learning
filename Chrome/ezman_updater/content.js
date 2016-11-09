chrome.runtime.onMessage.addListener(function (msg, sender, sendResponse) {
	if (msg.text === 'loaded?') {		
		var checkExist = window.setInterval(function(){waitForElement(sendResponse);}, 250);
		}
});

function waitForElement(sendResponse){
	targetElement = document.querySelector('body > form > table > tbody > tr:nth-child(2) > td > table:nth-child(9) > tbody > tr:nth-child(6) > td:nth-child(1) > input[type="submit"]');
	if (targetElement != undefined) {
		window.clearInterval(checkExist);
		window.sendResponse({text: 'loaded!'});
		}
}


