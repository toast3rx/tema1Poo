package main.hero;

import lombok.Data;
import main.card.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

@Data
public class EmpressThorina extends Hero {


    public EmpressThorina(final String name,
                          final String description,
                          final ArrayList<String> colors,
                          final int mana) {
        super(name, description, colors, mana);
    }


    /**
     * Destroy the card with the highest health on the row.
     * @param game Game containing all the information.
     * @param rowIdx Row index.
     * @throws Exception If the ability cannot be applied.
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

        Minion highestHealthCard = selectedRow.get(0);

        for (Minion minion : selectedRow) {
            if (minion.getHealth() > highestHealthCard.getHealth()) {
                highestHealthCard = minion;
            }
        }

        selectedRow.remove(highestHealthCard);
    }
}
