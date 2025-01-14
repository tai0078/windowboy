package util;

import org.json.JSONObject;

public class JsonToEntityUtil {
    public static GoldInfo jsonObjectToGoldInfo(JSONObject jsonObject) {
        GoldInfo goldInfo = new GoldInfo();
        if (jsonObject.has("variety")) {
            goldInfo.setVariety(jsonObject.getString("variety"));
        }
        if (jsonObject.has("latestpri")) {
            goldInfo.setLatestpri(jsonObject.getString("latestpri"));
        }
        if (jsonObject.has("openpri")) {
            goldInfo.setOpenpri(jsonObject.getString("openpri"));
        }
        if (jsonObject.has("maxpri")) {
            goldInfo.setMaxpri(jsonObject.getString("maxpri"));
        }
        if (jsonObject.has("minpri")) {
            goldInfo.setMinpri(jsonObject.getString("minpri"));
        }
        if (jsonObject.has("limit")) {
            goldInfo.setLimit(jsonObject.getString("limit"));
        }
        if (jsonObject.has("yespri")) {
            goldInfo.setYespri(jsonObject.getString("yespri"));
        }
        if (jsonObject.has("totalvol")) {
            goldInfo.setTotalvol(jsonObject.getString("totalvol"));
        }
        if (jsonObject.has("time")) {
            goldInfo.setTime(jsonObject.getString("time"));
        }
        return goldInfo;
    }
}