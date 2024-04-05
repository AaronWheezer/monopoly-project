function getGames(playerCount = null) {
	let params = `?prefix=${_config.groupPrefix}&started=false`;
	if (playerCount) {
		params += `&numberOfPlayers=${playerCount}`;
	}
	return fetchFromServer(`/games${params}`, "GET");
}

function createGameAndJoinGame(playerCount, playerAndPawnName) {
	const gameDetails = {
		prefix: _config.groupPrefix,
		numberOfPlayers: playerCount,
	};

	return fetchFromServer("/games", "POST", gameDetails).then((response) => {
		const gameId = response.id;
		saveToStorage("game", gameId);
		saveToGlobal("gameId",gameId);
		return joinGame(playerAndPawnName);
	});
}

function joinGame(playerAndPawnName) {
	const gameDetails = {
		playerName: playerAndPawnName,
	};
	return fetchFromServer(`/games/${loadFromGlobal("gameId")}/players`, "POST", gameDetails).then(
		(response) => {
			saveToGlobal("token",response.token);
			saveToStorage("token", loadFromGlobal("token"));
		}
	);
}

//turn management game
function rollDice() {
	const player= loadFromGlobal("token");
	const player_username = player.split("-");
	return fetchFromServer(`/games/${loadFromGlobal("gameId")}/players/${player_username[1]}/dice`, "POST");
}
function bankruptcy() {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}}/players/${loadFromGlobal("username")}/bankrupt`,
		"POST"
	);
}

//tax management game
function setTax() {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}}/players/${loadFromGlobal("username")}/tax/estimate`,
		"POST"
	);
}
function computeTax() {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}/players/${loadFromGlobal("username")}/tax/compute`,
		"POST"
	);
}

//buying property game
function buyProperty(PropertyName) {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}/players/${loadFromGlobal("username")}/properties/${PropertyName}`,
		"POST"
	);
}

function dontBuyProperty(PropertyName) {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}/players/${loadFromGlobal("username")}/properties/${PropertyName}`,
		"DELETE"
	);
}

//improving property game
function buildHouse(PropertyName) {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}/players/${loadFromGlobal("username")}/properties/${PropertyName}/houses`,
		"POST"
	);
}
function sellHouse(PropertyName) {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}/players/${loadFromGlobal("username")}/properties/${PropertyName}/houses`,
		"DELETE"
	);
}
function buildHotel(PropertyName) {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}/players/${loadFromGlobal("username")}/properties/${PropertyName}/hotel`,
		"POST"
	);
}
function sellHotel(PropertyName) {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}/players/${loadFromGlobal("username")}/properties/${PropertyName}/hotel`,
		"DELETE"
	);
}

//mortgage
function takeMortgage(PropertyName) {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}}/players/${loadFromGlobal("username")}/properties/${PropertyName}/mortgage`,
		"POST"
	);
}
function settleMortgage(PropertyName) {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}}/players/${loadFromGlobal("username")}/properties/${PropertyName}/mortgage`,
		"DELETE"
	);
}

//interaction with a player
function collectingDebt(PropertyName) {
	const gameDetails = {
		creditor: loadFromGlobal("username"),
		propertyName: PropertyName,
	};
	return fetchfromServer(
		`/games/${loadFromGlobal("gameId")}/players/${loadFromGlobal("username")}/debt`,
		"POST",
		gameDetails
	);
}

//prison
function prisonFine() {
	return fetchFromServer(`/games/${loadFromGlobal("gameId")}}/prison/${loadFromGlobal("username")}/fine`, "POST");
}
function prisonFreeCard() {
	return fetchFromServer(`/games/${loadFromGlobal("gameId")}}/prison/${loadFromGlobal("username")}/free`, "POST");
}

//auction
function auctionListBank() {
	return fetchFromServer(`/games/${loadFromGlobal("gameId")}/bank/auctions`, "GET");
}
function auctionBid(PropertyName) {
	const gameDetails = {
		bidder: loadFromGlobal("username"),
		amount: 0,
	};
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}/bank/auctions/${PropertyName}/bid`,
		"POST",
		gameDetails
	);
}
function auctionListPlayer() {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}/players/${loadFromGlobal("username")}/auctions`,
		"GET"
	);
}
function startAuction(PropertyName) {
	const gameDetails = {
		"start-bid": 50,
		"duration": 30,
	};
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}/players/${loadFromGlobal("username")}/auctions/${PropertyName}`,
		"POST",
		gameDetails
	);
}
function auctionBidPlayer(PropertyName) {
	const gameDetails = {
		bidder: loadFromGlobal("username"),
		amount: 0,
	};
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}/players/${loadFromGlobal("username")}/auctions/${PropertyName}/bid`,
		"POST",
		gameDetails
	);
}
function suggestTrade() {
	return fetchFromServer(
		`/games/${loadFromGlobal("gameId")}/players/${loadFromGlobal("username")}/trades`,
		"POST",
		gameDetails
	);
}

//general api info
function getTiles() {
	return fetchFromServer("/tiles", "GET");
}

function tileNames() {
	return fetchFromServer(`/tiles`, "GET");
}
function tileDetail(tileID) {
	return fetchFromServer(`/tiles/${tileID}`, "GET");
}
function chanceCards() {
	return fetchFromServer(`/chance`, "GET");
}
function communityChestCards() {
	return fetchFromServer(`/community-chest`, "GET");
}
// information about the game
function gameState() {
	return fetchFromServer(`/games/${loadFromGlobal("gameId")}`, "GET");
}
function dummyState() {
	return fetchFromServer(`/games/dummy`, "GET");
}


