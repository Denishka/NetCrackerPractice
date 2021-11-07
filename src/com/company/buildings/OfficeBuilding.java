package com.company.buildings;

import com.company.exceptions.FloorIndexOutOfBoundsException;
import com.company.exceptions.SpaceIndexOutOfBoundsException;
import org.w3c.dom.ls.LSOutput;

import java.io.Serializable;
import java.rmi.server.ExportException;
import java.util.Objects;

public class OfficeBuilding implements Building, Serializable {
    public OfficeBuilding() {
    }

    private static class Node implements Serializable {
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
        if (numberSpace < 0 || numberSpace >= getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        Node temp = head;
        int count = 0;

        do {
            temp = temp.next;
            count += temp.floor.getNumberSpaces();
        } while (numberSpace > count);
        int numberSpacePerFloor = temp.floor.getNumberSpaces();
        temp.floor.setSpaceByNumber(numberSpacePerFloor + numberSpace - count, newSpace);
    }

    public void addSpaceByNumber(int numberSpace, Space newSpace) {
        if (numberSpace < 0 || numberSpace > getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        Node temp = head;
        int count = 0;
        do {
            temp = temp.next;
            count += temp.floor.getNumberSpaces();
        } while (numberSpace > count);
        int numberSpacePerFloor = temp.floor.getNumberSpaces();
        temp.floor.addSpaceByNumber(numberSpacePerFloor + numberSpace - count, newSpace);

    }

    private Node getNodeByNumber(int numberNode) {
        Node temp = head;
        if (head == head.next)
            return null;
        for (int i = 0; i <= numberNode; i++) {
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
        if (numberNode < 0 || getNumberFloors() < numberNode)
            throw new Exception("Invalid numberNode; valid range: 0 to " + getNumberFloors() + "But your value is " + numberNode);

        Node temp = head;
        Node p = null;
        for (int i = 0; i < numberNode; i++) {
            temp = temp.next;
        }
        node.prev = temp;
        node.next = temp.next;
        p = temp.next;
        temp.next = node;
        p.prev = node;
    }

    private void eraseNodeByNumber(int numberNode) throws Exception {
        if (numberNode < 0 || getNumberFloors() <= numberNode)
            throw new Exception("Invalid numberNode; valid range: 0 to " + (getNumberFloors() - 1) + "But your value is " + numberNode);

        Node temp = head;
        Node p = null;
        for (int i = 0; i < numberNode; i++) { // по индексам
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
            temp = node;
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
        if (temp.next == head)
            return 0;
        do {
            temp = temp.next;
            sum += temp.floor.getSumAreas();
        } while (temp.next != head);
        return sum;
    }

    public int getNumberRooms() {
        int sum = 0;
        Node temp = head;
        if (temp.next == head)
            return 0;
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
        if (numberFloor < 0 || numberFloor >= getNumberFloors())
            throw new FloorIndexOutOfBoundsException("Invalid numberFloor");
        Node temp = head;
        int count = 0;
        while (count != numberFloor) {
            temp = temp.next;
            count++;
        }
        temp = temp.next;
        return temp.floor;
    }

    public void setFloorByNumber(int numberFloor, Floor newFloor) {
        if (numberFloor < 0 || numberFloor >= getNumberFloors())
            throw new FloorIndexOutOfBoundsException("Invalid numberFloor");
        Node temp = head;
        int count = 0;
        temp = temp.next;
        while (count != numberFloor) {
            temp = temp.next;
            count++;
        }
        temp.floor = newFloor;
    }

    public Space getSpaceByNumber(int numberSpace) {
        if (numberSpace < 0 || numberSpace >= getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        Node temp = head;
        int count = 0;

        do {
            temp = temp.next;
            count += temp.floor.getNumberSpaces();
        } while (numberSpace > count);
        int numberSpacePerFloor = temp.floor.getNumberSpaces();
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
        if (numberSpace < 0 || numberSpace >= getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid numberSpace");
        Node temp = head;
        int count = 0;
        do {
            temp = temp.next;
            count += temp.floor.getNumberSpaces();
        } while (numberSpace >= count);

        int numberSpacePerFloor = temp.floor.getNumberSpaces();
        temp.floor.eraseSpaceByNumber(numberSpacePerFloor + numberSpace - count);
    }

    public Space getBestSpace() {
        Node temp = head;
        if (temp.next == head)
            return null;
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
            for (int j = 0; j < temp.floor.getNumberSpaces(); j++) {
                array[count] = temp.floor.getSpaceByNumber(j);
                count++;
            }
            temp = temp.next;
        }
        for (int out = array.length - 1; out >= 0; out--) {
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


    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        Floor[] arrFloor = getArrayFloors();
        str.append("OfficeBuilding (").append(getNumberFloors());
        for (int i = 0; i < arrFloor.length; i++) {
            str.append(",").append(arrFloor[i].toString()).append("\t").append("\n");
        }
        str.append(")");
        return str.toString();

    }

  /*  @Override
    public String toString (){
        StringBuilder str= new StringBuilder("");
        str.append("OfficeBuilding: ").append("Floors count: ").append(getNumberFloors()).append("\n");
        for (int i = 0; i < getNumberFloors(); i++) {
            Floor currentFloor = getFloorByNumber(i);
            str.append("Floor ").append(i).append(": ").append("\n");
            for (int j = 0; j < currentFloor.getNumberSpaces(); j++) {
                str.append("\tOffice ").append(j).append(": ").append(currentFloor.getSpaceByNumber(j)).append("\n");
            }
            str.append("\n");
        }
        return str.toString();
    }*/


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof OfficeBuilding)) return false;
        OfficeBuilding that = (OfficeBuilding) object;
        Floor[] arr1 = getArrayFloors();
        Floor[] arr2 = that.getArrayFloors();
        if(arr1 == null && arr2 == null)
            return false;
        for (int i = 0; i < getNumberFloors(); i++) {
            if (!(arr1[i].equals(arr2[i])))
                return false;
        }
        return true;

    }


}
