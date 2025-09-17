package cn.violetgarden.game.json;

import java.util.List;
import java.util.Arrays;

import cn.violetgarden.game.json.conpoment.Card;
import cn.violetgarden.game.json.conpoment.Slot;

public class Player {
    public static final boolean CAMP_RED = true, CAMP_BLACK = false;

    Slot[] slots;
    List<Card> cards;
    boolean actionable = false;
    boolean camp;

    public Player() {
    }

    public Player(Slot[] slots, List<Card> cards, boolean actionable, boolean camp) {
        this.slots = slots;
        this.cards = cards;
        this.actionable = actionable;
        this.camp = camp;
    }

    public boolean isCamp() {
        return camp;
    }

    public void setCamp(boolean camp) {
        this.camp = camp;
    }
    
    public boolean isActionable() {
        return actionable;
    }

    public void setActionable(boolean actionable) {
        this.actionable = actionable;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(Slot[] slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "Player [slots=" + Arrays.toString(slots) + ", cards=" + cards + ", actionable=" + actionable + ", camp="
                + camp + "]";
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}
