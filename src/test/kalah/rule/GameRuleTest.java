package kalah.rule;

import junit.framework.TestCase;
import kalah.domian.BoardInfo;
import kalah.service.GameServiceImpl;

public class GameRuleTest extends TestCase {
    GameServiceImpl gameServiceImpl = new GameServiceImpl();
    GameRule gameRule = new GameRule();

    public void testDetectPlayer() {
    }

    public void testDetectGameOver() {
    }

    public void testSpecificMoving() {
        BoardInfo boardInfo = gameServiceImpl.init();
        boardInfo.setRunnerName("P1");
        boardInfo.setHousesNumber(1);
//        PrintUtil.printBoardInfo(boardInfo);
        boardInfo = gameRule.specificMoving("P1", 1, boardInfo);
//        PrintUtil.printBoardInfo(boardInfo);
    }
}