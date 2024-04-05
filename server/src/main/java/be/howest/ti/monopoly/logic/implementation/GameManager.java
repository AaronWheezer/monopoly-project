package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Rentable;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import be.howest.ti.monopoly.web.tokens.MonopolyUser;
import be.howest.ti.monopoly.web.tokens.PlainTextTokens;
import be.howest.ti.monopoly.web.tokens.TokenManager;

import java.util.*;

public class GameManager {
    private Map<String, Game> games;
    private Map<String, Player> players;
    private Map<String,List<BankAuction>> auctions;
    private final TokenManager tokenManager;
    private int id;

    public GameManager() {
        this.games = new HashMap<>();
        this.auctions = new HashMap<>();
        this.tokenManager = new PlainTextTokens();
        this.players = new HashMap<>();
        this.id = 0;
    }

    public List<BankAuction> getAuctions(String gameId) {
        for (BankAuction auction : this.auctions.get(gameId)) {
            if(auction.endAuction()){
                deleteAuction(gameId,auction);
            }
        }
        return this.auctions.get(gameId);
    }

    public void createAuction(String gameId ,Rentable property, int timeInSeconds) {
        Game game = getGame(gameId);
        game.setDirectSale(null);

        BankAuction auction = new BankAuction(game, property, timeInSeconds);
        List<BankAuction> bankAuctions = this.auctions.get(gameId);
        bankAuctions.add(auction);
        this.auctions.put(gameId,bankAuctions);
    }

    public void deleteAuction(String gameId,BankAuction bankAuction) {
        List<BankAuction> bankAuctions = auctions.get(gameId);
        bankAuctions.remove(bankAuction);
        auctions.put(gameId,bankAuctions);
    }

    public Game createGame(String prefix, int numberOfPlayers) {
        String gameId = getNewGameId(prefix);
        auctions.put(gameId,new ArrayList<>());
        Game game = new Game(numberOfPlayers, gameId);
        games.put(gameId, game);
        return game;
    }

    public void deleteGames() {
        games.clear();
    }

    public Map<String, String> joinGame(String gameId, String playerName) {
        Game game = games.get(gameId);
        boolean gameExists = game != null;
        if (gameExists) {
            if(game.getPlayers().isEmpty()) {
                game.setCurrentPlayer(playerName);
            }
            for (Player player : game.getPlayers()) {
                if (player.getName().equals(playerName)) {
                    return null;
                }
            }
            if (game.getPlayers().size() + 1 == game.getNumberOfPlayers()) {
                game.start();
            }
            String playerToken = tokenManager.createToken(new MonopolyUser(gameId, playerName));
            Player player = game.addPlayer(playerName);
            players.put(playerName, player);
            HashMap<String,String> response = new HashMap<>();
            response.put("token", playerToken);
            return response;
        }
        return null;
    }

    public Game getGame(String gameId) {
        return games.get(gameId);
    }

    public Player getPlayer(String token) {
        return players.get(token);
    }

    public Set<Game> getGames() {
        return new HashSet<>(this.games.values());
    }

    public Set<Game> getGames(boolean started, String prefix) {
        return getGames(true, false, started, 0, prefix);
    }

    public Set<Game> getGames(int numberOfPlayers, String prefix) {
        return getGames(false, true, false, numberOfPlayers, prefix);
    }

    public Set<Game> getGames(String prefix) {
        return getGames(false, false, false, 0, prefix);
    }

    public Set<Game> getGames(boolean started, int numberOfPlayers, String prefix) {
        return getGames(true, true, started, numberOfPlayers, prefix);
    }

    public Set<Game> clearGameList() {
        Set<Game> gamesList = new HashSet<>(this.games.values());
        gamesList.clear();
        return gamesList;
    }

    private boolean isValidGame(Game game, boolean hasStartedParam, boolean hasNumberOfPlayersParam, boolean started, int numberOfPlayers) {

        boolean gameIsStarted = game.isStarted();
        int gameNumberOfPlayers = game.getNumberOfPlayers();

        if (hasStartedParam && gameIsStarted != started) {
            return false;
        }
        return !hasNumberOfPlayersParam || gameNumberOfPlayers == numberOfPlayers;
    }


    private Set<Game> getGames(boolean hasStarted, boolean hasNumberOfPlayers, boolean started, int numberOfPlayers, String prefix) {
        Set<Game> gameSet = new HashSet<>();
        for (Game game : this.games.values()) {
            if(isValidGame(game, hasStarted, hasNumberOfPlayers, started, numberOfPlayers)) {
                gameSet.add(game);
            }
        }
        return gameSet;
    }

    private String getFormatId() {
        String formattedId = Integer.toString(this.id);

        while (formattedId.length() < Config.gameIdFormatLength) {
            formattedId = "0" + formattedId;
        }

        return formattedId;
    }

    private String getNewGameId(String prefix) {
        String formattedId = this.getFormatId();
        this.id++;
        return prefix + Config.separator + formattedId;
    }
}
