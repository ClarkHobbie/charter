package test.poker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Card {
    public static class CardComparator implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            if (c1.isGreaterThan(c2)) {
                return 1;
            }
            else if (!c1.isGreaterThan(c2) && !c2.isGreaterThan(c1)) {
                return 0;
            }
            else if (!c1.isGreaterThan(c2) && c2.isGreaterThan(c1)) {
                return -1;
            }
            else
                throw new IllegalArgumentException("c1 is not greater than c2, but neither is it equal to it or less than it.");
        }
    }

    private Suits suit;
    private Ranks rank;

    public Suits getSuit() {
        return suit;
    }

    public Ranks getRank() {
        return rank;
    }

    public Card (String s) {
        s = s.trim();

        int position = s.indexOf("10");
        int rankBegins = 0;
        int rankEnds = 1;
        if (position == -1) {
            rank = Ranks.asRanks(s.charAt(0));
            suit = Suits.asSuits(s.charAt(1));
        } else {
            rank = Ranks.Ten;
            suit = Suits.asSuits(s.charAt(2));
        }

    }

    public boolean isGreaterThan(Card other) {
        if (rank == other.rank)
            return suit.isGreaterThan(other.suit);
        else
            return rank.isGreaterThan(other.rank);
    }

    public boolean isGreaterThan (List<Card> cards) {
        for (Card other : cards)
        {
            if (this == other)
                continue;

            if (!this.isGreaterThan(other))
                return false;
        }

        return true;
    }

    public List<Card> getRankMatches (List<Card> cards) {
        List<Card> matches = new ArrayList<>();

        for (Card candidate : cards) {
            if (getRank() == candidate.getRank())
                matches.add(candidate);
        }

        return matches;
    }

    public List<Card> getSuitMatches (List<Card> cards) {
        List<Card> matches = new ArrayList<>();

        for (Card candidate : cards) {
            if (getSuit() == candidate.getSuit())
                matches.add(candidate);
        }

        return matches;
    }

    @Override
    public String toString() {
        return getRank().toString() + getSuit().toString();
    }

    public static List<Card> asCards (String s) {
        String[] sa = s.split(",");
        List<Card> cards = new ArrayList<>();
        for (String cardString : sa) {
            Card card = new Card(cardString);
            cards.add(card);
        }

        return cards;
    }
}
