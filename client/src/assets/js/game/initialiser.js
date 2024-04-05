"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {
    validateGame();
    updateStreetTiles();
    updateGame();
    setInterval(updateGame, _config.delay);
    scoreboard();
}
