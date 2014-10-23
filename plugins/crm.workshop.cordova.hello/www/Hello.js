var exec = require("cordova/exec"),
	Hello;

Hello = {
	sayHello: function (successCallback, errorCallback, options) {
		"use strict";

		var win, fail;
		win = function (hello) {
			successCallback(hello);
		};
		fail = function (error) {
			if (errorCallback) {
				errorCallback(error);
			}
		};
		exec(win, fail, "Hello", "sayHello", [options.name]);
	}
};

module.exports = Hello;
