package com.company;

import com.company.buildings.*;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        // class Office
        System.out.println();

        System.out.println("///////////////////////Class Office");
        Space a = new Office();
        System.out.println("getArea: " + a.getArea());
        System.out.println("getNumberRooms" + a.getNumberRooms());


        a.setArea(10);
        System.out.println(a.getArea());
        a.setNumberRooms(12);
        System.out.println(a.getNumberRooms());
        //

        System.out.println();

        //Office Floor
        System.out.println("///////////Office Floor");
        Floor b = new OfficeFloor(7);
        System.out.print("Area: ");
        for (int i = 0; i < b.getNumberSpaces(); i++) {
            System.out.print(b.getArraySpaces()[i].getArea() + " ");
        }
        System.out.println();
        System.out.print("Rooms: ");
        for (int i = 0; i < b.getNumberSpaces(); i++) {
            System.out.print(+b.getArraySpaces()[i].getNumberRooms() + " ");
        }
        System.out.println();
        System.out.println("BestSpace: " + b.getBestSpace().getArea());

        //////////////////add
        Office office = new Office(40, 40);
        b.addSpaceByNumber(5, office);

        System.out.println("После добавления:");
        System.out.print("Area: ");
        for (int i = 0; i < b.getNumberSpaces(); i++) {
            System.out.print(b.getArraySpaces()[i].getArea() + " ");
        }

        System.out.println();
        System.out.print("Rooms: ");
        for (int i = 0; i < b.getNumberSpaces(); i++) {
            System.out.print(+b.getArraySpaces()[i].getNumberRooms() + " ");
        }
        //////////////////

        //////////////////erase
        System.out.println();
        b.eraseSpaceByNumber(5);
        System.out.println("После удаления:");
        System.out.print("Area: ");
        for (int i = 0; i < b.getNumberSpaces(); i++) {
            System.out.print(b.getArraySpaces()[i].getArea() + " ");
        }
        System.out.println();
        System.out.print("Rooms: ");
        for (int i = 0; i < b.getNumberSpaces(); i++) {
            System.out.print(b.getArraySpaces()[i].getNumberRooms() + " ");
        }
        //////////////////
        System.out.println();
        System.out.println("SumArea: " + b.getSumAreas());

        ///set
        System.out.println("Check set:");
        b.setSpaceByNumber(1, new Office(1, 2));
        System.out.println("New area: " + b.getSpaceByNumber(1).getArea());
        System.out.println("New CountRooms: " + b.getSpaceByNumber(1).getNumberRooms());


        /////////////////////////////////////////////////////////Office Buildings////////////////////////
        System.out.println();
        int[] numberOfficePerFloor = new int[7];
        for (int i = 0; i < numberOfficePerFloor.length; i++) {
            numberOfficePerFloor[i] = 3;
        }
        Building tmp = new OfficeBuilding(7, numberOfficePerFloor);


        System.out.println("/////////////////////Office Buildings");
        System.out.println("Количество этажей: " + tmp.getNumberFloors());
        System.out.println("Количество офисов в здании: " + tmp.getNumberSpaces());
        System.out.println("BestSpace:" + tmp.getBestSpace());
        System.out.println("OfficeByNumberInOfficeBuilding: " + tmp.getSpaceByNumber(1));
        System.out.println("SumArea: " + tmp.getSumAreas());
        System.out.println("SumRoom: " + tmp.getNumberRooms());
        OfficeFloor newOffice = new OfficeFloor(5);
        tmp.setFloorByNumber(2, newOffice);
        System.out.println(tmp.getFloorByNumber(2).getNumberSpaces());
        System.out.println("Количество этажей в здании(после вставки): " + tmp.getNumberSpaces());
        Space[] array = tmp.getSortArraySpaces();
        System.out.println("Сортированный массив: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print("[" + array[i].getArea() + "] ");
        }
        System.out.println();
        Space newSpace = new Office(130, 130);
        tmp.setSpaceByNumber(2, newSpace);
        System.out.println("Площадь после setSpace: " + tmp.getSpaceByNumber(2).getArea());
        System.out.println("Количество комнат после setSpace: " + tmp.getSpaceByNumber(2).getNumberRooms());


        Scanner scanner = new Scanner(System.in);


        //  Buildings.writeBuildingFormat(tmp, new PrintWriter(System.out));
        // System.out.println("");
        //Buildings.readBuilding(new BufferedReader(new InputStreamReader(System.in)));


        /*try {
            FileOutputStream fileOut = new FileOutputStream("C:\\prac_3\\out.bin");
            System.out.println(tmp);
            Buildings.outputBuilding(tmp, fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
/*
        Building testInp;

        try {
            FileInputStream fileIn = new FileInputStream("C:\\prac_3\\out.bin");
            testInp = Buildings.inputBuilding(fileIn);
            System.out.println(testInp.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        ///txt
        try {
            FileWriter fileOut = new FileWriter("C:\\prac_3\\out.txt");
            System.out.println(tmp);
            Buildings.writeBuilding(tmp, fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println("///////////////////////////");
        try {
            FileReader fileIn = new FileReader("C:\\prac_3\\out.txt");
            testInp = Buildings.readBuilding(fileIn);
            System.out.println(testInp.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fileOut = new FileWriter("C:\\prac_3\\outFormat.txt");
            System.out.println(tmp);
            Buildings.writeBuildingFormat(tmp, fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
*/
        Building testInp;

     /* try {
          File file = new File("C:\\prac_3\\outFormat.txt");
            Scanner fileIn = new Scanner(file);
            testInp = Buildings.readBuilding(fileIn);
            System.out.println(testInp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/



        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\prac_3\\outSer.bin");
            System.out.println(tmp);
            Buildings.serializeBuilding(tmp, fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Building testInp1;



    }
}
