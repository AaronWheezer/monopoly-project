"use strict";

function submitUsername(e) {
	e.preventDefault();

	const $usernameInput = document.querySelector("#username");
	const usernameValue = $usernameInput.value;

	saveToStorage("username", "00"+usernameValue);
	window.location.href = "start.html";
}


