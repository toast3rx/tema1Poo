package main.card.minion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import main.exception.HeroDiedException;
import main.exception.MinionCantAttackException;
import main.card.Card;
import main.game.Game;
import main.hero.Hero;

import java.util.ArrayList;


@Data
@JsonIgnoreProperties({"frozen", "usedTurn"})
@JsonPropertyOrder({"mana", "attackDamage", "health", "description", "colors", "name"})
public abstract class Minion extends Card implements Cloneable {
    protected int attackDamage;

    protected boolean usedTurn;

    protected boolean isFrozen;

    protected int health;

    public Minion(final Minion minion) {
        super(minion.getName(), minion.getMana(), minion.getDescription(), minion.getColors());
        this.attackDamage = minion.getAttackDamage();
        this.health = minion.getHealth();
        this.usedTurn = minion.isUsedTurn();
        this.isFrozen = minion.isFrozen();
    }

    public Minion(final String name,
                  final int mana,
                  final String description,
                  final ArrayList<String> colors,
                  final int attackDamage,
                  final int health) {
        super(name, mana, description, colors);
        this.attackDamage = attackDamage;
        this.isFrozen = false;
        this.health = health;
    }

    /**
     * Attack the enemy hero
     *
     * @param game The game containing all the information
     * @throws HeroDiedException         If the enemy hero health is equal or lower than 0
     * @throws MinionCantAttackException If the minion can't attack
     */
    public void attackHero(final Game game) throws HeroDiedException, MinionCantAttackException {
        if (isFrozen) {
            throw new MinionCantAttackException("Attacker card is frozen.");
        }

        if (usedTurn) {
            throw new MinionCantAttackException("Attacker card has already attacked this turn.");
        }

        int frontRow = game.getPlayerTurn() == 1 ? 2 : 1;

        boolean existsTank = false;

        for (Minion minion : game.getBoard().get(Game.ROW_NUMBERS - 1 - frontRow)) {
            if (minion instanceof Tank) {
                existsTank = true;
                break;
            }
        }

        if (existsTank) {
            throw new MinionCantAttackException("Attacked card is not of type 'Tank'.");
        }

        Hero enemyHero = game.getPlayerTurn() == 1
                ? game.getPlayerTwo().getDeck().getHero()
                : game.getPlayerOne().getDeck().getHero();

        enemyHero.setHealth(enemyHero.getHealth() - attackDamage);

        usedTurn = true;

        if (enemyHero.getHealth() <= 0) {
            String playerTurn = game.getPlayerTurn() == 1 ? "one" : "two";
            throw new HeroDiedException("Player " + playerTurn + " killed the enemy hero.");
        }
    }

    /**
     * Attack an enemy minion
     *
     * @param game The game containing all the information
     * @param x    The x coordinate of the enemy minion
     * @param y    The y coordinate of the enemy minion
     * @throws MinionCantAttackException If the minion can't attack
     */
    public void attack(final Game game,
                       final int x,
                       final int y) throws MinionCantAttackException {

        try {
            canAttack(game, x, y);
        } catch (Exception exception) {
            throw new MinionCantAttackException(exception.getMessage());

        }

        Minion attackedMinion = game.getBoard().get(x).get(y);
        attackedMinion.setHealth(attackedMinion.getHealth() - this.getAttackDamage());

        if (attackedMinion.getHealth() <= 0) {
            game.getBoard().get(x).remove(y);
        }

        usedTurn = true;
    }

    /**
     * Check if the minion can attack another minion
     *
     * @param game The game containing all the information
     * @param x    The x coordinate of the enemy minion
     * @param y    The y coordinate of the enemy minion
     * @throws MinionCantAttackException If the minion can't attack
     */
    protected void canAttack(final Game game,
                             final int x,
                             final int y) throws MinionCantAttackException {

        if (isFrozen) {
            throw new MinionCantAttackException("Attacker card is frozen.");
        }

        if (usedTurn) {
            throw new MinionCantAttackException("Attacker card has already attacked this turn.");
        }

        int backRow = game.getPlayerTurn() == 1
                ? Game.PLAYER_ONE_BACK_ROW
                : Game.PLAYER_TWO_BACK_ROW;
        int frontRow = game.getPlayerTurn() == 1
                ? Game.PLAYER_ONE_FRONT_ROW
                : Game.PLAYER_TWO_FRONT_ROW;

        if (x == backRow || x == frontRow) {
            throw new MinionCantAttackException("Attacked card does not belong to the enemy.");

        }

        boolean existsTank = false;


        for (Minion minion : game.getBoard().get(Game.ROW_NUMBERS - 1 - frontRow)) {
            if (minion instanceof Tank) {
                existsTank = true;
                break;
            }
        }

        if (existsTank) {
            if (!(game.getBoard().get(x).get(y) instanceof Tank)) {
                throw new MinionCantAttackException("Attacked card is not of type 'Tank'.");
            }
        }
    }

    /**
     * Set attack damage for the minion
     * @param attackDamage value of attack damage
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = Math.max(attackDamage, 0);
    }

    /**
     * Clone the minion
     * @return the cloned minion
     */
    @Override
    public Minion clone() {
        try {
            return (Minion) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
