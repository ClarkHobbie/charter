package test.poker;

import java.util.ArrayList;
import java.util.List;

public class FullHouse extends RepresentativeHand {
    public FullHouse (List<Card> cards) {
        super(cards);
    }

    public static boolean matches (List<Card> cards) {
        List<Card> threeOfAKindMatches = new ArrayList<>();
        for (Card card : cards)
        {
            threeOfAKindMatches.clear();
            threeOfAKindMatches = rankMatchesFor(card, cards);
            if (threeOfAKindMatches.size() > 2)
                break;
        }

        if (threeOfAKindMatches.size() < 3)
            return false;

        List<Card> copy = new ArrayList<>(cards);
        copy.removeAll(threeOfAKindMatches);

        List<Card> pairMatches = new ArrayList<>();
        for (Card card : copy)
        {
            pairMatches.clear();
            pairMatches = rankMatchesFor(card, copy);
            if (pairMatches.size() > 1)
                break;
        }

        return pairMatches.size() > 1;
    }

    @Override
    public boolean isGreaterThan(PokerHand hand) {
        if ((hand instanceof StraightFlush) || (hand instanceof FourOfAKind))
            return false;
        else if (hand instanceof FullHouse) {
            FullHouse other = (FullHouse) hand;
            return getRepresentative().isGreaterThan(other.getRepresentative());
        }
        else return true;
    }

    @Override
    protected Card basicGetRepresentative() {
        List<Card> threeOfAKindMatches = new ArrayList<>();
        for (Card card : getCards())
        {
            threeOfAKindMatches.clear();
            if (threeOfAKindMatches.size() > 2)
                break;
        }

        return findGreatest(threeOfAKindMatches);
    }

    @Override
    public String toString() {
        return "Full house{" + getCards() + "}";
    }

}
