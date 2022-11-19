package main.card.enviroment;

import main.card.minion.Minion;
import main.game.Game;
import java.util.ArrayList;

public class Winterfell extends EnvironmentCard {
    public Winterfell(final String name,
                      final int mana,
                      final String description,
                      final ArrayList<String> colors) {
        super(name, mana, description, colors);
    }

    /**
     * Freeze all enemy minions from targeted row
     * @param game The game containing all the information
     * @param row The row card will affect
     */
    @Override
    public void useAbility(final Game game, final int row) {
        for (Minion minion : game.getBoard().get(row)) {
            minion.setFrozen(true);
        }
    }
}
