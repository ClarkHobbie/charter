package test.poker;

import java.util.ArrayList;
import java.util.List;

import static test.poker.Card.CardComparator;

/**
 * The base class that all classes inherit from.
 *
 * <h2>
 *     Abstract Class
 * </h2>
 * To be concrete a subclass must implement the following method:
 * <ul>
 *     <li>{@link #isGreaterThan(PokerHand)}</li>
 * </ul>
 */
public abstract class PokerHand {
    /**
     * Does this hand "beat" another hand?
     *
     * @param hand The other hand to compare against.
     * @return true if this hand "beats" the other hand. false otherwise.
     */
    abstract public boolean isGreaterThan (PokerHand hand);

    private List<Card> cards;

    public PokerHand (List<Card> cards) {
        this.cards = cards;
    }

    public PokerHand () {}

    public List<Card> getCards() {
        return cards;
    }

    public static PokerHand createInstance (String s) {
        PokerHand hand = new HighCard();

        List<Card> cards = asCards(s);
        CardComparator cardComparator = new CardComparator();
        cards.sort(cardComparator);

        if (StraightFlush.matches(cards))
            hand = new StraightFlush(cards);
        else if (FourOfAKind.matches(cards))
            hand = new FourOfAKind(cards);
        else if (FullHouse.matches(cards))
            hand = new FullHouse(cards);
        else if (Flush.matches(cards))
            hand = new Flush(cards);
        else if (Straight.matches(cards))
            hand = new Straight(cards);
        else if (ThreeOfAKind.matches(cards))
            hand = new ThreeOfAKind(cards);
        else if (TwoPair.matches(cards))
            hand = new TwoPair(cards);
        else if (OnePair.matches(cards))
            hand = new OnePair(cards);
        else hand = new HighCard(cards);

        return hand;
    }


    public static List<Card> asCards(String s) {
        List<Card> cards = new ArrayList<Card>();
        String[] sa = s.split(",");
        for (String string : sa) {
            Card card = new Card(string);
            cards.add(card);
        }
        return cards;
    }

    public Card findGreatest(List<Card> cards) {
        Card greatest = cards.get(0);

        for (Card candidate : cards) {
            boolean isGreatest = true;

            for (Card other : cards) {
                if (candidate == other)
                    continue;

                if (!candidate.isGreaterThan(other))
                    isGreatest = false;
            }

            if (isGreatest)
                return candidate;
        }

        return greatest;
    }

    public static List<Card> rankMatchesFor (Card card, List<Card> cards)  {
        List<Card> matches = new ArrayList<>();

        for (Card candidate : cards)
        {
            if (card.getRank() == candidate.getRank())
                matches.add(candidate);
        }

        return matches;
    }

    public static boolean containsRank (Card card, List<Card> cards) {
        for (Card candidate : cards) {
            if (card.getRank() == candidate.getRank())
                return true;
        }
        
        return false;
    }

    public void sortCards ()
    {
        CardComparator comparator = new CardComparator();
        getCards().sort(comparator);
    }
}
