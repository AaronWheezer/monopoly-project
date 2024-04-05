package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashCardTest {
    private Game game;
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;

    @BeforeEach
    void setUp() {
        GameManager gm = new GameManager();
        game = gm.createGame("groep",4);
        gm.joinGame(game.getId(),"Aaron");
        gm.joinGame(game.getId(),"Ben");
        gm.joinGame(game.getId(),"Adam");
        gm.joinGame(game.getId(),"Eva");
        p1 = game.getPlayer("Aaron");
        p2 = game.getPlayer("Ben");
        p3 = game.getPlayer("Adam");
        p4 = game.getPlayer("Eva");
    }

    @Test
    void receiveCashFromOtherPlayer() {
        assertEquals(1500, p1.getMoney());
        CashCard c = (CashCard) Config.chanceCards[6];
        c.execute(game, p1);
        assertEquals(1550, p1.getMoney());
    }

    @Test
    void payTax() {
        assertEquals(1500, p1.getMoney());
        CashCard c = (CashCard) Config.chanceCards[11];
        c.execute(game, p1);
        assertEquals(1485, p1.getMoney());
    }

    @Test
    void GiveCashToAllPlayers() {
        assertEquals(1500, p1.getMoney());
        CashCard c = (CashCard) Config.chanceCards[12];
        c.execute(game, p1);
        assertEquals(1350, p1.getMoney());
        assertEquals(1550, p2.getMoney());
        assertEquals(1550, p3.getMoney());
        assertEquals(1550, p4.getMoney());
    }

    @Test
    void ReceiveCashFromAllPlayers() {
        assertEquals(1500, p1.getMoney());
        CashCard c = (CashCard) Config.communityChestCards[6];
        c.execute(game, p1);
        assertEquals(1650, p1.getMoney());
        assertEquals(1450, p2.getMoney());
        assertEquals(1450, p3.getMoney());
        assertEquals(1450, p4.getMoney());
    }
}