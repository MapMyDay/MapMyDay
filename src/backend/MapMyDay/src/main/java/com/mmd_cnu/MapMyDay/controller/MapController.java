package com.mmd_cnu.MapMyDay.controller;

import com.mmd_cnu.MapMyDay.service.MapService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }


    @GetMapping("/maps")
    public String mapsPage(Model model) {
        var maps = mapService.getAllMaps();
        model.addAttribute("maps", maps);
        return "map-list";
    }
}
