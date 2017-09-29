package com.dk.thekids;

import com.dk.thekids.view.TimeTable;

public class Application {

    public static void main(String[] args) {
        TimeTable timeTable = new TimeTable();
        timeTable.createView();

        while(true){}
    }
}
