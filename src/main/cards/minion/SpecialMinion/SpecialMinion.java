package main.cards.minion.SpecialMinion;

import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public abstract class SpecialMinion extends Minion {

    public SpecialMinion(String name, int mana, String description, ArrayList<String> colors, int attackDamage, int health) {
        super(name, mana, description, colors, attackDamage, health);
    }

    public abstract void useAbility(Game game, int x, int y) throws Exception;


}
