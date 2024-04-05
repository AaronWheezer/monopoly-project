"use strict";

document.addEventListener("DOMContentLoaded", initstart);

function initstart() {
	updateGameList();
}

function updateGameList() {
	getGames().then((games) => {
		const $gamelist = document.querySelector("main ul");
		$gamelist.innerHTML = "";

		//sorts games on players joined (DESC)
		games = games.sort((a, b) => b.players.length - a.players.length);

		for (const game of games) {
			const $joinButton = document.createElement("button");
			$joinButton.id = game.id;
			$joinButton.addEventListener("click", joinSelectPawn);
			$joinButton.innerHTML = "Join game";
			$joinButton.href = "lobby.html";

			const $game = document.createElement("li");
			$game.innerHTML = `<div><p>${game.id}</p><p>${game.players.length}/${game.numberOfPlayers} players</p></div>`;
			$game.appendChild($joinButton);
			$gamelist.appendChild($game);
		}
	});
}

function joinSelectPawn(e) {
	e.preventDefault();
	const gameId = e.currentTarget.id;
	saveToStorage("game", gameId);

	window.location.href = "select-pawn.html";
}
