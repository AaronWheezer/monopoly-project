package be.howest.ti.monopoly.logic.implementation.player;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Turn {

    private final Player player;
    private final Game game;
    private final List<Move> moves = new ArrayList<>();

    public Turn(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public void move(Integer[] dices, Game game) {
        int totalRoll = 0;
        for (int dice:  dices) {
            totalRoll += dice;
        }
        if(!player.getJailed()){
            String tile = this.player.getCurrentTile();
            Tile newTile = Utils.getNextTile(tile, totalRoll);
            this.player.setCurrentTile(newTile);
            String title = newTile.getName();
            String description = newTile.onLand(this.player, game);
            Move move = new Move(title,description);
            moves.add(move);
            return;
        }
        Move move = new Move("Jail","Is still jailed");
        moves.add(move);
    }

    public List<Move> getMoves() {
        return moves;
    }

    public String getPlayer() {
        return player.getName();
    }
}

