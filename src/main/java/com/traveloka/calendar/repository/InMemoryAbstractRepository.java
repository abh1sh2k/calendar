package com.traveloka.calendar.repository;

import com.traveloka.calendar.model.MeetingRoom;
import com.traveloka.calendar.model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

@Component
class MeetingRoomCompare implements Comparator<MeetingRoom> {
    @Override
    public int compare(MeetingRoom o1, MeetingRoom o2) {
        if(o1.getStart().compareTo(o2.getStart()) != 0){
            return o1.getStart().compareTo(o2.getStart());
        }
        else{
            return o1.getEnd().compareTo(o2.getEnd());
        }
    }
}
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
       users.put(user.getId() , user);
       return true;
    }

    @Override
    public List<Integer> getMeetingRoom(int userid) {
        Stream<Integer> meetingRoomStream = rooms.values().stream().filter(room -> room.getUsers().
                contains(userid)).map(room -> room.getId());
        return meetingRoomStream.collect(Collectors.toList());
    }

    protected boolean checkValidMeetingRoom(MeetingRoom room){
        if(!users.containsKey(room.getAdmin()))
            return false;
        if(!users.get(room.getAdmin()).hasPermissionTocreateMeetingRoom() )
        return false ;
        //need to add  other checks
        return true;
    }

    protected boolean checkValidUser(User user){
        if(user.getId()<=0)
            return false;
        users.put(user.getId() , user);
        //need to add  other checks
        return true ;
    }

    @Override
    public int intMinimumRoomRequired() {
        if(rooms.values().size()==0)
            return 0;
        List<MeetingRoom> roomList = rooms.values().stream().collect(toCollection(ArrayList::new));
        Collections.sort(roomList ,  new MeetingRoomCompare());
        int max  =  1 ;

        PriorityQueue<MeetingRoom> queues = new PriorityQueue<>(new MeetingRoomCompare());
        for(int i = 0 ; i < roomList.size() ; i++){
            MeetingRoom curr = roomList.get(i);
            if(queues.size()==0){
                queues.add(curr);
            }
            else{
                MeetingRoom top = queues.peek();
                while(top.getEnd().compareTo(curr.getStart())<=0){
                    queues.poll();
                    if(queues.size()==0)
                        break;
                }
                queues.add(curr);
            }
            if(queues.size()> max)
                max = queues.size();
        }
        return max;
    }

}
