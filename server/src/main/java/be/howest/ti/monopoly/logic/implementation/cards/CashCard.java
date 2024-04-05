package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.CardAction;
import be.howest.ti.monopoly.web.exceptions.InvalidRequestException;


public class CashCard extends Card {
    private int cash;
    private String type;

    public CashCard(String text, int cash, String type) {
        super(text);
        this.cash = cash;
        this.type = type;
    }

    public void execute(Game game, Player player) {
        switch (this.type) {
            case "pay":
                player.pay(cash);
                break;
            case "receive":
                player.receive(cash);
                break;
            case "pay_all":
                CardAction.payAllPlayers(game, player, cash);
                break;
            case "receive_all":
                CardAction.receiveMoneyFromAllPlayers(game, player, cash);
                break;
            default:
                throw new InvalidRequestException("Invalid card type");
        }
    }
}
