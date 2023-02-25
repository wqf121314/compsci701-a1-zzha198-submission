package kalah.utils;

import com.qualitascorpus.testsupport.MockIO;
import junit.framework.TestCase;
import kalah.domian.BoardInfo;
import kalah.service.GameServiceImpl;

public class PrintUtilTest extends TestCase {
    GameServiceImpl gameService = new GameServiceImpl();

    public void testPrintBoardInfo() {
        PrintUtil.houseEmpty(new MockIO());
    }

    public void testQuit() {
        PrintUtil.quit(new MockIO());

    }

    public void testOver() {
        BoardInfo boardInfo = gameService.init();
        boardInfo.setRunnerName("P1");
        boardInfo.setHousesNumber(1);
//        PrintUtil.over(boardInfo);
        PrintUtil.over(new MockIO(), boardInfo);
    }
}