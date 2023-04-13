package com.mmd_cnu.MapMyDay.service.googleService;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    public Map<String, List<String>> directionList(String origin, String destination, String mode) throws Exception {
        Map<String, List<String>> directionsMap = new HashMap<>();

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
                    List<String> legDirections = new ArrayList<>();
                    for (int j = 0; j < result.routes[0].legs[i].steps.length; j++) {
                        if (result.routes[0].legs[i].steps[j].travelMode == TravelMode.TRANSIT) {
                            String transitDetails = result.routes[0].legs[i].steps[j].transitDetails.headsign + " " +
                                    result.routes[0].legs[i].steps[j].transitDetails.numStops + " " +
                                    result.routes[0].legs[i].steps[j].transitDetails.line.shortName;
                            legDirections.add(transitDetails);
                        } else {
                            String stepInstructions = result.routes[0].legs[i].steps[j].htmlInstructions;
                            legDirections.add(stepInstructions);
                        }
                    }
                    directionsMap.put("directions" + (i + 1), legDirections);

                    // 시간과 요금 정보를 가져와서 리스트에 추가합니다.
                    List<String> legInfo = new ArrayList<>();
                    legInfo.add(result.routes[0].legs[i].duration.humanReadable);
                    legInfo.add(result.routes[0].legs[i].distance.humanReadable);
                    directionsMap.put("info" + (i + 1), legInfo);
                }
            }
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return directionsMap;
    }
//    public List<String> directionList(String origin, String destination, String mode) throws Exception {
//        List<String> directionsList = new ArrayList<>();
//
//        // GeoApiContext를 생성합니다.
//        GeoApiContext context = new GeoApiContext.Builder()
//                .apiKey(apiKey)
//                .build();
//
//        // DirectionsApiRequest를 생성합니다.
//        DirectionsApiRequest request = DirectionsApi.getDirections(context, origin, destination);
//
//        // 이동 수단(mode)을 설정합니다.
//        if (mode != null) {
//            request.mode(TravelMode.valueOf(mode));
//        }
//
//        // DirectionsResult를 반환합니다.
//        try {
//            DirectionsResult result = request.await();
//            if (result.routes != null && result.routes.length > 0) {
//                for (int i = 0; i < result.routes[0].legs.length; i++) {
//                    directionsList.add(result.routes[0].legs[i].startAddress);
//                    for (int j = 0; j < result.routes[0].legs[i].steps.length; j++) {
//                        if (result.routes[0].legs[i].steps[j].travelMode == TravelMode.TRANSIT) {
//                            String transitDetails = result.routes[0].legs[i].steps[j].transitDetails.headsign + " " +
//                                    result.routes[0].legs[i].steps[j].transitDetails.numStops + " " +
//                                    result.routes[0].legs[i].steps[j].transitDetails.line.shortName;
//                            directionsList.add(transitDetails);
//                        } else {
//                            String stepInstructions = result.routes[0].legs[i].steps[j].htmlInstructions;
//                            directionsList.add(stepInstructions);
//                        }
//                    }
//                    directionsList.add(result.routes[0].legs[i].endAddress);
//                }
//            }
//        } catch (ApiException | InterruptedException | IOException e) {
//            e.printStackTrace();
//        }
//
//        return directionsList;
//    }

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
    
    // 입력 값 : 장소이름, 장소유형, 현재 위도, 현재 경도
    public List<String[]> searchPlaces(String placeName, String placeType, double latitude, double longitude) throws Exception {

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        LatLng location = new LatLng(latitude, longitude);

        PlacesSearchResponse response = PlacesApi.textSearchQuery(context, placeName)
                .location(location)
                .radius(10000) // 반경 10km 내에서 검색
                .type(PlaceType.valueOf(placeType)) // 장소 유형 검색
                .rankby(RankBy.DISTANCE) // 거리순으로 정렬
                .await();

        List<String[]> resultList = new ArrayList<>();

        for (PlacesSearchResult result : response.results) {
            String[] placeInfo = new String[2];
            // 이름과 장소 위치만 반환
            placeInfo[0] = result.name;
            placeInfo[1] = result.formattedAddress;
            resultList.add(placeInfo);
        }

        return resultList;
    }
}
