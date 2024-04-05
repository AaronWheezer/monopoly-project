package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.Map;
import java.util.Set;

public class ServiceAdapter implements IService {

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile[] getTiles() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(int position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getChance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getCommunityChest() {
        throw new UnsupportedOperationException();
    }


    @Override
    public Game createGame(String prefix, int numberOfPlayers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, String> joinGame(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Game> clearGameList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Game> getGames(String prefix) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Game> getGames(String prefix, int numberOfPlayers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Game> getGames(String prefix, boolean started) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Game> getGames(String prefix, int numberOfPlayers, boolean started) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getGame(String gameId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getDummyGame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Street buyHouse() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Street sellHouse() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Street buyHotel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Street sellHotel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int takeMortgage() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int settleMortgage() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int collectDebt(String gameId,String playerName,String property,String debtor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int trade() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getOutOfJailFine() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getOutOfJailFree() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getBankAuctions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object placeBidOnBankAuction() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getPlayerAuctions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object startPlayerAuction() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object placeBidOnPlayerAuction() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game rollDice(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Player declareBankruptcy(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Player useEstimateTax(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Player useComputeTax(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String,Object>  buyProperty(String gameId, String playerName, String property) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Object> dontBuyProperty(String gameId, String playerName, String property) {
        throw new UnsupportedOperationException();
    }

}
