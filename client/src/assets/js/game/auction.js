function isAuctionActive() {
    auctionListBank().then((auctionResponse) => {
        const auctions = auctionResponse.auctions;
        console.log(Object.keys(auctions).length);
        console.log(auctions);
        return Object.keys(auctions).length !== 0;
    });
}

function showAuction(auction) {
    const auctionButton = new Button("blue", "Bid", bidAuctionAction);
    new Popup(
        "Auction",
        `Would you like to bid for this house starting price: 0$ property:  ${auction.property} you have 30 seconds `, [auctionButton],
        false
    );
}

//TODO: bid auctions
function bidAuctionAction() {
    const game = loadFromGlobal("game");
    tileDetail(nameToParameter(game)).then((tileDetail) => {
        auctionBid(tileDetail.nameAsPathParameter);
    });
}