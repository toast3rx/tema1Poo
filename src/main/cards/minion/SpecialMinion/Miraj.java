package main.cards.minion.SpecialMinion;

import main.cards.minion.FrontRow;
import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public class Miraj extends SpecialMinion implements FrontRow {


    public Miraj(final String name,
                 final int mana,
                 final String description,
                 final ArrayList<String> colors,
                 final int attackDamage,
                 final int health) {
        super(name, mana, description, colors, attackDamage, health);
    }

    /**
     * Swap the health of the targeted minion with the health of this minion
     * @param game The game containing all the information
     * @param x The x coordinate of the minion
     * @param y The y coordinate of the minion
     * @throws Exception If the ability cannot be used
     */
    @Override
    public void useAbility(final Game game, final int x, final int y) throws Exception {

        try {
            canAttack(game, x, y);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }

        Minion target = game.getBoard().get(x).get(y);

        int oldHp = this.getHealth();
        this.setHealth(target.getHealth());
        target.setHealth(oldHp);

        usedTurn = true;
    }
}
