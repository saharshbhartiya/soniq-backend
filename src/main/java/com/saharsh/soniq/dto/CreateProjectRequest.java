package com.saharsh.soniq.dto;

public class CreateProjectRequest {
    private String title;
    private int bpm;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getBpm() { return bpm; }
    public void setBpm(int bpm) { this.bpm = bpm; }
}