package com.company;

public class OfficeBuilding {
    private static class Node {
        private OfficeFloor floor;
        private Node next;
        private Node prev;

        public Node(OfficeFloor data, Node next, Node prev) {
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
        head = new Node(null, head, head);
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

    private void eraseNodeByNumber(int numberNode) throws Exception{
        if (numberNode < 1)
            throw new Exception("Invalid numberNode");
        if(getNumberFloors() < numberNode)
            throw new Exception("Invalid numberNode");

        Node temp = head;
        Node p = null;
        for (int i = 0; i < numberNode - 1; i++) {
            temp = temp.next;
        }
        p=temp.next.next;
        temp.next = p;
        p.prev = temp;
    }

    public OfficeBuilding (int numberFloors, int[] numberOfficePerFloor){
        initHead();
        Node temp = head;
        Node p = null;
        for (int i = 0; i < numberFloors; i++) {
            for (int j = 0; j < numberOfficePerFloor[i]; j++) {
                Node node = new Node();
                node.floor.

            }
        }


    }


    public OfficeBuilding(OfficeFloor[] arrayFloors){
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








}
