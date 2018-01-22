package test.poker;

import java.util.List;

/**
 * A hand where the owner doesn't have anything.
 */
public class HighCard extends RepresentativeHand {
    private Card highCard;

    public HighCard(List<Card> cards) {
        super(cards);
    }

    public HighCard () {}

    public boolean isGreaterThan (PokerHand pokerHand) {
        if (!(pokerHand instanceof HighCard))
            return false;
        else {
            HighCard other = (HighCard) pokerHand;
            return getRepresentative().isGreaterThan(other.getRepresentative());
        }
    }

    @Override
    protected Card basicGetRepresentative() {
        return findGreatest(getCards());
    }

    @Override
    public String toString() {
        return "High card{" + getCards() + "}";
    }

}
