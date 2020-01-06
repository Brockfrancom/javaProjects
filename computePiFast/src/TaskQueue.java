import java.util.NoSuchElementException;

/**
 * Assignment 1 for CS 2420
 * Generics queue/linked queue
 * @author Brock Francom, A02052161
 */

public class TaskQueue<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    //Initializes an empty queue.
    public TaskQueue() {
        first = null;
        last  = null;
        n = 0;
    }

    //Returns true if this queue is empty.
    public synchronized boolean isEmpty() {
        return first == null;
    }

    //Returns the number of items in this queue.
    public int size() {
        return n;
    }

    //Returns the item least recently added to this queue.
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("TaskQueue underflow");
        return first.item;
    }

    //Adds the item to this queue.
    public synchronized void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }

    //Removes and returns the item on this queue that was least recently added.
    public synchronized Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("TaskQueue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        return item;
    }
}