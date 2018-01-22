package test.poker;

import java.util.List;

public class ThreeOfAKind extends RepresentativeHand {
    public ThreeOfAKind (List<Card> cards) {
        super(cards);
    }

    public static boolean matches (List<Card> cards) {
        for (Card card : cards) {
            List<Card> matches = card.getRankMatches(cards);
            if (matches.size() > 2)
                return true;
        }

        return false;
    }

    @Override
    public boolean isGreaterThan(PokerHand hand) {
        if ((hand instanceof StraightFlush) || (hand instanceof FourOfAKind) || (hand instanceof FullHouse) || (hand instanceof Flush) || (hand instanceof Straight))
            return false;

        else if (hand instanceof ThreeOfAKind)
        {
            ThreeOfAKind other = (ThreeOfAKind) hand;
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
        return "Three of a kind{" + getCards() + "}";
    }

}
