package kalah.utils;


/**
 * environment configuration
 * reading from src/config.properties
 */
public class ConfigUtil {
    private static final Integer Maximum_Players = 2;
    private static final Integer Maximum_houses = 6;
    private static final Integer Initial_seeds = 4;

    //Maximum_Players
    public static int getMaximumPlayers() {
        return ConfigUtil.Maximum_Players;
    }

    //Maximum_houses
    public static int getMaximumHouses() {
        return ConfigUtil.Maximum_houses;
    }

    //Initial_seeds
    public static int getInitialSeeds() {
        return ConfigUtil.Initial_seeds;
    }
}
