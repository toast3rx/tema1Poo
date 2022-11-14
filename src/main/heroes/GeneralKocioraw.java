package main.heroes;

import main.cards.minion.Minion;

import java.util.ArrayList;

public class GeneralKocioraw extends Hero{


    public GeneralKocioraw(String name, String description, ArrayList<String> colors, int mana) {
        super(name, description, colors, mana);
    }

    //Blood Thirst: +1 atac pentru toate cărțile de pe rând.
    @Override
    public void useAbility(ArrayList<Minion> selectedRow ) {
        for (Minion minion : selectedRow)
            minion.setAttackDamage(minion.getAttackDamage() + 1);
    }
}
