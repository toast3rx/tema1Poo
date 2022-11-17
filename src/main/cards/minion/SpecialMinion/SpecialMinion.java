package main.cards.minion.SpecialMinion;

import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public abstract class SpecialMinion extends Minion {

    public SpecialMinion(final String name,
                         final int mana,
                         final String description,
                         final ArrayList<String> colors,
                         final int attackDamage,
                         final int health) {
        super(name, mana, description, colors, attackDamage, health);
    }

    /**
     * Use special ability of the minion.
     * @param game The game containing all the information.
     * @param x The x coordinate of the target.
     * @param y The y coordinate of the target.
     * @throws Exception If the minion can't use its special ability.
     */
    public abstract void useAbility(Game game, int x, int y) throws Exception;


}
