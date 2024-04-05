package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Rentable;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import be.howest.ti.monopoly.logic.implementation.utils.Utils;

import java.util.*;


public class MonopolyService extends ServiceAdapter {
    private GameManager gameManager;

    public MonopolyService() {
        gameManager = new GameManager();
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public Tile[] getTiles() {
        return Config.tiles;
    }

    @Override
    public Tile getTile(int position) {
        for (Tile tile : getTiles()) {
            if (tile.getPosition() == position) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public Tile getTile(String name) {
        for (Tile tile : getTiles()) {
            if (tile.getNameAsPathParameter().equals(name)) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public String[] getChance() {
        return Utils.convertTypeArrayToString(Config.chanceCards);
    }

    @Override
    public String[] getCommunityChest() {
        return Utils.convertTypeArrayToString(Config.communityChestCards);
    }


    @Override
    public Game getGame(String gameId) {
        return gameManager.getGame(gameId);
    }

    @Override
    public Set<Game> getGames(String prefix) {
        return gameManager.getGames(prefix);
    }

    @Override
    public Set<Game> getGames(String prefix, int numberOfPlayers) {
        return gameManager.getGames(numberOfPlayers, prefix);
    }

    @Override
    public Set<Game> getGames(String prefix, boolean started) {
        return gameManager.getGames(started, prefix);
    }

    @Override
    public Set<Game> getGames(String prefix, int numberOfPlayers, boolean started) {
        return gameManager.getGames(started, numberOfPlayers, prefix);
    }

    @Override
    public Game createGame(String prefix, int numberOfPlayers) {
        return gameManager.createGame(prefix, numberOfPlayers);
    }

    @Override
    public Map<String, String> joinGame(String gameId, String playerName) {
        return gameManager.joinGame(gameId, playerName);
    }

    @Override
    public Game rollDice(String gameId, String playerName) {
        Game game = gameManager.getGame(gameId);
        game.rollDice(playerName);
        return game;
    }

    @Override
    public Player declareBankruptcy(String gameId, String playerName) {
        Game game = gameManager.getGame(gameId);
        Player player = game.getPlayer(playerName);
        player.declareBankruptcy(game);
        return player;
    }

    @Override
    public int collectDebt(String gameId,String playerName,String propertyName,String debtor) {
        Game game = gameManager.getGame(gameId);
        Player player = game.getPlayer(playerName);
        Player debtorPlayer = game.getPlayer(debtor);
        Rentable property = (Rentable) Utils.getTile(propertyName);
        property.payRent(game,player,debtorPlayer);
        return property.getRent();
    }

    @Override
    public Set<Game> clearGameList() {
        return gameManager.clearGameList();
    }

    @Override
    public Player useEstimateTax(String gameId, String playerName) {
        return setTax(gameId, playerName, "ESTIMATE");
    }

    @Override
    public Player useComputeTax(String gameId, String playerName) {
        return setTax(gameId, playerName, "COMPUTE");
    }

    private Player setTax(String gameId, String playerName, String type) {
        Game game = gameManager.getGame(gameId);
        if (game == null) {
            throw new MonopolyResourceNotFoundException("Game does not exist");
        }

        Player player = game.getPlayer(playerName);
        if (player == null) {
            throw new MonopolyResourceNotFoundException("Player does not exist");
        }
        player.setTaxSystem(type);
        return player;
    }

    @Override
    public Map<String, Object> buyProperty(String gameId, String playerName, String propertyName) {
        Game game = gameManager.getGame(gameId);
        Player player = game.getPlayer(playerName);
        Rentable property = (Rentable) Utils.getTile(propertyName);
        boolean boughtProperty = property.buyProperty(game,player);

        final Map<String, Object> buyProperty = new HashMap<>();
        buyProperty.put("property", property.getName());
        buyProperty.put("purchased", boughtProperty);
        return buyProperty;
    }

    public Map<String, Object> dontBuyProperty(String gameId, String playerName, String propertyName) {
        Game game = gameManager.getGame(gameId);
        String currentPlayerName = game.getCurrentPlayer();
        if(!currentPlayerName.equals(playerName)) {
            throw new MonopolyResourceNotFoundException("It is not your turn");
        }
        Player player = game.getPlayer(playerName);
        String playerTile = player.getCurrentTile();
        if(!playerTile.equals(propertyName)) {
            throw new MonopolyResourceNotFoundException("You are not on the property");
        }
        Rentable property = (Rentable) Utils.getTile(propertyName);
        gameManager.createAuction(gameId, property, 60);

        final Map<String, Object> buyProperty = new HashMap<>();
        buyProperty.put("property", property.getName());
        buyProperty.put("purchased", false);
        return buyProperty;
    }

}
