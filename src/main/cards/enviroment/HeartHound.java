package main.cards.enviroment;

import main.cards.minion.Minion;
import main.game.Game;
import java.util.ArrayList;

public class HeartHound extends EnvironmentCard {
    public HeartHound(final String name,
                      final int mana,
                      final String description,
                      final ArrayList<String> colors) {
        super(name, mana, description, colors);
    }

    /**
     * Steal an enemy minion from targeted row and add it to mirrored row
     * @param game The game containing all the information
     * @param row The row card will affect
     * @throws Exception If the mirrored row is full
     */
    @Override
    public void useAbility(final Game game, final int row) throws Exception {
        ArrayList<Minion> selectedRow = game.getBoard().get(row);

        ArrayList<Minion> mirroredRow = game.getBoard().get(game.ROW_NUMBERS - 1 - row);

        if (mirroredRow.size() == game.MAX_CARDS_ON_ROW) {
            throw new Exception("Cannot steal enemy card since the player's row is full.");
        }

        Minion highestHealthMinion = selectedRow.get(0);
        for (Minion minion : selectedRow) {
            if (minion.getHealth() > highestHealthMinion.getHealth()) {
                highestHealthMinion = minion;
            }
        }
        Minion stolenMinion = highestHealthMinion;
        selectedRow.remove(stolenMinion);
        mirroredRow.add(stolenMinion);
        }
}
