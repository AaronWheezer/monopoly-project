function showPlayerAction() {
	const game = loadFromGlobal("game");

	if (game.directSale) {
		tileDetail(nameToParameter(game.directSale)).then((tileDetail) => {
			showDirectSale(tileDetail);
		});
	}
}

function buyPropertyAction() {
	removePopups();
    const game = loadFromGlobal("game");
    tileDetail(nameToParameter(game.directSale)).then((tileDetail) => {
        buyProperty(tileDetail.nameAsPathParameter);
    });
}

function dontBuyPropertyAction() {
	removePopups();
	const game = loadFromGlobal("game");
    tileDetail(nameToParameter(game.directSale)).then((tileDetail) => {
		dontBuyProperty(tileDetail.nameAsPathParameter).then((auction) => {
			showAuction(auction);
		});
	});
}
