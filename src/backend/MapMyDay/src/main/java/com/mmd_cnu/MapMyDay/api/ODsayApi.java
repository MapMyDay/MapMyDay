package com.mmd_cnu.MapMyDay.api;
import com.mmd_cnu.MapMyDay.model.location;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ODsayApi {
    String apiKey;


    public ODsayApi() throws IOException {
        // ODsay Api Key 정보
        apiKey = "4zVzlDJLvu3atqAt+eZOeEGXHYifZ9AUVo7A+/Ck0/s";
    }

    //두 location 사이의 대중교통 길찾기 경로를 map 형태로 반환한다
    public Map<String, Object> findingWay(location from, location to) throws IOException, JSONException {
        String SX = "SX=" + from.getLatitude();
        String SY = "SY=" + from.getLongitude();
        String EX = "EX=" + to.getLatitude();
        String EY = "EY=" + to.getLongitude();

        String urlInfo = "https://api.odsay.com/v1/api/searchPubTransPathT?" +
                SX +
                "&" +
                SY +
                "&" +
                EX +
                "&" +
                EY +
                "&apiKey=" +
                URLEncoder.encode(apiKey, "UTF-8");
        // http 연결
        URL url = new URL(urlInfo);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        bufferedReader.close();
        conn.disconnect();

        // 결과 리턴
        String jsonString = sb.toString();
        JSONObject jsonObject = new JSONObject(jsonString);
        Map<String, Object> map = new HashMap<>();
        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = jsonObject.get(key);
            map.put(key, value);
        }
        return map;
    }
}
