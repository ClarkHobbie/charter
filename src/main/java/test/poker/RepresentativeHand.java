package test.poker;

import java.util.List;

/**
 * A {@link PokerHand} that can be represented by a single card.
 * For example, three eigths would be represented by one of the eights,
 * a straight of 5,6,7,8,9 would be represented by the nine.
 */
abstract public class RepresentativeHand extends PokerHand {

    protected abstract Card basicGetRepresentative();

    protected RepresentativeHand () {}
    public Card representative = null;

    public RepresentativeHand (List<Card> cards) {
        super(cards);
    }

    public Card getRepresentative() {
        if (null == representative)
            representative = basicGetRepresentative();

        return representative;
    }
}
