package cn.violetgarden.game.json.conpoment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Card {

    private static String[] charas_black = {"♣", "♠"}, charas_red = {"♦", "♥"}, values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    public static List<Card> cards_black, cards_red;
    static {
        System.out.println("Card static tunning");
        Card[] blacks = new Card[26], reds = new Card[26];
        for(int i=0; i<values.length; i++) {
            blacks[i*2] = new Card(charas_black[0], values[i]);
            blacks[i*2+1] = new Card(charas_black[1], values[i]);
            reds[i*2] = new Card(charas_red[0], values[i]);
            reds[i*2+1] = new Card(charas_red[1], values[i]);
        }
        cards_black = Arrays.asList(blacks);
        cards_red = Arrays.asList(reds);
        Collections.shuffle(cards_red);
        Collections.shuffle(cards_black);
    }
    String chara;
    String value;

    public int valueToInt(){
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return switch (value) {
                case "A" -> 1;
                case "J" -> 11;
                case "Q" -> 12;
                case "K" -> 13;
                default -> 0;
            };
        }
    }

    public Card() {
    }
    public Card(String chara, String value) {
        this.chara = chara;
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getChara() {
        return chara;
    }
    public void setChara(String chara) {
        this.chara = chara;
    }
    @Override
    public String toString() {
        return "Card [value=" + value + ", chara=" + chara + "]";
    }
}
