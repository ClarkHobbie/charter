package test.poker;

import java.util.List;

/**
 * A sequence of cards of differing siuts.
 */
public class Straight extends RepresentativeHand {
    public Straight (List<Card> cards) {
        super(cards);
    }

    public static boolean matches (List<Card> cards) {
        Card first = cards.get(0);
        int firstValue = first.getRank().getValue();

        for (int i = firstValue; i < firstValue + 5; i++) {
            if (!containsRankValue(i, cards)) {
                return false;
            }
        }

        return true;
    }

    private static boolean containsRankValue(int value, List<Card> cards) {
        for (Card card : cards) {
            if (card.getRank().getValue() == value)
                return true;
        }

        return false;
    }

    @Override
    protected Card basicGetRepresentative() {
        return getCards().get(0);
    }

    @Override
    public boolean isGreaterThan(PokerHand hand) {
        if ((hand instanceof StraightFlush) || (hand instanceof FourOfAKind) || (hand instanceof FullHouse) || (hand instanceof Flush))
            return false;
        else if (hand instanceof Straight)
        {
            Straight other = (Straight) hand;
            return getRepresentative().isGreaterThan(other.getRepresentative());
        }
        else return true;
    }

    @Override
    public String toString() {
        return "Straight{" + getCards() + "}";
    }
}
