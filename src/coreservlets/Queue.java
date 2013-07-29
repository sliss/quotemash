/*************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue
 *
 *  A generic queue, implemented using a linked list. Each queue
 *  element is of type Item.
 *
 *************************************************************************/
package coreservlets;

public class Queue<Item> {
    private int N;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;

        Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    // is the queue empty?
    public boolean isEmpty() { return first == null; }
    public int length()      { return N;             }
    public int size()        { return N;             }

    // add an item to the queue
    public void enqueue(Item item) {
        Node x = new Node(item, null);
        if (isEmpty()) { first = x;     last = x; }
        else           { last.next = x; last = x; }
        N++;
    }

    // remove and return the least recently added item
    public Item dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue underflow");
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    // remove and return the least recently added item
    public String toString() {
        String s = "";
        for (Node x = first; x != null; x = x.next)
            s += x.item + " ";
        return s;
    }



    // a test client
    public static void main(String[] args) {

       /***********************************************
        *  A queue of strings
        ***********************************************/
        Queue<String> q1 = new Queue<String>();
        q1.enqueue("Vertigo");
        q1.enqueue("Just Lose It");
        q1.enqueue("Pieces of Me");
        System.out.println(q1.dequeue());
        q1.enqueue("Drop It Like It's Hot");
        while (!q1.isEmpty())
            System.out.println(q1.dequeue());
        System.out.println();


       /*********************************************************
        *  A queue of integers. Illustrates autoboxing and
        *  auto-unboxing.
        *********************************************************/
        Queue<Integer> q2 = new Queue<Integer>();
        for (int i = 0; i < 10; i++)
            q2.enqueue(i);

        // test out dequeue and enqueue
        while (q2.size() >= 2) {
            int a = q2.dequeue();
            int b = q2.dequeue();
            int c = a + b;
            System.out.println(c);
            q2.enqueue(a + b);
        }

    }
}
