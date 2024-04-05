function getParameters() {
    const parameterString = window.location.search;
    console.log(parameterString);
    return new URLSearchParams(parameterString);
}

function insertPawns() {
    let type_drink = "f";
    for (let i = 0; i < 2; i++) {
        for (const pawn of _config.pawns) {
            const $pawn = document.createElement("li");
            $pawn.id = `${pawn}${type_drink}`;
            $pawn.style.backgroundImage = `url("./assets/media/images/pawns/${pawn}${type_drink}.jpg")`;
            $pawn.addEventListener("click", selectPawn);
            document.querySelector("ul").appendChild($pawn);
        }
        type_drink = "g";
    }
}

function updatePawns() {
    const params = getParameters();
    const playerRange = params.get("playerRange");
    const joinGame = params.get("join");
    hasAvailableGame().then((hasGame) => {
        if (!playerRange || (joinGame === "on" && hasGame)) {
            getGames().then((games) => {
                updatePawnList(games);
            });
        }
    });
}

function updatePawnList(games) {
    const game = getGame(games);
    if (!game) {
        return;
    }
    const pawns = [];

    for (const player of game.players) {
        const pawn = getIcon(player.name);
        const playerName = getName(player.name);

        pawns[pawn] = playerName;
    }
    const $pawns = document.querySelectorAll("li");
    for (const $pawn of $pawns) {
        updatePawn($pawn, pawns);
    }
}

function getGame(games) {
    return games.find(
        (gameElement) => gameElement.id === loadFromGlobal("gameId")
    );
}

function updatePawn($pawn, pawns) {
    const pawnName = $pawn.id.toLowerCase();
    const alreadyChosenClass = "already-picked";
    if (pawns[pawnName]) {
        $pawn.classList.add(alreadyChosenClass);
    } else {
        $pawn.classList.remove(alreadyChosenClass);
    }
}

function selectPawn(e) {
    e.preventDefault();
    const $pawn = e.currentTarget;

    if ($pawn.classList.contains("already-picked")) {
        return;
    }

    const $selectedPawn = document.querySelector(".selected");
    if ($selectedPawn) {
        $selectedPawn.classList.remove("selected");
    }
    $pawn.classList.add("selected");
}

function submitChoice(e) {
    e.preventDefault();
    const $pawn = document.querySelector(".selected");
    if ($pawn) {
        const pawnName = $pawn.id;

        const params = getParameters();
        const playerRange = params.get("playerRange");
        const joinExistingGame = params.get("join");

        const playerCount = stringToInt(playerRange);
        console.log(loadFromGlobal("username"));
        const playerName = getName(loadFromGlobal("username"));

        joinOrCreateGame(pawnName, params, playerRange, joinExistingGame, playerCount, playerName);
    }
}

function joinOrCreateGame(pawnName, params, playerRange, joinExistingGame, playerCount, playerName) {
    const playerAndPawnName = `${pawnName}${playerName}`;
    hasAvailableGame().then((hasGame) => {
        const existingGameAvailable = !playerRange || (joinExistingGame === "on" && hasGame);
        if (existingGameAvailable) {
            joinGame(playerAndPawnName).then((response) => {
                window.location.href = "lobby.html";
            });
        } else {
            createGameAndJoinGame(playerCount, playerAndPawnName).then(
                (response) => {
                    window.location.href = "lobby.html";
                }
            );
        }
    });
}

function hasAvailableGame(playerCount) {
    return getGames(playerCount).then((response) => {
        const params = getParameters();
        const playerRange = params.get("playerRange");
        if (response.length > 0 && playerRange) {
            const games = response.sort(
                (a, b) => b.players.length - a.players.length
            );
            saveToGlobal("gameId", games[0].id);
            saveToStorage("game", games[0].id);
            return true;
        } else {
            return false;
        }
    });
}
