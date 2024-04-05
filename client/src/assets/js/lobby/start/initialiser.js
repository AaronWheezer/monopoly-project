"use strict";

document.addEventListener("DOMContentLoaded", initStart);

function initStart() {
	if (loadFromGlobal("username")) {
		document.querySelector("#username").value = getName(
			loadFromGlobal("username")
		);
	}

	const form = document.querySelector("form");
	form.addEventListener("submit", submitUsername);
}
