package com.company.buildings;

import com.company.exceptions.FloorIndexOutOfBoundsException;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

import java.util.Arrays;

public class Dwelling implements Building {
    private Floor[] arrayFloor;

    public Dwelling(int countFloor, int[] countFlatPerFloor) {
        arrayFloor = new Floor[countFloor];
        for (int i = 0; i < arrayFloor.length; i++) {
            arrayFloor[i] = new DwellingFloor(countFlatPerFloor[i]);
        }

    }

    public Dwelling(Floor[] arrayFloorNew) {
        arrayFloor = arrayFloorNew;
    }

    public int getNumberFloors() {
        return arrayFloor.length;
    }

    public int getNumberSpaces() {
        int sum = 0;
        for (int i = 0; i < arrayFloor.length; i++) {
            sum += arrayFloor[i].getNumberSpaces();
        }
        return sum;
    }

    public float getSumAreas() {
        int sum = 0;
        for (int i = 0; i < arrayFloor.length; i++) {
            sum += arrayFloor[i].getSumAreas();
        }
        return sum;
    }

    public int getNumberRooms() {
        int sum = 0;
        for (int i = 0; i < arrayFloor.length; i++) {
            sum += arrayFloor[i].getNumberSpaces();
        }
        return sum;
    }

    public Floor[] getArrayFloors() {
        return arrayFloor;
    }

    public Floor getFloorByNumber(int numberFloor) {
        return arrayFloor[numberFloor];
    }

    public void setFloorByNumber(int numberFloor, Floor newFloor) {
        if (numberFloor < 1 || numberFloor > getNumberFloors())
            throw new FloorIndexOutOfBoundsException("Invalid numberFloor");
        arrayFloor[numberFloor] =  newFloor;
    }

    public Space getSpaceByNumber(int numberSpace) {
        if (numberSpace < 1 || numberSpace > getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        int countFlats = 0;
        for (int i = 0; i < arrayFloor.length; i++) {
            int countFlatsInCurrentFloor = arrayFloor[i].getNumberSpaces();
            if (numberSpace < countFlats + countFlatsInCurrentFloor) {
                //ищем квартиру на текущем этаже
                int numberFlatOnCurrentFloor = numberSpace - countFlats;
                return arrayFloor[i].getSpaceByNumber(numberFlatOnCurrentFloor);
            }
            countFlats += countFlatsInCurrentFloor;

        }
        return null;
    }

    public void setSpaceByNumber(int numberSpace, Space newSpace) {
        if (numberSpace < 1 || numberSpace > getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        int countFlats = 0;
        for (int i = 0; i < arrayFloor.length; i++) {
            int countFlatsInCurrentFloor = arrayFloor[i].getNumberSpaces();
            if (numberSpace < countFlats + countFlatsInCurrentFloor) {
                //ищем квартиру на текущем этаже
                int numberFlatOnCurrentFloor = numberSpace - countFlats;
                arrayFloor[i].setSpaceByNumber(numberFlatOnCurrentFloor, newSpace);
            }
            countFlats += countFlatsInCurrentFloor;

        }
    }

    public void addSpaceByNumber(int numberSpace, Space newSpace) {
        if (numberSpace < 1 || numberSpace > getNumberSpaces() + 1)
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        Floor lastFloor = arrayFloor[arrayFloor.length - 1];
        lastFloor.addSpaceByNumber(numberSpace, newSpace);
    }

    public void eraseSpaceByNumber(int numberSpace) {
        if (numberSpace < 1 || numberSpace > getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        int countFlats = 0;
        for (int i = 0; i < arrayFloor.length; i++) {
            int countFlatsInCurrentFloor = arrayFloor[i].getNumberSpaces();
            if (numberSpace < countFlats + countFlatsInCurrentFloor) {
                //ищем квартиру на текущем этаже
                int numberFlatOnCurrentFloor = numberSpace - countFlats;
                arrayFloor[i].eraseSpaceByNumber(numberFlatOnCurrentFloor);
            }
            countFlats += countFlatsInCurrentFloor;

        }
    }

    public Space getBestSpace() {

        float maxSpace = 0;
        Space result = null;
        for (int i = 0; i < arrayFloor.length; i++) {
            for (int j = 0; j < arrayFloor[i].getNumberSpaces(); j++) {
                if (arrayFloor[i].getSpaceByNumber(j).getArea() > maxSpace) {
                    maxSpace = arrayFloor[i].getSpaceByNumber(j).getArea();
                    result = arrayFloor[i].getSpaceByNumber(j);
                }

            }
        }
        return result;
    }

    private Space[] getAllFlats() {
        int sumFlat = 0;
        int sum = 0;
        for (int i = 0; i < arrayFloor.length; i++) {
            sum += arrayFloor[i].getNumberSpaces();
        }
        Space[] arrayAllFlat = new Space[sum];
        int countFlat = 0;

        for (int i = 0; i < arrayFloor.length; i++) {
            for (int j = 0; j < arrayFloor[i].getNumberSpaces(); j++) {
                arrayAllFlat[countFlat] = arrayFloor[i].getSpaceByNumber(j);
                countFlat++;
            }
        }
        return  arrayAllFlat;
    }


    public Space[] getSortArraySpaces() {
        Space[] allFlats = getAllFlats();
        for (int out = allFlats.length - 1; out >= 1; out--) {
            for (int in = 0; in < out; in++) {
                if (allFlats[in].getArea() < allFlats[in + 1].getArea()) {

                    Space tmp = allFlats[in];
                    allFlats[in] = allFlats[in + 1];
                    allFlats[in + 1] = tmp;

                }
            }
        }
        return  allFlats;
    }

    @Override
    public String toString() {
        return "Dwelling{" +
                "arrayFloor=" + Arrays.toString(arrayFloor) +
                '}';
    }
}
