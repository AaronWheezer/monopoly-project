package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.Map;
import java.util.Set;


public class TestService implements IService {

    IService delegate = new ServiceAdapter();

    void setDelegate(IService delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getVersion() {
        return delegate.getVersion();
    }

    @Override
    public Tile[] getTiles() {
        return delegate.getTiles();
    }

    @Override
    public Tile getTile(int position) {
        return delegate.getTile(position);
    }

    @Override
    public Tile getTile(String name) {
        return delegate.getTile(name);
    }

    @Override
    public String[] getChance() {
        return delegate.getChance();
    }

    @Override
    public Set<Game> getGames(String prefix) {
        return delegate.getGames(prefix);
    }

    @Override
    public Set<Game> getGames(String prefix, int numberOfPlayers) {
        return delegate.getGames(prefix, numberOfPlayers);
    }

    @Override
    public Set<Game> getGames(String prefix , boolean started) {
        return delegate.getGames(prefix, started);
    }

    @Override
    public Set<Game> getGames(String prefix, int numberOfPlayers, boolean started) {
        return delegate.getGames(prefix, numberOfPlayers, started);
    }

    @Override
    public String[] getCommunityChest() {
        return delegate.getCommunityChest();
    }

    @Override
    public Game createGame(String prefix, int numberOfPlayers) {
        return delegate.createGame(prefix, numberOfPlayers);
    }

    @Override
    public Map<String, String> joinGame(String gameId, String playerName) {
        return delegate.joinGame(gameId,playerName);
    }

    @Override
    public Set<Game> clearGameList() {
        return delegate.clearGameList();
    }

    @Override
    public Game getGame(String gameId) {
        return delegate.getGame(gameId);
    }

    @Override
    public Game getDummyGame() {
        return delegate.getDummyGame();
    }

    @Override
    public Street buyHouse() {
        return delegate.buyHouse();
    }

    @Override
    public Street sellHouse() {
        return delegate.sellHouse();
    }

    @Override
    public Street buyHotel() {
        return delegate.buyHotel();
    }

    @Override
    public Street sellHotel() {
        return delegate.sellHotel();
    }

    @Override
    public int takeMortgage() {
        return delegate.takeMortgage();
    }

    @Override
    public int settleMortgage() {
        return delegate.settleMortgage();
    }

    @Override
    public int collectDebt(String gameId,String playerName,String property,String debtor) {
        return delegate.collectDebt(gameId,playerName,property,debtor);
    }

    @Override
    public int trade() {
        return delegate.trade();
    }

    @Override
    public int getOutOfJailFine() {
        return delegate.getOutOfJailFine();
    }

    @Override
    public Object getOutOfJailFree() {
        return delegate.getOutOfJailFree();
    }

    @Override
    public Object getBankAuctions() {
        return delegate.getBankAuctions();
    }

    @Override
    public Object placeBidOnBankAuction() {
        return delegate.placeBidOnBankAuction();
    }

    @Override
    public Object getPlayerAuctions() {
        return delegate.getPlayerAuctions();
    }

    @Override
    public Object startPlayerAuction() {
        return delegate.startPlayerAuction();
    }

    @Override
    public Object placeBidOnPlayerAuction() {
        return delegate.placeBidOnPlayerAuction();
    }

    @Override
    public Game rollDice(String gameID, String playerName) {
        return delegate.rollDice(gameID, playerName);
    }

    @Override
    public Object declareBankruptcy(String gameID, String playerName) {
        return delegate.declareBankruptcy(gameID,playerName);
    }

    @Override
    public Player useEstimateTax(String gameId, String playerName) {
        return delegate.useEstimateTax(gameId, playerName);
    }

    @Override
    public Player useComputeTax(String gameId, String playerName) {
        return delegate.useComputeTax(gameId, playerName);
    }

    @Override
    public Map<String, Object> buyProperty(String gameId, String playerName, String property) {
        return delegate.buyProperty(gameId, playerName, property);
    }

    @Override
    public Map<String, Object> dontBuyProperty(String gameId, String playerName, String property) {
        return delegate.dontBuyProperty(gameId, playerName, property);
    }

}
