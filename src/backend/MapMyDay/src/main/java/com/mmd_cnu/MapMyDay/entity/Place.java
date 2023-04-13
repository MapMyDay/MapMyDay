package com.mmd_cnu.MapMyDay.entity;

import com.mmd_cnu.MapMyDay.model.request.PlaceRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Getter
@Entity(name = "place")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Setter
    private String name; // 위치 정보 이름

    @Column
    @Setter
    private double latitude; // 위도

    @Column
    @Setter
    private double longitude; // 경도

    @Column
    @Setter
    private int spenTime; // 사용 시간

    @Column
    @Setter
    private int totalPrice; // 사용 시간

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "price_list",
            joinColumns = @JoinColumn(name = "place_id"))
    @MapKeyColumn(name = "menu_name")
    @Column(name = "price")
    private Map<String, Integer> priceList; // 이름 : 가격

    @Builder
    public Place(String name, double latitude, double longitude, int spenTime, Map<String, Integer> priceList) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.spenTime = spenTime;
        this.priceList = priceList;
    }

    public void setPriceList(Map<String, Integer> priceList) {
        this.priceList.clear();
        this.priceList.putAll(priceList);
        setTotalPrice(priceList);
    }

    public void setTotalPrice(Map<String, Integer> priceList) {
        int sum = 0;
        for (int value : priceList.values()) {
            sum += value;
        }
        this.totalPrice = sum;
    }
}
