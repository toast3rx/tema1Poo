package main.hero;

import main.card.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public class KingMudface extends Hero {


    public KingMudface(final String name,
                       final String description,
                       final ArrayList<String> colors,
                       final int mana) {
        super(name, description, colors, mana);
    }

    /**
     * Give +1 health to all minions on the same row.
     * @param game The game containing all the information.
     * @param rowIdx The row to use the ability on.
     * @throws Exception If the ability cannot be used.
     */
    @Override
    public void useAbility(final Game game, final int rowIdx) throws Exception {
        ArrayList<Minion> selectedRow = game.getBoard().get(rowIdx);

        int backRow = game.getPlayerTurn() == 1
                ? Game.PLAYER_ONE_BACK_ROW
                : Game.PLAYER_TWO_BACK_ROW;
        int frontRow = game.getPlayerTurn() == 1
                ? Game.PLAYER_ONE_FRONT_ROW
                : Game.PLAYER_TWO_FRONT_ROW;

        if (rowIdx != backRow && rowIdx != frontRow) {
            throw new Exception("Selected row does not belong to the current player.");
        }

        for (Minion minion : selectedRow) {
            minion.setHealth(minion.getHealth() + 1);
        }
    }
}
