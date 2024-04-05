package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentableTest {
    private Rentable property;
    private Player p1;
    private Player p2;
    private Game game;
    private Street street;

    @BeforeEach
    void setUp() {
        GameManager gm = new GameManager();
        game = gm.createGame("test", 2);
        gm.joinGame(game.getId(), "Aaron");
        gm.joinGame(game.getId(), "Bob");
        p1 = game.getPlayer("Aaron");
        p2 = game.getPlayer("Bob");
        street = (Street) Config.tiles[1];
        property = (Rentable) Config.tiles[1];
        p1.setCurrentTile(property);
    }

    @Test
    void getCost() {
        assertEquals(60, property.getCost());
    }

    @Test
    void getRent() {
        assertEquals(2, property.getRent());
    }

    @Test
    void getMortgage() {
        property.buyProperty(game,p1);
        property.mortgage(p1);
        assertTrue(property.getMortgage());
    }

    @Test
    void settleMortgage() {
        property.buyProperty(game,p1);
        property.mortgage(p1);
        assertTrue(property.getMortgage());
        property.settleMortgage(game,p1);
        assertTrue(property.getMortgage());
    }

    @Test
    void getGroupSize() {
        assertEquals(2, property.getGroupSize());
    }

    @Test
    void getColor() {
        assertEquals("PURPLE", property.getColor());
    }

    @Test
    void getOwner() {
        street.buyProperty(game, p1);
        assertEquals(p1.getName(), street.getOwner());
    }

    @Test
    void getValue() {
        Street s = (Street) Config.tiles[9];
        assertEquals(120, s.getValue());
    }
}