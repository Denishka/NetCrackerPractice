package com.company.buildings;

public interface Space extends Comparable<Space> {
    int getNumberRooms();

    void setNumberRooms(int newNumberRooms);

    float getArea();

    void setArea(float newArea);

     Object clone();
}
