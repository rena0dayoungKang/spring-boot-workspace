$(function() {
	console.log("bb")
	var loginBtn = $('#loginBtn');

	loginBtn.click(function() {		
		location.href = '/';
	});
	
	var retryBtn = $('#retryBtn');

	retryBtn.click(function() {		
		history.back();
	});
});