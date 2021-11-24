package com.company.buildings.dwelling;

import com.company.buildings.PlacementExchanger;
import com.company.buildings.Space;
import com.company.exceptions.InvalidRoomsCountException;
import com.company.exceptions.InvalidSpaceAreaException;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class Flat implements Space, Serializable, Cloneable {
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
    public int hashCode() {
        byte[] arrDoubleBytes;
        double areaDouble = getArea();
        //преобразуем общую сумму площадей в byte[]
        arrDoubleBytes = ByteBuffer.allocate(8).putDouble(areaDouble).array();
        byte[] arrFirstBytes = new byte[4];
        byte[] arrLastBytes = new byte[4];

        // в первый массив - первые 4 байта, во второй - последние 4 байта
        for (int i = 0; i < 4; i++) {
            arrFirstBytes[i] = arrDoubleBytes[i];
            arrLastBytes[i] = arrDoubleBytes[4 + i];
        }

        int intFirstByte = ByteBuffer.wrap(arrFirstBytes).getInt();
        int intSecondByte = ByteBuffer.wrap(arrLastBytes).getInt();

        return getNumberRooms() ^ intFirstByte ^ intSecondByte;
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

    @Override
    public int compareTo(Space o) {
        if(this.getArea() > o.getArea())
            return 1;
        if(this.getArea() == o.getArea())
            return 0;
        return -1;
    }
}
