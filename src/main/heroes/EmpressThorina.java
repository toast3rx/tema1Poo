package main.heroes;

import lombok.Data;
import main.cards.minion.Minion;

import java.util.ArrayList;

@Data
public class EmpressThorina extends Hero{


    public EmpressThorina(String name, String description, ArrayList<String> colors, int mana) {
        super(name, description, colors, mana);
    }

    //   Low Blow: distruge cartea cu cea mai mare viață de pe rând.
    @Override
    public void useAbility(ArrayList<Minion> selectedRow ) {
        Minion highestHealthCard = selectedRow.get(0);

        for (Minion minion : selectedRow) {
            if (minion.getHealth() > highestHealthCard.getHealth()) {
                highestHealthCard = minion;
            }
        }

        selectedRow.remove(highestHealthCard);
    }
}
