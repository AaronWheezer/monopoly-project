package be.howest.ti.monopoly.logic.implementation.player;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.utils.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TurnTest {

    private Game game;
    private Turn turn;
    private Integer[] dices = Dice.rollDices();

    @BeforeEach
    void setUp() {
        GameManager gm = new GameManager();
        game = gm.createGame("groep", 4);
        gm.joinGame(game.getId(),"Aaron");
        Player p = game.getPlayer("Aaron");
        turn = new Turn(p, game);
    }

    @Test
    void getMoves() {
        turn.move(dices, game);
        assertNotNull(turn.getMoves());
    }
}