package main.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import fileio.Coordinates;
import lombok.*;
import main.cards.Card;
import main.cards.minion.Minion;
import main.heroes.EmpressThorina;
import main.heroes.Hero;

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
    public ActionOutput(String command, Coordinates cardAttacker, Coordinates cardAttacked, String error) {
        this.command = command;
        this.cardAttacker = new Coordinates();
        this.cardAttacker.setX(cardAttacker.getX());
        this.cardAttacker.setY(cardAttacker.getY());

        this.cardAttacked = new Coordinates();
        this.cardAttacked.setX(cardAttacked.getX());
        this.cardAttacked.setY(cardAttacked.getY());

        this.error = error;
    }

    public ActionOutput(String command, Coordinates cardAttacker, String error) {
        this.command = command;
        this.cardAttacker = new Coordinates();
        this.cardAttacker.setX(cardAttacker.getX());
        this.cardAttacker.setY(cardAttacker.getY());

        this.error = error;
    }

    public ActionOutput(String command) {
        this.command = command;
    }

    public ActionOutput(String command, ArrayList<Card> output, Integer playerIdx) {
        this.command = command;
        this.playerIdx = playerIdx;

        this.output = new ArrayList<Card>();
        for (Card card : output) {
            if (card instanceof Minion) {
                ((ArrayList) this.output).add(new Minion((Minion) card));
            } else {
                ((ArrayList) this.output).add(card);
            }
        }
    }

    /**
     * Constructor for Minion output
     */
    public ActionOutput(String command, Minion output) {
        this.command = command;
        this.output = new Minion(output);
    }

//    public ActionOutput(ActionOutput actionOutput) {
//        this.command = actionOutput.getCommand();
//    }


    /**
     * Constructor for Using Environment Card
     */
    public ActionOutput(String command, int handIdx, int affectedRow, String error) {
        this.command = command;
        this.handIdx = handIdx;
        this.affectedRow = affectedRow;
        this.error = error;
    }

    public ActionOutput(String command, Integer output) {
        this.command = command;
        this.output = output;
    }

    /** Constructor for get player mana command */
    public ActionOutput(String command, Integer mana, Integer playerIdx) {
        this.command = command;
        this.output = mana;
        this.playerIdx = playerIdx;
    }

    public ActionOutput(String command, Integer playerIdx, Hero output) {
        this.command = command;
        this.playerIdx = playerIdx;

        this.output =  output.clone();
    }

    /** Constructor for place card action */
    public ActionOutput(String command, Integer handIdx, String error) {
        this.command = command;
        this.handIdx = handIdx;
        this.error = error;
    }

    public ActionOutput(String command, String output, int x, int y){
        this.command = command;
        this.x = x;
        this.y = y;
        this.output = output;
    }
    public ActionOutput(String command, Integer x, Integer y, Minion output) {
        this.command = command;
        this.x = x;
        this.y = y;
        this.output = new Minion(output);
    }
    /**
     * Constructor for showing the cards on the table
     *
     * @param command
     * @param output
     */
    public ActionOutput(String command, ArrayList<?> output) {
        this.command = command;

        this.output = new ArrayList<>(output);
    }
}
