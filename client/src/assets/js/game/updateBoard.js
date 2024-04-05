function updateStreetTiles() {
  getTiles().then((tiles) => {
    const $tilesContainer = document.querySelector("#board_display");
    console.log(tiles);
    $tilesContainer.innerHTML = "";
    for (const tile of tiles) {
      // insert the other background images and the tile names.
      insertExceptions(tile);
    }
    insertStreetsInformation();
  });
}

function insertExceptions(tile) {
  const $tilesContainer = document.querySelector("#board_display");
  const $tile = document.createElement("section");
  const tileClass = nameToParameter(tile.type);
  $tile.classList.add(tileClass);
  $tile.innerHTML = `<div><h3>${tile.name}</h3> </div>`;

  if (tileClass !== "street") {
    // if the street tile class was not found insert the background images of the other tiles like go / chance etc..
    insertTileImagesForNonStreetTypes($tile, tile);
  }
  $tilesContainer.appendChild($tile);
  if ($tile.classList.contains("street")) {
    //insert the street names in the tile
    insertStreetName($tile, tile);
  }
  if (tileClass == "utility") {
    //if its a utility first insert the electric company and then water works.
    insertUtilityException($tile, tile);
  }
}

function insertTileImagesForNonStreetTypes($tile, tile) {
  const tileClass = nameToParameter(tile.type);
  $tile.style.backgroundImage = `url("./assets/media/images/board_icons/${tileClass}.png")`;
}

function insertStreetName($tile, tile) {
  $tile.innerHTML += `<div id="${tile.color}"></div> <div class="name"> <h3>${tile.name}</h3> </div>`;
  const $tiletitle = document.querySelectorAll(".street div:first-child");
  $tiletitle.forEach((title) => {
    title.innerHTML = "";
  });
}
function insertUtilityException($tile, tile) {
  if (tile.name === "Electric Company") {
    $tile.style.backgroundImage = `url("./assets/media/images/board_icons/electric_company.png")`;
  } else {
    $tile.style.backgroundImage = `url("./assets/media/images/board_icons/water_works.png")`;
  }
}

function insertStreetsInformation() {
  getTiles().then((tiles) => {
    let arrayStreetTiles = [];
    const streets = document.querySelectorAll("#board_display .street .name");
    for (const tile of tiles) {
      if (tile.type == "street") {
        arrayStreetTiles.push(tile);
      }
    }
    for (let streetnr = 0; streetnr < arrayStreetTiles.length; streetnr++) {
      insertStreetValues(streetnr, streets, arrayStreetTiles);
    }
  });
}

function insertStreetValues(streetnr, streets, arrayStreetTiles) {
  streets[streetnr].innerHTML = ` <h3>${arrayStreetTiles[streetnr].name}
        </h3> <ul><li> cost: ${arrayStreetTiles[streetnr].cost}$ </li> 
        <li>group size: ${arrayStreetTiles[streetnr].groupSize} </li>
        <li>house price: ${arrayStreetTiles[streetnr].housePrice}$ </li>
        <li>mortage: ${arrayStreetTiles[streetnr].mortgage}$ </li>
        <div class="myDIV"> <div class="hide">
        <h2> Rent: </h2><li>rent cost: ${arrayStreetTiles[streetnr].rent}$ </li> 
        <li>1 house: ${arrayStreetTiles[streetnr].rentWithOneHouse}$ </li>
        <li>2 houses: ${arrayStreetTiles[streetnr].rentWithTwoHouses}$ </li> 
        <li>3 houses: ${arrayStreetTiles[streetnr].rentWithThreeHouses}$ </li> 
        <li>4 houses: ${arrayStreetTiles[streetnr].rentWithFourHouses}$ </li>
        <li>hotel: ${arrayStreetTiles[streetnr].rentWithHotel}$ </li></div></div></ul>`;
}

function scoreboard() {
  const aside = document.querySelector("aside");
  aside.innerHTML = "";
  gameState().then((game) => {
    //sort the players on money calculation
    game.players.sort((a, b) => b.money - a.money);
    let i = 0;

    for (const player of game.players) {
      i += 1;
      aside.innerHTML += `
      <section data-playername=${player.name}>
        <h2 >${player.name.slice(2)} <span> #${i}</span> </h2>
          <p> cash: ${player.money}</p>
          <p> Properties amount: ${player.properties.length}</p>
          <p> Value properties: ${player.properties}  </p>
          <div> 
            <button type="button"> View Properties </button>
            <button type="button"> View Player Position</button>
          </div> 
        </section>`;
    }

    const buttonSelector = document.querySelectorAll(
      "aside section div > button:first-child"
    );
    for (const playerButton of buttonSelector) {
      playerButton.addEventListener("click", showProperties);
    }
  });
}


function showYourProperties(){
  console.log("tst")
  const playerName = loadFromGlobal("username");
  const yourPropertiesList = document.querySelector("#properties");
  gameState().then((game) => {
    const player = game.players.find((player) => player.name === playerName);
    yourPropertiesList.innerHTML = "<h1> Your Properties </h1>";
    for (const property of player.properties) {
      console.log()
      yourPropertiesList.innerHTML += ` <section> <h2>${Object.values(property)[0]} </h2>
      <p>mortage: ${Object.values(property)[1]}  </p>
      <p> housecount: ${Object.values(property)[2]} </p>
      <p> hotelcount: ${Object.values(property)[3]} </p> </section>`;
    }
  });
}