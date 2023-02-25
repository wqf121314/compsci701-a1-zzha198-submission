package kalah.service;

import com.qualitascorpus.testsupport.MockIO;
import junit.framework.TestCase;
import kalah.domian.BoardInfo;
import kalah.utils.PrintUtil;


public class GameServiceImplTest extends TestCase {
    GameServiceImpl gameServiceImpl = new GameServiceImpl();

    public void testInit() {
        BoardInfo boardInfo = gameServiceImpl.init();
        System.out.println(boardInfo.toString());
        PrintUtil.printBoardInfo(new MockIO(), boardInfo);
    }

    public void testMove() {
        BoardInfo boardInfo = gameServiceImpl.init();
        boardInfo.setRunnerName("P1");
        boardInfo.setHousesNumber(1);
        PrintUtil.printBoardInfo(new MockIO(), boardInfo);
        gameServiceImpl.move(boardInfo);
        PrintUtil.printBoardInfo(new MockIO(), boardInfo);
    }
}