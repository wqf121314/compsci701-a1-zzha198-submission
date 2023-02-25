package kalah.utils;

import com.qualitascorpus.testsupport.IO;
import kalah.domian.BoardInfo;
import kalah.domian.PlayerInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * printing utils
 */
public class PrintUtil {
    /**
     * using boardInfo to print Boardw
     *
     * @param boardInfo
     */
    public static void printBoardInfo(IO io, BoardInfo boardInfo) {
        List<PlayerInfo> playerInfoList = boardInfo.getPlayerInfos();
        PlayerInfo p1 = playerInfoList.get(0);
        PlayerInfo p2 = playerInfoList.get(1);
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.println("| " + p2.getName() + " " +
                "| 6[" + matchingFormat(p2.getHouses().get(6)) + "] | 5[" + matchingFormat(p2.getHouses().get(5)) + "] " +
                "| 4[" + matchingFormat(p2.getHouses().get(4)) + "] | 3[" + matchingFormat(p2.getHouses().get(3)) + "] " +
                "| 2[" + matchingFormat(p2.getHouses().get(2)) + "] | 1[" + matchingFormat(p2.getHouses().get(1)) + "] " +
                "| " + matchingFormat(p1.getStore()) + " |");
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println("| " + matchingFormat(p2.getStore()) + " " +
                "| 1[" + matchingFormat(p1.getHouses().get(1)) + "] | 2[" + matchingFormat(p1.getHouses().get(2)) + "] " +
                "| 3[" + matchingFormat(p1.getHouses().get(3)) + "] | 4[" + matchingFormat(p1.getHouses().get(4)) + "] " +
                "| 5[" + matchingFormat(p1.getHouses().get(5)) + "] | 6[" + matchingFormat(p1.getHouses().get(6)) + "] " +
                "| " + p1.getName() + " |");
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");

    }

    private static final String matchingFormat(Integer num) {
        if (num <= 9) {
            return " " + String.valueOf(num);
        } else {
            return String.valueOf(num);
        }
    }

    public static void quit(IO io) {
        io.println("Game over");
    }

    public static void tie(IO io) {
        io.println("A tie!");
    }

    public static void houseEmpty(IO io) {
        io.println("House is empty. Move again.");
    }

    public static void over(IO io, BoardInfo boardInfo) {
        printBoardInfo(io, boardInfo);
        quit(io);
        printBoardInfo(io, boardInfo);
        //get score
        Map<String, Integer> champion = new HashMap<>();
        for (PlayerInfo p : boardInfo.getPlayerInfos()) {
            int player_seeds = 0;
            HashMap<Integer, Integer> houses = p.getHouses();
            for (Map.Entry<Integer, Integer> entry : houses.entrySet()) {
                player_seeds = player_seeds + entry.getValue();
            }
            player_seeds = player_seeds + p.getStore();
            champion.put(p.getName(), player_seeds);
            io.println("\t" + changeName(p.getName()).toLowerCase() + ":" + player_seeds);
        }

        //get champion
        boolean is_tie = false;
        if (champion.get("P1") == champion.get("P2")) {
            tie(io);

        } else {
            String champion_name = "P1";
            int champion_seeds = -1;
            for (Map.Entry<String, Integer> entry : champion.entrySet()) {
                if ((champion_seeds < entry.getValue())) {
                    champion_name = entry.getKey();
                    champion_seeds = entry.getValue();
                }
            }
            io.println(changeName(champion_name) + " wins!");
        }
    }

    private static String changeName(String name) {
        String end = name.substring(1, name.length());
        return "Player " + end;
    }
}
