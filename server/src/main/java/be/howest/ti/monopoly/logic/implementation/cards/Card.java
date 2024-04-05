package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.player.Player;

public abstract class Card {
    private final String description;


    public Card(String description) {
        this.description = description;
    }

    public void execute(Player player) {
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String getDescription() {
        return this.description;
    }
}
