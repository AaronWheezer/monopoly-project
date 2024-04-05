package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.player.Player;

public abstract class Tile {

    protected final String name;
    private final int position;
    protected final String type;
    private final String nameAsPathParameter;

    public Tile(String name, int position, String type) {
        this.name = name;
        this.position = position;
        this.type = type;
        this.nameAsPathParameter = name.replace(" ", "_");
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public String getNameAsPathParameter() {
        return nameAsPathParameter;
    }

    public String onLand(Player player, Game game) {
        return "";
    }
}
