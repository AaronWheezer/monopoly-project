package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.Config;

public class Tax extends Tile {
    private int taxAmount;

    public Tax(String name,int position, int taxAmount) {
        super(name, position, "Tax Income");
        this.taxAmount = taxAmount;
    }

    private void payTax(Player player) {
        if(player.getTaxSystem().equals("ESTIMATE")) {
            player.pay(taxAmount);
        }else{
            int tax = player.getValue() / 10;
            player.pay(tax);
        }
    }

    @Override public String onLand(Player player, Game game) {
        payTax(player);
        return Config.moveDescriptions.get("tax");
    }
}
