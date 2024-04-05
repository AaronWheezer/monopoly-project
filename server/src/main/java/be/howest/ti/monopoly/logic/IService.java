package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.Map;
import java.util.Set;

public interface IService {
    String getVersion();
    Tile[] getTiles();

    Tile getTile(int position);

    Tile getTile(String name);

    String[] getChance();

    Set<Game> getGames(String prefix);

    Set<Game> getGames(String prefix, int numberOfPlayers);

    Set<Game> getGames(String prefix, boolean started);


    Set<Game> getGames(String prefix, int numberOfPlayers, boolean started);

    String[] getCommunityChest();

    Game createGame(String prefix, int numberOfPlayers);

    Map<String, String> joinGame(String gameId, String playerName);

    Set<Game> clearGameList();

    Game getGame(String gameId);

    Game getDummyGame();

    Street buyHouse();

    Street sellHouse();

    Street buyHotel();

    Street sellHotel();

    int takeMortgage();

    int settleMortgage();

    int collectDebt(String gameId,String playerName,String property,String debtor);

    int trade();

    int getOutOfJailFine();

    Object getOutOfJailFree();

    Object getBankAuctions();

    Object placeBidOnBankAuction();

    Object getPlayerAuctions();

    Object startPlayerAuction();

    Object placeBidOnPlayerAuction();

    Game rollDice(String gameId , String playerName);

    Object declareBankruptcy(String gameId , String playerName);

    Player useEstimateTax(String gameId, String playerName);

    Player useComputeTax(String gameId, String playerName);

    Map<String,Object> buyProperty(String gameId, String playerName, String property);

    Map<String, Object> dontBuyProperty(String gameId, String playerName, String property);
}
