package main.cards.enviroment;

import main.cards.Card;
import main.cards.minion.Minion;
import main.game.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Firestorm extends EnvironmentCard{
    public Firestorm(String name, int mana, String description, ArrayList<String> colors) {
        super(name, mana, description, colors);
    }

    @Override
    public void useAbility(Game game, int row) {
        ArrayList<Minion> rowBoard = game.getBoard().get(row);
        for (Minion minion : rowBoard) {
            minion.setHealth(minion.getHealth() - 1);
        }

        rowBoard.removeIf(minion -> minion.getHealth() <= 0);
    }
}
