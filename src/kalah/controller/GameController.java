package kalah.controller;


import com.qualitascorpus.testsupport.IO;
import kalah.domian.BoardInfo;
import kalah.rule.GameRule;
import kalah.service.GameService;
import kalah.service.GameServiceImpl;
import kalah.utils.PrintUtil;

public class GameController {
    private GameService gameService = new GameServiceImpl();
    private GameRule gameRule = new GameRule();

    public BoardInfo initialization() {
        BoardInfo boardInfo = gameService.init();
        return boardInfo;
    }

    public BoardInfo action(IO io, String key, BoardInfo boardInfo) {
        //insure moving runner
        boardInfo.setMovingRunnerName(boardInfo.getRunnerName());
        //insure house is not empty

        //moving
        switch (key) {
            case "1":
                boardInfo.setHousesNumber(1);
                if (gameService.isHouseEmpty(boardInfo)) {
                    PrintUtil.houseEmpty(io);
                } else {
                    boardInfo = gameService.move(boardInfo);
                }
                return boardInfo;
            case "2":
                boardInfo.setHousesNumber(2);
                if (gameService.isHouseEmpty(boardInfo)) {
                    PrintUtil.houseEmpty(io);
                } else {
                    boardInfo = gameService.move(boardInfo);
                }
                return boardInfo;
            case "3":
                boardInfo.setHousesNumber(3);
                if (gameService.isHouseEmpty(boardInfo)) {
                    PrintUtil.houseEmpty(io);
                } else {
                    boardInfo = gameService.move(boardInfo);
                }
                return boardInfo;
            case "4":
                boardInfo.setHousesNumber(4);
                if (gameService.isHouseEmpty(boardInfo)) {
                    PrintUtil.houseEmpty(io);
                } else {
                    boardInfo = gameService.move(boardInfo);
                }
                return boardInfo;
            case "5":
                boardInfo.setHousesNumber(5);
                if (gameService.isHouseEmpty(boardInfo)) {
                    PrintUtil.houseEmpty(io);
                } else {
                    boardInfo = gameService.move(boardInfo);
                }
                return boardInfo;
            case "6":
                boardInfo.setHousesNumber(6);
                if (gameService.isHouseEmpty(boardInfo)) {
                    PrintUtil.houseEmpty(io);
                } else {
                    boardInfo = gameService.move(boardInfo);
                }
                return boardInfo;
            default:
                System.exit(0);
        }
        return boardInfo;
    }

    public boolean isGameOver(BoardInfo boardInfo) {
        return gameRule.detectGameOver(boardInfo);
    }
}
