window.onload = function(){
	jquery_script = document.createElement('script');
	jquery_script.src = 'https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.9.1.min.js';
	my_head = document.querySelector('head');
	my_head.appendChild(jquery_script);
}