package com.mmd_cnu.MapMyDay.service.googleService;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            request.mode(TravelMode.valueOf(mode));
        }

        // DirectionsResult를 반환합니다.
        return request.await();
    }

    public List<String> directionList(String origin, String destination, String mode) throws Exception {
        List<String> directionsList = new ArrayList<>();

        // GeoApiContext를 생성합니다.
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        // DirectionsApiRequest를 생성합니다.
        DirectionsApiRequest request = DirectionsApi.getDirections(context, origin, destination);

        // 이동 수단(mode)을 설정합니다.
        if (mode != null) {
            request.mode(TravelMode.valueOf(mode));
        }

        // DirectionsResult를 반환합니다.
        try {
            DirectionsResult result = request.await();
            if (result.routes != null && result.routes.length > 0) {
                for (int i = 0; i < result.routes[0].legs.length; i++) {
                    directionsList.add(result.routes[0].legs[i].startAddress);
                    for (int j = 0; j < result.routes[0].legs[i].steps.length; j++) {
                        if (result.routes[0].legs[i].steps[j].travelMode == TravelMode.TRANSIT) {
                            String transitDetails = result.routes[0].legs[i].steps[j].transitDetails.headsign + " " +
                                    result.routes[0].legs[i].steps[j].transitDetails.numStops + " " +
                                    result.routes[0].legs[i].steps[j].transitDetails.line.shortName;
                            directionsList.add(transitDetails);
                        } else {
                            String stepInstructions = result.routes[0].legs[i].steps[j].htmlInstructions;
                            directionsList.add(stepInstructions);
                        }
                    }
                    directionsList.add(result.routes[0].legs[i].endAddress);
                }
            }
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return directionsList;
    }

    public List<LatLng> getLatLngList(String address) throws Exception {
        // GeoApiContext를 생성합니다.
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
        List<LatLng> latLngList = new ArrayList<>();
        for (GeocodingResult result : results) {
            latLngList.add(result.geometry.location);
        }
        return latLngList;
    }
}