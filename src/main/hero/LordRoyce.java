package main.hero;

import main.card.minion.Minion;
import main.game.Game;
import java.util.ArrayList;

public class LordRoyce extends Hero {


    public LordRoyce(final String name,
                     final String description,
                     final ArrayList<String> colors,
                     final int mana) {
        super(name, description, colors, mana);
    }

    /**
     * Freeze the minion with the highest attack on the row.
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

        if (rowIdx == backRow || rowIdx == frontRow) {
            throw new Exception("Selected row does not belong to the enemy.");
        }

        // get the card with the highest attack
        Minion highestAttackCard = selectedRow.get(0);
        for (Minion card : selectedRow) {
            if (card.getAttackDamage() > highestAttackCard.getAttackDamage()) {
                highestAttackCard = card;
            }
        }
        highestAttackCard.setFrozen(true);

    }
}
