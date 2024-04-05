package be.howest.ti.monopoly.logic.implementation.cards;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JailCardTest {
    private Player p;

    @BeforeEach
    void setUpClass() {
        GameManager gm = new GameManager();
        Game game = gm.createGame("groep", 4);
        gm.joinGame(game.getId(),"Aaron");
        p = game.getPlayer("Aaron");

    }

    @Test
    void goToJailCard() {
        JailCard j = (JailCard) Config.chanceCards[9];
        assertFalse(p.getJailed());
        j.execute(p);
        assertTrue(p.getJailed());
    }

    @Test
    void getOutOfJailCard() {
        JailCard j = (JailCard) Config.chanceCards[7];
        assertEquals(0, p.getGetOutOfJailFreeCards());
        j.execute(p);
        assertEquals(1, p.getGetOutOfJailFreeCards());
    }

    @Test
    void useGetOutOfJailCard() {
        p.setJailed(true);
        p.useGetOutOfJailFreeCard();
        assertTrue(p.getJailed());
        JailCard j = (JailCard) Config.chanceCards[7];
        j.execute(p);
        p.useGetOutOfJailFreeCard();
        assertFalse(p.getJailed());
    }

    @Test
    void payFineToGetOutOfJail() {
        p.setJailed(true);
        p.payJailFine();
        assertFalse(p.getJailed());
    }
}