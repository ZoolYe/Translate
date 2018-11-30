package zool.utils.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author : zoolye
 * @date : 2018-11-29 10:57
 * @describe :
 */
public class HttpUtil {

    public static String getJosnByInternet(String path){
        try {

            URL url = new URL(path);

            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (200 == urlConnection.getResponseCode()){
                InputStream is = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while (-1 !=(len = is.read(buffer))){
                    baos.write(buffer,0,len);
                    baos.flush();
                }
                return baos.toString("UTF-8");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
