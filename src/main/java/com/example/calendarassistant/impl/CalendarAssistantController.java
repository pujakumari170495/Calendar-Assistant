package com.example.calendarassistant.impl;

import com.example.calendarassistant.models.FreeSlot;
import com.example.calendarassistant.models.MeetingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/calendar-assistant")
public class CalendarAssistantController {

    @Autowired
    CalendarAssistantImpl calendarAssistant;

    @RequestMapping(value = "/book-meeting",
    method = RequestMethod.POST)
    public MeetingStatus bookIndividual(@RequestParam("calendarId") String calendarId,
                                        @RequestParam("startTime") String startTime,
                                        @RequestParam("duration") int durationInMinutes) {
            return calendarAssistant.bookMeeting(calendarId,startTime,durationInMinutes);
    }

    @RequestMapping(value="/free-slots")
    public Set<FreeSlot> bookForTwo(@RequestParam("calendarId1") String calendarId1,
                                     @RequestParam("calendarId2") String calendarId2,
                                     @RequestParam("duration") int durationInMinutes) {
        return calendarAssistant.getFreeSlots(calendarId1,calendarId2,durationInMinutes);
    }

    @RequestMapping("/conflicts")
    public List<String> getConflictingParticipants(@RequestParam("meetingId") String meetingId,
                                                   @RequestParam("startTime") String startTime,
                                                   @RequestParam("duration") int durationInMinutes,
                                                   @RequestParam("calendarIds") Set<String> calendarIds) {
        return calendarAssistant.getConflictingParticipants(meetingId,startTime, durationInMinutes,calendarIds);
    }
}
