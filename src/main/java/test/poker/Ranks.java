package test.poker;

public enum Ranks {
    Ace(14, "A"),
    King(13, "K"),
    Queen(12, "Q"),
    Jack(11, "J"),
    Ten(10, "10"),
    Nine(9, "9"),
    Eight(8, "8"),
    Seven(7, "7"),
    Six(6, "6"),
    Five(5, "5"),
    Four(4, "4"),
    Three(3, "3"),
    Two(2, "2"),
    Unknown(0, "0");

    private int value;
    private String shortHand;

    private Ranks (int value, String shortHand) {
        this.value = value;
        this.shortHand = shortHand;
    }

    public static Ranks asRanks (char c) {
        Ranks ranks = Unknown;

        switch (c) {
            case 'A' :
                ranks = Ace;
                break;
            case 'K' :
                ranks = King;
                break;
            case 'Q' :
                ranks = Queen;
                break;
            case 'J' :
                ranks = Jack;
                break;
            case '9' :
                ranks = Nine;
                break;
            case '8' :
                ranks = Eight;
                break;
            case '7' :
                ranks = Seven;
                break;
            case '6' :
                ranks = Six;
                break;
            case '5' :
                ranks = Five;
                break;
            case '4' :
                ranks = Four;
                break;
            case '3' :
                ranks = Three;
                break;
            case '2' :
                ranks = Two;
                break;
            default:
                throw new IllegalArgumentException("Unrecognized rank: " + c);
        }

        return ranks;
    }

    public boolean isGreaterThan (Ranks other) {
        return this.value > other.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return shortHand;
    }
}
