package com.example.calendarassistant.models;

public class MeetingStatus {
   Status status;
   Meeting meeting;

   public MeetingStatus(Status status, Meeting meeting) {
      this.status = status;
      this.meeting = meeting;
   }

   public Status getMeetingStatus() {
      return status;
   }

   public void setMeetingStatus(Status status) {
      this.status = status;
   }

   public Meeting getMeeting() {
      return meeting;
   }

   public void setMeeting(Meeting meeting) {
      this.meeting = meeting;
   }
}
