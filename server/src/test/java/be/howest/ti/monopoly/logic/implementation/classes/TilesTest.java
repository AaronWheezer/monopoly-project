package be.howest.ti.monopoly.logic.implementation.classes;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.RailRoad;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.tiles.ToJail;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TilesTest {
    private Game game;
    private Player p;
    private Tile tile1;
    private Tile tile2;
    private RailRoad railRoad;

    @BeforeEach
    void setUpClass() {
        GameManager gm = new GameManager();
        game = gm.createGame("groep",2);
        tile1 = Config.tiles[5];
        tile2 = Config.tiles[7];
        railRoad = (RailRoad) Config.tiles[5];
        gm.joinGame(game.getId(),"Aaron");
        p = game.getPlayer("Aaron");
    }

    @Test
    void getTileName() {
        assertEquals("Reading RR", tile1.getName());
    }

    @Test
    void getTilePosition() {
        assertEquals(5, tile1.getPosition());
    }

    @Test
    void getTileType() {
        assertEquals("chance", tile2.getType());
    }

    @Test
    void getNameAsPathParameter() {
        assertEquals("Reading_RR", tile1.getNameAsPathParameter());
    }

    @Test
    void toJail() {
        p.setCurrentTile(tile2);
        assertEquals("Chance I", p.getCurrentTile());
        ToJail j = (ToJail) Config.tiles[30];
        j.onLand(p, game);
        assertEquals("Jail", p.getCurrentTile());
        assertTrue(p.getJailed());
    }

    @Test
    void getType() {
        assertEquals("railroad", tile1.getType());
    }

    //Rentable Tests
    @Test
    void getRent() {
        assertEquals(25, railRoad.getRent());
    }
}
