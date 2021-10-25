package com.company.buildings;

public interface Floor {
    int getNumberSpaces();

    float getSumAreas();

    int getNumberRooms();

    Space[] getArraySpaces();

    Space getSpaceByNumber(int numberSpace);

    void setSpaceByNumber(int numberSpace, Space space);

    void addSpaceByNumber(int numberSpace, Space space);

    void eraseSpaceByNumber(int numberSpace);

    Space getBestSpace();

}
