package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.GameManager;
import be.howest.ti.monopoly.logic.implementation.player.Player;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import org.junit.jupiter.api.BeforeEach;

class NoActionTileTest {


    private Player player;
    private Game game;

    @BeforeEach
    void setUp() {
        GameManager gm = new GameManager();
        game = gm.createGame("groep", 4);
        player = game.getPlayer("Aaron");
        gm.joinGame(game.getId(), "Aaron");
        player.setCurrentTile(Config.tiles[0]);
    }
}