package be.howest.ti.monopoly.logic.implementation.player;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.tiles.Rentable;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.utils.Config;

import java.util.*;

public class Player {
    private String name;
    private int money;
    private Tile currentTile;
    private boolean jailed;
    private boolean bankrupt;
    private int getOutOfJailFreeCards;
    private String taxSystem;
    private Set<Rentable> properties;
    private int debt;
    private int doubleRolls;


    public Player(String name) {
        this.name = name;
        this.money = 1500;
        this.currentTile = Config.tiles[0];
        this.jailed = false;
        this.bankrupt = false;
        this.getOutOfJailFreeCards = 0;
        this.taxSystem = "ESTIMATE";
        this.properties = new HashSet<>();
        this.debt = 0;
        this.doubleRolls = 0;
    }

    public void setCurrentTile(Tile tile) {
        boolean finishedRound = tile.getPosition() < currentTile.getPosition();
        if (finishedRound) {
            money += Config.fullRoundReward;
        }
        this.currentTile = tile;
    }

    public  String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public String getCurrentTile() {
        return currentTile.getName();
    }

    public String getCurrentTileType() {
        return currentTile.getType();
    }

    public boolean getJailed() {
        return jailed;
    }

    public boolean getBankrupt() {
        return bankrupt;
    }

    public int getGetOutOfJailFreeCards() {
        return getOutOfJailFreeCards;
    }

    public String getTaxSystem() {
        return taxSystem;
    }

    public void setTaxSystem(String taxSystem) {
        this.taxSystem = taxSystem;
    }

    public Set<Rentable> getProperties() {
        return properties;
    }

    public int getDebt() {
        return debt;
    }

    public void addDebt(int debt){
        this.debt -= debt;
    }

    public void pay(int amountToPay) {
        boolean hasEnoughMoney = money >= amountToPay;
        if(hasEnoughMoney) {
            money -= amountToPay;
        }else{
            bankrupt();
        }
    }

    private void bankrupt() {
        this.bankrupt = true;
    }

    public void receive(int amount) {
        money += amount;
    }

    public void payPlayer(Player player, int amount) {
        this.pay(amount);
        player.receive(amount);
    }

    public void payPlayers(List<Player> players, int amount) {
        for(Player player : players) {
            boolean isSelf = player.equals(this);
            if(!isSelf){
                payPlayer(player, amount);
            }
        }
    }

    public void addGetOutOfJailFreeCard() {
        getOutOfJailFreeCards++;
    }

    private Set<Street> getStreets() {
        Set<Street> streets = new HashSet<>();
        for(Rentable property : this.properties) {
            if(property instanceof Street) {
                streets.add((Street) property);
            }
        }
        return streets;
    }

    public Map<String,Integer> houseAndHotelCount() {
        Map<String,Integer> count = new HashMap<>();
        count.put("houseCount", 0);
        count.put("hotelCount", 0);
        Set<Street> streets = getStreets();
        for(Street street : streets) {
            int houses = street.getHouses();
            int hotels = street.getHotels();
            int currentHouseCount = count.get("houseCount");
            int currentHotelCount = count.get("hotelCount");
            int newHouseCount = currentHouseCount + houses;
            int newHotelCount = currentHotelCount + hotels;
            count.put("houseCount", newHouseCount);
            count.put("hotelCount", newHotelCount);
        }
        return count;
    }

    public void collectMoneyFromPlayers(List<Player> players, int amount) {
        players.forEach(player -> {
        if(player != this) {
            player.payPlayer(this, amount);
        }
    });
    }

    public void setJailed(boolean jailed) {
        this.currentTile = Config.tiles[10];
        this.jailed = jailed;
    }

    public void useGetOutOfJailFreeCard() {
        if(getOutOfJailFreeCards > 0 && this.jailed) {
            this.jailed = false;
            getOutOfJailFreeCards--;
        }
    }

    public void mortgageProperty(Rentable property){
        property.mortgage(this);
    }

    public void payJailFine(){
        pay(Config.jailFine);
        setJailed(false);
    }

    public void doubleRoll(Game game){
        doubleRolls++;
        if(this.jailed) {
                this.jailed = false;
        }else if(doubleRolls == 3) {
            setJailed(true);
            this.doubleRolls = 0;
        }else{
            game.setCurrentPlayer(this.name);
        }
    }

    public void resetDoubleRolls(){
        this.doubleRolls = 0;
    }

    public void declareBankruptcy(Game game) {
        bankrupt = true;
        game.removePlayer(this);
        if(game.getPlayers().size() == 1) {
            game.endGame();
        }
    }

    public int getValue() {
        int value = this.money;
        for(Rentable property : properties) {
            value += property.getValue();
        }
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Player) {
            Player player = (Player) obj;
            return this.name.equals(player.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public void addProperty(Rentable rentable) {
        this.properties.add(rentable);
    }

    public void payOffDebt(int amount) {
        pay(amount);
        debt += amount;
    }
}
