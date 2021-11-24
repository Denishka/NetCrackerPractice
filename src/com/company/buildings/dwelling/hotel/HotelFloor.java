package com.company.buildings.dwelling.hotel;

import com.company.buildings.Space;
import com.company.buildings.dwelling.DwellingFloor;

import java.util.Objects;

public class HotelFloor extends DwellingFloor {

    final static int DEFAULT_NUMBER_STARS = 1;

    private int numberStars;


    public HotelFloor(int countFlatFloor) {
        super(countFlatFloor);
        this.numberStars = DEFAULT_NUMBER_STARS;
    }


    public HotelFloor(Space[] arrayFlatFloor) {
        super(arrayFlatFloor);
        this.numberStars = DEFAULT_NUMBER_STARS;
    }


    public int getNumberStars() {
        return numberStars;
    }

    public void setNumberStars(int numberStars) {
        this.numberStars = numberStars;
    }


    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        Space[] arrayOffices = getArraySpaces();
        str.append("HotelFloor (").append(getNumberStars()).append(", ").append(getNumberSpaces());
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
        if (!(object instanceof HotelFloor))
            return false;
        HotelFloor other  = (HotelFloor) object;
        for (int i = 0; i < getNumberSpaces(); i++) {
            if(!(getSpaceByNumber(i).equals(other.getSpaceByNumber(i))))
                return false;
        }
        return this.getNumberStars()== ((HotelFloor) object).getNumberStars();
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
