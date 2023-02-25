package kalah.utils;

import junit.framework.TestCase;

public class ConfigUtilTest extends TestCase {

    public void testGetMaximumPlayers() {
        System.out.printf("MaximumPlayers--->" + ConfigUtil.getMaximumPlayers());
    }

    public void testGetMaximumHouses() {
        System.out.printf("MaximumHouses--->" + ConfigUtil.getMaximumHouses());

    }

    public void testGetInitialSeeds() {
        System.out.printf("InitialSeeds--->" + ConfigUtil.getInitialSeeds());
    }
}