package main.heroes;

import main.cards.minion.Minion;

import java.util.ArrayList;

public class KingMudface extends Hero{


    public KingMudface(String name, String description, String colors, int mana, int health) {
        super(name, description, colors, mana, health);
    }

    //  Earth Born: +1 viață pentru toate cărțile de pe rând.
    @Override
    public void useAbility(ArrayList<Minion> selectedRow ) {
        for (Minion minion : selectedRow) {
            minion.setHealth(minion.getHealth() + 1);
        }
    }
}
