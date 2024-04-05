package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.utils.Utils;

public class MoveBackCard extends Card{
    private int amount;
    public MoveBackCard(String text,int amount){
        super(text);
        this.amount = amount;
    }

    @Override
    public void execute(Player player){
        Tile newTile = Utils.getPreviousTile(player.getCurrentTile(),amount);
        player.setCurrentTile(newTile);
    }
}

