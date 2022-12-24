package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
       super(emailId,Integer.MAX_VALUE);
        calendar=new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        Collections.sort(calendar, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if (o1.getEndTime().compareTo(o2.getEndTime())>0) {
                    return 1;
                }
                else if(o1.getStartTime().compareTo(o2.getStartTime())>0){
                    return 1;
                }

                return -1;
            }
        });
//        for(Meeting m : calendar){
//            System.out.println(m.toString());
//        }
        int count=1;
        LocalTime maxlocaltime = calendar.get(0).getEndTime();
        for(int i=1; i< calendar.size();i++){
            if(calendar.get(i).getStartTime().compareTo(maxlocaltime)>0){
               count++;
               maxlocaltime=calendar.get(i).getEndTime();
            }

        }
        return count;
    }

}
