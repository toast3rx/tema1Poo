package main.cards.minion.SpecialMinion;

import main.cards.minion.FrontRow;
import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public class TheRipper extends SpecialMinion implements FrontRow {
    public TheRipper(String name, int mana, String description, ArrayList<String> colors, int attackDamage, int health) {
        super(name, mana, description, colors, attackDamage, health);
    }

    @Override
    public void useAbility(Game game, int x, int y) throws Exception{

        try {
            canAttack(game, x, y);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }

        Minion target = game.getBoard().get(x).get(y);
        target.setAttackDamage(target.getAttackDamage() - 2);
    }
}
