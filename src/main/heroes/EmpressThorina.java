package main.heroes;

import lombok.Data;
import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

@Data
public class EmpressThorina extends Hero{


    public EmpressThorina(String name, String description, ArrayList<String> colors, int mana) {
        super(name, description, colors, mana);
    }

    //   Low Blow: distruge cartea cu cea mai mare viață de pe rând.
    @Override
    public void useAbility(Game game, int rowIdx) throws Exception {
        ArrayList<Minion> selectedRow = game.getBoard().get(rowIdx);

        int backRow = game.getPlayerTurn() == 1 ? 3 : 0;
        int frontRow = game.getPlayerTurn() == 1 ? 2 : 1;

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
