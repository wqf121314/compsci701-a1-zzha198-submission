package kalah.service;

import kalah.dao.BoardInfoDao;
import kalah.dao.BoardInfoDaoImpl;
import kalah.domian.BoardInfo;
import kalah.domian.PlayerInfo;
import kalah.rule.GameRule;
import kalah.utils.ConfigUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameServiceImpl implements GameService {
    private Integer MAX_PLAYERS = ConfigUtil.getMaximumPlayers();
    private Integer MAX_HOUSES = ConfigUtil.getMaximumHouses();
    private Integer MAX_SEEDS = ConfigUtil.getInitialSeeds();
    private BoardInfoDao boardInfoDao = new BoardInfoDaoImpl();
    private GameRule gameRule = new GameRule();

    /**
     * initial BoardInfo
     *
     * @return BoardInfo
     */
    public BoardInfo init() {
        BoardInfo boardInfo = new BoardInfo();
        //initial PlayerInfo
        List<PlayerInfo> playerInfoList = new ArrayList<>();
        for (int i = 1; i <= MAX_PLAYERS; i++) {
            PlayerInfo playerInfo = new PlayerInfo();
            playerInfo.setName("P" + i);
            HashMap<Integer, Integer> houses = new HashMap<>();
            for (int j = 1; j <= MAX_HOUSES; j++) {
                houses.put(j, MAX_SEEDS);
            }
            playerInfo.setHouses(houses);
            playerInfo.setStore(0);
            playerInfoList.add(playerInfo);
        }
        boardInfo.setPlayerInfos(playerInfoList);
        boardInfo.setRunnerName("P1");
        boardInfo.setMovingRunnerName("P1");
        boardInfo.setNextRunnerName("P2");
        return boardInfo;
    }

    public boolean isHouseEmpty(BoardInfo boardInfo) {
        boolean is_empty = false;
        Integer seeds = boardInfoDao.getSeeds(boardInfo.getMovingRunnerName(), boardInfo.getHousesNumber(), boardInfo);
        if (seeds == 0) {
            is_empty = true;
        }
        return is_empty;
    }

    /**
     * moving seeds
     *
     * @param boardInfo
     * @return
     */
    public BoardInfo move(BoardInfo boardInfo) {
        /*
            Refresh hand seeds.
            get hand seeds from runner's houses
         */
        int hand_seeds = boardInfoDao.getSeeds(boardInfo.getRunnerName(), boardInfo.getHousesNumber(), boardInfo);
        boardInfo.setHandSeeds(hand_seeds);
        /*
            EASY moving
         */
        //move seeds at own houses
        boardInfo = movingOwnHouseByAppointedHousesNumber(boardInfo);

        //move seeds to store
        if (boardInfo.getHandSeeds() > 0) {
            boardInfo = movingOwnStore(boardInfo);
        }

        /*
            moving more hands seeds
         */
        hand_seeds = boardInfo.getHandSeeds();
        while (hand_seeds > 0) {
            //each time need to check hand seeds
            if (boardInfo.getHandSeeds() > 0) {
                boardInfo = movingOpponentHouse(boardInfo);
                //update hand seeds
                hand_seeds = boardInfo.getHandSeeds();
            }
            if (boardInfo.getHandSeeds() > 0) {
                boardInfo = movingOwnHouseByFirstHouse(boardInfo);
                hand_seeds = boardInfo.getHandSeeds();
            }
            if (boardInfo.getHandSeeds() > 0) {
                boardInfo = movingOwnStore(boardInfo);
                hand_seeds = boardInfo.getHandSeeds();
            }
        }
        return boardInfo;
    }

    private BoardInfo movingOwnHouseByAppointedHousesNumber(BoardInfo boardInfo) {
        //get need moving seeds
        int hand_seeds = boardInfo.getHandSeeds();
        // empty runners house by he's key
        boardInfo = boardInfoDao.setSeeds(boardInfo.getMovingRunnerName(), boardInfo.getHousesNumber(), 0, boardInfo);
        //get own houses
        HashMap<Integer, Integer> houses = boardInfoDao.getHouses(boardInfo.getMovingRunnerName(), boardInfo);
        //check specific seeds
        boolean is_specific = false;
        int specific_houses_number = 0;
        //moving seeds
        for (int i = boardInfo.getHousesNumber() + 1; i <= ConfigUtil.getMaximumHouses(); i++) {
            if (hand_seeds > 0) {
                if (hand_seeds == 1 && houses.get(i) == 0) {
                    is_specific = true;
                    specific_houses_number = i;
                }
                int seed = houses.get(i) + 1;
                houses.put(i, seed);
                hand_seeds--;
            }
        }
        /*
            refresh boardInfo data
         */
        //update hand seeds
        boardInfo.setHandSeeds(hand_seeds);
        //specific seeds moving
        if (is_specific) {
            boardInfo = gameRule.specificMoving(boardInfo.getMovingRunnerName(), specific_houses_number, boardInfo);
        }
        //update houses
        boardInfo = boardInfoDao.setHouses(boardInfo.getMovingRunnerName(), houses, boardInfo);
        //check player
        if (hand_seeds == 0) {
            boardInfo = gameRule.swapPlayer(boardInfo);
        }
        return boardInfo;
    }

    private BoardInfo movingOwnStore(BoardInfo boardInfo) {
        int hand_seeds = boardInfo.getHandSeeds();
        int store = boardInfoDao.getStore(boardInfo.getMovingRunnerName(), boardInfo);
        if (hand_seeds > 0) {
            store++;
            hand_seeds--;
        }
        /*
            refresh boardInfo data
         */
        //return hand seeds
        boardInfo.setHandSeeds(hand_seeds);
        //check player
        if (hand_seeds == 0) {
            boardInfo = boardInfoDao.setPlayer(boardInfo.getRunnerName(), boardInfo.getRunnerName(), boardInfo.getRunnerName(), boardInfo);
        }
        //update store
        boardInfo = boardInfoDao.setStore(boardInfo.getMovingRunnerName(), store, boardInfo);
        return boardInfo;
    }

    private BoardInfo movingOpponentHouse(BoardInfo boardInfo) {
        int hand_seeds = boardInfo.getHandSeeds();
        String player_name = "P1".equals(boardInfo.getMovingRunnerName()) ? "P2" : "P1";
        HashMap<Integer, Integer> houses = boardInfoDao.getHouses(player_name, boardInfo);
        for (int i = 1; i <= ConfigUtil.getMaximumHouses(); i++) {
            if (hand_seeds > 0) {
                int seed = houses.get(i) + 1;
                houses.put(i, seed);
                hand_seeds--;
            }
        }
        /*
            refresh boardInfo data
         */
        //return hand seeds
        boardInfo.setHandSeeds(hand_seeds);
        //check player
        if (hand_seeds == 0) {
            boardInfo = gameRule.swapPlayer(boardInfo);
        }
        //return Opponent houses
        boardInfo = boardInfoDao.setHouses(player_name, houses, boardInfo);
        return boardInfo;
    }

    private BoardInfo movingOwnHouseByFirstHouse(BoardInfo boardInfo) {
        //get need moving seeds
        int hand_seeds = boardInfo.getHandSeeds();
        //check specific seeds
        boolean is_specific = false;
        int specific_houses_number = 0;
        //get own houses
        HashMap<Integer, Integer> houses = boardInfoDao.getHouses(boardInfo.getMovingRunnerName(), boardInfo);

        for (int i = 1; i <= ConfigUtil.getMaximumHouses(); i++) {
            if (hand_seeds > 0) {
                if (hand_seeds == 1 && houses.get(i) == 0) {
                    is_specific = true;
                    specific_houses_number = i;
                }
                int seed = houses.get(i) + 1;
                houses.put(i, seed);
                hand_seeds--;
            }
        }
        /*
            refresh boardInfo data
         */
        //return hand seeds
        boardInfo.setHandSeeds(hand_seeds);
        //update own houses
        boardInfo = boardInfoDao.setHouses(boardInfo.getMovingRunnerName(), houses, boardInfo);
        //specific seeds moving
        if (is_specific) {
            boardInfo = gameRule.specificMoving(boardInfo.getMovingRunnerName(), specific_houses_number, boardInfo);
        }
        //check player
        if (hand_seeds == 0) {
            boardInfo = gameRule.swapPlayer(boardInfo);
        }
        return boardInfo;
    }

}
