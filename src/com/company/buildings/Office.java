package com.company.buildings;

import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;

public class Office implements Space, Serializable {
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
        return "Flat (" + area + "; " + rooms + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (!(object instanceof Office))
            return false;
        Office office = (Office) object;
        return PlacementExchanger.checkSwapSpace(this, office);

    }

    @Override
    public int hashCode() {
        int result;
        byte[] arrDoubleBytes;
        double areaDouble = (double) getArea();
        //преобразуем общую сумму площадей в byte[]
        arrDoubleBytes = ByteBuffer.allocate(8).putDouble(areaDouble).array();
        byte[] arrFirstBytes = new byte[4];
        byte[] arrLastBytes = new byte[4];
        // в первый массив - первые 4 байта, во второй - последние 4 байта
        for (int i = 0; i < 4; i++) {
            arrFirstBytes[i] = arrDoubleBytes[i];
            arrLastBytes[i] = arrDoubleBytes[7 - i];
        }

        int intFirstByte = ByteBuffer.wrap(arrFirstBytes).getInt();
        int intSecondByte = ByteBuffer.wrap(arrLastBytes).getInt();

        return getNumberRooms() ^ intFirstByte ^ intSecondByte;
    }


}
