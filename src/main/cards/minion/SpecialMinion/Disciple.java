package main.cards.minion.SpecialMinion;

import main.cards.minion.BackRow;
import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

public class Disciple extends SpecialMinion implements BackRow {
    public Disciple(final String name,
                    final int mana,
                    final String description,
                    final ArrayList<String> colors,
                    final int attackDamage,
                    final int health) {
        super(name, mana, description, colors, attackDamage, health);
    }

    /**
     * Add +2 health to a friendly minion
     * @param game The game containing all the information
     * @param x The x coordinate of the minion
     * @param y The y coordinate of the minion
     * @throws Exception If the ability cannot be used
     */
    @Override
    public void useAbility(final Game game, final int x, final int y) throws Exception {

        if (isFrozen) {
            throw new Exception("Attacker card is frozen.");
        }

        if (usedTurn) {
            throw new Exception("Attacker card has already attacked this turn.");
        }

        int backRow = game.getPlayerTurn() == 1
                ? Game.PLAYER_ONE_BACK_ROW
                : Game.PLAYER_TWO_BACK_ROW;
        int frontRow = game.getPlayerTurn() == 1
                ? Game.PLAYER_ONE_FRONT_ROW
                : Game.PLAYER_TWO_FRONT_ROW;

        if (x != backRow && x != frontRow) {
            throw new Exception("Attacked card does not belong to the current player.");
        }

            Minion target = game.getBoard().get(x).get(y);

            target.setHealth(target.getHealth() + 2);

            setUsedTurn(true);

        }

}
