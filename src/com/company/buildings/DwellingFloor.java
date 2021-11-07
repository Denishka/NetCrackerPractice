package com.company.buildings;

import com.company.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class DwellingFloor implements Floor, Serializable {

    private Space[] arrayFlat; // массив квартир

    public DwellingFloor(int countFlatFloor) {
        if (countFlatFloor < 1)
            throw new SpaceIndexOutOfBoundsException("Invalid countFlarFloor");
        arrayFlat = new Space[countFlatFloor];
        for (int i = 0; i < arrayFlat.length; i++) {
            arrayFlat[i] = new Flat(new Random().nextInt(70) + 3, new Random().nextInt(4) + 2);
        }
    }


    public DwellingFloor(Space[] arrayFlatFloor) {
        arrayFlat = arrayFlatFloor;
    }

    public int getNumberSpaces() {
        return arrayFlat.length;
    }

    public float getSumAreas() {
        float sum = 0;
        for (Space flat : arrayFlat) {
            sum += flat.getArea();
        }
        return sum;
    }

    public int getNumberRooms() {
        int sum = 0;
        for (int i = 0; i < arrayFlat.length; i++) {
            sum += arrayFlat[i].getNumberRooms();
        }
        return sum;
    }

    public Space[] getArraySpaces() {
        return arrayFlat;
    }

    public Space getSpaceByNumber(int numberSpace) {
        return arrayFlat[numberSpace];
    }

    public void setSpaceByNumber(int numberSpace, Space space) {
        if (numberSpace < 1 || numberSpace > getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        arrayFlat[numberSpace] =  space;
    }

    public void addSpaceByNumber(int newNumberFlat, Space newFlat) {
        if (newNumberFlat < 1 || newNumberFlat > getNumberSpaces() + 1)
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        Space[] tmpFlat = new Space[arrayFlat.length + 1];
        for (int i = 0, j = i; i < arrayFlat.length; i++, j++) {
            if (i == newNumberFlat) {
                tmpFlat[j] = newFlat;
                j++;
            }
            tmpFlat[j] = arrayFlat[i];
        }
        arrayFlat = tmpFlat;
    }

    public void eraseSpaceByNumber(int numberSpace) {
        if (numberSpace < 1 || numberSpace > getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        Space[] tmpArrayFlat = new Space[arrayFlat.length - 1];
        for (int i = 0, j = i; i < arrayFlat.length; i++, j++) {
            if (i == numberSpace)
                j++;
            tmpArrayFlat[j] = arrayFlat[i];
        }
        arrayFlat =  tmpArrayFlat;
    }

    public Space getBestSpace() {
        int count = 0;
        float maxArea = arrayFlat[0].getArea();
        for (int i = 1; i < arrayFlat.length; i++) {
            if (maxArea < arrayFlat[i].getArea())
                maxArea = arrayFlat[i].getArea();
            count++;
        }
        return arrayFlat[count];
    }


    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        Space[] arrayOffices = getArraySpaces();
        str.append("DwellingFloor (").append(getNumberSpaces());
        for (int i = 0; i < arrayOffices.length; i++) {
            str.append(", ").append(arrayOffices[i].toString());
        }
        str.append(")");
        return str.toString();
    }


    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof DwellingFloor))
            return false;
        DwellingFloor other  = (DwellingFloor) object;
        for (int i = 0; i < getNumberSpaces(); i++) {
           if(!(arrayFlat[i].equals(other.arrayFlat[i])))
               return false;
        }
        return true;
    }
}