package com.example.calendarassistant.models;

import java.time.LocalDateTime;

public class Slot {
    String id;
    LocalDateTime startTime;
    LocalDateTime endTime;

    public Slot() {
    }

    public Slot(String id, LocalDateTime startTime, int durationInMinutes) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(durationInMinutes);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
