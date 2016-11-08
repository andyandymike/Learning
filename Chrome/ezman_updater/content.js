function test(){
	chrome.tabs.sendMessage({text: 'body_loaded'}, function (targetElement){});
}

function waitForElement(targetElement){
	if(targetElement != undefined){
		return true;
	}
}

var checkExist = setInterval(function() {
   if (waitForElement(document.body)) {
	   clearInterval(checkExist);
	   test();
   }
}, 100);
