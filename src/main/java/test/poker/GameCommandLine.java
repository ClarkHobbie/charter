package test.poker;

import com.ltsllc.common.commandline.CommandLine;

/**
 * A class that represents the poker command line.
 *
 * <p>
 *     The point of this class is weather to rank suits.
 * </p>
 */
public class GameCommandLine extends CommandLine {
    public enum Options {
        Unknown(1 + Switches.LAST.getIndex()),

        RankSuits(2 + Switches.LAST.getIndex());

        private int index;

        Options(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }


        public static Options toOption(Switches aSwitch) {
            Options option = Unknown;

            if (aSwitch.getIndex() == RankSuits.getIndex())
                option = RankSuits;

            return option;
        }
    }

    public static final String OPTION_RANK_SUITS_SHORT = "-s";
    public static final String OPTION_RANK_SUITS_LONG = "--rankSuits";


    private boolean rankSuits = false;

    public GameCommandLine(String[] argv) {
        super(argv);
    }


    public boolean getRankSuits() {
        return rankSuits;
    }

    public void setRankSuits(boolean rankSuits) {
        this.rankSuits = rankSuits;
    }

    public Switches toSwitch(String argument) {
        Switches aSwitch = Switches.PlaceHolder;

        if (argument.equals(OPTION_RANK_SUITS_SHORT) || (argument.equals(OPTION_RANK_SUITS_LONG)))
            aSwitch.setIndex(Options.RankSuits.getIndex());

        return aSwitch;
    }

    public void processSwitch(Switches aSwitch) {
        Options option = Options.toOption(aSwitch);

        switch (option) {
            case RankSuits: {
                setRankSuits(true);
            }

            default: {
                backup();
                error("Unknown option: " + getArgAndAdvance());
                break;

            }
        }
    }


    public void error(String message) {
        System.err.println(message);
        System.err.println(getUsageString());
    }

    @Override
    public String getUsageString() {
        return "usage: poker [-s|--rankSuits]";
    }
}
