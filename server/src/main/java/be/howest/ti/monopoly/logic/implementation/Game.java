package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.player.Turn;
import be.howest.ti.monopoly.logic.implementation.tiles.Rentable;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import be.howest.ti.monopoly.logic.implementation.utils.Dice;
import be.howest.ti.monopoly.logic.implementation.utils.Utils;

import java.util.*;

public class Game {
    private int numberOfPlayers;
    private boolean started;
    private String directSale;
    private int availableHouses;
    private int availableHotels;
    private List<Player> players;
    private String currentPlayer;
    private String id;
    private List<Turn> turns;
    private Integer[] lastDiceRoll;
    private boolean canRoll;
    private boolean ended;

    public Game(int numberOfPlayers, String id) {
        this.numberOfPlayers = numberOfPlayers;
        this.id = id;
        this.directSale = null;
        this.canRoll = true;
        this.ended = false;
        this.lastDiceRoll = new Integer[0];
        this.turns = new ArrayList<>();
        this.availableHouses = Config.startHousesCount;
        this.availableHotels = Config.startHotelsCount;
        this.players = new ArrayList<>(numberOfPlayers);
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public boolean isStarted() {
        return started;
    }

    public String getDirectSale() {
        return directSale;
    }

    public boolean getCanRoll() {
        return this.canRoll;
    }

    public int getAvailableHouses() {
        return availableHouses;
    }

    public int getAvailableHotels() {
        return availableHotels;
    }


    public void newHouse() {
        if (availableHouses > 0) {
            availableHouses--;
        }
    }

    public void newHotel() {
        if (availableHouses > 0) {
            availableHotels--;
        }
    }

    public void setCurrentPlayer(String playerName) {
        this.currentPlayer = playerName;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void rollDice(String playerName) {
        if (playerName.equals(currentPlayer) && this.canRoll) {
            Player player = getPlayer(playerName);

            executeTurn(player);
            updateCanRoll();
            executeTile(player);
            nextPlayerTurn();
        }
    }

    private void executeTurn(Player player) {
        Turn turn = new Turn(player,this);
        Integer[] dices = Dice.rollDices();

        this.lastDiceRoll = dices;
        checkDoubleRolls(dices,player);
        turn.move(dices,this);
        turns.add(turn);
    }

    private void checkDoubleRolls(Integer[] dices, Player player) {
        if(dices.length == 2) {
            boolean doubles = Objects.equals(dices[0], dices[1]);
            if(doubles) {
                player.doubleRoll(this);
            }else{
                player.resetDoubleRolls();
            }
        }
    }

    private void updateCanRoll() {
        boolean directSaleActive = this.directSale != null;
        this.canRoll = !directSaleActive;
    }

    private void executeTile(Player player) {
        String tileName = player.getCurrentTile();
        Tile tile = Utils.getTile(tileName);
        tile.onLand(player, this);
    }

    public void nextPlayerTurn() {
        if (this.directSale == null) {
            this.canRoll = true;
            int lastPlayerIndex = players.lastIndexOf(getPlayer(currentPlayer));
            int nextPlayerIndex = (lastPlayerIndex + 1) % players.size();
            Player nextPlayer = players.get(nextPlayerIndex);
            this.currentPlayer = nextPlayer.getName();
        }
    }


    public List<Turn> getTurns() {
        return turns;
    }

    public Player addPlayer(String playerName) {
        if (players.size() < numberOfPlayers) {
            Player player = new Player(playerName);
            players.add(player);
            return player;
        }
        return null;
    }

    public Player getPlayer(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public Integer[] getLastDiceRoll() {
        return this.lastDiceRoll;
    }

    public void setDirectSale(String name) {
        this.directSale = name;
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void payRent(Player owner, Rentable property) {
        for (Player player : players) {
            boolean isOwner = player.equals(owner);
            boolean isOnProperty = player.getCurrentTile().equals(property.getName());
            if(!isOwner && isOnProperty) {
                property.payRent(this,owner,player);
            }
        }
    }

    public void start() {
        this.started = true;
    }

    public boolean getEnded() {
        return this.ended;
    }

    public void endGame() {
        this.ended = true;
    }
}
