package com.mmd_cnu.MapMyDay.controller;


import com.google.maps.model.DirectionsResult;
import com.mmd_cnu.MapMyDay.entity.Place;
import com.mmd_cnu.MapMyDay.service.googleService.GoogleMapsService;
import com.mmd_cnu.MapMyDay.service.googleService.PlaceService;
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
@RequestMapping("/utils")
public class UtilController {

    private final PlaceService placeService;
    private GoogleMapsService googleMapsService;


    public UtilController(PlaceService placeService, GoogleMapsService googleMapsService) {
        this.placeService = placeService;
        this.googleMapsService = googleMapsService;
    }

    @GetMapping("/totalPlacePrice")
    public ResponseEntity<Integer> getTotalPlaces() {
        int total = 0;
        for (Place place:placeService.getPlaces()) {
            total += place.getTotalPrice();
        };
        return ResponseEntity.ok(total);
    }

    @GetMapping("/totalPlaceSpenTime")
    public ResponseEntity<Integer> getSpenTime() {
        int total = 0;
        for (Place place:placeService.getPlaces()) {
            total += place.getSpenTime();
        };
        return ResponseEntity.ok(total);
    }
}
