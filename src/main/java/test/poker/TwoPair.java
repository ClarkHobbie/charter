package test.poker;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link PokerHand} that consists of two groups of cards of the same rank, with two cards in each group
 */
public class TwoPair extends PokerHand {
    private Card secondaryRepresentative;
    private Card representative;

    public Card getRepresentative() {
        if (null == representative)
            representative = basicGetRepresentative();

        return representative;
    }

    public TwoPair (List<Card> cards) {
        super(cards);
    }

    public static boolean matches (List<Card> cards)
    {
        List<Card> copy = new ArrayList<>(cards);
        List<Card> pair = new ArrayList<>();

        //
        // First pair
        //
        for (Card card : copy)
        {
            pair = rankMatchesFor(card, copy);
            if (pair.size() > 1)
                break;
        }

        if (pair.size() <= 1)
            return false;

        //
        // second pair
        //
        copy.removeAll(pair);
        for (Card card : copy)
        {
            pair = rankMatchesFor(card, copy);
            if (pair.size() > 1)
                break;
        }

        return  (pair.size() > 1);
    }


    protected Card basicGetRepresentative() {
        List<Card> pair = new ArrayList<>();
        Card firstCandidate = null;
        for (Card candidate : getCards()) {
            pair = rankMatchesFor(candidate, getCards());
            if (pair.size() > 1) {
                firstCandidate = findGreatest(pair);
                break;
            }
        }

        List<Card> copy = new ArrayList<>(getCards());
        List<Card> secondPair = new ArrayList<>();
        copy.removeAll(pair);
        Card secondCandidate = null;

        for (Card candidate : copy) {
            secondPair = rankMatchesFor(candidate, copy);

            if (secondPair.size() > 1) {
                secondCandidate = findGreatest(secondPair);
                break;
            }
        }

        if (firstCandidate.isGreaterThan(secondCandidate))
            return firstCandidate;
        else
            return secondCandidate;
    }

    @Override
    public boolean isGreaterThan(PokerHand hand) {
        if ((hand instanceof StraightFlush) || (hand instanceof FourOfAKind) || (hand instanceof FullHouse) || (hand instanceof Flush) || (hand instanceof Straight) || (hand instanceof ThreeOfAKind))
            return false;
        else if (hand instanceof TwoPair) {
            TwoPair other = (TwoPair) hand;
            if (getRepresentative().isGreaterThan(other.getRepresentative()))
                return true;
            else if (other.getRepresentative().isGreaterThan(getRepresentative()))
                return false;
            else
                return getSecondaryRepresentative().isGreaterThan(other.getSecondaryRepresentative());
        }
        else
            return true;
    }

    private Card getSecondaryRepresentative() {
        if (null == secondaryRepresentative)
            secondaryRepresentative = basicGetSecondaryRepresentative();

        return secondaryRepresentative;
    }

    private Card basicGetSecondaryRepresentative() {
        List<Card> pair = new ArrayList<>();
        Card firstCandidate = null;
        for (Card candidate : getCards()) {
            pair = rankMatchesFor(candidate, getCards());
            if (pair.size() > 1) {
                firstCandidate = findGreatest(pair);
                break;
            }
        }

        List<Card> copy = new ArrayList<>(getCards());
        List<Card> secondPair = new ArrayList<>();
        copy.removeAll(pair);
        Card secondCandidate = null;

        for (Card candidate : copy) {
            secondPair = rankMatchesFor(candidate, copy);

            if (secondPair.size() > 1) {
                secondCandidate = findGreatest(secondPair);
                break;
            }
        }

        if (firstCandidate.isGreaterThan(secondCandidate))
            return secondCandidate;
        else
            return firstCandidate;
    }

    @Override
    public String toString() {
        return "Two pair{" + getCards() + "}";
    }

}
