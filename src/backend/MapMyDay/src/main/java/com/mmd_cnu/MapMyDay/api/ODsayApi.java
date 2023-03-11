package com.mmd_cnu.MapMyDay.api;
import com.mmd_cnu.MapMyDay.model.location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
public class ODsayApi {
    String apiKey;


    public ODsayApi() throws IOException {
        // ODsay Api Key 정보
        apiKey = "4zVzlDJLvu3atqAt+eZOeEGXHYifZ9AUVo7A+/Ck0/s";
    }

    public String findingWay(location from, location to) throws IOException {
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

        // 결과 출력
        return sb.toString();
    }
}
