"use strict";

document.addEventListener("DOMContentLoaded", initstart);

function initstart() {
	const $playerCountInput = document.querySelector("#playerRange");
	$playerCountInput.addEventListener("input", updateLabel);
}

function updateLabel(e) {
	const $playerCountInput = e.target;
	const playerCount = $playerCountInput.value;

	const $playerLabel = document.querySelector("label[for='playerRange']");

	$playerLabel.innerHTML = `${playerCount} players`;
}
