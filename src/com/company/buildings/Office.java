package com.company.buildings;

import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

public class Office implements Space {
    final static float DEFAULT_AREA = 250;
    final static int DEFAULT_ROOMS = 1;

    private float area;
    private int rooms;

    public Office() {
        area = DEFAULT_AREA;
        rooms = DEFAULT_ROOMS;
    }

    public Office(float area_new) {
        if (area_new < 1)
            throw new InvalidSpaceAreaException("Invalid space area");
        area = area_new;
        rooms = DEFAULT_ROOMS;
    }

    public Office(float area_new, int rooms_new) {
        if (rooms_new < 1)
            throw new InvalidRoomsCountException("Invalid rooms number");
        if (area_new < 1)
            throw new InvalidSpaceAreaException("Invalid space area");
        area = area_new;
        rooms = rooms_new;
    }

    public int getNumberRooms() {
        return rooms;
    }

    public void setNumberRooms(int newNumberRooms) {
        if (newNumberRooms < 1)
            throw new InvalidRoomsCountException("Invalid rooms number");
        rooms = newNumberRooms;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float newArea) {
        if (newArea < 1)
            throw new InvalidSpaceAreaException("Invalid space area");
        area = newArea;
    }

    @Override
    public String toString() {
        return "Office (area:" + area + "; rooms:" + rooms + ")";
    }


}
