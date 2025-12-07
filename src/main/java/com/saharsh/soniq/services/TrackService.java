package com.saharsh.soniq.services;

import com.saharsh.soniq.entity.Project;
import com.saharsh.soniq.entity.Track;
import com.saharsh.soniq.entity.User;
import com.saharsh.soniq.repository.ProjectRepository;
import com.saharsh.soniq.repository.TrackRepository;
import com.saharsh.soniq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrackService {
    @Autowired private TrackRepository trackRepository;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private UserRepository userRepository;

    @Transactional
    public Track addTrackToProject(Long projectId, String trackName) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found: " + projectId));
        Track newTrack = new Track();
        newTrack.setName(trackName);
        newTrack.setProject(project);
        return trackRepository.save(newTrack);
    }

    @Transactional
    public Track lockTrack(Long trackId, Long userId) {
        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new RuntimeException("Track not found: " + trackId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        if (track.getLockedBy() != null && !track.getLockedBy().getId().equals(userId)) {
            throw new IllegalStateException("Track locked by another user.");
        }
        track.setLockedBy(user);
        return trackRepository.save(track);
    }

    @Transactional
    public Track unlockTrack(Long trackId, Long userId) {
        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new RuntimeException("Track not found: " + trackId));
        if (track.getLockedBy() == null) return track;
        if (!track.getLockedBy().getId().equals(userId)) {
            throw new IllegalStateException("You do not hold the lock.");
        }
        track.setLockedBy(null);
        return trackRepository.save(track);
    }
}