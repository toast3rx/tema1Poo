package main.hero;

import main.card.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public class GeneralKocioraw extends Hero {


    public GeneralKocioraw(final String name,
                           final String description,
                           final ArrayList<String> colors,
                           final int mana) {
        super(name, description, colors, mana);
    }

    //Blood Thirst: +1 atac pentru toate cărțile de pe rând.

    /**
     * Give +1 attack to all minions on the same row.
     * @param game THe game containing all the information.
     * @param rowIdx The row to apply the effect.
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
            minion.setAttackDamage(minion.getAttackDamage() + 1);
        }
    }
}
