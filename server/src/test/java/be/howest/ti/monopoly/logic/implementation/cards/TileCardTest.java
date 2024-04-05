package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileCardTest {
    private Player p1;
    private Player p2;
    private Tile tile1;
    private Tile tile2;
    private Card c1;
    private Card c2;

    @BeforeEach
    void setUp() {
        GameManager gm = new GameManager();
        Game game = gm.createGame("groep", 4);
        gm.joinGame(game.getId(),"Aaron");
        gm.joinGame(game.getId(),"Ewout");
        p1 = game.getPlayer("Aaron");
        p2 = game.getPlayer("Ewout");
        tile1 = Config.tiles[36];
        tile2 = Config.tiles[7];
        c1 = Config.chanceCards[2];
        c2 = Config.chanceCards[3];
    }

    @Test
    void playerMovesToLocationThroughGo() {
        p1.setCurrentTile(tile1);
        p2.setCurrentTile(tile1);
        assertEquals(1500, p1.getMoney());
        assertEquals(1500, p2.getMoney());
        c1.execute(p1);
        c2.execute(p2);
        assertEquals("Illinois Avenue", p1.getCurrentTile());
        assertEquals("Saint Charles Place", p2.getCurrentTile());
        assertEquals(1700, p1.getMoney());
        assertEquals(1700, p2.getMoney());
    }

    @Test
    void playerMovesToLocationNotThroughGo() {
        p1.setCurrentTile(tile2);
        p2.setCurrentTile(tile2);
        assertEquals(1500, p1.getMoney());
        assertEquals(1500, p2.getMoney());
        c1.execute(p1);
        c2.execute(p2);
        assertEquals("Illinois Avenue", p1.getCurrentTile());
        assertEquals("Saint Charles Place", p2.getCurrentTile());
        assertEquals(1500, p1.getMoney());
        assertEquals(1500, p2.getMoney());
    }

    @Test
    void playerAdvancesToGo() {
        p1.setCurrentTile(tile1);
        assertEquals(1500, p1.getMoney());
        Card c = Config.chanceCards[1];
        c.execute(p1);
        assertEquals("go", p1.getCurrentTile());
        assertEquals(1700, p1.getMoney());
    }

    @Test
    void playerAdvancesToBoardwalk() {
        p1.setCurrentTile(tile1);
        assertEquals(1500, p1.getMoney());
        Card c = Config.chanceCards[0];
        c.execute(p1);
        assertEquals("Boardwalk", p1.getCurrentTile());
        assertEquals(1500, p1.getMoney());
    }

    @Test
    void playerAdvancesToCurrentTile() {
        Tile tile1 = Config.tiles[24];
        Tile tile2 = Config.tiles[11];
        p1.setCurrentTile(tile1);
        p2.setCurrentTile(tile2);
        c1.execute(p1);
        c2.execute(p2);
        assertEquals("Illinois Avenue", p1.getCurrentTile());
        assertEquals("Saint Charles Place", p2.getCurrentTile());
        assertEquals(1500, p1.getMoney());
        assertEquals(1500, p2.getMoney());
    }
}