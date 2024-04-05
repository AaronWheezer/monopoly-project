package be.howest.ti.monopoly.logic.implementation.utils;
import java.util.*;

public class Dice {
    public static Integer[] rollDices() {
        Integer[] dices = new Integer[Config.dicesCount];
        for (int i = 0; i < Config.dicesCount; i++) {
            dices[i] = Utils.random.nextInt(Config.diceMaxValue) + 1;
        }
        return dices;
    }
}
