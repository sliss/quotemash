/*************************************************************************
 *  Compilation:  javac RandomQueue.java
 *  Execution:    java RandomQueue
 *
 *  A generic queue, implemented using a linked list. Each queue
 *  element is of type Item.
 *
 *************************************************************************/
package coreservlets;

public class RandomQueue<Item> {
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
    public void add(Item item) {
        Node x = new Node(item, null);
        if (isEmpty()) { first = x;     last = x; }
        else           { last.next = x; last = x; }
        N++;
    }
    
    // return a random item from the queue, or null if empty
    public Item sample() {
        if (first == null) {
        	System.out.println("first is null!");
            return null;
        }

        int chosen = (int) (Math.random() * N);

        for (int i = 0; i < chosen; i++) {
            add(dequeue());
        }
        
        return first.item;
    }

    // remove and return the least recently added item
    public Item dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }


    // remove and return the least recently added item
    public String toString() {
        String s = "";
        for (Node x = first; x != null; x = x.next) {
            s += x.item + " ";
        }
        return s;
    }
}
