package kalah.dao;

import kalah.domian.BoardInfo;

import java.util.HashMap;

public interface BoardInfoDao {

    BoardInfo setSeeds(String player, Integer housesNumber, Integer handSeeds, BoardInfo boardInfo);

    int getSeeds(String player, Integer housesNumber, BoardInfo boardInfo);

    HashMap<Integer, Integer> getHouses(String player, BoardInfo boardInfo);

    BoardInfo setHouses(String player, HashMap<Integer, Integer> houses, BoardInfo boardInfo);

    int getStore(String player, BoardInfo boardInfo);

    BoardInfo setStore(String player, Integer store, BoardInfo boardInfo);

    BoardInfo setPlayer(String runner, String nextRunner, String movingRunner, BoardInfo boardInfo);

}
