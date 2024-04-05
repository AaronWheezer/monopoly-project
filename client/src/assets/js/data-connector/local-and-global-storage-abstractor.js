"use strict";

const global = {};

function saveToStorage(key, value) {
	if (localStorage) {
		return localStorage.setItem(key, JSON.stringify(value));
	}
}

function loadFromStorage(key) {
	if (localStorage) {
		return JSON.parse(localStorage.getItem(key));
	}
}

function saveToGlobal(key, value) {
	if (localStorage) {
		global[key] = value;
		return global[key];
	}
}

function loadFromGlobal(key) {
	return global[key];
}
