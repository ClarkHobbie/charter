package test.poker;

public class Game {
    private static boolean rankSuits;

    public static boolean getRankSuits() {
        return rankSuits;
    }

    public static void setRankSuits(boolean rankSuits) {
        Game.rankSuits = rankSuits;
    }

    public static void main(String[] argv) {
        GameCommandLine gameCommandLine = new GameCommandLine(argv);
        gameCommandLine.parse();

        Game game = new Game();
        Game.setRankSuits(gameCommandLine.getRankSuits());

        Poker.main(argv);
    }

}
