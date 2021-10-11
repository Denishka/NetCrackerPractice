package com.company;

import java.util.Random;

public class OfficeFloor {

    private static class Node {
        Office office;
        Node next;

        public Node(Office office, Node next) {
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
        if (temp == null)
            return null;
        for (int i = 0; i < numberNode; i++)
            temp = temp.next;

        return temp;
    }

    private void addNodeByNumber(Node node, int numberNode) throws Exception {
        if (numberNode < 1)
            throw new Exception("Invalid numberNode");
        Node temp = head;

        for (int i = 0; i < numberNode - 1; i++) {
            temp = temp.next;
        }
        node.next = temp.next;
        temp.next = node;
    }

    private void eraseNodeByNumber(int numberNode) throws Exception {
        if (numberNode < 1)
            throw new Exception("Invalid numberNode");
        Node temp = head;
        for (int i = 0; i < numberNode - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    public OfficeFloor(int numberOfficePerFloor) {
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

    public OfficeFloor(Office[] arrayOfficePerFloor) {
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

    public float getSumAreaOffice() {
        float sum = 0;
        Node temp = head;
        do {
            temp = temp.next;
            sum += temp.office.getAreaOffice();
        } while (temp.next != head);
        return sum;
    }

    private void initHead() {
        head = new Node(null, head);
    }

    public int getSumRoomOfficePerFloor() {
        int sum = 0;
        Node temp = head;
        do {
            temp = temp.next;
            sum += temp.office.getCountRoomsOffice();
        } while (temp.next != head);
        return sum;
    }

    public int getNumberOfficePerFloor() {
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

    public Office[] getArrayOffice() {
        Node temp = head;
        int numOffices = getNumberOfficePerFloor();
        if (numOffices < 1)
            return null;

        Office[] array = new Office[numOffices];
        for (int i = 0; i < numOffices; i++) {
            temp = temp.next;
            array[i] = temp.office;
        }
        return array;
    }

    public Office getOfficeByNumberPerFloor(int numberOfficePerFloor) {
        Node temp = head;
        int count = 0;
        while (count != numberOfficePerFloor) {
            temp = temp.next;
            count++;
        }
        return temp.office;
    }

    public void setOfficeByNumberPerFloor(int numberOfficePerFloor, Office newOffice) {
        Node temp = head;
        int count = 0;
        while (count != numberOfficePerFloor) {
            temp = temp.next;
            count++;
        }
        temp.office = newOffice;
    }

    public void addOfficeByNumberPerFloor(int numberOfficePerFloor, Office newOffice) throws Exception {
        Node temp = new Node();
        temp.office = newOffice;
        addNodeByNumber(temp, numberOfficePerFloor);
    }

    public void eraseOfficeByNumberPerFloor(int numberOfficePerFloor) throws Exception {
        eraseNodeByNumber(numberOfficePerFloor);
    }

    public Office getBestSpace() {
        float bestSpace = 0;
        Office officeBestSpace = null;
        Node temp = head;
        for (int i = 0; i < getNumberOfficePerFloor(); i++) {
            temp = temp.next;
            if (temp.office.getAreaOffice() > bestSpace) {
                bestSpace = temp.office.getAreaOffice();
                officeBestSpace = temp.office;
            }
        }
        return officeBestSpace;
    }


}


