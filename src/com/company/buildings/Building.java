package com.company.buildings;

public interface Building {
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
}
