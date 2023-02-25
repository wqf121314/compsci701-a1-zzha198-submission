package kalah.rule;

import kalah.utils.ConfigUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputRule {
    /*
       only can input 1,2,3,4,5,6 ,q,Q
     */
    public static boolean keyCheck(String key) {
        String pattern = "^[1-" + ConfigUtil.getMaximumHouses() + "qQ]{1}";
        key = key.trim();
        if (key.length() == 1) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(key);
            return !m.matches();
        }
        return true;
    }
}
