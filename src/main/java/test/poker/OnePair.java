package test.poker;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link RepresentativeHand} that has a pair.
 */
public class OnePair extends RepresentativeHand {
    public OnePair (List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean isGreaterThan(PokerHand hand) {
        if ((hand instanceof StraightFlush) || (hand instanceof FourOfAKind) || (hand instanceof FullHouse) || (hand instanceof Flush) || (hand instanceof Straight) || (hand instanceof TwoPair))
            return false;
        else if (hand instanceof OnePair) {
            OnePair other = (OnePair) hand;
            return getRepresentative().isGreaterThan(other.getRepresentative());
        }
        else return true;
    }

    @Override
    protected Card basicGetRepresentative() {
        List<Card> pair = new ArrayList<>();

        for (Card card : getCards()) {
            pair = rankMatchesFor(card, getCards());
            if (pair.size() > 1)
                break;
        }

        return findGreatest(pair);
    }

    public static boolean matches (List<Card> cards) {
        List matches = new ArrayList();
        for (Card card : cards)
        {
            matches.clear();
            matches = rankMatchesFor(card, cards);

            if (matches.size() > 1)
                break;
        }

        return matches.size() > 1;
    }

    @Override
    public String toString() {
        return "One pair{" + getCards() + "}";
    }
}
