package be.howest.ti.monopoly.logic.implementation.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    private Move move;

    @BeforeEach
    void setUp() {
        move = new Move("Mediterranean", "street");
    }

    @Test
    void getTitle() {
        assertEquals("Mediterranean", move.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("street", move.getDescription());
    }
}