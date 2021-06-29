package com.example.calendarassistant.impl;

import com.example.calendarassistant.models.Calendar;
import com.example.calendarassistant.models.FreeSlot;
import com.example.calendarassistant.models.Meeting;
import org.springframework.stereotype.Controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.UUID;

@Controller
public class CalendarAssistantResources {
    CalendarAssistantResources(){
        initResources();
    }

    Map<String,Calendar> map;

    public void initResources(){
        map=new HashMap<>();

        //Dummy data for testing
        //First person's calendar
        List<Meeting> meetingList1 =new ArrayList<>();
        Calendar calendar1=new Calendar("101", meetingList1);
        map.put("101", calendar1);

        //Second person's calendar
        List<Meeting> meetingList2 =new ArrayList<>();
        Calendar calendar2=new Calendar("102", meetingList2);
        map.put("102", calendar2);

        //Third person's calendar
        List<Meeting> meetingList3 =new ArrayList<>();
        Calendar calendar3=new Calendar("103", meetingList3);
        map.put("103", calendar3);

        //Fourth person's calendar
        List<Meeting> meetingList4 =new ArrayList<>();
        Calendar calendar4=new Calendar("104", meetingList4);
        map.put("104", calendar4);

        //Fifth person's calendar
        List<Meeting> meetingList5 =new ArrayList<>();
        Calendar calendar5=new Calendar("105", meetingList5);
        map.put("105", calendar5);
    }

    public boolean isAvailable(String calendarId, LocalDateTime startTime, int durationInMinutes) {
        List<Meeting> meetings =map.get(calendarId).getMeetings();
        LocalDateTime endTime=startTime.plusMinutes(durationInMinutes);
        for(Meeting meeting : meetings){
            if(startTime.isBefore(meeting.getEndTime()) && endTime.isAfter(meeting.getStartTime()))
                return false;
        }
        return true;
    }

    public Meeting bookMeeting(String calendarId, LocalDateTime startTime, int durationInMinutes) {
        List<Meeting> meetings =map.get(calendarId).getMeetings();
        Meeting meeting =new Meeting(UUID.randomUUID().toString(), startTime, durationInMinutes);
        meeting.getCalendarIds().add(calendarId);
        meetings.add(meeting);
        return meeting;
    }

    public List<String> getConflictingParticipants(Meeting meeting) {
        Set<String> calendarIds=meeting.getCalendarIds();
        List<String> participants=new ArrayList<>();
        for(String calendarId:calendarIds){
            if(!isAvailable(calendarId, meeting.getStartTime(), (int) Duration.between(meeting.getStartTime(), meeting.getEndTime()).toMinutes()))
                participants.add(calendarId);
        }
        return participants;
    }

    public Set<FreeSlot> getFreeSlots(String calendarId1, String calendarId2, int durationInMinutes) {
        Set<FreeSlot> freeSlots =new HashSet<>();
        List<FreeSlot> freeSlots1 = findFreeSlots(map.get(calendarId1).getMeetings());
        List<FreeSlot> freeSlots2 = findFreeSlots(map.get(calendarId2).getMeetings());

        PriorityQueue<FreeSlot> pq = new PriorityQueue<FreeSlot>(new Comparator<FreeSlot>(){
         public int compare(FreeSlot m1, FreeSlot m2){
                return (int) Duration.between(m2.getStartTime(), m1.getStartTime()).toMinutes();
            }
       });

        for(FreeSlot freeSlot : freeSlots1) {
            if (Duration.between(freeSlot.getStartTime(), freeSlot.getEndTime()).toMinutes() >= durationInMinutes)
                pq.offer(freeSlot);
        }

        for(FreeSlot freeSlot : freeSlots2) {
            if (Duration.between(freeSlot.getStartTime(), freeSlot.getEndTime()).toMinutes() >= durationInMinutes)
                pq.offer(freeSlot);
        }

        while(pq.size()>1) {
            FreeSlot poll = pq.poll();
            FreeSlot peek = pq.peek();
            if (Duration.between(peek.getStartTime(), poll.getEndTime()).toMinutes() >= durationInMinutes) {
                FreeSlot freeSlot=new FreeSlot(peek.getId(), peek.getStartTime(), 0);
                freeSlot.setEndTime(peek.getEndTime());
                freeSlots.add(freeSlot);
            }
        }
        return freeSlots;
    }

    private List<FreeSlot> findFreeSlots(List<Meeting> meetings) {
        LocalDateTime startTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<FreeSlot> freeSlots =new ArrayList<>();
        for(Meeting meeting : meetings){
            LocalDateTime end= meeting.getStartTime();
            FreeSlot free=new FreeSlot(UUID.randomUUID().toString(),startTime, 0);
            free.setEndTime(end);
            freeSlots.add(free);
            startTime=meeting.getEndTime();
        }
        FreeSlot free = new FreeSlot(UUID.randomUUID().toString(), startTime, 10000);
        freeSlots.add(free);
        return freeSlots;
    }
}
