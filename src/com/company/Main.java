package com.company;

import java.sql.SQLOutput;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        // class Office

        Office a = new Office();
        System.out.println(a.getAreaOffice());
        System.out.println(a.getCountRoomsOffice());


        a.setAreaOffice(10);
        System.out.println(a.getAreaOffice());
        a.setCountRoomsOffice(12);
        System.out.println(a.getCountRoomsOffice());
        //

        System.out.println();
        System.out.println();

        //Office Floor
        OfficeFloor b = new OfficeFloor(7);
        System.out.print("Area: ");
        for (int i = 0; i < b.getNumberOfficePerFloor(); i++) {
            System.out.print(b.getArrayOffice()[i].getAreaOffice()+" ");
        }
        System.out.println();
        System.out.print("Rooms: ");
        for (int i = 0; i < b.getNumberOfficePerFloor(); i++) {
            System.out.print(+b.getArrayOffice()[i].getCountRoomsOffice()+" ");
        }
        System.out.println();
        System.out.println("BestSpace: "+b.getBestSpace().getAreaOffice());

        //////////////////add
        Office office = new Office(40,40);
        b.addOfficeByNumberPerFloor(5, office);

        System.out.println("После добавления:");
        System.out.print("Area: ");
        for (int i = 0; i < b.getNumberOfficePerFloor(); i++) {
            System.out.print(b.getArrayOffice()[i].getAreaOffice()+" ");
        }

        System.out.println();
        System.out.print("Rooms: ");
        for (int i = 0; i < b.getNumberOfficePerFloor(); i++) {
            System.out.print(+b.getArrayOffice()[i].getCountRoomsOffice()+" ");
        }
        //////////////////

        //////////////////erase
        System.out.println();
        b.eraseOfficeByNumberPerFloor(5);
        System.out.println("После удаления:");
        System.out.print("Area: ");
        for (int i = 0; i < b.getNumberOfficePerFloor(); i++) {
            System.out.print(b.getArrayOffice()[i].getAreaOffice()+" ");
        }
        System.out.println();
        System.out.print("Rooms: ");
        for (int i = 0; i < b.getNumberOfficePerFloor(); i++) {
            System.out.print(b.getArrayOffice()[i].getCountRoomsOffice()+" ");
        }
        //////////////////
        System.out.println();
        System.out.println("SumArea: "+b.getSumAreaOffice());

        ///set
        System.out.println("Check set:");
        b.setOfficeByNumberPerFloor(1,new Office(1,2));
        System.out.println("New area: "+b.getOfficeByNumberPerFloor(1).getAreaOffice());
        System.out.println("New CountRooms: "+b.getOfficeByNumberPerFloor(1).getCountRoomsOffice());




    }
}
