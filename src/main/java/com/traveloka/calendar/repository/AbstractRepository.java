package com.traveloka.calendar.repository;

import com.traveloka.calendar.model.MeetingRoom;
import com.traveloka.calendar.model.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractRepository {
    Map<Integer, MeetingRoom> rooms;
    Map<Integer, User> users;

    public abstract boolean addMeetingRoom(MeetingRoom room);

    public abstract boolean addUsers(User user);

    public abstract List<Integer> getMeetingRoom(int userid);

    protected abstract boolean checkValidMeetingRoom(MeetingRoom room);

    protected abstract boolean checkValidUser(User user);

    public abstract int intMinimumRoomRequired();
}
