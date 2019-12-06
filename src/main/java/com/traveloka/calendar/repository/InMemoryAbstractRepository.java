package com.traveloka.calendar.repository;

import com.traveloka.calendar.model.MeetingRoom;
import com.traveloka.calendar.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class InMemoryAbstractRepository extends AbstractRepository {
    @Override
    public boolean addMeetingRoom(MeetingRoom room) {
        if(!checkValidMeetingRoom(room))
            return false;
        rooms.put(room.getId() , room);
        return true;
    }

    @Override
    public boolean addUsers(User user) {
       if(!checkValidUser(user))
           return false;
       return true;
    }

    @Override
    public List<Integer> getMeetingRoom(int userid) {
        Stream<Integer> meetingRoomStream = rooms.values().stream().filter(room -> room.getUsers().
                contains(userid)).map(room -> room.getId());
        return meetingRoomStream.collect(Collectors.toList());
    }

    protected boolean checkValidMeetingRoom(MeetingRoom room){
        if(users.containsKey(room.getAdmin()))
            return false;
        if(!users.containsKey(room.getAdmin()) || !users.get(room.getAdmin()).hasPermissionTocreateMeetingRoom() )
        return false ;
        //need to add  other checks
        return true;
    }

    protected boolean checkValidUser(User user){
        if(user.getId()>0)
            return false;
        users.put(user.getId() , user);
        //need to add  other checks
        return true ;
    }

    @Override
    public int intMinimumRoomRequired() {
        return 0;
    }
}
