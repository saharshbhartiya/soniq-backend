package com.saharsh.soniq.entity;

import jakarta.persistence.*;

@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double startTime;
    private double duration;
    private String audioURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="track_id")
    private Track track;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public double getStartTime() { return startTime; }
    public void setStartTime(double startTime) { this.startTime = startTime; }
    public double getDuration() { return duration; }
    public void setDuration(double duration) { this.duration = duration; }
    public String getAudioURL() { return audioURL; }
    public void setAudioURL(String audioURL) { this.audioURL = audioURL; }
    public Track getTrack() { return track; }
    public void setTrack(Track track) { this.track = track; }
}