package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.Utils;

import java.util.Set;

public class Utility extends Rentable{

    private final int rentDouble;
    private String color;
    private int groupSize;


    public Utility(String name, int position, int cost, int rent,int rentDouble, String color, int groupSize) {
        super( name,  position,  "utility",cost,rent,color,groupSize);
        this.rentDouble = rentDouble;
    }

    public int getRent(int diceRoll){
        Set<Utility> utilities = Utils.getTilesByType("utility");
        boolean ownsAll = true;

        for (Utility tile : utilities) {
            boolean ownsTile = tile.getOwner().equals(this.owner);
            if (!ownsTile) {
                ownsAll = false;
                break;
            }
        }

        if(ownsAll){
            return this.rentDouble * diceRoll;
        }
        return this.rent * diceRoll;
    }

    public void payUtilRent(Player player, int diceRoll){
        int rent = this.getRent(diceRoll);
        player.pay(rent);
    }

    public String getUtilRent() {
        return String.format("%x or %x times the dice roll",this.rent,rentDouble);
    }
}
