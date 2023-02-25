package kalah.dao;

import kalah.domian.BoardInfo;
import kalah.domian.PlayerInfo;

import java.util.HashMap;
import java.util.List;

public class BoardInfoDaoImpl implements BoardInfoDao {

    public int getSeeds(String player, Integer housesNumber, BoardInfo boardInfo) {
        HashMap<Integer, Integer> houses = getHouses(player, boardInfo);
        if (houses != null) {
            return houses.get(housesNumber);
        }
        return -1;
    }

    public BoardInfo setSeeds(String player, Integer housesNumber, Integer handSeeds, BoardInfo boardInfo) {
        HashMap<Integer, Integer> houses = getHouses(player, boardInfo);
        houses.put(housesNumber, handSeeds);
        return setHouses(player, houses, boardInfo);
    }


    public HashMap<Integer, Integer> getHouses(String player, BoardInfo boardInfo) {
        List<PlayerInfo> playerInfoList = boardInfo.getPlayerInfos();
        for (PlayerInfo p : playerInfoList) {
            if (p.getName().equals(player)) {
                return p.getHouses();
            }
        }
        return null;
    }

    public BoardInfo setHouses(String player, HashMap<Integer, Integer> houses, BoardInfo boardInfo) {
        List<PlayerInfo> playerInfoList = boardInfo.getPlayerInfos();
        for (PlayerInfo p : playerInfoList) {
            if (p.getName().equals(player)) {
                p.setHouses(houses);
            }
        }
        return boardInfo;
    }

    public int getStore(String player, BoardInfo boardInfo) {
        List<PlayerInfo> playerInfoList = boardInfo.getPlayerInfos();
        for (PlayerInfo p : playerInfoList) {
            if (p.getName().equals(player)) {
                return p.getStore();
            }
        }
        return -1;
    }

    public BoardInfo setStore(String player, Integer store, BoardInfo boardInfo) {
        List<PlayerInfo> playerInfoList = boardInfo.getPlayerInfos();
        for (PlayerInfo p : playerInfoList) {
            if (p.getName().equals(player)) {
                p.setStore(store);
            }
        }
        return boardInfo;
    }

    public BoardInfo setPlayer(String runner, String nextRunner, String movingRunner, BoardInfo boardInfo) {
        boardInfo.setRunnerName(runner);
        boardInfo.setNextRunnerName(nextRunner);
        boardInfo.setMovingRunnerName(movingRunner);
        return boardInfo;
    }
}
