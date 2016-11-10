var intervalFunc = null;
var targetElementSelector = 'body > form > table > tbody > tr:nth-child(2) > td > table:nth-child(5) > tbody:nth-child(2) > tr:nth-child(2) > td:nth-child(1)'
var intervalCount = 0;
var timeOutCount = 100;
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
	targetElement = document.querySelector(targetElementSelector);
	if (targetElement != undefined) {
		clearInterval(intervalFunc);
		sendResponse({status: 'loaded'});
		}
}