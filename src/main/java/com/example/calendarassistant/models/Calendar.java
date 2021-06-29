package com.example.calendarassistant.models;

import java.util.List;

public class Calendar {
    String id;
    List<Meeting> meetings;

    public Calendar() {
    }

    public Calendar(String id, List<Meeting> meetings) {
        this.id = id;
        this.meetings = meetings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }
}
