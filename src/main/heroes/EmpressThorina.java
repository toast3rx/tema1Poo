package main.heroes;

import main.cards.minion.Minion;

import java.util.ArrayList;

public class EmpressThorina extends Hero{
    public EmpressThorina(String name, String description, String colors, int mana, int health) {
        super(name, description, colors, mana, health);
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
