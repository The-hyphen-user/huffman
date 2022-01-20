package Huffman;

public class Node {

    public char letter;
    public int num;
    public Node next;
    public Node prev;
    public Node left;
    public Node right;

    public Node(char value, int number, Node left, Node right){
        this.num = number;
        this.letter = value;
        this.next = null;
        this.prev = null;
        this.left = left;
        this.right = right;
    }

    Node next(){
        Node temp = this.next;
        return temp;
    }

    Node prev(){
        Node temp = this.prev;
        return temp;
    }
}
