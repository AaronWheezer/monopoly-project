package be.howest.ti.monopoly.logic.implementation.utils;


import be.howest.ti.monopoly.logic.implementation.cards.*;
import be.howest.ti.monopoly.logic.implementation.cards.Card;
import be.howest.ti.monopoly.logic.implementation.tiles.*;

import java.util.HashMap;
import java.util.Map;

public class Config {

    //fixes error from boilerplate code
    public Config() {
        moveDescriptions.put("in jail", "");
        moveDescriptions.put("visiting jail", "is just visiting");
        moveDescriptions.put("go to jail", "has to go to jail");
        moveDescriptions.put("new property", "can buy this property in direct sale");
        moveDescriptions.put("owned property", "should pay rent");
        moveDescriptions.put("tax", "should pay tax");
        moveDescriptions.put("my property", "your property, move on");
        moveDescriptions.put("go", "passes 'GO!' and receives 200 for it");
        moveDescriptions.put("free Parking", "You can park here for free.");
    }

    public static final int minPlayers = 2;
    public static final int maxPlayers = 10;
    public static final int startHousesCount = 32;
    public static final int startHotelsCount = 12;

    public static final int MaxPrefixLength = 7;

    public static final int maxPlayerNameLength = 15;

    public static final String separator = "_";
    public static final int groupNumber = 34;
    public static final String prefix = "group" + groupNumber;

    public static final int gameIdFormatLength = 3;

    public static final int fullRoundReward = 200;
    public static int jailFine = 50;

    public static final int dicesCount = 2;
    public static final int diceMaxValue = 6;

    public static final Map<String, String> moveDescriptions = new HashMap<>();

    public static final Tile[] tiles = new Tile[]{
            new NoActionTile("go", 0, "go"),
            new Street("Mediterranean", 1, 60, 2, "PURPLE", 2, 10, 30, 90, 160, 250, 50),
            new CardTile("Community Chest I", 2, "community chest"),
            new Street("Baltic", 3, 60, 4, "PURPLE", 4, 20, 60, 180, 320, 450, 50),
            new Tax("Tax Income", 4, 200),
            new RailRoad("Reading RR", 5, 200, 25, "BLACK", 4),
            new Street("Oriental", 6, 100, 6, "LIGHTBLUE", 3, 30, 90, 270, 400, 550, 50),
            new CardTile("Chance I", 7, "chance"),
            new Street("Vermont", 8, 100, 8, "LIGHTBLUE", 3, 40, 100, 300, 450, 600, 50),
            new Street("Connecticut", 9, 120, 9, "LIGHTBLUE", 3, 50, 150, 450, 625, 750, 50),
            new NoActionTile("Jail", 10, "jail"),
            new Street("Saint Charles Place", 11, 140, 11, "VIOLET", 2, 50, 150, 450, 625, 750, 100),
            new Utility("Electric Company", 12, 150, 4, 10, "WHITE", 2),
            new Street("States", 13, 140, 13, "VIOLET", 2, 50, 150, 450, 625, 750, 100),
            new Street("Virginia", 14, 160, 14, "VIOLET", 2, 60, 180, 500, 700, 900, 100),
            new RailRoad("Pennsylvania RR", 15, 200, 25, "BLACK", 4),
            new Street("Saint James", 16, 180, 16, "ORANGE", 3, 90, 270, 400, 550, 700, 100),
            new CardTile("Community Chest II", 17, "community chest"),
            new Street("Tennessee", 18, 180, 18, "ORANGE", 3, 100, 300, 450, 600, 750, 100),
            new Street("New York", 19, 200, 19, "ORANGE", 3, 100, 300, 450, 600, 750, 100),
            new NoActionTile("Free Parking", 20, "free Parking"),
            new Street("Kentucky Avenue", 21, 220, 21, "RED", 3, 110, 330, 510, 660, 830, 100),
            new CardTile("Chance II", 22, "chance"),
            new Street("Indiana Avenue", 23, 220, 23, "RED", 3, 110, 330, 510, 660, 830, 100),
            new Street("Illinois Avenue", 24, 240, 24, "RED", 3, 120, 360, 600, 720, 900, 100),
            new RailRoad("Baltimore and Ohio RR", 25, 200, 25, "BLACK", 4),
            new Street("Atlantic", 26, 260, 26, "YELLOW", 3, 110, 330, 800, 975, 1150, 150),
            new Street("Ventnor", 27, 260, 27, "YELLOW", 3, 110, 330, 800, 975, 1150, 150),
            new Utility("Water Works", 28, 150, 4, 10, "WHITE", 2),
            new Street("Marvin Gardens", 29, 280, 29, "YELLOW", 3, 120, 360, 850, 1025, 1200, 150),
            new ToJail("Go To Jail", 30, "Go To Jail"),
            new Street("Pacific", 31, 300, 31, "DARKGREEN", 3, 130, 390, 900, 1100, 1275, 200),
            new Street("North Carolina", 32, 300, 32, "DARKGREEN", 3, 130, 390, 900, 1100, 1275, 200),
            new CardTile("Community Chest III", 33, "community chest"),
            new Street("Pennsylvania", 34, 320, 34, "DARKGREEN", 3, 150, 450, 1000, 1200, 1400, 200),
            new RailRoad("Short Line RR", 35, 200, 25, "BLACK", 4),
            new CardTile("Chance III", 36, "chance"),
            new Street("Park Place", 37, 350, 35, "DARKBLUE", 2, 175, 500, 1100, 1300, 1500, 200),
            new Tax("Luxury Tax", 38, 100),
            new Street("Boardwalk", 39, 400, 40, "DARKBLUE", 2, 200, 600, 1400, 1700, 2000, 200)
    };

    public static final Card[] chanceCards = new Card[]{
            new TileCard("Advance to Boardwalk", tiles[39]),
            new TileCard("Advance to Go (Collect $200)", tiles[0]),
            new TileCard("Advance to Illinois Avenue. If you pass Go, collect $200", tiles[24]),
            new TileCard("Advance to St.Charles Place. If you pass Go, collect $200", tiles[11]),
            new MoveCard("Advance to the nearest Railroad", "railroad"),
            new MoveCard("Advance to the nearest Utility", "utility"),
            new CashCard("Bank pays you dividend of $50", 50, "receive"),
            new JailCard("Get out of jail free. This card may be kept until needed or sold", false),
            new MoveBackCard("Go back 3 spaces", 3),
            new JailCard("Go directly to jail. Do not pass Go, do not collect $200", true),
            new PropertyCard("Make general repairs on all your property. For each house pay $25. For each hotel pay $100", 25, 100),
            new CashCard("Pay poor tax of $15", 15, "pay"),
            new CashCard("You have been elected chairman of the board. Pay each player $50", 50, "pay_all"),
            new CashCard("Your building and loan matures. Collect $150", 150, "receive"),
    };

    public static final Card[] communityChestCards = new Card[]{
            new TileCard("Advance to Go (Collect $200)", tiles[0]),
            new CashCard("Bank error in your favor. Collect $200", 200, "receive"),
            new CashCard("Doctor's fees. Pay $50", 50, "pay"),
            new CashCard("From sale of stock you get $50", 50, "receive"),
            new JailCard("Get out of jail free. This card may be kept until needed or sold", false),
            new JailCard("Go to jail. Do not pass Go, do not collect $200", true),
            new CashCard("Grand Opera Night. Collect $50 from every player for opening night seats", 50, "receive_all"),
            new CashCard("Holiday Fund matures. Receive $100", 100, "receive"),
            new CashCard("Income tax refund. Collect $20", 20, "receive"),
            new CashCard("It is your birthday. Collect $10 from every player", 10, "receive_all"),
            new CashCard("Life insurance matures. Collect $100", 100, "receive"),
            new CashCard("Pay hospital fees of $100", 100, "pay"),
            new CashCard("Pay school fees of $150", 150, "pay"),
            new CashCard("Receive $25 consultancy fee", 25, "receive"),
            new PropertyCard("You are assessed for street repairs. Pay $40 per house and $115 per hotel", 40, 115),
            new CashCard("You have won second prize in a beauty contest. Collect $10", 10, "receive"),
            new CashCard("You inherit $100", 100, "receive"),
    };
}
