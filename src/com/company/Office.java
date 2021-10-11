package com.company;

public class Office {
    final static float DEFAULT_AREA = 250;
    final static int DEFAULT_ROOMS = 1;

    private float area;
    private int rooms;

    public Office() {
        area = DEFAULT_AREA;
        rooms = DEFAULT_ROOMS;
    }

    public Office(float area_new) {
        area = area_new;
        rooms = DEFAULT_ROOMS;
    }

    public Office(float area_new, int rooms_new) {
        area = area_new;
        rooms = rooms_new;
    }

    public int getCountRoomsOffice() {
        return rooms;
    }

    public void setCountRoomsOffice(int rooms_new) {
        rooms = rooms_new;
    }

    public float getAreaOffice() {
        return area;
    }

    public void setAreaOffice(float area_new) {
        area = area_new;
    }


}
