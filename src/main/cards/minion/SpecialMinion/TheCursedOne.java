package main.cards.minion.SpecialMinion;

import main.cards.minion.BackRow;
import main.cards.minion.Minion;
import main.game.Game;
import java.util.ArrayList;

public class TheCursedOne extends SpecialMinion implements BackRow {
    public TheCursedOne(final String name,
                        final int mana,
                        final String description,
                        final ArrayList<String> colors,
                        final int attackDamage,
                        final int health) {
        super(name, mana, description, colors, attackDamage, health);
    }

    /**
     * Swap targeted minion's attack and health.
     * @param game The game containing all the information.
     * @param x The x coordinate of the target.
     * @param y The y coordinate of the target.
     * @throws Exception If the ability can't be used.
     */
    @Override
    public void useAbility(final Game game, final int x, final int y) throws Exception {

        try {
            canAttack(game, x, y);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }

        Minion target = game.getBoard().get(x).get(y);

        int attack = target.getAttackDamage();
        target.setAttackDamage(target.getHealth());
        target.setHealth(attack);

        if (target.getHealth() <= 0) {
            game.getBoard().get(x).remove(y);
        }

        usedTurn = true;
    }
}
