package com.mmd_cnu.MapMyDay.service;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsService {

    // 구글 맵 API 키를 application.properties에서 가져옵니다.
    @Value("${google.maps.apikey}")
    private String apiKey;

    public DirectionsResult getDirections(String origin, String destination, String mode) throws Exception {
        // GeoApiContext를 생성합니다.
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        // DirectionsApiRequest를 생성합니다.
        DirectionsApiRequest request = DirectionsApi.getDirections(context, origin, destination);

        // 이동 수단(mode)을 설정합니다.
        if (mode != null) {
            request.mode(mode);
        }

        // DirectionsResult를 반환합니다.
        return request.await();
    }
}