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
@RequestMapping("/api/tracks")
public class TrackOperationsController {
    @Autowired private TrackService trackService;
    @Autowired private RegionService regionService;

    @PostMapping("/{trackId}/regions")
    public Region addRegionToTrack(@PathVariable Long trackId, @RequestBody RegionRequest request) {
        return regionService.addRegionToTrack(trackId, request.getStartTime(), request.getDuration(), request.getAudioURL());
    }

    @PostMapping("/{trackId}/lock")
    public Track lockTrack(@PathVariable Long trackId, @RequestBody LockRequest request) {
        return trackService.lockTrack(trackId, request.getUserId());
    }

    @PostMapping("/{trackId}/unlock")
    public Track unlockTrack(@PathVariable Long trackId, @RequestBody LockRequest request) {
        return trackService.unlockTrack(trackId, request.getUserId());
    }
}