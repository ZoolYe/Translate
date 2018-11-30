package zool.utils.http;

import zool.constant.TranslateURL;
import zool.utils.md5.Md5;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author : zoolye
 * @date : 2018-11-30 16:02
 * @describe : 构建URL
 */
public class BuildingURL {

    public static String buildingURL(String text) {

        String appKey = "3e47739f6377efae";
        String key = "PxyDCouSLDZGdcRsLKYmfJhz1Lp8if5h";
        String salt = String.valueOf(System.currentTimeMillis());
        String sign = Md5.md5(appKey + text + salt + key);

        StringBuilder sb = new StringBuilder();
        sb.append("from=auto&to=auto");
        sb.append("&appKey=").append(appKey);
        sb.append("&salt=").append(salt);
        sb.append("&sign=").append(sign);

        try {
            String encoder = URLEncoder.encode(text, "UTF-8");
            sb.append("&q=").append(encoder);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return TranslateURL.NETEASEURL_NEW + sb.toString();

    }

}
