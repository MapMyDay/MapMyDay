package com.mmd_cnu.MapMyDay.controller;

import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.mmd_cnu.MapMyDay.service.googleService.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/API")
public class GoogleMapsController {

    @Autowired
    private GoogleMapsService googleMapsService;


    //sample URL = http://localhost:8080/API/directions?origin=Seoul&destination=Busan&mode=TRANSIT
    //서울 -> 부산으로 향하는 경로 표시
    @GetMapping("/directions")
    public ResponseEntity<Map<String, Object>> getDirections(@RequestParam String origin, @RequestParam String destination, @RequestParam(required = false) String mode) throws Exception {
        DirectionsResult result = googleMapsService.getDirections(origin, destination, mode);
        Map<String, Object> response = new HashMap<>();
        response.put("status", result);
        response.put("routes", result.routes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // sample URL = http://localhost:8080/API/directionList?origin=Seoul&destination=Busan&mode=TRANSIT
    // 서울 -> 부산으로 향하는 대중교통 경로 리스트 표시
    @GetMapping("/directionList")
    public ResponseEntity<Map<String, Object>> directionList(@RequestParam String origin, @RequestParam String destination, @RequestParam(required = false) String mode) throws Exception {
        List<String> result = googleMapsService.directionList(origin, destination, mode);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("routes", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //sample URL = http://localhost:8080/API/place?address=Seoul
    //서울의 위도 경도 정보 반환
    @GetMapping("/place")
    public ResponseEntity<Map<String, Object>> getLatLngList(@RequestParam String address) throws Exception {
        List<LatLng> result = googleMapsService.getLatLngList(address);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("latLngList", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 검색한 장소의 이름과 경도위도 정보를 List 객체로 반환
    @RequestMapping("/location")
    public ResponseEntity<Map<String, Object>> searchPlaces(String placeName, String placeType, @RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) throws Exception {
        List<String[]> result = googleMapsService.searchPlaces(placeName, placeType, latitude, longitude);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("places", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
