package kalah.domian;

import java.util.HashMap;

public class PlayerInfo {
    private String name;
    private HashMap<Integer, Integer> houses;
    private Integer store;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, Integer> getHouses() {
        return houses;
    }

    public void setHouses(HashMap<Integer, Integer> houses) {
        this.houses = houses;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "name='" + name + '\'' +
                ", houses=" + houses +
                ", store=" + store +
                '}';
    }
}
