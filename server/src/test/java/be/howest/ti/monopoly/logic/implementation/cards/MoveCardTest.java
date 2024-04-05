package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveCardTest {
    private Player p;
    private Tile tile;

    @BeforeEach
    void setUp() {
        tile = Config.tiles[7];
        GameManager gm = new GameManager();
        Game game = gm.createGame("groep", 4);
        gm.joinGame(game.getId(),"Aaron");
        p = game.getPlayer("Aaron");
    }

    @Test
    void moveToNearestRailroad() {
        p.setCurrentTile(tile);
        assertEquals("Chance I", p.getCurrentTile());
        MoveCard m = (MoveCard) Config.chanceCards[4];
        m.execute(p);
        // assertEquals(, m.execute());
        assertEquals("Pennsylvania RR", p.getCurrentTile());
    }

    @Test
    void moveToNearestUtility() {
        p.setCurrentTile(tile);
        assertEquals("Chance I", p.getCurrentTile());
        MoveCard m = (MoveCard) Config.chanceCards[5];
        m.execute(p);
        assertEquals("Electric Company", p.getCurrentTile());
    }
}