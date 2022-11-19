package main.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import fileio.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.card.Card;
import main.card.minion.Minion;
import main.hero.Hero;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionOutput {
    protected String command = null;

    private Coordinates cardAttacker = null;

    private Coordinates cardAttacked = null;

    private Integer handIdx = null;

    private Integer affectedRow = null;

    private Integer playerIdx = null;

    private Integer x;
    private Integer y;

    private String error = null;

    private String gameEnded = null;


    /**
     * General output object
     */
    private Object output = null;

    /**
     * Constructor for Card Attack Command
     */
    public ActionOutput(final String command,
                        final Coordinates cardAttacker,
                        final Coordinates cardAttacked,
                        final String error) {
        this.command = command;
        this.cardAttacker = new Coordinates();
        this.cardAttacker.setX(cardAttacker.getX());
        this.cardAttacker.setY(cardAttacker.getY());

        this.cardAttacked = new Coordinates();
        this.cardAttacked.setX(cardAttacked.getX());
        this.cardAttacked.setY(cardAttacked.getY());

        this.error = error;
    }

    public ActionOutput(final String command,
                        final Coordinates cardAttacker,
                        final String error) {
        this.command = command;
        this.cardAttacker = new Coordinates();
        this.cardAttacker.setX(cardAttacker.getX());
        this.cardAttacker.setY(cardAttacker.getY());

        this.error = error;
    }

    public ActionOutput(final String command) {
        this.command = command;
    }

    public ActionOutput(final String command,
                        final ArrayList<Card> output,
                        final Integer playerIdx) {
        this.command = command;
        this.playerIdx = playerIdx;

        this.output = new ArrayList<Card>();
        for (Card card : output) {
            if (card instanceof Minion) {
                ((ArrayList) this.output).add(((Minion) card).clone());
            } else {
                ((ArrayList) this.output).add(card);
            }
        }
    }

    /**
     * Constructor for Minion output
     */
    public ActionOutput(final String command, final Minion output) {
        this.command = command;
        this.output = output.clone();
    }


    /**
     * Constructor for Using Environment Card
     */
    public ActionOutput(final String command,
                        final int handIdx,
                        final int affectedRow,
                        final String error) {
        this.command = command;
        this.handIdx = handIdx;
        this.affectedRow = affectedRow;
        this.error = error;
    }

    public ActionOutput(final String command, final Integer output) {
        this.command = command;
        this.output = output;
    }

    /** Constructor for get player mana command */
    public ActionOutput(final String command,
                        final Integer mana,
                        final Integer playerIdx) {
        this.command = command;
        this.output = mana;
        this.playerIdx = playerIdx;
    }

    public ActionOutput(final String command,
                        final Integer playerIdx,
                        final Hero output) {
        this.command = command;
        this.playerIdx = playerIdx;

        this.output =  output.clone();
    }

    /** Constructor for place card action */
    public ActionOutput(final String command,
                        final Integer handIdx,
                        final String error) {
        this.command = command;
        this.handIdx = handIdx;
        this.error = error;
    }

    public ActionOutput(final String command,
                        final String output,
                        final int x,
                        final int y) {
        this.command = command;
        this.x = x;
        this.y = y;
        this.output = output;
    }
    public ActionOutput(final String command,
                        final Integer x,
                        final Integer y,
                        final Minion output) {
        this.command = command;
        this.x = x;
        this.y = y;
        this.output = output.clone();
    }
    /**
     * Constructor for showing the cards on the table
     */
    public ActionOutput(final String command,  final ArrayList<?> output) {
        this.command = command;

        this.output = new ArrayList<>(output);
    }
}
