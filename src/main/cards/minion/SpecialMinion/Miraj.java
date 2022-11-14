package main.cards.minion.SpecialMinion;

import main.cards.minion.FrontRow;
import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public class Miraj extends SpecialMinion implements FrontRow {


    public Miraj(String name, int mana, String description, ArrayList<String> colors, int attackDamage, int health) {
        super(name, mana, description, colors, attackDamage, health);
    }

    /**
     * Swap this card health with the targeted minion
     * @param
     */
    @Override
    public void useAbility(Game game, int x, int y) throws Exception {

        try {
            canAttack(game, x, y);
        } catch (Exception exception) {
//            System.out.println("Error thrown from Miraj");

            throw new Exception(exception.getMessage());
        }

        Minion target = game.getBoard().get(x).get(y);

        int oldHp = this.getHealth();
        this.setHealth(target.getHealth());
        target.setHealth(oldHp);

        usedTurn = true;
    }
}
