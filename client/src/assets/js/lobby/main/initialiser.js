"use strict";

document.addEventListener("DOMContentLoaded", initStart);

function initStart() {
	updatePlayerList();
	setInterval(updatePlayerList, _config.delay);
	setgamepin();
	updateGameInfo();
	setInterval(updateGameInfo, _config.delay);
}

function updatePlayerList() {
	gameState().then((response) => {
		const players = response.players;

		const playerList = document.querySelector("ul");
		playerList.innerHTML = "";
		players.forEach((player) => {
			const name = getName(player.name);
			const icon = getIcon(player.name);
			const playerElement = document.createElement("li");
			console.log(icon);
			playerElement.style.backgroundImage = `url("./assets/media/images/pawns/${icon}.jpg")`;
			console.log(name);
			playerElement.innerHTML = name;
			playerList.appendChild(playerElement);
		});
	});
}

function setgamepin() {
	// add the game from localstorage to the html document
	const gamepin = document.querySelector("p");
	gamepin.innerHTML = "";
	gamepin.innerHTML = `game pin: ${loadFromGlobal("gameId")}`;
}

function updateGameInfo() {
	gameState().then((game) => {
		const isGameFull = game.players.length === game.numberOfPlayers;
		if (isGameFull) {
			// set time out for the window redirection
			setTimeout(() => {
				window.location.href = "./board.html";
			}, _config.delay);
			return;
		}
		const gameInfo = document.querySelector("footer div p + p ");
		gameInfo.innerHTML = "";
		console.log(game);
		gameInfo.innerHTML = `${game.players.length}/${game.numberOfPlayers} players`;
	});
}
