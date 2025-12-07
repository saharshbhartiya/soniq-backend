package com.saharsh.soniq.services;

import com.saharsh.soniq.entity.Region;
import com.saharsh.soniq.entity.Track;
import com.saharsh.soniq.repository.RegionRepository;
import com.saharsh.soniq.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegionService {
    @Autowired private RegionRepository regionRepository;
    @Autowired private TrackRepository trackRepository;

    @Transactional
    public Region addRegionToTrack(Long trackId, double startTime, double duration, String audioURL) {
        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new RuntimeException("Track not found: " + trackId));
        Region newRegion = new Region();
        newRegion.setStartTime(startTime);
        newRegion.setDuration(duration);
        newRegion.setAudioURL(audioURL);
        newRegion.setTrack(track);
        return regionRepository.save(newRegion);
    }

    @Transactional
    public Region updateRegionStartTime(Long regionId, double newStartTime) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RuntimeException("Region not found: " + regionId));
        region.setStartTime(newStartTime);
        return regionRepository.save(region);
    }
}