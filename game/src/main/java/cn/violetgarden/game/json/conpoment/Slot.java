package cn.violetgarden.game.json.conpoment;

import java.util.Arrays;
import java.util.List;

public class Slot {

    int index;
    Card[] cards;
    boolean selected;
    int countAttackable = 0;


    List<Card> attackers;
    List<Card> defenders;
    
    public int sumSlotAttack(){
        return attackers.stream().mapToInt(Card::valueToInt).sum();
    }
    public int sumSlotDefence(){
        return defenders.stream().mapToInt(Card::valueToInt).sum();
    }


    public List<Card> getAttackers() {
        return attackers;
    }
    public int getCountAttackable() {
        return countAttackable;
    }
    public void setCountAttackable(int countAttackable) {
        this.countAttackable = countAttackable;
    }
    public void setAttackers(List<Card> attackers) {
        this.attackers = attackers;
    }
    public List<Card> getDefenders() {
        return defenders;
    }
    public void setDefenders(List<Card> defenders) {
        this.defenders = defenders;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public Card[] getCards() {
        return cards;
    }
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
    @Override
    public String toString() {
        return "Slot [index=" + index + ", cards=" + Arrays.toString(cards) + ", selected=" + selected
                + ", countAttackable=" + countAttackable + ", attackers=" + attackers + ", defenders=" + defenders
                + "]";
    }
}
