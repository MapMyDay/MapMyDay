package com.mmd_cnu.MapMyDay.service.googleService;
import com.mmd_cnu.MapMyDay.entity.Place;
import com.mmd_cnu.MapMyDay.model.request.PlaceRequest;
import com.mmd_cnu.MapMyDay.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    public Place createPlace(PlaceRequest placeRequest) {
        return placeRepository.save(placeRequest.toEntity());
    }

    public List<Place> getPlaces() {
        return placeRepository.findAll();
    }

    public Optional<Place> getPlace(Integer placeId) {
        return placeRepository.findById(placeId);
    }

    public Optional<Place> updatePlace(Integer placeId, PlaceRequest placeRequest) {
        return placeRepository.findById(placeId)
                .map(place -> {
                    place.setLatitude(placeRequest.getLatitude());
                    place.setLongitude(placeRequest.getLongitude());
                    place.setName(placeRequest.getName());
                    place.setSpenTime(placeRequest.getSpenTime());
                    place.setPriceList(placeRequest.getPriceList());
                    return placeRepository.save(place);
                });
    }

    public Optional<Place> updatePlaceInfo(Integer placeId, PlaceRequest placeRequest) {
        return placeRepository.findById(placeId)
                .map(place -> {
                    place.setLatitude(placeRequest.getLatitude());
                    place.setLongitude(placeRequest.getLongitude());
                    place.setName(placeRequest.getName());
                    place.setSpenTime(placeRequest.getSpenTime());
                    place.setPriceList(placeRequest.getPriceList());
                    return placeRepository.save(place);
                });
    }

    public void deletePlace(Integer postId) {
        placeRepository.findById(postId)
                .ifPresent(placeRepository::delete);
    }
}
