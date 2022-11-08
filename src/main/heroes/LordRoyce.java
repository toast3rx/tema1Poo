package main.heroes;

import main.cards.minion.Minion;

import java.util.ArrayList;

public class LordRoyce extends Hero {

    public LordRoyce(String name, String description, String colors, int mana, int health) {
        super(name, description, colors, mana, health);
    }

    // Sub-Zero: îngheață cartea cu cel mai mare atac de pe rând.
    @Override
    public void useAbility( ArrayList<Minion> selectedRow ) {
        // get the card with the highest attack
        Minion highestAttackCard = selectedRow.get(0);
        for (Minion card : selectedRow) {
            if (card.getAttackDamage() > highestAttackCard.getAttackDamage()) {
                highestAttackCard = card;
            }
        }
        highestAttackCard.setFrozen(true);

    }
}
