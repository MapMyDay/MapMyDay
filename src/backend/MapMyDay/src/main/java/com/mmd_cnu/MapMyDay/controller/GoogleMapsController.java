package com.mmd_cnu.MapMyDay.controller;

import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.mmd_cnu.MapMyDay.service.googleService.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoogleMapsController {

    @Autowired
    private GoogleMapsService googleMapsService;


    //sample URL = http://localhost:8080/directions?origin=Seoul&destination=Busan&mode=TRANSIT
    //서울 -> 부산으로 향하는 경로 표시
    @GetMapping("/directions")
    public DirectionsResult getDirections(@RequestParam String origin, @RequestParam String destination, @RequestParam(required = false) String mode) throws Exception {
        return googleMapsService.getDirections(origin, destination, mode);
    }


    // sample URL = http://localhost:8080/directionList?origin=Seoul&destination=Busan&mode=TRANSIT
    // 서울 -> 부산으로 향하는 대중교통 경로 리스트 표시
    @GetMapping("/directionList")
    public List<String> directionList(@RequestParam String origin, @RequestParam String destination, @RequestParam(required = false) String mode) throws Exception {
        return googleMapsService.directionList(origin, destination, mode);
    }

    //sample URL = http://localhost:8080/place?address=Seoul
    //서울의 위도 경도 정보 반환
    @GetMapping("/place")
    public List<LatLng> getLatLngList(@RequestParam String address) throws Exception {
        return googleMapsService.getLatLngList(address);
    }
}