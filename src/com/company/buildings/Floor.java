package com.company.buildings;

public interface Floor extends Iterable<Space>, Comparable<Floor>{
    int getNumberSpaces();

    float getSumAreas();

    int getNumberRooms();

    Space[] getArraySpaces();

    Space getSpaceByNumber(int numberSpace);

    void setSpaceByNumber(int numberSpace, Space space);

    void addSpaceByNumber(int numberSpace, Space space);

    void eraseSpaceByNumber(int numberSpace);

    Space getBestSpace();

    Object clone();

    int compareTo(Floor o);
}
