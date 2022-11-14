package main.cards.enviroment;

import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;
import java.util.Comparator;

public class HeartHound extends EnvironmentCard{
    public HeartHound(String name, int mana, String description, ArrayList<String> colors) {
        super(name, mana, description, colors);
    }

    /**
     * Steal enemy minion and add it to the mirrored row
     * @param game
     * @param row
     */
    @Override
    public void useAbility(Game game, int row) throws Exception {
        ArrayList<Minion> selectedRow = game.getBoard().get(row);

        ArrayList<Minion> mirroredRow = game.getBoard().get(3 - row);

        if (mirroredRow.size() == game.MAX_CARDS_ON_ROW)
            throw new Exception("Cannot steal enemy card since the player's row is full.");

        Minion highestHealthMinion = selectedRow.get(0);
        for (Minion minion : selectedRow) {
            if (minion.getHealth() > highestHealthMinion.getHealth())
                highestHealthMinion = minion;
        }
        Minion stolenMinion = highestHealthMinion;
        selectedRow.remove(stolenMinion);
        mirroredRow.add(stolenMinion);
        }
}
