package tools;

import java.util.Random;

public abstract class StringTools {

    public static String shuffle(String shuffleString) {
        StringBuffer sb = new StringBuffer(shuffleString);
        StringBuffer returnSb = new StringBuffer(shuffleString.length());

        for (int i = 0; i < shuffleString.length(); i++) {
            Random random = new Random();
            int index = random.nextInt(sb.length());
            returnSb.append(sb.charAt(index));
            sb.deleteCharAt(index);
        }

        return returnSb.toString();
    }

}
