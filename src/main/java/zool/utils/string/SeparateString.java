package zool.utils.string;

/**
 * @author : zoolye
 * @date : 2018-11-29 20:09
 * @describe : 分隔字符串
 */
public class SeparateString {

    /**
     * 将字符串大写字母分隔
     * helloWord -> hello Word
     *
     * @return
     */
    public static String separateString(String str) {
        //.*?[A-Z].*?
        if (!str.matches("[A-Z]?[a-z]+[A-Z].*?")) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char[] c = str.toCharArray();
        int start;
        int end = 0;
        for (int i = 0; i < c.length; i++) {
            if ('A' <= c[i] && c[i] <= 'Z' && i != 0) {
                start = end;
                end = i;
                if (String.valueOf(c[i - 1]).equals(" ")) {
                    sb.append(str, start, end);
                } else {
                    sb.append(str.substring(start, end) + " ");
                }
            }
        }
        sb.append(str, end, c.length);
        return sb.toString();
    }

}
