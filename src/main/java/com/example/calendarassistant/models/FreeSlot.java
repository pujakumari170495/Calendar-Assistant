package com.example.calendarassistant.models;

import java.time.LocalDateTime;

public class FreeSlot extends Slot{

    public FreeSlot(String id, LocalDateTime startTime, int duration) {
        super(id,startTime,duration);
    }

    public FreeSlot() {
        super();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + startTime.getDayOfMonth()+startTime.getMonthValue()+startTime.getHour()+startTime.getMinute();
        result = prime * result + endTime.getDayOfMonth()+endTime.getMonthValue()+endTime.getHour()+endTime.getMinute();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FreeSlot other = (FreeSlot) obj;
        if (startTime.isBefore(other.startTime) || startTime.isAfter(other.startTime))
            return false;
        if (endTime.isBefore(other.endTime) || endTime.isAfter(other.endTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FreeSlot{" +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
