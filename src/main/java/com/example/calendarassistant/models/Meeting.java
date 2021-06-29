package com.example.calendarassistant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Meeting extends Slot{
    @JsonIgnore
    Set<String> calendarIds;

    public Meeting() {
        super();
    }

    public Meeting(String id, LocalDateTime startTime, int durationInMinutes) {
        super(id,startTime,durationInMinutes);
        this.calendarIds=new HashSet<>();
    }

    public Set<String> getCalendarIds() {
        return calendarIds;
    }

    public void setCalendarIds(Set<String> calendarIds) {
        this.calendarIds = calendarIds;
    }
}
