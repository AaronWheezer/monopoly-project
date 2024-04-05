package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.Config;

public abstract class Rentable extends Tile {

    protected final int cost;
    protected int rent;
    private final int groupSize;
    private final String color;
    protected String owner;
    private boolean mortgage;


    public Rentable(String name, int position, String type, int cost, int rent, String color, int groupSize) {
        super(name, position, type);
        this.cost = cost;
        this.rent = rent;
        this.mortgage = false;
        this.groupSize = groupSize;
        this.color = color;
    }

    public int getCost() {
        return cost;
    }

    public int getRent() {
        return rent;
    }

    public boolean getMortgage() {
        return this.mortgage;
    }

    public boolean mortgage(Player player){
        boolean isOwner = owner.equals(player.getName());
        if(isOwner){
            int mortgageValue = this.getCost() / 2;
            player.receive(mortgageValue);
            player.addDebt(mortgageValue);
            return this.mortgage = true;
        }
        return false;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public String getColor() {
        return color;
    }

    public void payRent(Game game,Player owner,Player player) {
        if(!owner.equals(player.getName())){
            throw new IllegalArgumentException("Player is not the owner of this property");
        }
        player.payPlayer(owner, rent);
    }

    public void settleMortgage(Game game,Player player){
        Player owner = game.getPlayer(this.owner);
        int price = (int)(this.cost * 1.1);
        boolean isOwner = this.owner.equals(player);
        boolean hasEnoughMoney = player.getMoney() >= price;
        if(isOwner && hasEnoughMoney){
            owner.payOffDebt(price);
        }
    }

    public boolean buyProperty(Game game, Player player) {
        return buyProperty(game, player, this.cost);
    }

    public boolean buyProperty(Game game, Player player,int price) {
        boolean playerOnProperty = player.getCurrentTile().equals(this.name);
        boolean playerHasMoney = player.getMoney() >= price;

        if (playerHasMoney && playerOnProperty) {
            player.pay(price);
            player.addProperty(this);
            this.owner = player.getName();
            game.setDirectSale(null);
            game.nextPlayerTurn();
            return true;
        }
        return false;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String onLand(Player player, Game game) {
        Player owner = game.getPlayer(this.owner);
        if (this.owner == null) {
            game.setDirectSale(this.name);
            return Config.moveDescriptions.get("new property");
        }
        if (this.owner.equals(player)) {
            return Config.moveDescriptions.get("my property");
        }
        player.payPlayer(owner, this.rent);
        return Config.moveDescriptions.get("owned property");
    }

    public int getValue() {
        return this.cost;
    }
}
