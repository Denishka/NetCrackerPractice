package com.company.buildings;

import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

import java.io.Serializable;

public class Flat implements Space, Serializable {
    final static float DEFAULT_AREA = 50;
    final static int DEFAULT_ROOMS = 2;

    private float area;
    private int rooms;

    public Flat() {
        area = DEFAULT_AREA;
        rooms = DEFAULT_ROOMS;
    }

    public Flat(float areaNew) {
        rooms = DEFAULT_ROOMS;
    }

    public Flat(float areaNew, int roomsNew) {
        if (roomsNew < 1)
            throw new InvalidRoomsCountException("Invalid rooms number");
        if (areaNew < 1)
            throw new InvalidSpaceAreaException("Invalid space area");
        area = areaNew;
        rooms = roomsNew;
    }

    public int getNumberRooms() {
        return rooms;
    }

    public float getArea() {
        return area;
    }

    public void setNumberRooms(int newNumberRooms) {
        if (newNumberRooms < 1)
            throw new InvalidRoomsCountException("Invalid rooms number");
        rooms = newNumberRooms;
    }

    public void setArea(float newArea) {
        if (newArea < 1)
            throw new InvalidSpaceAreaException("Invalid space area");
        area = newArea;
    }


    @Override
    public String toString() {
        return "Flat{" +
                "" + area +
                ", " + rooms +
                '}';
    }


    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (!(object instanceof Flat))
            return false;
        Flat flat = (Flat) object;
        return PlacementExchanger.checkSwapSpace(this, flat);

    }


    @Override
    public Object clone(){
        Object result = null;

        try {
            result = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
