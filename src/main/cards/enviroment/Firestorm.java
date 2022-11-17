package main.cards.enviroment;

import main.cards.minion.Minion;
import main.game.Game;
import java.util.ArrayList;

public class Firestorm extends EnvironmentCard {
    public Firestorm(final String name,
                     final int mana,
                     final String description,
                     final ArrayList<String> colors) {
        super(name, mana, description, colors);
    }

    /**
     * Reduce all minions' health by 1 from the targeted row
     * @param game The game containing all the information
     * @param row The row card will affect
     */
    @Override
    public void useAbility(final Game game, final int row) {
        ArrayList<Minion> rowBoard = game.getBoard().get(row);
        for (Minion minion : rowBoard) {
            minion.setHealth(minion.getHealth() - 1);
        }

        rowBoard.removeIf(minion -> minion.getHealth() <= 0);
    }
}
