package test.poker;

import java.util.List;

public class FourOfAKind extends PokerHand {
    private Card representative;

    public Card getRepresentative() {
        if (null == representative)
            representative = findRepresentative();

        return representative;
    }

    public FourOfAKind (List<Card> cards) {
        super(cards);
    }

    public static boolean matches (List<Card> cards) {
        for (Card candidate : cards) {
            List<Card> matches = rankMatchesFor(candidate, cards);
            if (matches.size() > 3)
                return true;
        }

        return false;
    }

    @Override
    public boolean isGreaterThan(PokerHand hand) {
        if (hand instanceof StraightFlush)
            return false;
        else if (hand instanceof FourOfAKind)
        {
            FourOfAKind other = (FourOfAKind) hand;
            return isGreaterThan(other);
        }
        else
        {
            return true;
        }
    }

    public Card findRepresentative ()
    {
        for (Card c1 : getCards()) {
            for (Card c2 : getCards()) {
                if (c1 == c2)
                    break;

                List<Card> matches = rankMatchesFor(c1, getCards());
                if (matches.size() < 4)
                    break;

                if (!Game.getRankSuits())
                    return c1;
                else if (c1.isGreaterThan(matches))
                    return c1;
            }
        }

        throw new IllegalStateException("Impossible case!  Four of a kind has no representative!");
    }

    @Override
    public String toString() {
        return "Four of a kind{" + getCards() + "}";
    }

}
