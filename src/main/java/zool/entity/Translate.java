package zool.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author : zoolye
 * @date : 2018-11-29 15:00
 * @describe :
 */
public class Translate {


    private BasicBean basic;
    private String query;
    private int errorCode;
    private List<String> translation;
    private List<WebBean> web;

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public static class BasicBean {

        @SerializedName("us-phonetic")
        private String usphonetic;

        private String phonetic;

        @SerializedName("uk-phonetic")
        private String ukphonetic;

        private List<String> explains;

        public String getUsphonetic() {
            return usphonetic;
        }

        public void setUsphonetic(String usphonetic) {
            this.usphonetic = usphonetic;
        }

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getUkphonetic() {
            return ukphonetic;
        }

        public void setUkphonetic(String ukphonetic) {
            this.ukphonetic = ukphonetic;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }
    }

    public static class WebBean {

        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return toStringConvert();
    }

    public String toStringConvert() {

        StringBuffer sb = new StringBuffer();

        if (null != translation) {
            sb.append(query).append(" ：").append(translation.toString() + "\n");
        }

        if (null != basic) {

            if (null != basic.getUsphonetic()) {
                sb.append("美式 ：").append(basic.getUsphonetic() + "\n");
            }

            if (null != basic.getUkphonetic()) {
                sb.append("英式 ：").append(basic.getUkphonetic() + "\n");
            }

            if (null != basic.getExplains()) {
                sb.append("详解 ：").append(basic.getExplains() + "\n");
            }

        }

        if (null != web) {
            sb.append("网络释义 ：");
            web.stream().forEach(x-> sb.append(x.getKey()+" ："+x.getValue()+"\n"));
        }

        return sb.toString();
    }

}
