package com.company.buildings;

import java.util.Iterator;

public interface Building extends Iterable<Floor> {

    int getNumberFloors();

    int getNumberSpaces();

    float getSumAreas();

    int getNumberRooms();

    Floor[] getArrayFloors();

    Floor getFloorByNumber(int numberFloor);

    void setFloorByNumber(int numberFloor, Floor newFloor);

    Space getSpaceByNumber(int numberSpace);

    void setSpaceByNumber(int numberSpace, Space newSpace);

    void addSpaceByNumber(int numberSpace, Space newSpace);

    void eraseSpaceByNumber(int numberSpace);

    Space getBestSpace();

    Space[] getSortArraySpaces();

    Object clone();
}
