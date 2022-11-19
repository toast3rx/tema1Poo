package main.card.minion.SpecialMinion;

import main.card.minion.FrontRow;
import main.card.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public class TheRipper extends SpecialMinion implements FrontRow {
    public TheRipper(final String name,
                     final int mana,
                     final String description,
                     final ArrayList<String> colors,
                     final int attackDamage,
                     final int health) {
        super(name, mana, description, colors, attackDamage, health);
    }

    /**
     * Reduce the attack damage of the enemy minion by 2.
     * @param game The game containing all the information.
     * @param x The x coordinate of the target.
     * @param y The y coordinate of the target.
     * @throws Exception If the minion can't use its ability.
     */
    @Override
    public void useAbility(final Game game, final int x, final int y) throws Exception {
        try {
            canAttack(game, x, y);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }

        Minion target = game.getBoard().get(x).get(y);
        target.setAttackDamage(target.getAttackDamage() - 2);
    }
}
