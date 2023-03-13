package com.mmd_cnu.MapMyDay.controller;

import com.mmd_cnu.MapMyDay.api.ODsayApi;
import com.mmd_cnu.MapMyDay.model.Location;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
public class LocationController {
    ODsayApi oDsayApi = new ODsayApi();

    public LocationController() throws IOException {
    }

    @RequestMapping("/FindWay")
    public Object mapsPage() throws JSONException, IOException {
        return oDsayApi.findingWay(new Location(UUID.randomUUID(), "from", 126.9027279, 37.5349277), new Location(UUID.randomUUID(), "to", 126.9145430, 37.5499421));
    }

//    @RequestMapping(value="/getMapsByUserId", method= RequestMethod.GET, params="user_id")
//    public List<MapData> getMapsByUserId(@RequestParam UUID user_id) {
//        List<MapData> maps = mapService.getMapsByUserId(user_id);
//        return maps;
//    }
}
