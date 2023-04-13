package com.mmd_cnu.MapMyDay.model.request;

import com.mmd_cnu.MapMyDay.entity.Place;
import jakarta.validation.Valid;
import lombok.Getter;

import java.util.Map;

@Getter
public class PlaceRequest {
    @Valid
    private String name; // 위치 정보 이름
    private double latitude; // 위도
    private double longitude; // 경도
    private int spenTime; // 사용 시간
    private Map<String, Integer> priceList; // 이름 : 가격

    public Place toEntity() {
        return Place.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .spenTime(spenTime)
                .priceList(priceList)
                .build();
    }
}
