var intervalFunc = null;
var complete = false;
var targetElementSelector = 'body > form > table > tbody > tr:nth-child(2) > td > table:nth-child(9) > tbody > tr:nth-child(6) > td:nth-child(1) > input[type="submit"]'
var statusSelector = 'body > form > table > tbody > tr:nth-child(2) > td > table:nth-child(5) > tbody:nth-child(2) > tr:nth-last-child(2) > td:nth-child(4) > select > option:nth-child(1)'
var buildNumSelector = 'body > form > table > tbody > tr:nth-child(2) > td > table:nth-child(5) > tbody:nth-child(2) > tr:nth-last-child(2) > td:nth-child(5) > select > option:nth-child(1)'
var intervalCount = 0;
var timeOutCount = 200;
var intervalTime = 600;

chrome.runtime.onMessage.addListener(function (msg, sender, sendResponse){
	if(msg.post == 'complete'){
		complete = true;
	}
	if(msg.request === 'loaded?'){
		var intervalFunc = setInterval(function(){waitForElement(sendResponse, msg);}, intervalTime);
	}
	return true;
});

function waitForElement(sendResponse, msg){
	intervalCount++;
	var statusCorrectlyLoaded = false;
	var buildNumCorrectlyLpoaded = false;
	var targetElement = document.querySelector(targetElementSelector);
	if(intervalCount > timeOutCount){
		sendResponse({status: 'timeout'});
	}
	if(msg.updateStatus == 'null' || document.querySelector(statusSelector).innerHTML == msg.updateStatus){
		statusCorrectlyLoaded = true;
	}
	if(msg.updateBuildNum == 'null' || document.querySelector(buildNumSelector).innerHTML == msg.updateBuildNum){
		buildNumCorrectlyLpoaded = true;
	}
	if(!statusCorrectlyLoaded || !buildNumCorrectlyLpoaded){
		sendResponse({status: 'refresh'});
	}
	if(targetElement != undefined && complete && statusCorrectlyLoaded && buildNumCorrectlyLpoaded){
		clearInterval(intervalFunc);
		sendResponse({status: 'loaded'});
	}
}