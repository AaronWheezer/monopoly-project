package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class TileCard extends Card {
    private Tile tile;
    public TileCard(String text, Tile tile) {
        super(text);
        this.tile = tile;
    }

    @Override
    public void execute(Player player){
        player.setCurrentTile(this.tile);
    }
}
