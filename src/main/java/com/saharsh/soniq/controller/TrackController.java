package com.saharsh.soniq.controller;

import com.saharsh.soniq.dto.LockRequest;
import com.saharsh.soniq.dto.RegionRequest;
import com.saharsh.soniq.entity.Region;
import com.saharsh.soniq.entity.Track;
import com.saharsh.soniq.service.RegionService;
import com.saharsh.soniq.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class TrackController {
    @Autowired private TrackService trackService;
    @Autowired private RegionService regionService;

    @PostMapping("/{projectId}/tracks")
    public Track addTrackToProject(@PathVariable Long projectId, @RequestBody com.saharsh.soniq.dto.RegisterRequest request) {
        return trackService.addTrackToProject(projectId, request.getName());
    }
}