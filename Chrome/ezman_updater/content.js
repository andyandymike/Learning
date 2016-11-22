var intervalFunc = null;
var targetElementSelector = 'body > form > table > tbody > tr:nth-child(2) > td > table:nth-child(9) > tbody > tr:nth-child(6) > td:nth-child(1) > input[type="submit"]'
var intervalCount = 0;
var timeOutCount = 500;
var intervalTime = 600;

chrome.runtime.onMessage.addListener(function (msg, sender, sendResponse) {
	if (msg.request === 'loaded?') {
		var intervalFunc = setInterval(function(){waitForElement(sendResponse);}, intervalTime);
		}
	return true;
});

function waitForElement(sendResponse){
	intervalCount++;
	if(intervalCount > timeOutCount){
		sendResponse({status: 'timeout'});
	}
	var targetElement = document.querySelector(targetElementSelector);
	if (targetElement != undefined) {
		clearInterval(intervalFunc);
		sendResponse({status: 'loaded'});
		}
}