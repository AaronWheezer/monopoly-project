package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.player.Player;

public class JailCard extends Card {
    private boolean toJail;
    public JailCard(String text, boolean toJail) {
        super(text);
        this.toJail = toJail;
    }
    @Override
    public void execute(Player player){
        if(toJail){
            player.setJailed(true);
        }else{
            player.addGetOutOfJailFreeCard();
        }
    }
}
