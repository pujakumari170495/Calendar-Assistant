package com.example.calendarassistant.impl;

import com.example.calendarassistant.models.FreeSlot;
import com.example.calendarassistant.models.Meeting;
import com.example.calendarassistant.models.MeetingStatus;
import com.example.calendarassistant.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Controller
public class CalendarAssistantImpl {

    @Autowired
    CalendarAssistantResources calendarAssistantResources;

    public MeetingStatus bookMeeting(String calendarId, String start, int durationInMinutes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(start, formatter);

        if(calendarAssistantResources.isAvailable(calendarId,startTime, durationInMinutes)){
            Meeting meeting = calendarAssistantResources.bookMeeting(calendarId,startTime, durationInMinutes);
            return new MeetingStatus(Status.BOOKED, meeting);
        }
        return  new MeetingStatus(Status.NOT_BOOKED, new Meeting(null, startTime, durationInMinutes));
    }

    public Set<FreeSlot> getFreeSlots(String calendarId1, String calendarId2, int durationInMinutes) {
        return calendarAssistantResources.getFreeSlots(calendarId1,calendarId2,durationInMinutes);
    }

    public List<String> getConflictingParticipants(String meetingId,String start, int durationInMinutes, Set<String> calendarIds) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(start, formatter);
        Meeting meeting =new Meeting(meetingId, startTime, durationInMinutes);
        meeting.setCalendarIds(calendarIds);
        return calendarAssistantResources.getConflictingParticipants(meeting);
    }
}
