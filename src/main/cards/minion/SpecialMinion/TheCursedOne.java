package main.cards.minion.SpecialMinion;

import main.cards.Card;
import main.cards.minion.BackRow;
import main.cards.minion.Minion;
import main.game.Game;
import main.game.PlayerInfo;

import java.util.ArrayList;

public class TheCursedOne extends SpecialMinion implements BackRow {
    public TheCursedOne(String name, int mana, String description, ArrayList<String> colors, int attackDamage, int health) {
        super(name, mana, description, colors, attackDamage, health);
    }

    /**
     * Swap targeted minion's health with attack damage
     * @param
     */
    @Override
    public void useAbility(Game game, int x, int y) throws Exception {

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
