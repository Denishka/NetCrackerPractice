package com.company.buildings;

import com.company.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class OfficeFloor implements Floor, Serializable  {

    public OfficeFloor() {
        initHead();
    }
    private static class Node implements Serializable {
        private Space office;
        private Node next;

        public Node(Space office, Node next) {
            this.office = office;
            this.next = next;
        }

        public Node() {
            this.office = null;
            this.next = null;
        }
    }

    private Node head;


    private Node getNodeByNumber(int numberNode) {
        Node temp = head;
        if (head == head.next)
            return null;
        for (int i = 0; i <= numberNode; i++)
            temp = temp.next;

        return temp;
    }

    private void addNodeByNumber(Node node, int numberNode) {
        if (numberNode < 0)
            throw new SpaceIndexOutOfBoundsException("Invalid numberNode");
        Node temp = head;

        for (int i = 0; i < numberNode; i++) {
            temp = temp.next;
        }
        node.next = temp.next;
        temp.next = node;
    }

    private void eraseNodeByNumber(int numberNode) {
        if (numberNode < 0)
            throw new SpaceIndexOutOfBoundsException("Invalid numberNode");
        Node temp = head;
        for (int i = 0; i < numberNode; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    public OfficeFloor(int numberOfficePerFloor) {
        if (numberOfficePerFloor < 1)
            throw new SpaceIndexOutOfBoundsException("invalid numberOfficePerFloor");
        initHead();
        Node temp = head;
        for (int i = 0; i < numberOfficePerFloor; i++) {
            Node node = new Node();
            node.office = new Office(new Random().nextInt(70) + 3, new Random().nextInt(4) + 2);
            temp.next = node;
            temp = node;
        }
        //так как список циклический
        temp.next = head;
    }


    public OfficeFloor(Space[] arrayOfficePerFloor) {
        initHead();
        Node temp = head;
        for (int i = 0; i < arrayOfficePerFloor.length; i++) {
            Node node = new Node();
            node.office = arrayOfficePerFloor[i];
            temp.next = node;
            temp = node;
        }
        temp.next = head;
    }

    public float getSumAreas() {
        if (head == head.next)
            return 0;
        float sum = 0;
        Node temp = head;
        do {
            temp = temp.next;
            sum += temp.office.getArea();
        } while (temp.next != head);
        return sum;
    }

    private void initHead() {
        head = new Node();
        head.next = head;
    }

    public int getNumberRooms() {
        if (head == head.next)
            return 0;
        int sum = 0;
        Node temp = head;
        do {
            temp = temp.next;
            sum += temp.office.getNumberRooms();
        } while (temp.next != head);
        return sum;
    }

    public int getNumberSpaces() {
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

    public Space[] getArraySpaces() {
        Node temp = head;
        int numOffices = getNumberSpaces();
        if (numOffices < 1)
            return null;

        Space[] array = new Space[numOffices];
        for (int i = 0; i < numOffices; i++) {
            temp = temp.next;
            array[i] = temp.office;
        }
        return array;
    }

    public Space getSpaceByNumber(int numberSpace) {
        if (head == head.next)
            return null;
        Node temp = head;
        int count = 0;
        int numberSpaces = getNumberSpaces();
        if (numberSpace < 0 || numberSpace >= numberSpaces)
            throw new SpaceIndexOutOfBoundsException("Invalid number office: index must be between " + 0 + " and " + numberSpaces);

        while (count != numberSpace) {
            temp = temp.next;
            count++;
        }
        temp = temp.next;
        return temp.office;
    }

    public void setSpaceByNumber(int numberSpace, Space space) {
        if (numberSpace < 0 || numberSpace >= getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid number office");
        Node temp = head;
        int count = 0;
        while (count != numberSpace) {
            temp = temp.next;
            count++;
        }
        temp.office = space;
    }

    public void addSpaceByNumber(int numberSpace, Space space) {
        if (numberSpace < 0 || numberSpace > getNumberSpaces())
            throw new SpaceIndexOutOfBoundsException("Invalid number offices");
        Node temp = new Node();
        temp.office = space;
        addNodeByNumber(temp, numberSpace);
    }

    public void eraseSpaceByNumber(int numberSpace) {
        eraseNodeByNumber(numberSpace);
    }

    public Space getBestSpace() {
        if (head == head.next)
            return null;
        float bestSpace = 0;
        Space officeBestSpace = null;
        Node temp = head;
        for (int i = 0; i < getNumberSpaces(); i++) {
            temp = temp.next;
            if (temp.office.getArea() > bestSpace) {
                bestSpace = temp.office.getArea();
                officeBestSpace = temp.office;
            }
        }
        return officeBestSpace;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        Space[] arrayOffices = getArraySpaces();
        str.append("OfficeFloor (").append(getNumberSpaces());
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
        if (!(object instanceof OfficeFloor))
            return false;
        OfficeFloor officeFloor = (OfficeFloor) object;
        if (head == null && officeFloor.head != null)
                return false;
        else if (!(head.equals(officeFloor.head))) {
            return false;
        }
        if (!(PlacementExchanger.checkSwapFloor(this, officeFloor)))
                return false;

        Space[] arr1 = getArraySpaces();
        Space[] arr2 = officeFloor.getArraySpaces();

        for (int i = 0; i < getNumberSpaces(); i++) {
           if(!(arr1[i].equals(arr2[i])))
               return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(head);
    }


}


