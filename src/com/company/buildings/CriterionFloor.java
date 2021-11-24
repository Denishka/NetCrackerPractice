package com.company.buildings;

import java.util.Comparator;

public class CriterionFloor implements Comparator<Floor> {
    @Override
    public int compare (Floor floor1, Floor floor2){
        if(floor1.getSumAreas() < floor2.getSumAreas())
            return 1;
        if(floor1.getSumAreas() > floor2.getSumAreas())
            return -1;
        return 0;
    }
}
