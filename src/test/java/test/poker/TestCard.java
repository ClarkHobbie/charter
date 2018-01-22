package test.poker;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestCard extends PokerTestCase {
    @Before
    public void clear () {
        setDontRankSuits();
    }

    @Test
    public void testComparator () {
        PokerHand hand = PokerHand.createInstance("2S, 3D, 4C, 5H, 7S");
        hand.sortCards();
        assert (hand.toString().equals("High card{[2S, 3D, 4C, 5H, 7S]}"));

        hand = PokerHand.createInstance("7S, 5H, 4C, 3D, 2S");
        hand.sortCards();
        assert (hand.toString().equals("High card{[2S, 3D, 4C, 5H, 7S]}"));

        hand = PokerHand.createInstance("2S, 5H, 3D, 4C, 7S");
        hand.sortCards();
        assert (hand.toString().equals("High card{[2S, 3D, 4C, 5H, 7S]}"));

        hand = PokerHand.createInstance("2S, 3D, 3C, 3H, 4S");
        hand.sortCards();
        assert (hand.toString().equals("Three of a kind{[2S, 3D, 3C, 3H, 4S]}"));
    }

    @Test
    public void testConstuctor () {
        PokerHand hand = PokerHand.createInstance("2S, 3D, 4C, 5H, 10S");
        hand.sortCards();
        assert (hand.toString().equals("High card{[2S, 3D, 4C, 5H, 10S]}"));

        hand = PokerHand.createInstance("2S, 3D, 4C, 5H, 7S");
        hand.sortCards();
        assert (hand.toString().equals("High card{[2S, 3D, 4C, 5H, 7S]}"));
    }

    @Test
    public void testIsGreaterThan () {
        setDontRankSuits();
        Card card1 = new Card("2S");
        Card card2 = new Card("2D");
        assert (!(card1.isGreaterThan(card2)));

        setRankSuits();
        assert (card1.isGreaterThan(card2));
    }

    @Test
    public void testIsGreaterThanHand () {
        setDontRankSuits();
        Card card = new Card("10S");
        PokerHand hand = PokerHand.createInstance("9H, 8S, 7D, 5C");
        assert (card.isGreaterThan(hand.getCards()));
        hand = PokerHand.createInstance("10H, 9S, 8D, 6C");
        assert (!(card.isGreaterThan(hand.getCards())));

        setRankSuits();
        hand = PokerHand.createInstance("10H, 9S, 8D, 6C");
        assert (card.isGreaterThan(hand.getCards()));
    }

    @Test
    public void testGetRankMatches () {
        List<Card> cards = Card.asCards("10S, 9D, 8C, 7H");
        Card ten = new Card("10C");
        List<Card> matches = ten.getRankMatches(cards);
        String stringMatches = matches.toString();
        assert (stringMatches.equals("[10S]"));

        Card jack = new Card("JC");
        List<Card> noMatch = jack.getRankMatches(cards);
        String stringNoMatch = noMatch.toString();
        assert (stringNoMatch.equals("[]"));
    }

    @Test
    public void testAsCards () {
        List<Card> cards = Card.asCards("10S, 9D, 8C, 7H");
        String stringCards = cards.toString();
        assert (stringCards.equals("[10S, 9D, 8C, 7H]"));
    }
}
