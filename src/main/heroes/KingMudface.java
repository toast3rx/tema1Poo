package main.heroes;

import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public class KingMudface extends Hero{


    public KingMudface(String name, String description, ArrayList<String> colors, int mana) {
        super(name, description, colors, mana);
    }

    //  Earth Born: +1 viață pentru toate cărțile de pe rând.
    @Override
    public void useAbility(Game game, int rowIdx) throws Exception{
        ArrayList<Minion> selectedRow = game.getBoard().get(rowIdx);

        int backRow = game.getPlayerTurn() == 1 ? 3 : 0;
        int frontRow = game.getPlayerTurn() == 1 ? 2 : 1;

        if (rowIdx != backRow && rowIdx != frontRow) {
            throw new Exception("Selected row does not belong to the current player.");
        }

        for (Minion minion : selectedRow) {
            minion.setHealth(minion.getHealth() + 1);
        }
    }
}
