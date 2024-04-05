package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.CardAction;

public class MoveCard extends Card{
    private String type;
    public MoveCard(String text, String type){
        super(text);
        this.type = type;
    }

    @Override
    public void execute(Player player){
        CardAction.nearestTypeTile(player, this.type);
    }
}
