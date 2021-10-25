package com.company.buildings;

import com.company.exceptions.FloorIndexOutOfBoundsException;
import com.company.exceptions.SpaceIndexOutOfBoundsException;

import java.rmi.server.ExportException;

public class OfficeBuilding implements Building {
    private static class Node {
        private Floor floor;
        private Node next;
        private Node prev;

        public Node(Floor data, Node next, Node prev) {
            this.floor = data;
            this.next = next;
            this.prev = prev;
        }

        public Node() {
            this.floor = null;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;

    private void initHead() {
        head = new Node();
        head.next = head;
        head.prev = head;

    }

    public void setSpaceByNumber(int numberSpace, Space newSpace) {
        if (numberSpace < 1 || numberSpace > getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        Node temp = head;
        Space result = null;
        int count = 0;

        do {
            temp = temp.next;
            count += temp.floor.getNumberSpaces();
        } while (numberSpace > count);
        Floor currentFloor = temp.floor;
        int numberSpacePerFloor = temp.floor.getNumberSpaces();
        int n = 0;

        temp.floor.setSpaceByNumber(numberSpacePerFloor + numberSpace - count, newSpace);
    }

    public void addSpaceByNumber(int numberSpace, Space newSpace) {
        if (numberSpace < 1 || numberSpace > getNumberSpaces() + 1)
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        Node temp = head;
        Space result = null;
        int count = 0;

        do {
            temp = temp.next;
            count += temp.floor.getNumberSpaces();
        } while (numberSpace > count);
        Floor currentFloor = temp.floor;
        int numberSpacePerFloor = temp.floor.getNumberSpaces();
        int n = 0;

        temp.floor.addSpaceByNumber(numberSpacePerFloor + numberSpace - count, newSpace);

    }

    private Node getNodeByNumber(int numberNode) {
        Node temp = head;
        if (temp == null)
            return null;
        for (int i = 0; i < numberNode; i++) {
            temp = temp.next;

        }
        return temp;
    }

    public int getNumberFloors() {
        int count = 0;
        Node temp = head;
        if (temp.next == head)
            return 0;
        do {
            temp = temp.next;
            count++;
        } while (temp.next != head);
        return count;
    }


    private void addNodeByNumber(Node node, int numberNode) throws Exception {
        if (numberNode < 1)
            throw new Exception("Invalid numberNode");
        if (getNumberFloors() + 1 <= numberNode)
            throw new Exception("Invalid numberNode");

        Node temp = head;
        Node p = null;
        for (int i = 0; i < numberNode - 1; i++) {
            temp = temp.next;
        }
        node.prev = temp;
        node.next = temp.next;
        p = temp.next;
        temp.next = node;
        p.prev = node;
    }

    private void eraseNodeByNumber(int numberNode) throws Exception {
        if (numberNode < 1)
            throw new Exception("Invalid numberNode");
        if (getNumberFloors() < numberNode)
            throw new Exception("Invalid numberNode");

        Node temp = head;
        Node p = null;
        for (int i = 0; i < numberNode - 1; i++) {
            temp = temp.next;
        }
        p = temp.next.next;
        temp.next = p;
        p.prev = temp;
    }

    public OfficeBuilding(int numberFloors, int[] numberOfficePerFloor) {
        if (numberFloors < 1)
            throw new FloorIndexOutOfBoundsException("Invalid numberFloors");
        initHead();
        Node temp = head;
        for (int i = 0; i < numberFloors; i++) {
            Node node = new Node();
            node.floor = new OfficeFloor(numberOfficePerFloor[i]);
            temp.next = node;
            node.prev = temp;
            temp = node;
        }
        temp.next = head;
        head.prev = temp;
    }


    public OfficeBuilding(Floor[] arrayFloors) {
        initHead();
        Node p = null;
        Node temp = head;
        for (int i = 0; i < arrayFloors.length; i++) {
            Node node = new Node();
            node.floor = arrayFloors[i];
            node.prev = temp;
            node.next = temp.next;
            p = temp.next;
            temp.next = node;
            p.prev = node;
        }
        temp.next = head;
        head.prev = temp;
    }


    public int getNumberSpaces() {
        int count = 0;
        Node temp = head;
        if (temp.next == head) {
            return 0;
        }
        do {
            temp = temp.next;
            count += temp.floor.getNumberSpaces();
        } while (temp.next != head);
        return count;
    }

    public float getSumAreas() {
        float sum = 0;
        Node temp = head;
        do {
            temp = temp.next;
            sum += temp.floor.getSumAreas();
        } while (temp.next != head);
        return sum;
    }

    public int getNumberRooms() {
        int sum = 0;
        Node temp = head;
        do {
            temp = temp.next;
            sum += temp.floor.getNumberRooms();
        } while (temp.next != head);
        return sum;
    }

    public Floor[] getArrayFloors() {
        Node temp = head;
        int numberFloors = getNumberFloors();
        if (numberFloors < 1)
            return null;

        Floor[] array = new Floor[numberFloors];
        for (int i = 0; i < numberFloors; i++) {
            temp = temp.next;
            array[i] = temp.floor;
        }
        return array;
    }

    public Floor getFloorByNumber(int numberFloor) {
        if (numberFloor < 1 || numberFloor > getNumberFloors())
            throw new FloorIndexOutOfBoundsException("Invalid numberFloor");
        Node temp = head;
        int count = 0;
        while (count != numberFloor) {
            temp = temp.next;
            count++;
        }
        return temp.floor;
    }

    public void setFloorByNumber(int numberFloor, Floor newFloor) {
        if (numberFloor < 1 || numberFloor > getNumberFloors())
            throw new FloorIndexOutOfBoundsException("Invalid numberFloor");
        Node temp = head;
        int count = 0;
        while (count != numberFloor) {
            temp = temp.next;
            count++;
        }
        temp.floor = newFloor;
    }

    public Space getSpaceByNumber(int numberSpace) {
        if (numberSpace < 1 || numberSpace > getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        Node temp = head;
        int count = 0;

        do {
            temp = temp.next;
            count += temp.floor.getNumberSpaces();
        } while (numberSpace > count);
        Floor currentFloor = temp.floor;
        int numberSpacePerFloor = temp.floor.getNumberSpaces();
        int n = 0;
        //while (count != numberSpace) {
        //   count--;
        //   n++;
        //}
        // количество посчитанных квартир 30
        // нужная квартира 24
        //+
        // количество квартир на этаже 10 ; 24+10 - 30
        return temp.floor.getSpaceByNumber(numberSpacePerFloor + numberSpace - count);
    } //

    public void eraseSpaceByNumber(int numberSpace) {
        if (numberSpace < 1 || numberSpace > getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        Node temp = head;
        Office result = null;
        int count = 0;
        // if (numberSpace > getNumberOfficeInOfficeBuildings())
        // throw new Exception("Bad numberOffice");
        do {
            temp = temp.next;
            count += temp.floor.getNumberSpaces();
        } while (numberSpace > getNumberSpaces());

        while (count != numberSpace) {
            count++;
            temp = temp.next;
        }
        temp.floor.eraseSpaceByNumber(count);
    }

    public Space getBestSpace() {
        Node temp = head;
        Space max = temp.next.floor.getBestSpace();

        while (temp != head)
            if (max.getArea() < temp.next.floor.getBestSpace().getArea())
                max = temp.next.floor.getBestSpace();
        return max;
    }

    public Space[] getSortArraySpaces() {
        Node temp = head;
        temp = temp.next;
        int count = 0;
        Space[] array = new Space[getNumberSpaces()];
        for (int i = 0; i < getNumberFloors(); i++) {
            for (int j = 1; j <= temp.floor.getNumberSpaces(); j++) {
                array[count] = temp.floor.getSpaceByNumber(j);
                count++;
            }
            temp = temp.next;
        }
        for (int out = array.length - 1; out >= 1; out--) {
            for (int in = 0; in < out; in++) {
                if (array[in].getArea() < array[in + 1].getArea()) {
                    Space tmp = array[in];
                    array[in] = array[in + 1];
                    array[in + 1] = tmp;
                }
            }
        }
        return array;
    }
}
