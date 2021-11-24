package com.company.buildings;

import java.util.Comparator;

public class CriterionSpace implements Comparator<Space> {

    @Override
    public int compare (Space space1, Space space2){
        if(space1.getNumberRooms() < space2.getNumberRooms())
            return 1;
        if(space1.getNumberRooms() > space2.getNumberRooms())
            return -1;
        return 0;
    }


}
