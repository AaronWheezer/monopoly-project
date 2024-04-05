package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.CardAction;

public class PropertyCard extends Card {
    private int houseRent;
    private int hotelRent;
    public PropertyCard(String text, int houseRent, int hotelRent) {
        super(text);
        this.houseRent = houseRent;
        this.hotelRent = hotelRent;
    }

    @Override
    public void execute(Player player) {
        CardAction.housePayment(player, houseRent, hotelRent);
    }
}
