<<<<<<< HEAD
/*
Document: app-custom.js
Author: Rustheme
Description: Write your custom code here
*/

// Below is an example of function and its initialization
var AppCustom = function() {
	var showAppName = function() {
		console.log( 'AppUI - Admin & Frontend template' );
	};

	return {
		init: function() {
			showAppName();
		}
	}
}();

// Initialize AppCustom when page loads
jQuery( function() {
	AppCustom.init();
});
=======
/*
Document: app-custom.js
Author: Rustheme
Description: Write your custom code here
*/

// Below is an example of function and its initialization
var AppCustom = function() {
	var showAppName = function() {
		console.log( 'AppUI - Admin & Frontend template' );
	};

	return {
		init: function() {
			showAppName();
		}
	}
}();

// Initialize AppCustom when page loads
jQuery( function() {
	AppCustom.init();
});
>>>>>>> ec03a69a5a0c8666fe3de9825155e6169114b796
