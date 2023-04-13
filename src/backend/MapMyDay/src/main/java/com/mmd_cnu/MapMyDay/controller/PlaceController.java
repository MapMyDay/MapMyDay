package com.mmd_cnu.MapMyDay.controller;

import com.mmd_cnu.MapMyDay.entity.Place;
import com.mmd_cnu.MapMyDay.model.request.PlaceRequest;
import com.mmd_cnu.MapMyDay.service.googleService.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity<Place> createPlace(@RequestBody PlaceRequest placeRequest) {
        return ResponseEntity.ok(placeService.createPlace(placeRequest));
    }

    @GetMapping
    public ResponseEntity<List<Place>> getPlaces() {
        return ResponseEntity.ok(placeService.getPlaces());
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<Place> getPlace(@PathVariable("placeId") Integer placeId) {
        return ResponseEntity.ok(placeService.getPlace(placeId).orElse(null));
    }

    @PutMapping("/{placeId}")
    public ResponseEntity<Place> updatePlace(@PathVariable("placeId")Integer placeId,
                                           @RequestBody PlaceRequest placeRequest) {
        return ResponseEntity.ok(placeService.updatePlace(placeId, placeRequest).orElse(null));
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<Void> deletePlace(@PathVariable("placeId") Integer placeId) {
        placeService.deletePlace(placeId);

        return ResponseEntity.noContent().build();
    }


}
