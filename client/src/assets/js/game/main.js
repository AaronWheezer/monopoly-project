function updateGame() {
  const player = loadFromGlobal("token");
  let playerName = player.split("-")[1];
  gameState().then((game) => {
    console.log(game);
    saveToGlobal("game", game);
    const myTurn = game.currentPlayer == playerName;
    if (myTurn) {
      if (game.canRoll) {
        showDiceRollButton();
      } else {
        removeDiceRollButton();
        showPlayerAction();
      }
    } else {
      removeDiceRollButton();
      showCurrentTurn(game);
      if (isAuctionActive()) {
        showAuction();
      }
    }
    showYourProperties();
    const lastDiceRoll = game.lastDiceRoll;
    console.log(lastDiceRoll);
    updateDiceImages(lastDiceRoll);
  });
}

function validateGame() {
  if (!loadFromGlobal("gameId")) {
    window.location.href = "./index.html";
  }
}
