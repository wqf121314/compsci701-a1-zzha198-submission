package kalah.dao;

import junit.framework.TestCase;
import kalah.domian.BoardInfo;
import kalah.service.GameServiceImpl;

import java.util.HashMap;

public class BoardInfoDaoImplTest extends TestCase {
    BoardInfoDaoImpl boardInfoDaoImpl = new BoardInfoDaoImpl();
    GameServiceImpl gameServiceImpl = new GameServiceImpl();

    public void testGetSeeds() {
    }
    public void testSetSeeds(){
        BoardInfo boardInfo = gameServiceImpl.init();
        System.err.println(boardInfo);
        boardInfoDaoImpl.setSeeds("P2",2,0,boardInfo);
        System.err.println(boardInfo);
    }
    public void testSetHouses() {
        BoardInfo boardInfo = gameServiceImpl.init();
        System.err.println(boardInfo);
        HashMap<Integer, Integer> houses= boardInfoDaoImpl.getHouses("P1",boardInfo);
        houses.put(1,5);
        boardInfoDaoImpl.setHouses("P1",houses,boardInfo);
        System.err.println(boardInfo);

    }

    public void testGetHouses() {
    }

    public void testGetStore() {
    }
}