import com.google.maps.DirectionsApiRequest;
import com.google.maps.DirectionsResult;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GoogleMapsServiceTest {

    @Test
    public void testGetDirections() throws Exception {
        // Google Maps API 키를 입력합니다.
        String apiKey = "YOUR_API_KEY";

        // 출발지와 목적지를 입력합니다.
        LatLng origin = new LatLng(37.5666102, 126.9783881); // 서울시청
        LatLng destination = new LatLng(37.5662952, 126.9779451); // 광화문

        // TravelMode를 설정합니다. 예시에서는 DRIVING으로 설정합니다.
        TravelMode travelMode = TravelMode.DRIVING;

        // GeoApiContext 객체를 생성합니다.
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        // DirectionsApiRequest 객체를 생성합니다.
        DirectionsApiRequest directions = new DirectionsApiRequest(context)
                .origin(origin)
                .destination(destination)
                .mode(travelMode);

        // DirectionsResult 객체를 받아옵니다.
        DirectionsResult directionsResult = directions.await();

        // DirectionsResult 객체가 null이 아닌지 확인합니다.
        assertNotNull(directionsResult);

        // DirectionsResult 객체에서 DirectionsRoute 객체를 추출합니다.
        DirectionsRoute route = directionsResult.routes[0];

        // DirectionsRoute 객체가 null이 아닌지 확인합니다.
        assertNotNull(route);

        // DirectionsRoute 객체에서 DirectionsLeg 객체를 추출합니다.
        DirectionsLeg leg = route.legs[0];

        // DirectionsLeg 객체가 null이 아닌지 확인합니다.
        assertNotNull(leg);

        // 기타 다른 검증 작업을 수행합니다.
        // ...
    }
}