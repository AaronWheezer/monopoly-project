package be.howest.ti.monopoly.logic.implementation.utils;

import be.howest.ti.monopoly.logic.implementation.cards.Card;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.tiles.Utility;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class Utils {
    public static final SecureRandom random = new SecureRandom();
    public static Card getRandomChanceCard(){
        int index = random.nextInt(Config.chanceCards.length - 1);
        return Config.chanceCards[index];
    }

    public static Card getRandomCommunityCard(){
        int index = random.nextInt(Config.communityChestCards.length - 1);
        return Config.communityChestCards[index];
    }

    public static Tile getNextTile(Tile tile, int steps){
        int positionSum = tile.getPosition() + steps;
        int nextTilePosition = positionSum % Config.tiles.length;

        if(nextTilePosition < 0){
            nextTilePosition = Config.tiles.length - (Config.tiles.length + nextTilePosition);
        }

        return Config.tiles[nextTilePosition];
    }

    public static Tile getNextTile(String tileId, int steps){
        Tile tile = getTile(tileId);
        if(tile != null){
            return getNextTile(tile,steps);
        }
        return null;
    }

    public static Tile getPreviousTile(String tileId, int steps){
        return getNextTile(tileId, -steps);
    }

    public static Tile getTile(String name){
        for(Tile tile : Config.tiles){
            if(tile.getName().equals(name) || tile.getNameAsPathParameter().equals(name)){
                return tile;
            }
        }
        return null;
    }

    public static Tile getNextTile(Tile tile){
        return getNextTile(tile,1);
    }

    public static Set<Utility> getTilesByType(String type){
        Set<Utility> typeTiles = new HashSet<>();
        for(Tile tile : Config.tiles){
            if(tile.getType().equals(type)){
                typeTiles.add((Utility) tile);
            }
        }
        return typeTiles;
    }

    public static String[] convertTypeArrayToString(Object[] typeArray){
        String[] stringArray = new String[typeArray.length];
        for(int i = 0; i < typeArray.length; i++){
            stringArray[i] = typeArray[i].toString();
        }
        return stringArray;
    }
}
