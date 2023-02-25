package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.controller.GameController;
import kalah.domian.BoardInfo;
import kalah.rule.InputRule;
import kalah.utils.PrintUtil;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure. Remove this comment (or rather, replace it
 * with something more appropriate)
 */
public class Kalah {
    private GameController gameController = new GameController();

    public static void main(String[] args) {
        new Kalah().play(new MockIO());
    }

    public void play(IO io) {
        BoardInfo boardInfo = gameController.initialization();
        String key = "";
        while (true) {
            //check game end
            if (gameController.isGameOver(boardInfo)) {
                PrintUtil.over(io, boardInfo);
                break;
            }
            //game action
            do {
                PrintUtil.printBoardInfo(io, boardInfo);
                key = io.readFromKeyboard("Player " + boardInfo.getRunnerName() + "'s turn - Specify house number or 'q' to quit: ");
            } while (InputRule.keyCheck(key));

            if (key.equals("q")) {
                PrintUtil.quit(io);
                PrintUtil.printBoardInfo(io, boardInfo);
                break;
            }
            gameController.action(io, key, boardInfo);
        }
    }
}
