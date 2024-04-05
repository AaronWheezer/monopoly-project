package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.Config;

public class NoActionTile extends Tile {
    public NoActionTile(String name,int position, String type) {
        super(name, position, type);
    }

    @Override
    public String onLand(Player player, Game game) {
        return Config.moveDescriptions.get(type);
    }
}
