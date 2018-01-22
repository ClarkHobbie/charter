package test.poker;

import java.util.List;

/**
 * A {@link RepresentativeHand} that represents a Flush.
 */
public class Flush extends RepresentativeHand {

    public Flush (List<Card> cards) {
        super(cards);
    }

    public static boolean matches (List<Card> cards) {
        Card first = cards.get(0);

        for (Card card : cards) {
            if (card.getSuit() != first.getSuit())
                return false;
        }

        return true;
    }

    @Override
    public boolean isGreaterThan(PokerHand hand) {
        if ((hand instanceof StraightFlush) || (hand instanceof FourOfAKind))
            return false;
        else if (hand instanceof Flush) {
            Flush other = (Flush) hand;
            return getRepresentative().isGreaterThan(other.getRepresentative());
        }
        else return true;
    }

    @Override
    protected Card basicGetRepresentative() {
        return findGreatest(getCards());
    }

    @Override
    public String toString() {
        return "Flush{" + getCards() + ")";
    }
}
