package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.Config;

public class ToJail extends Tile {

    public ToJail(String name,int position,String type) {
        super(name,position,type);
    }

    @Override
    public String onLand(Player player, Game game) {
        player.setJailed(true);
        return Config.moveDescriptions.get("go to jail");
    }
}
