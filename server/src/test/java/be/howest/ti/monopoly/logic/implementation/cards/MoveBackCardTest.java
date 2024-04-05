package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveBackCardTest {
    private Player p;
    private Tile tile;

    @BeforeEach
    void setUp() {
        tile = Config.tiles[36];
        GameManager gm = new GameManager();
        Game game = gm.createGame("groep", 4);
        gm.joinGame(game.getId(),"Aaron");
        p = game.getPlayer("Aaron");
    }

    @Test
    void moveBack() {
        p.setCurrentTile(tile);
        assertEquals("Chance III", p.getCurrentTile());
        MoveBackCard m = (MoveBackCard) Config.chanceCards[8];
        m.execute(p);
        assertEquals("Community Chest III", p.getCurrentTile());
    }
}