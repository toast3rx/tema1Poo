package main.cards.minion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import main.Exceptions.HeroDiedException;
import main.cards.Card;
import main.cards.minion.SpecialMinion.Disciple;
import main.game.Game;
import main.heroes.Hero;

import java.util.ArrayList;


@Data
@JsonIgnoreProperties({"frozen", "usedTurn"})
@JsonPropertyOrder({"mana", "attackDamage", "health", "description", "colors", "name"})
public class Minion extends Card {
    protected int attackDamage;

    protected boolean usedTurn;

    protected boolean isFrozen;

    protected int health;

    public Minion(Minion minion) {
        super(minion.getName(), minion.getMana(), minion.getDescription(), minion.getColors());
        this.attackDamage = minion.getAttackDamage();
        this.health = minion.getHealth();
        this.usedTurn = minion.isUsedTurn();
        this.isFrozen = minion.isFrozen();
    }

    public Minion(String name, int mana, String description, ArrayList<String> colors, int attackDamage, int health) {
        super(name, mana, description, colors);
        this.attackDamage = attackDamage;
        this.isFrozen = false;
        this.health = health;
    }

    public void attackHero(Game game) throws HeroDiedException, Exception {
        if (isFrozen)
            throw new Exception("Attacker card is frozen.");

        if (usedTurn) {
            throw new Exception("Attacker card has already attacked this turn.");
        }

        int backRow = game.getPlayerTurn() == 1 ? 3 : 0;
        int frontRow = game.getPlayerTurn() == 1 ? 2 : 1;


        boolean existsTank = false;

        for (Minion minion : game.getBoard().get(3 - backRow)) {
            if (minion instanceof Tank) {
                existsTank = true;
                break;
            }
        }

        for (Minion minion : game.getBoard().get(3 - frontRow)) {
            if (minion instanceof Tank) {
                existsTank = true;
                break;
            }
        }

//        if (existsTank && !(game.getBoard().get(x).get(y) instanceof Tank)) {
//            throw new Exception("Attacked card is not of type 'Tank'.");
//        }

        if (existsTank) {
                throw new Exception("Attacked card is not of type 'Tank'.");

        }

        Hero enemyHero = game.getPlayerTurn() == 1 ? game.getPlayerTwo().getDeck().getHero() : game.getPlayerOne().getDeck().getHero();

        enemyHero.setHealth(enemyHero.getHealth() - attackDamage);

        usedTurn = true;

        if (enemyHero.getHealth() <= 0) {
            throw new HeroDiedException("Player " + game.getPlayerTurn() + " killed the enemy hero.");
        }
    }

    public void attack(Game game, int x, int y) throws Exception {


        try {
            canAttack(game, x, y);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());

        }

        Minion attackedMinion = game.getBoard().get(x).get(y);
        attackedMinion.setHealth(attackedMinion.getHealth() - this.getAttackDamage());

        if (attackedMinion.getHealth() <= 0)
            game.getBoard().get(x).remove(y);

        usedTurn = true;
    }


    protected void canAttack(Game game, int x, int y) throws Exception {
        if (isFrozen)
            throw new Exception("Attacker card is frozen.");

        if (usedTurn) {
            throw new Exception("Attacker card has already attacked this turn.");
        }

        int backRow = game.getPlayerTurn() == 1 ? 3 : 0;
        int frontRow = game.getPlayerTurn() == 1 ? 2 : 1;

            if (x == backRow || x == frontRow) {
                throw new Exception("Attacked card does not belong to the enemy.");

        }


        boolean existsTank = false;

        for (Minion minion : game.getBoard().get(3 - backRow)) {
            if (minion instanceof Tank) {
                existsTank = true;
                break;
            }
        }

        for (Minion minion : game.getBoard().get(3 - frontRow)) {
            if (minion instanceof Tank) {
                existsTank = true;
                break;
            }
        }

//        if (existsTank && !(game.getBoard().get(x).get(y) instanceof Tank)) {
//            throw new Exception("Attacked card is not of type 'Tank'.");
//        }

        if (existsTank) {
            if (!(game.getBoard().get(x).get(y) instanceof Tank)) {
//                System.out.println("NOT A TANK");
//                System.out.println("X: " + x + " Y: " + y);
//                System.out.println("Card name: " + game.getBoard().get(x).get(y).getName());
                throw new Exception("Attacked card is not of type 'Tank'.");

            }
        }
    }


    public void setAttackDamage(int attackDamage) {
        attackDamage = Math.max(attackDamage, 0);

        this.attackDamage = attackDamage;
    }
}
