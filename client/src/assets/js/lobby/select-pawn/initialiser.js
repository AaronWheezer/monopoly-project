"use strict";

document.addEventListener("DOMContentLoaded", initstart);

function initstart() {

	insertPawns();
	updatePawns();

	const $submitButton = document.querySelector("main a");
	$submitButton.addEventListener("click", submitChoice);
	setInterval(() => {
		updatePawns();
	}, _config.delay);
}
