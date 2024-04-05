package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Rentable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class BankAuction {
    private int currentBid;
    private Player currentPlayer;
    private Player winner;
    private List<Player> players;
    private Rentable property;
    private Map<String,Integer> bids;
    private  Game game;
    private Date endDate;

    public BankAuction(Game game, Rentable property, int timeInSeconds) {
        this.players = game.getPlayers();
        this.game = game;
        this.property = property;
        this.currentBid = 0;
        endDate = new Date();
        addSecondsToEndDate(timeInSeconds);
    }

    public void bid(Player player) {
        if (player.equals(currentPlayer)) {
            updateEndDate();
            int bidIncrease = property.getCost() / 5;
            int nextBid = currentBid + bidIncrease;
            if (player.getMoney() >= nextBid) {
                bids.put(player.getName(), nextBid);
                this.winner = currentPlayer;
                this.currentBid = nextBid;
            }
        }
    }

    private void updateEndDate(){
        Date now = new Date();
        long timeLeft = this.endDate.getTime() - now.getTime();
        long secondsLeft = (timeLeft/ 1000);
        
        if(secondsLeft < 10){
            addSecondsToEndDate(10);
        }
    }

    public boolean endAuction(){
        if(endDate.after(new Date())){
            if(this.winner != null){
                property.buyProperty(game,winner, currentBid);
            }
            return true;
        }
        return false;
    }

    private void addSecondsToEndDate(int seconds){
        endDate.setTime(endDate.getTime() + seconds * 1000);
    }
}
