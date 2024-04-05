package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreetTest {
    private Game game;
    private Player p;
    private Player p2;
    private Player p3;
    private Street street;
    private Street street2;
    private Street street3;

    @BeforeEach
    void setUp() {
        GameManager gm = new GameManager();
        game = gm.createGame("test", 3);
        gm.joinGame(game.getId(),"Aaron");
        gm.joinGame(game.getId(),"Bob");
        gm.joinGame(game.getId(),"Jozef");
        p = game.getPlayer("Aaron");
        p2 = game.getPlayer("Bob");
        p3 = game.getPlayer("Jozef");
        street = (Street) Config.tiles[1];
        street2 = (Street) Config.tiles[3];
        street3 = (Street) Config.tiles[6];
        p.setCurrentTile(street);
        p2.setCurrentTile(street2);
        p3.setCurrentTile(street3);
    }

    @Test
    void getRent() {
        street3.buyProperty(game, p3);
        assertEquals(6, street3.getRent());
    }

    @Test
    void getRentWithOneHouse() {
        street.buyProperty(game, p);
        street.upgradeProperty(game);
        assertEquals(10, street.getRentWithOneHouse());
    }

    @Test
    void getRentWithTwoHouses() {
        street.buyProperty(game, p);
        street.upgradeProperty(game);
        street.upgradeProperty(game);
        assertEquals(30, street.getRentWithTwoHouses());
    }

    @Test
    void getRentWithThreeHouses() {
        street.buyProperty(game, p);
        street.upgradeProperty(game);
        street.upgradeProperty(game);
        street.upgradeProperty(game);
        assertEquals(90, street.getRentWithThreeHouses());
    }

    @Test
    void getRentWithFourHouses() {
        street.buyProperty(game, p);
        street.upgradeProperty(game);
        street.upgradeProperty(game);
        street.upgradeProperty(game);
        street.upgradeProperty(game);
        assertEquals(160, street.getRentWithFourHouses());
    }

    @Test
    void getRentWithHotel() {
        street.buyProperty(game, p);
        street.upgradeProperty(game);
        street.upgradeProperty(game);
        street.upgradeProperty(game);
        street.upgradeProperty(game);
        street.upgradeProperty(game);
        assertEquals(250, street.getRentWithHotel());
    }

    @Test
    void getHousePrice() {
        assertEquals(50, street.getHousePrice());
    }

    @Test
    void getStreetColor() {
        assertEquals("PURPLE", street.getStreetColor());
    }

    @Test
    void getValue() {
        street3.buyProperty(game, p3);
        assertEquals(100, street3.getValue());
    }

    @Test
    void getHouses() {
        street2.buyProperty(game, p2);
        street2.upgradeProperty(game);
        street2.upgradeProperty(game);
        street2.upgradeProperty(game);
        street2.upgradeProperty(game);
        assertEquals(4, street2.getHouses());
    }

    @Test
    void getHotels() {
        street2.buyProperty(game, p2);
        street2.upgradeProperty(game);
        street2.upgradeProperty(game);
        street2.upgradeProperty(game);
        street2.upgradeProperty(game);
        street2.upgradeProperty(game);
        assertEquals(1, street2.getHotels());
    }
}