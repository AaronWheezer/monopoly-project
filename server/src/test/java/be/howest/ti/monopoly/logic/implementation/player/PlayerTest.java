package be.howest.ti.monopoly.logic.implementation.player;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.tiles.Rentable;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player p1;
    private Player p2;
    private Street street1;
    private Street street2;
    private Street street3;
    private Game game;

    @BeforeEach
    void setUp() {
        GameManager gm = new GameManager();
        game = gm.createGame("groep", 4);
        gm.joinGame(game.getId(), "Gert");
        gm.joinGame(game.getId(), "Jan");
        p1 = new Player("Gert");
        p2 = new Player("Jan");
        street1 = (Street) Config.tiles[3];
        street2 = (Street) Config.tiles[6];
        street3 = (Street) Config.tiles[8];
    }

    @Test
    void setCurrentTile() {
        p1.setCurrentTile(street1);
        assertEquals(street1.getName(), p1.getCurrentTile());
    }

    @Test
    void getName() {
        assertEquals("Gert", p1.getName());
    }

    @Test
    void getMoney() {
        assertEquals(1500, p1.getMoney());
    }

    @Test
    void getCurrentTile() {
        assertNotNull(p1.getCurrentTile());
    }

    @Test
    void getCurrentTileType() {
        p1.setCurrentTile(street1);
        assertEquals(street1.getType(), p1.getCurrentTileType());
    }

    @Test
    void getJailed() {
        assertFalse(p1.getJailed());
    }

    @Test
    void getBankrupt() {
        assertFalse(p1.getBankrupt());
    }

    @Test
    void getGetOutOfJailFreeCards() {
        assertEquals(0, p1.getGetOutOfJailFreeCards());
        p1.addGetOutOfJailFreeCard();
        assertEquals(1, p1.getGetOutOfJailFreeCards());
    }

    @Test
    void getTaxSystem() {
        assertEquals("ESTIMATE", p1.getTaxSystem());
    }

    @Test
    void setTaxSystem() {
        p1.setTaxSystem("ESTIMATE");
        assertEquals("ESTIMATE", p1.getTaxSystem());
    }

    @Test
    void getProperties() {
        Rentable r1 = (Rentable) Config.tiles[3];
        p1.addProperty(r1);
        assertEquals(1, p1.getProperties().size());
    }

    @Test
    void getDebt() {
        assertEquals(0, p1.getDebt());
    }

    @Test
    void addDebt() {
        p1.addDebt(100);
        assertEquals(-100, p1.getDebt());
    }

    @Test
    void pay() {
        p1.pay(100);
        assertEquals(1400, p1.getMoney());
    }


    @Test
    void goToJail() {
        assertFalse(p1.getJailed());
        p1.setJailed(true);
        assertTrue(p1.getJailed());
    }

    @Test
    void receive() {
        assertEquals(1500, p1.getMoney());
        p1.receive(500);
        assertEquals(2000, p1.getMoney());
    }

    @Test
    void payPlayer() {
        p1.payPlayer(p2, 500);
        assertEquals(1000, p1.getMoney());
        assertEquals(2000, p2.getMoney());
    }

    @Test
    void payPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        p1.payPlayers(players, 500);
        assertEquals(1000, p1.getMoney());
        assertEquals(2000, p2.getMoney());
    }

    @Test
    void addGetOutOfJailFreeCard() {
        assertEquals(0, p1.getGetOutOfJailFreeCards());
        p1.addGetOutOfJailFreeCard();
        assertEquals(1, p1.getGetOutOfJailFreeCards());
    }

    @Test
    void collectMoneyFromPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        p1.collectMoneyFromPlayers(players, 500);
        assertEquals(2000, p1.getMoney());
        assertEquals(1000, p2.getMoney());
    }

    @Test
    void setJailed() {
        assertFalse(p1.getJailed());
        p1.setJailed(true);
        assertTrue(p1.getJailed());
    }

    @Test
    void useGetOutOfJailFreeCard() {
        p1.addGetOutOfJailFreeCard();
        assertEquals(1, p1.getGetOutOfJailFreeCards());
        p1.setJailed(true);
        p1.useGetOutOfJailFreeCard();
        assertFalse(p1.getJailed());
    }

    @Test
    void mortgageProperty() {
        p1.setCurrentTile(street1);
        street1.buyProperty(game, p1);
        p1.mortgageProperty(street1);
        assertTrue(street1.mortgage(p1));
    }

    @Test
    void payJailFine() {
        p1.payJailFine();
        assertEquals(1450, p1.getMoney());
    }

    @Test
    void declareBankruptcy() {
        p1.declareBankruptcy(game);
        assertTrue(p1.getBankrupt());
    }

    @Test
    void getValue() {
        assertEquals(1500, p1.getValue());
        p1.setCurrentTile(street1);
        street1.buyProperty(game, p1);
        assertEquals(1800, p1.getValue());
    }

    @Test
    void addProperty() {
        assertEquals(0, p1.getProperties().size());
        p1.addProperty(street1);
        assertEquals(1, p1.getProperties().size());
    }

    @Test
    void payOffDebt() {
        p1.addDebt(100);
        assertEquals(-100, p1.getDebt());
        p1.payOffDebt(100);
        assertEquals(0, p1.getDebt());
    }

    @Test
    void payButGetMortgage() {
        assertEquals(1500, p1.getMoney());
        p1.setCurrentTile(street1);
        street1.buyProperty(game, p1);
        p1.setCurrentTile(street2);
        street2.buyProperty(game, p1);
        p1.setCurrentTile(street3);
        street3.buyProperty(game, p1);
        assertEquals(1240, p1.getMoney());
        p1.mortgageProperty(street1);
        p1.pay(1250);
        assertEquals(20, p1.getMoney());
    }
}