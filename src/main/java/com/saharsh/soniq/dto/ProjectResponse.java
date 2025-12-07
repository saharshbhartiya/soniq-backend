package com.saharsh.soniq.dto;

public class ProjectResponse {
    private Long id;
    private String title;
    private int bpm;
    private OwnerResponse owner;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getBpm() { return bpm; }
    public void setBpm(int bpm) { this.bpm = bpm; }
    public OwnerResponse getOwner() { return owner; }
    public void setOwner(OwnerResponse owner) { this.owner = owner; }
}