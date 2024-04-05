package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyCardTest {
    private Player p1;
    private Street street;
    private Game game;

    @BeforeEach
    void setUp() {
        GameManager gm = new GameManager();
        game = gm.createGame("groep", 4);
        gm.joinGame(game.getId(),"Aaron");
        p1 = game.getPlayer("Aaron");
        street = (Street) Config.tiles[3];
    }

    @Test
    void payPropertyTax() {
        assertEquals(1500, p1.getMoney());
        p1.setCurrentTile(street);
        street.buyProperty(game, p1);
        for (int i = 0; i < 6; i++) {
            street.upgradeProperty(game);
        }
        PropertyCard p = (PropertyCard) Config.chanceCards[10];
        p.execute(p1);
        assertEquals(1015, p1.getMoney());
    }
}