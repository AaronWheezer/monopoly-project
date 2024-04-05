"use strict";
function showDiceRollButton() {
  const dice_button = document.querySelector("#overview div p");
  dice_button.innerHTML = `<button type="button" id="roll_dice" onclick="rollDiceButton">Roll Dice</button>`;
  rollDiceButton();
}

function removeDiceRollButton() {
  const button = document.querySelector("#roll_dice");
  if(button){
    button.remove();
  }
}

function showCurrentTurn(game){
  const $textContainer = document.querySelector("#overview > div p");
  $textContainer.innerHTML = `currently ${game.currentPlayer.slice(2)} 's turn please wait`;
}

function rollDiceButton() {
  const button = document.querySelector("#roll_dice");
  button.addEventListener("click", () => {
    removeDiceRollButton();
    saveToGlobal("popup", true);
    rollDice().then((game) => {
      updateDiceImages(game.lastDiceRoll);
    });
  });
}

function updateDiceImages(dice) {
  const $diceContainer = document.querySelector("#overview > div div");
  $diceContainer.innerHTML = "";
  console.log(dice);
  for (const diceRoll of dice) {
    console.log(diceRoll);
    $diceContainer.innerHTML += `<div id="rolledDices"><img src="./assets/media/images/dice/${diceRoll}.png" alt="dice"></div>`;
  }
}

