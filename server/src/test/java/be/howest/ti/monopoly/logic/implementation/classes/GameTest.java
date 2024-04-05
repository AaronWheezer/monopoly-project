package be.howest.ti.monopoly.logic.implementation.classes;

import be.howest.ti.monopoly.logic.implementation.BankAuction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.player.Turn;
import be.howest.ti.monopoly.logic.implementation.tiles.CardTile;
import be.howest.ti.monopoly.logic.implementation.tiles.Rentable;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import be.howest.ti.monopoly.logic.implementation.utils.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private Player p1;
    private Player p2;
    private Player p3;
    private GameManager gm;
    private Integer[] lastDiceRoll;
    private Rentable property = (Rentable) Config.tiles[1];
    private List<BankAuction> auctions = new ArrayList<>();
    private String directSale;
    private List<Turn> turns = new ArrayList<>();

    @BeforeEach
    void setUp() {
        gm = new GameManager();
        game = gm.createGame("test",2);
        gm.joinGame(game.getId(),"Aaron");
        gm.joinGame(game.getId(),"Bob");
        p1 = game.getPlayer("Aaron");
        p2 = game.getPlayer("Bob");
        Turn turn = new Turn(p1,game);
    }

    @Test
    void getNumberOfPlayers() {
        assertEquals(2, game.getNumberOfPlayers());
    }

    @Test
    void getPlayers() {
        assertTrue(game.getPlayers().contains(game.getPlayer("Aaron")));
        assertTrue(game.getPlayers().contains(game.getPlayer("Bob")));
    }

    @Test
    void getDirectSale() {
        p1.setCurrentTile(Config.tiles[1]);
        Config.tiles[1].onLand(p1, game);
        assertEquals(p1.getCurrentTile(), game.getDirectSale());
    }

    @Test
    void landOnCommunityTile() {
        CardTile c = (CardTile) Config.tiles[2];
        p1.setCurrentTile(c);
        c.onLand(p1, game);
        assertEquals("community chest", c.getType());
    }

    @Test
    void landOnChanceTile() {
        CardTile c2 = (CardTile)Config.tiles[7];
        p1.setCurrentTile(c2);
        c2.onLand(p1, game);
        assertEquals("chance", c2.getType());
    }

    @Test
    void getCanRoll() {
        assertTrue(game.getCanRoll());
    }

    @Test
    void getAvailableHouses() {
        assertEquals(32, game.getAvailableHouses());
    }

    @Test
    void getAvailableHotels() {
        assertEquals(12, game.getAvailableHotels());
    }

    @Test
    void newHouse() {
        if (game.getAvailableHouses() > 0) {
            game.newHouse();
            assertEquals(31, game.getAvailableHouses());
        }
    }

    @Test
    void newHotel() {
        if (game.getAvailableHotels() > 0) {
            game.newHotel();
            assertEquals(11, game.getAvailableHotels());
        }
    }

    @Test
    void setCurrentPlayer() {
        p1.setCurrentTile(Config.tiles[1]);
        assertEquals("Mediterranean", p1.getCurrentTile());
    }

    @Test
    void getCurrentPlayer() {
        assertEquals(p1.getName(), game.getCurrentPlayer());
    }

    @Test
    void getTurns() {
        Turn turn = new Turn(p1,game);
        turns.add(turn);
        assertEquals(1, turns.size());
    }

    @Test
    void addPlayer() {
        gm = new GameManager();
        game = gm.createGame("test",2);
        gm.joinGame(game.getId(),"Liam");
        assertEquals(1, game.getPlayers().size());
    }

    @Test
    void getPlayer() {
        assertTrue(this.game.getPlayers().contains(this.game.getPlayer("Aaron")));
    }

    @Test
    void getId() {
        assertEquals("test_000", game.getId());
    }

    @Test
    void getLastDiceRoll() {
        Integer[] dices = Dice.rollDices();
        game.rollDice("Aaron");
        this.lastDiceRoll = dices;
        assertArrayEquals(dices, lastDiceRoll);
    }

    @Test
    void setDirectSale() {
        game.setDirectSale(property.getName());
        assertEquals(property.getName(), game.getDirectSale());
    }

    @Test
    void createAuction() {
        BankAuction auction = new BankAuction(this.game, property, 100);
        this.directSale = null;
        auctions.add(auction);
        assertEquals(1, auctions.size());
    }

    @Test
    void removePlayer() {
        assertEquals(2, game.getPlayers().size());
        game.removePlayer(p1);
        assertEquals(1, game.getPlayers().size());
    }
}