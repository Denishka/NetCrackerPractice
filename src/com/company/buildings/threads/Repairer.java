package com.company.buildings.threads;

import com.company.buildings.Floor;
import com.company.buildings.Space;
import com.company.buildings.dwelling.DwellingFloor;
import com.company.buildings.office.OfficeFloor;

public class Repairer extends Thread{
    private Floor floor;
    public Repairer (Floor floor1){
        floor = floor1;
    }

    public void readSpaces (){
        Space[] arraySpaces = floor.getArraySpaces();
        int counter=0;
        for (Space current:arraySpaces) {
            System.out.printf("Repairing space number %d with total area %f square meters", counter, current.getArea());
            counter++;
        }
    }

    @Override
    public void run(){
        readSpaces();
    }




}
