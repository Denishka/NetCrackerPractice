package com.company.buildings.dwelling.hotel;

import com.company.buildings.Floor;
import com.company.buildings.Space;
import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.DwellingFloor;

public class Hotel extends Dwelling {

    public Hotel(int countFloor, int[] countFlatPerFloor) {
        super(countFloor, countFlatPerFloor);
    }

    public Hotel(Floor[] arrayFloorNew) {
        super(arrayFloorNew);
    }

    // получить звезды отеля
    public int getNumberStars() {
        int result = 0;
        for (int i = 0; i < this.arrayFloor.length; i++) {
            if (this.getFloorByNumber(i) instanceof HotelFloor)
                if (result < ((HotelFloor) this.getFloorByNumber(i)).getNumberStars())
                    result = ((HotelFloor) this.getFloorByNumber(i)).getNumberStars();
        }
        return result;
    }


    // лучший номер (bestSpace = area* coeff)
    @Override
    public Space getBestSpace() {
        double result = 0;
        double tmp = 0;
        Space resultSpace = null;
        double[] coeff = {0.25, 0.5, 1, 1.25, 1.5};

        for (int i = 0; i < this.arrayFloor.length; i++) {
            if (this.getFloorByNumber(i) instanceof HotelFloor) {
                for (int j = 0; j < this.arrayFloor[i].getArraySpaces().length; j++) {
                    tmp = coeff[((HotelFloor) this.getFloorByNumber(i)).getNumberStars() - 1] * this.getFloorByNumber(i).getSpaceByNumber(j).getArea();
                    if (result < tmp) {
                        result = tmp;
                        resultSpace = this.getFloorByNumber(i).getSpaceByNumber(j);
                    }
                }
            }

        }
        return resultSpace;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        Floor[] arrFloor = getArrayFloors();
        str.append("Hotel (").append(getNumberStars()).append(", ").append(getNumberFloors());
        for (int i = 0; i < arrFloor.length; i++) {
            str.append(", ").append(arrFloor[i].toString()).append("\t").append("\n");
        }
        str.append(")");
        return str.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Hotel)) return false;
        Hotel that = (Hotel) object;
        if (arrayFloor == null && that.arrayFloor == null)
            return false;
        for (int i = 0; i < getNumberFloors(); i++) {
            if (!(arrayFloor[i].equals(that.arrayFloor[i])))
                return false;
        }
        return this.getNumberStars()==((Hotel) object).getNumberStars();
    }

    @Override
    public int hashCode(){
        int result = this.getNumberSpaces() ^ this.getNumberStars();
        for(int i = 0; i < this.getNumberSpaces(); i++)
        {
            result = result^this.getSpaceByNumber(i).hashCode();

        }

        return result;
    }





}


