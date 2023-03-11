package com.mmd_cnu.MapMyDay.controller;

import com.mmd_cnu.MapMyDay.model.MapData;
import com.mmd_cnu.MapMyDay.model.MapStatus;
import com.mmd_cnu.MapMyDay.service.MapService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {
    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }


    @RequestMapping("/maps")
    public List<MapData> mapsPage(Model model) {
        var maps = mapService.getAllMaps();
        return maps;
    }

    @GetMapping("/insertMaps")
    public void insertMaps() {
        mapService.createMap(MapStatus.INCOMPLETE);
    }
}
