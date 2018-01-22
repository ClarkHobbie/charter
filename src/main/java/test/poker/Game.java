package test.poker;

import com.ltsllc.common.application.Application;
import com.ltsllc.common.commandline.CommandLine;

/**
 * A class that holds the options for the game.
 */
public class Game extends Application {
    private static boolean rankSuits;

    public Game(String[] argv) {
        super(argv);
    }

    @Override
    public CommandLine createCommandLine(String[] argv) {
        return new GameCommandLine(argv);
    }

    public static boolean getRankSuits() {
        return rankSuits;
    }

    public static void setRankSuits(boolean rankSuits) {
        Game.rankSuits = rankSuits;
    }

    public static void start(String[] argv) {
        String[] empty = {};
        Game game = new Game(argv);
        Game.setRankSuits(Game.getRankSuits());

        Poker.main(empty);
    }

}
