package be.howest.ti.monopoly.logic.implementation.utils;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.List;
import java.util.Map;

public class CardAction {
    public static void payAllPlayers(Game game, Player player, int amount) {
        List<Player> allPlayers = game.getPlayers();
        allPlayers.remove(player);

        player.payPlayers(allPlayers,amount);
    }

    public static void receiveMoneyFromAllPlayers(Game game, Player player, int amount) {
        List<Player> allPlayers = game.getPlayers();
        allPlayers.remove(player);

        player.collectMoneyFromPlayers(allPlayers,amount);
    }

    public static void nearestTypeTile(Player player,String type) {

        Tile currentTile = Utils.getTile(player.getCurrentTile());
        boolean isTypeTile = currentTile.getType().equals(type);

        while(!isTypeTile) {
            currentTile = Utils.getNextTile(currentTile);
            isTypeTile = currentTile.getType().equals(type);
        }
        player.setCurrentTile(currentTile);
    }


    public static void housePayment(Player player, int houseRent, int hotelRent) {
        Map<String,Integer> propertyCount =  player.houseAndHotelCount();
        int houseCount = propertyCount.get("houseCount");
        int hotelCount = propertyCount.get("hotelCount");

        int housePayment = houseRent * houseCount;
        int hotelPayment = hotelRent * hotelCount;
        player.pay(housePayment + hotelPayment);
    }

}
