package test.poker;

import java.util.List;

/**
 * A straight that is also a flush.
 */
public class StraightFlush extends PokerHand {
    public StraightFlush(List<Card> cards) {
        super(cards);
    }

    public static boolean matches(List<Card> cards) {
        if (!Flush.matches(cards))
            return false;

        if (!Straight.matches(cards))
            return false;

        return true;
    }

    @Override
    public boolean isGreaterThan(PokerHand other) {
        if (!(other instanceof StraightFlush))
            return true;

        StraightFlush otherStraightFlush = (StraightFlush) other;
        return isGreaterThan(otherStraightFlush);
    }

    @Override
    public String toString() {
        return "Straight flush{" + getCards() + "}";
    }

}
