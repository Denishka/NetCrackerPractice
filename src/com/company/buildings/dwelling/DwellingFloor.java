package com.company.buildings.dwelling;

import com.company.buildings.Floor;
import com.company.buildings.Space;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class DwellingFloor implements Floor, Serializable, Cloneable {

    private Space[] arrayFlat; // массив квартир

    @Override
    public Iterator<Space> iterator() {
        return new DwellingFloorIterator();
    }


    //пользовательский класс итератор
    public class DwellingFloorIterator implements Iterator<Space>
    {
        ///-1 проверяем есть ли начальный элемент
        int currentPos=-1;
        @Override
        public boolean hasNext() {
            return currentPos + 1 < arrayFlat.length;
        }

        @Override
        public Space next() {
            if(hasNext()) {
                return arrayFlat[++currentPos];
            }
            throw new NoSuchElementException("");
        }

    }

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
        for (Space space : arrayFlat) {
            sum += space.getNumberRooms();
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
        if (numberSpace < 0 || numberSpace > getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        Space[] tmpArrayFlat = new Space[arrayFlat.length - 1];
        for (int i = 0; i < numberSpace; i++) {
            tmpArrayFlat[i] = arrayFlat[i];
        }
        for (int i = numberSpace; i < arrayFlat.length-1; i++) {
            tmpArrayFlat[i] = arrayFlat[i+1];
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
    public int hashCode() {
        int result = getNumberSpaces();
        for (int i = 0; i < getNumberSpaces(); ++i) {
            result ^= this.getSpaceByNumber(i).hashCode();
        }
        return result;
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

    @Override
    public Object clone(){
        Object result = null;

        try {
            result = super.clone();
            ((DwellingFloor)result).arrayFlat = new Space[getNumberSpaces()];
            for (int i = 0; i < getNumberSpaces(); i++) {
                ((DwellingFloor) result).arrayFlat[i] = (Space) getSpaceByNumber(i).clone();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int compareTo(Floor o) {
        if(this.getNumberSpaces() > o.getNumberSpaces())
            return 1;
        if(this.getNumberSpaces() == o.getNumberSpaces())
            return 0;
    return -1;
    }


}