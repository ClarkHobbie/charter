import com.ltsllc.common.commandline.CommandException;
import com.ltsllc.common.commandline.CommandLine;
import test.poker.GameCommandLine;

/**
 * The "main class" for the application.
 */
public class PokerApplication extends com.ltsllc.common.application.Application {
    public PokerApplication(String[] argv) {
        super(argv);
    }

    @Override
    public CommandLine createCommandLine(String[] argv) {
        return new GameCommandLine(argv);
    }

    public void go () {
        try {
            super.go();

            String[] empty = {};
            test.poker.Poker.main(empty);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] argv) {
        PokerApplication poker = new PokerApplication(argv);
        poker.go();
    }
}
