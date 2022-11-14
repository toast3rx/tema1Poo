package main.heroes;

import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public class GeneralKocioraw extends Hero{


    public GeneralKocioraw(String name, String description, ArrayList<String> colors, int mana) {
        super(name, description, colors, mana);
    }

    //Blood Thirst: +1 atac pentru toate cărțile de pe rând.
    @Override
    public void useAbility(Game game, int rowIdx ) throws Exception {
        ArrayList<Minion> selectedRow = game.getBoard().get(rowIdx);

        int backRow = game.getPlayerTurn() == 1 ? 3 : 0;
        int frontRow = game.getPlayerTurn() == 1 ? 2 : 1;

        if (rowIdx != backRow && rowIdx != frontRow) {
            throw new Exception("Selected row does not belong to the current player.");
        }

        for (Minion minion : selectedRow)
            minion.setAttackDamage(minion.getAttackDamage() + 1);
    }
}
