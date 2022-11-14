package main.cards.enviroment;

import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public class Winterfell extends EnvironmentCard{
    public Winterfell(String name, int mana, String description, ArrayList<String> colors) {
        super(name, mana, description, colors);
    }

    /**
     * All cards from row are frozen
     * @param game
     * @param row
     */
    @Override
    public void useAbility(Game game, int row) {
        for (Minion minion : game.getBoard().get(row)) {
            minion.setFrozen(true);
        }
    }
}
