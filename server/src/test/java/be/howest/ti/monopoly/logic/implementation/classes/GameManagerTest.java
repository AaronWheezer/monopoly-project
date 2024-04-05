package be.howest.ti.monopoly.logic.implementation.classes;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {
    private  GameManager gm;
    @BeforeEach
    void setUpClass() {
        gm = new GameManager();
    }

    @Test
    void createGame() {
        gm.createGame("", 3);

        assertFalse(gm.getGames().isEmpty());
    }

    @Test
    void getGame() {
        Game createdGame = gm.createGame("game", 3);
        Game game = gm.getGame(createdGame.getId());
        assertNotNull(game);
    }

    @Test
    void getGames() {
        Game game1 = gm.createGame("", 3);
        Set<Game> games = new HashSet<>();
        games.add(game1);
        assertEquals(games,gm.getGames());
    }

    @Test
    void getGamesEmpty() {
        assertTrue(gm.getGames().isEmpty());
    }

    @Test
    void getGameNotExist() {
        assertNull(gm.getGame("game"));
    }


    @Test
    void getGamesFilterNumberOfPlayers() {
        Game game1 = gm.createGame("", 3);
        Game game2 = gm.createGame("", 3);
        Game game3 = gm.createGame("", 5);
        Set<Game> games = new HashSet<>();
        games.add(game1);
        games.add(game2);
        games.add(game3);

        assertEquals(games,gm.getGames());
        //todo: add filter for number of players
    }

}