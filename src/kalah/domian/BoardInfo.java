package kalah.domian;

import java.util.List;

public class BoardInfo {
    List<PlayerInfo> playerInfos;
    String runnerName;
    String nextRunnerName;
    Integer housesNumber;
    Integer handSeeds;
    String movingRunnerName;

    public List<PlayerInfo> getPlayerInfos() {
        return playerInfos;
    }

    public void setPlayerInfos(List<PlayerInfo> playerInfos) {
        this.playerInfos = playerInfos;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public String getNextRunnerName() {
        return nextRunnerName;
    }

    public void setNextRunnerName(String nextRunnerName) {
        this.nextRunnerName = nextRunnerName;
    }

    public Integer getHousesNumber() {
        return housesNumber;
    }

    public void setHousesNumber(Integer housesNumber) {
        this.housesNumber = housesNumber;
    }

    public Integer getHandSeeds() {
        return handSeeds;
    }

    public void setHandSeeds(Integer handSeeds) {
        this.handSeeds = handSeeds;
    }

    public String getMovingRunnerName() {
        return movingRunnerName;
    }

    public void setMovingRunnerName(String movingRunnerName) {
        this.movingRunnerName = movingRunnerName;
    }

    @Override
    public String toString() {
        return "BoardInfo{" +
                "playerInfos=" + playerInfos +
                ", runnerName='" + runnerName + '\'' +
                ", nextRunnerName='" + nextRunnerName + '\'' +
                ", housesNumber=" + housesNumber +
                ", handSeeds=" + handSeeds +
                ", movingRunnerName='" + movingRunnerName + '\'' +
                '}';
    }
}
