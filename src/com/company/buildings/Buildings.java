package com.company.buildings;

import java.io.*;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;


public class Buildings implements Serializable{
    // 	записи данных о здании в байтовый поток
    public static void outputBuilding(Building building, OutputStream out) {
        try {
            DataOutputStream out1 = new DataOutputStream(out);
            ////
            out1.writeInt(building.getNumberFloors());
            for (int i = 0; i < building.getNumberFloors(); i++) {
                out1.writeInt(building.getFloorByNumber(i).getNumberSpaces());
                for (int j = 0; j < building.getFloorByNumber(i).getNumberSpaces(); j++) {
                    out1.writeInt(building.getFloorByNumber(i).getSpaceByNumber(j).getNumberRooms());
                    out1.writeFloat(building.getFloorByNumber(i).getSpaceByNumber(j).getArea());
                }
            }
            out1.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Some error occurred!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //	чтения данных о здании из байтового потока
    public static Building inputBuilding(InputStream in) {
        DataInputStream in1 = new DataInputStream(in);
        //in.
        Building building = null;
        try {
            int numberFloor = in1.readInt();
            building = new OfficeBuilding(new OfficeFloor[numberFloor]);
            for (int i = 0; i < numberFloor; i++) {
                Floor currentFloor = new OfficeFloor();
                building.setFloorByNumber(i, currentFloor);

                int numberFlat = in1.readInt();
                for (int j = 0; j < numberFlat; j++) {
                    Space currentSpace = new Office();
                    currentFloor.addSpaceByNumber(j, currentSpace);
                    int numberRooms = in1.readInt();
                    currentSpace.setNumberRooms(numberRooms);

                    float area = in1.readFloat();
                    currentSpace.setArea(area);

                }
            }
            in1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return building;
    }

    // записи здания в символьный поток
    public static void writeBuilding(Building building, Writer out) {

        try {
            PrintWriter printWriter = new PrintWriter(out);

            printWriter.print(building.getNumberFloors());
            printWriter.print(" ");
            for (int i = 0; i < building.getNumberFloors(); i++) {
                printWriter.print(building.getFloorByNumber(i).getNumberSpaces());
                printWriter.print(" ");
                for (int j = 0; j < building.getFloorByNumber(i).getNumberSpaces(); j++) {
                    printWriter.print(building.getFloorByNumber(i).getSpaceByNumber(j).getNumberRooms());
                    printWriter.print(" ");
                    printWriter.print(building.getFloorByNumber(i).getSpaceByNumber(j).getArea());
                    printWriter.print(" ");
                }
            }
            printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double getNextIntFromTokenaizer(StreamTokenizer in1) throws Exception {
        if (in1.nextToken() != StreamTokenizer.TT_NUMBER)
            throw new Exception("NaN");
        return in1.nval;
    }


    // чтения здания из символьного потока
    public static Building readBuilding(Reader in) {
        StreamTokenizer in1 = new StreamTokenizer(in);
        Building building = null;
        try {

            int numberFloor = (int) getNextIntFromTokenaizer(in1);

            building = new OfficeBuilding(new OfficeFloor[numberFloor]);
            for (int i = 0; i < numberFloor; i++) {
                Floor currentFloor = new OfficeFloor();
                building.setFloorByNumber(i, currentFloor);

                int numberFlat = (int) getNextIntFromTokenaizer(in1);
                for (int j = 0; j < numberFlat; j++) {
                    Space currentSpace = new Office();
                    currentFloor.addSpaceByNumber(j, currentSpace);

                    int numberRooms = (int) getNextIntFromTokenaizer(in1);

                    currentSpace.setNumberRooms(numberRooms);

                    float area = (float) getNextIntFromTokenaizer(in1);

                    currentSpace.setArea(area);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return building;

    }

    // сериализации здания в байтовый поток
    public static void serializeBuilding(Building building, OutputStream out) {
        try {
            ObjectOutputStream out1 = new ObjectOutputStream(out);
            out1.writeObject(building);
            out1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //	десериализации здания из байтового потока
    public static Building deserializeBuilding(InputStream in) {
        Building building = null;
        try {
            ObjectInputStream in1 = new ObjectInputStream(in);
            building = (Building) in1.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return building;

    }

    public static void writeBuildingFormat(Building building, Writer out) {
        Formatter f = new Formatter(out);
        int numberFloors = building.getNumberFloors();
        StringBuilder str = new StringBuilder("");
        str.append("NumberFloors: ").append(numberFloors);
        try {
            for (int i = 0; i < building.getNumberFloors(); i++) {
                str.append(" NumberFlats: ");
                str.append(building.getFloorByNumber(i).getNumberSpaces());
                for (int j = 0; j < building.getFloorByNumber(i).getNumberSpaces(); j++) {
                    str.append(" NumberRooms: ");
                    str.append(building.getFloorByNumber(i).getSpaceByNumber(j).getNumberRooms());
                    str.append(" Area: ");
                    str.append(building.getFloorByNumber(i).getSpaceByNumber(j).getArea());
                }
            }
            f.format("Building: %s\n", str.toString());
            f.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Building readBuilding(Scanner scanner) {
        scanner.useLocale(Locale.US);
        scanner.skip("Building:");
        scanner.skip(" NumberFloors:");
        int numberFloors = scanner.nextInt();
        Floor[] floorsArray = new Floor[numberFloors];
        int numberSpaces = 0;
        int numberRooms = 0;
        float area = 0;

        for (int i = 0; i < numberFloors; i++) {
            scanner.skip(" NumberFlats:");
            numberSpaces = scanner.nextInt();
            floorsArray[i] = new OfficeFloor(numberSpaces);
            for (int j = 0; j < numberSpaces; j++) {
                scanner.skip(" NumberRooms:");
                numberRooms = scanner.nextInt();
                scanner.skip(" Area: ");

                area = scanner.nextFloat();
                floorsArray[i].addSpaceByNumber(j, new Office(area, numberRooms));

            }
        }
        Building readBuilding = new OfficeBuilding(floorsArray);
        return readBuilding;
    }


}


