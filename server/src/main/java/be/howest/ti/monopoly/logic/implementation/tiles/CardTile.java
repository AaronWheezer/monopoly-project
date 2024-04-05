package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.cards.Card;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.Utils;

public class CardTile extends Tile {

    public CardTile(String name, int position, String type) {
        super(name, position, type);
    }

    @Override
    public String onLand(Player player, Game game) {
        Card card;
        if(this.type.equals("chance")){
            card = Utils.getRandomChanceCard();
        }else{
            card = Utils.getRandomCommunityCard();
        }
        card.execute(player);
        return card.getDescription();
    }
}
