package com.company.buildings;

import com.company.exceptions.InexchangeableSpacesException;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

public class PlacementExchanger {

    public static boolean checkSwapSpace(Space a, Space b)
    {
        return (a.getArea() == b.getArea() && a.getNumberRooms() == b.getNumberRooms());
    }

    public boolean checkSwapFloor (Floor a, Floor b)
    {
        return (a.getSumAreas() == b.getSumAreas() && a.getNumberSpaces() == b.getNumberRooms());
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) {
        Space[] arraySpaceA = floor1.getArraySpaces();
        Space[] arraySpaceB = floor2.getArraySpaces();
        if(arraySpaceA.length != arraySpaceB.length)
            throw new SpaceIndexOutOfBoundsException("Lenght floor1 != lenght floor2");
        for (int i = 0; i < arraySpaceA.length; i++) {
            if(!checkSwapSpace(arraySpaceA[i], arraySpaceB[i])){

            }


        }


    }
}
