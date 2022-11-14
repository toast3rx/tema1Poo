package main.cards.minion.SpecialMinion;

import main.cards.minion.BackRow;
import main.cards.minion.Minion;
import main.cards.minion.Tank;
import main.game.Game;

import java.util.ArrayList;

public class Disciple extends SpecialMinion implements BackRow {
    public Disciple(String name, int mana, String description, ArrayList<String> colors, int attackDamage, int health) {
        super(name, mana, description, colors, attackDamage, health);
    }

    /**
     * Add +2 health to a friendly minion
     *
     * @param
     */
    @Override
    public void useAbility(Game game, int x, int y) throws Exception {

        if (isFrozen)
            throw new Exception("Attacker card is frozen.");

        if (usedTurn) {
            throw new Exception("Attacker card has already attacked this turn.");
        }

        int backRow = game.getPlayerTurn() == 1 ? 3 : 0;
        int frontRow = game.getPlayerTurn() == 1 ? 2 : 1;

        if (x != backRow && x != frontRow) {
            throw new Exception("Attacked card does not belong to the current player.");
        }


        boolean existsTank = false;

        for (Minion minion : game.getBoard().get(3 - backRow)) {
            if (minion instanceof Tank) {
                existsTank = true;
                break;
            }
        }

        for (Minion minion : game.getBoard().get(3 - frontRow)) {
            if (minion instanceof Tank) {
                existsTank = true;
                break;
            }
        }

//        if (existsTank && !(game.getBoard().get(x).get(y) instanceof Tank)) {
//            throw new Exception("Attacked card is not of type 'Tank'.");
//        }

        if (existsTank) {
            if (!(game.getBoard().get(x).get(y) instanceof Tank)) {
//                System.out.println("NOT A TANK");
//                System.out.println("X: " + x + " Y: " + y);
//                System.out.println("Card name: " + game.getBoard().get(x).get(y).getName());
                throw new Exception("Attacked card is not of type 'Tank'.");

            }

            Minion target = game.getBoard().get(x).get(y);

            target.setHealth(target.getHealth() + 2);

            setUsedTurn(true);

        }
    }
}
