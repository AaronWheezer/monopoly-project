package be.howest.ti.monopoly.logic.implementation.tiles;

public class RailRoad extends Rentable {

    public RailRoad(String name,int position, int cost, int rent,String color,int groupSize) {
        super(name, position, "railroad", cost, rent, color, groupSize);
    }
}
