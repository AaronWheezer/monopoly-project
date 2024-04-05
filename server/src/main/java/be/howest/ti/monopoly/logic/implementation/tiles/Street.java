package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.player.Player;

public class Street extends Rentable {

    private final int rentWithOneHouse;
    private final int rentWithTwoHouses;
    private final int rentWithThreeHouses;
    private final int rentWithFourHouses;
    private final int rentWithHotel;
    private final int housePrice;
    private final String streetColor;
    private int numberOfHouses;
    private  int numberOfHotels;

    public Street(String name, int position, int cost, int rent, String color, int groupSize,int rentWithOneHouse, int rentWithTwoHouses, int rentWithThreeHouses, int rentWithFourHouses, int rentWithHotel, int housePrice) {
        super( name,  position,  "street",  cost,  rent,  color,  groupSize);
        this.rentWithOneHouse = rentWithOneHouse;
        this.rentWithTwoHouses = rentWithTwoHouses;
        this.rentWithThreeHouses = rentWithThreeHouses;
        this.rentWithFourHouses = rentWithFourHouses;
        this.rentWithHotel = rentWithHotel;
        this.housePrice = housePrice;
        this.streetColor = color;
    }

    public int getRentWithOneHouse() {
        return rentWithOneHouse;
    }

    public int getRentWithTwoHouses() {
        return rentWithTwoHouses;
    }

    public int getRentWithThreeHouses() {
        return rentWithThreeHouses;
    }

    public int getRentWithFourHouses() {
        return rentWithFourHouses;
    }

    public int getRentWithHotel() {
        return rentWithHotel;
    }

    @Override
    public int getRent(){
        if(numberOfHouses == 1){
            return rentWithOneHouse;
        }else if(numberOfHouses == 2){
            return rentWithTwoHouses;
        }else if(numberOfHouses == 3){
            return rentWithThreeHouses;
        }else if(numberOfHouses == 4){
            return rentWithFourHouses;
        }else if(numberOfHotels == 1){
            return rentWithHotel;
        }
        return this.rent;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public String getStreetColor() {
        return streetColor;
    }

    public int getHouses() {
        return numberOfHouses;
    }

    public int getHotels() {
        return numberOfHotels;
    }

    public void upgradeProperty(Game game){
        Player owner = game.getPlayer(this.owner);
        if(numberOfHouses < 4){
            if(game.getAvailableHouses() > 0 && owner.getMoney() >= housePrice){
                owner.pay(housePrice);
                numberOfHouses++;
                game.newHouse();
                this.rent = getRent();
            }
        }else if(numberOfHouses == 4 && numberOfHotels == 0){
            if(game.getAvailableHotels() != 0 && owner.getMoney() >= housePrice){
                numberOfHouses = 0;
                owner.pay(housePrice);
                numberOfHotels++;
                game.newHouse();
                this.rent = getRent();
            }
        }
    }

    @Override
    public int getValue() {
        int houseValue = this.numberOfHouses * this.housePrice;
        int hotelValue = this.numberOfHotels * this.housePrice * 5;
        int totalValue = this.cost + houseValue + hotelValue;
        return totalValue;
    }
}
