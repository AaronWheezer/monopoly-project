"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {
	const token = loadFromStorage("token");
	if(token) {
		saveToStorage("username", token.split("-")[1]);
	}

	saveToGlobal("gameId", loadFromStorage("game"));
	saveToGlobal("username", loadFromStorage("username"));
	saveToGlobal("token", loadFromStorage("token"));
	testConnection();
}

function testConnection() {
	getTiles();
}
