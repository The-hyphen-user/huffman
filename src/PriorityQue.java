package Huffman;

import Huffman.Node;

public class PriorityQue {
    public int currentSize;
    public Node current;
    public Node current2;
    public Node head;
    public Node tail;

    public PriorityQue(){
        this.currentSize = 0;
        this.head = null;
        this.tail = null;
    }

    public void insert(char letter, int num,Node left, Node right){
        Node temp = new Node(letter, num, left, right);
        if (currentSize == 0){//empty list
            head = temp;
            tail = temp;
            currentSize++;
        } else if (temp.num <= head.num){//first spot
            temp.next = head;
            head.prev = temp;
            head = temp;
            currentSize++;
        } else if (head.next == null) {//1 in array second spot
            head.next = temp;
            temp.prev = head;
            tail = temp;
            currentSize++;
        } else{
            current = head;
            while (current.next != null){
                if(temp.num <= current.next.num){
                    temp.prev = current;
                    temp.next = current.next;
                    temp.next.prev = temp;
                    current.next = temp;
                    currentSize++;
                    break;
                } else if (current.next.next == null){
                    current = current.next;
                    current.next = temp;
                    temp.prev = current;
                    tail = temp;
                    currentSize++;
                    break;
                }
                current = current.next;
            }
        }
    }

    public void printSize(){
        System.out.println("que length: " + currentSize);
    }

    public void print(){
        current = head;
        while (current != null){
            System.out.println(current.num + " " + current.letter);
            current = current.next;
        }
    }

    //used to create an array without duplicates and in order of priority que
    public char[] fillArrayFromQueChar(char[] c){
        current = head;
        for (int i=0;i<currentSize;i++){
            c[i] = current.letter;
            current = current.next;
        }
        return c;
    }


    public void formTree(){//create a tree with the data, uses insert() when que is larger then 2
        int treesize = currentSize;
        while (currentSize >1) {
            if (currentSize == 1) {
            } else if (currentSize == 2) {
                int newValue = head.num + head.next.num;
                Node temp = new Node('0', newValue, head, head.next);
                head = temp;
                currentSize = 1;
            } else if (currentSize > 2) {
                int newValue = head.num + head.next.num;
                current = head;
                current2 = head.next;
                head = head.next.next;
                insert('0', newValue, current, current2);
                currentSize = currentSize-2;//-2 instead of -1 to offset +1 in insert
            }
        }
         currentSize = treesize;//returns size to original size even though data is in tree form
    }


    public void printTree(){
        printRecurcivly(head, 0);
    }

    public void printRecurcivly(Node n, int code){
        if (n == null){
            return;
        }
        if (n.left == null && n.right == null){
            System.out.println(n.letter + ", " + n.num + ", " + code);
        }
        printRecurcivly(n.right, (code*10)+1);
        if (code == 0){
            printRecurcivly(n.left, code+10);
        } else {
            printRecurcivly(n.left, code * 10);
        }
    }

    // takes in array of char and an empty array of int
    //goes down the tree creating binary code: 0 for the lft, 1 for the right
    //matches the binary to the position of the char in the other array
    public int[] fillCodeArray(Node n, int[] code, char[] word, int digit){
        if (n == null){
            return code;
        }
        if (n.left == null && n.right == null){
            for (int i=0;i<word.length;i++){
                if (word[i] == n.letter ){
                    code[i] = digit;
                }
            }
        }
        fillCodeArray(n.right, code, word, (digit*10)+1);
        if (digit == 0){
            fillCodeArray(n.left, code, word, digit+10);
        } else {
            fillCodeArray(n.left, code, word, digit * 10);
        }
        return code;
    }
}
