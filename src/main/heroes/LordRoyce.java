package main.heroes;

import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public class LordRoyce extends Hero {


    public LordRoyce(String name, String description, ArrayList<String> colors, int mana) {
        super(name, description, colors, mana);
    }

    // Sub-Zero: îngheață cartea cu cel mai mare atac de pe rând.
    @Override
    public void useAbility(Game game, int rowIdx) throws Exception {

        ArrayList<Minion> selectedRow = game.getBoard().get(rowIdx);

        int backRow = game.getPlayerTurn() == 1 ? 3 : 0;
        int frontRow = game.getPlayerTurn() == 1 ? 2 : 1;

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
