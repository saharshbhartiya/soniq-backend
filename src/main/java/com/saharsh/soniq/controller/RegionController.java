package com.saharsh.soniq.controller;

import com.saharsh.soniq.dto.UpdateRegionRequest;
import com.saharsh.soniq.entity.Region;
import com.saharsh.soniq.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/regions")
public class RegionController {
    @Autowired private RegionService regionService;

    @PutMapping("/{regionId}")
    public Region updateRegionStartTime(@PathVariable Long regionId, @RequestBody UpdateRegionRequest request) {
        return regionService.updateRegionStartTime(regionId, request.getNewStartTime());
    }
}