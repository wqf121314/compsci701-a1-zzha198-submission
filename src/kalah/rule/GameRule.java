package kalah.rule;

import kalah.dao.BoardInfoDaoImpl;
import kalah.domian.BoardInfo;
import kalah.domian.PlayerInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameRule {
    BoardInfoDaoImpl boardInfoDaoImpl = new BoardInfoDaoImpl();

    /**
     * change player due to hand seeds
     *
     * @param boardInfo
     * @return BoardInfo
     */
    public BoardInfo swapPlayer(BoardInfo boardInfo) {
        String runner = boardInfo.getRunnerName();
        String next_runner = boardInfo.getNextRunnerName();

        if (runner.equals(next_runner) && runner.equals("P1")) {
            return boardInfoDaoImpl.setPlayer("P2", runner, "P2", boardInfo);
        }
        if (runner.equals(next_runner) && runner.equals("P2")) {
            return boardInfoDaoImpl.setPlayer("P1", runner, "P1", boardInfo);
        }
        return boardInfoDaoImpl.setPlayer(next_runner, runner, next_runner, boardInfo);
    }


    /**
     * detect game over.
     * The game ends when the player whose turn it is has no possible moves (that is, all houses owned by the player are empty).
     * The score for a player is determined by adding all the seeds in that player's houses and store.
     *
     * @param boardInfo
     * @return
     */
    public boolean detectGameOver(BoardInfo boardInfo) {
        boolean is_houses_empty = true;
        List<PlayerInfo> playerInfos = boardInfo.getPlayerInfos();
        for (PlayerInfo p : playerInfos) {
            if (p.getName().equals(boardInfo.getMovingRunnerName())) {
                HashMap<Integer, Integer> houses = p.getHouses();
                for (Map.Entry<Integer, Integer> entry : houses.entrySet()) {
                    if (entry.getValue() != 0) {
                        is_houses_empty = false;
                    }
                }
            }
        }
        return is_houses_empty;
    }

    /**
     * move opponent's house to own house.
     * capture
     *
     * @param boardInfo
     * @return
     */
    public BoardInfo specificMoving(String player, Integer houses_number, BoardInfo boardInfo) {
        int player_seeds = boardInfoDaoImpl.getSeeds(player, houses_number, boardInfo);
        if (player_seeds == 1) {
            /*
                get seeds
             */
            //get opponent Name
            String opponent = player.equals("P1") ? "P2" : "P1";
            Integer opponent_seeds = boardInfoDaoImpl.getSeeds(opponent, getRelativeHousesNumber(houses_number), boardInfo);
            //get players seeds
            Integer store = boardInfoDaoImpl.getStore(player, boardInfo);
            store = store + player_seeds + opponent_seeds;

            /*
                set seeds
             */
            //when opponent_seeds =0 then just move to player's house.
            if (opponent_seeds == 0) {
                boardInfo = boardInfoDaoImpl.setSeeds(player, houses_number, 1, boardInfo);
                return boardInfo;
            }
            //set opponent house seeds
            boardInfo = boardInfoDaoImpl.setSeeds(opponent, getRelativeHousesNumber(houses_number), 0, boardInfo);
            //set player house seeds
            boardInfo = boardInfoDaoImpl.setSeeds(player, houses_number, 0, boardInfo);
            //set player store
            boardInfo = boardInfoDaoImpl.setStore(player, store, boardInfo);
        }
        return boardInfo;
    }

    private Integer getRelativeHousesNumber(Integer houses_number) {
        HashMap<Integer, Integer> movingRule = movingRule();
        return movingRule.get(houses_number);
    }

    //This is a bad way to implement it
    private HashMap<Integer, Integer> movingRule() {
        HashMap<Integer, Integer> rule = new HashMap<>();
        rule.put(1, 6);
        rule.put(2, 5);
        rule.put(3, 4);
        rule.put(4, 3);
        rule.put(5, 2);
        rule.put(6, 1);
        return rule;
    }
}
