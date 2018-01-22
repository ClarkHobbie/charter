package test.poker;

public enum Suits {
    Spades("S"),
    Clubs("C"),
    Hearts("H"),
    Diamonds("D"),
    Unknown("U");

    private String shortHand;

    private Suits (String shortHand) {
        this.shortHand = shortHand;
    }

    public static Suits asSuits(char c) {
        Suits suits = Unknown;
        switch(c) {
            case 'S' :
                suits = Spades;
                break;
            case 'C' :
                suits = Clubs;
                break;
            case 'H' :
                suits = Hearts;
                break;
            case 'D' :
                suits = Diamonds;
                break;
            default:
                throw new IllegalArgumentException("Unreconized suit: " + c);

        }

        return suits;
    }

    public boolean isGreaterThan (Suits other) {
        if (!Game.getRankSuits())
            return false;
        else if (this == Spades)
            return other != Spades;
        else if (this == Hearts)
            return other != Hearts && other != Spades;
        else if (this == Diamonds)
            return other != Diamonds && other != Hearts && other != Spades;
        else
            return false;
    }


    @Override
    public String toString() {
        return shortHand;
    }
}
