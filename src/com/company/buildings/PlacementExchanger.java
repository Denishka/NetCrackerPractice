package com.company.buildings;

import com.company.exceptions.FloorIndexOutOfBoundsException;
import com.company.exceptions.InexchangeableFloorsException;
import com.company.exceptions.InexchangeableSpacesException;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

public class PlacementExchanger {

    public static boolean checkSwapSpace(Space a, Space b) {
        return (a.getArea() == b.getArea() && a.getNumberRooms() == b.getNumberRooms());
    }

    public static boolean checkSwapFloor(Floor a, Floor b) {
        return (a.getSumAreas() == b.getSumAreas() && a.getNumberSpaces() == b.getNumberRooms());
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) {
        Space[] arraySpaceA = floor1.getArraySpaces();
        Space[] arraySpaceB = floor2.getArraySpaces();
        if (index1 > arraySpaceA.length - 1)
            throw new SpaceIndexOutOfBoundsException("Invalid index1");
        if (index2 > arraySpaceB.length - 1)
            throw new SpaceIndexOutOfBoundsException("Invalid index2");
        if (arraySpaceA.length != arraySpaceB.length)
            throw new SpaceIndexOutOfBoundsException("Lenght floor1 != lenght floor2");
        try {
            for (int i = 0; i < arraySpaceA.length; i++)
                if (!checkSwapSpace(arraySpaceA[i], arraySpaceB[i]))
                    throw new InexchangeableSpacesException("Not swap");
              //swap
            Space tmp = floor1.getSpaceByNumber(index1);
            floor1.setSpaceByNumber(index1, floor2.getSpaceByNumber(index2));
            floor2.setSpaceByNumber(index2, tmp);

        } catch (InexchangeableSpacesException a) {
            System.out.println(a.getMessage());
            return;
        }

    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) {
        Floor[] arrayFloorA = building1.getArrayFloors();
        Floor[] arrayFloorB = building2.getArrayFloors();
        if (index1 > arrayFloorA.length - 1)
            throw new FloorIndexOutOfBoundsException("Invalid index1");
        if (index2 > arrayFloorB.length - 1)
            throw new FloorIndexOutOfBoundsException("Invalid index2");
        try {
            if (arrayFloorA.length != arrayFloorB.length)
                throw new SpaceIndexOutOfBoundsException("Lenght floor1 != lenght floor2");

            for (int i = 0; i < arrayFloorA.length; i++)
                if (!checkSwapFloor(arrayFloorA[i], arrayFloorB[i]))
                    throw new InexchangeableFloorsException("Not swap");
                //swap
            Floor tmp = building1.getFloorByNumber(index1);
            building1.setFloorByNumber(index1, building2.getFloorByNumber(index2));
            building2.setFloorByNumber(index2, tmp);

        } catch (InexchangeableFloorsException a) {
            System.out.println(a.getMessage());
            return;
        }
    }








}
