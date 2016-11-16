var intervalFunc = null;
var targetElementSelector = 'body > form > table > tbody > tr:nth-child(2) > td > table:nth-child(9) > tbody > tr:nth-child(6) > td:nth-child(1) > input[type="submit"]'
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
	var targetElement = document.querySelector(targetElementSelector);
	if (targetElement != undefined) {
		clearInterval(intervalFunc);
		sendResponse({status: 'loaded'});
		}
}

// This code will be injected to run in webpage context
function codeToInject() {
    window.addEventListener('error', function(e) {
        var error = {
            stack: e.error.stack
        };
        document.dispatchEvent(new CustomEvent('ReportError', {detail: error}));
    });
	
	window.onerror = function (errorMsg, url, lineNumber, column, errorObj) {
		console.log('Caught content script error');
		console.log('errorMsg: ' + errorMsg);
		console.log('url: ' + url);
		console.log('lineNumber: ' + column);
		console.log('column: ' + column);
		console.log('errorObj follows:');
		console.log(errorObj);
		return true;
	};
}

document.addEventListener('ReportError', function(e) {
    console.log('CONTENT SCRIPT', e.detail.stack);
});

//Inject code
var script = document.createElement('script');
script.textContent = '(' + codeToInject + '())';
(document.head||document.documentElement).appendChild(script);
//script.parentNode.removeChild(script);