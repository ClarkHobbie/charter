package test.poker;

import java.util.List;

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
