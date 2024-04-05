function showDirectSale(tile) {
    if (!tile) {
        return;
    }
    console.log("tile", tile);
    const buyButton = new Button("green", "Buy", buyPropertyAction);
    const dontBuyProperty = new Button("blue", "Auction", dontBuyPropertyAction);
    new Popup(
        "Direct Sale",
        `Would you like to buy this house for $${tile.cost} or auction it?`, [buyButton, dontBuyProperty],
        false
    );
}

function showProperties(e) {
    const $button = e.target;
    const $section = $button.closest("section");
    const player = $section.dataset.playername;
    saveToGlobal("popup", true);
    const properties = getPropertiesPlayer(player);
    new Popup(
        "Properties",
        `These are your properties: ${properties}`, [], true
    );
}

function getPropertiesPlayer(player) {
    const playerName = this.parentNode.parentNode.getAttribute("data-playername");
    getTiles().then((tiles) => {
        const playerProperties = [];
        for (const tile of tiles) {
            if (tile.owner === playerName) {
                playerProperties.push(tile.name);
            }
        }
        alert(`${playerName} has the following properties: ${playerProperties}`);
    });
}

function getPropertyHTML(property) {
    return `<p>${property}</p>`;
}