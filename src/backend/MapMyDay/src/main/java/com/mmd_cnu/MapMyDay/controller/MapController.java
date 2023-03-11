package com.mmd_cnu.MapMyDay.controller;

import com.mmd_cnu.MapMyDay.model.MapData;
import com.mmd_cnu.MapMyDay.model.MapStatus;
import com.mmd_cnu.MapMyDay.service.MapService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MapController {
    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }


    @RequestMapping("/getAllMaps")
    public List<MapData> getAllMaps() {
        var maps = mapService.getAllMaps();
        return maps;
    }

    @RequestMapping(value="/getMapsByUserId", method= RequestMethod.GET, params="user_id")
    public List<MapData> getMapsByUserId(@RequestParam UUID user_id) {
        List<MapData> maps = mapService.getMapsByUserId(user_id);
        return maps;
    }

    @RequestMapping(value="/createMap", method= RequestMethod.GET, params="user_id")
    public void createMap(@RequestParam UUID user_id) {
        mapService.createMap(user_id);
    }

    @RequestMapping(value="/deleteMap", method= RequestMethod.GET, params="map_id")
    public void deleteMap(@RequestParam UUID map_id) {
        mapService.deleteMap(map_id);
    }

}
