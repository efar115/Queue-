package dsassignment3_customer;

import ADTs.*;
import DataStructures.*;
import Exceptions.*;
import java.util.Random;

/**
 * This data structure is a special form of queue designed to handle Customers,
 * although it is capable of handling other comparable Objects as well. Note
 * that a typical queue does not use add/remove at an index methods.
 *
 * @author Perrin Mele
 * @param <T>
 * @version 1.0
 */
public class CustomerServiceQueue<T extends Comparable<T>>
        implements QueueADT<T> {

    private LinearNode<T> front;
    private LinearNode<T> back;
    private int size;

    /**
     * Constructor for a new CustomerServiceQueue.
     */
    public CustomerServiceQueue() {
        front = null;
        back = null;
        size = 0;
    }

    /**
     * Adds an element to the back of the queue.
     *
     * @param customer
     */
    @Override
    public void enqueue(T customer) {
        LinearNode<T> temp = new LinearNode<>(customer);
        if (back == null) {
            // queue is empty, adding first node
            back = temp;
            front = temp;
            size++;

        } else {
            temp.setPrev(back);
            back.setNext(temp);
            back = temp;
            size++;
        }
    }

    /**
     * Removes an element from the front of the queue.
     *
     * @return the removed element.
     * @throws EmptyCollectionException
     */
    @Override
    public T dequeue() throws EmptyCollectionException { //Give actual
        if (size == 0) {
            throw new EmptyCollectionException("CustomerServiceQueue");
        }
        T temp = front.getElement();
        if (size == 1) {
            front = back = null;
            size--;
            return temp;
        }
        front.setPrev(null);
        front = front.getNext();

        size--;
        return temp;
    }

    /**
     * Looks at, but does not remove, the front element in the queue.
     *
     * @return the front element.
     * @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (size == 0) {
            throw new EmptyCollectionException("CustomerServiceQueue");
        }
        T frontElement = front.getElement();
        return frontElement;
    }

    /**
     *
     * @return the size of the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     *
     * @return whether or not the queue is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This method has a 50% chance of removing (true) and a 50% chance of
     * removing but re-adding to the back (false). It represents whether or not
     * a Customer was successfully helped - if not, they get back in line.
     *
     * @return a boolean representing if the element was completely removed.
     * @throws EmptyCollectionException
     */
    public boolean help() throws EmptyCollectionException {
        //Chance of returning to back of queue
        Random rand = new Random();
        if (rand.nextInt(2) == 0) { //Helped successfully
            this.dequeue();
            return true;
        } else { //Helped unsuccessfully, had to get back in line
            this.enqueue(this.dequeue());
            return false;
        }
    }

    /**
     * Pushes relevant elements forward 1 in the queue. Relevant elements
     * include any who are considered by the compareTo method to be equivalent
     * to the parameter element. A Customer object is considered equal to
     * another when they share the same issue.
     *
     * @param elem , to be removed from a list and added to another list
     * @throws EmptyCollectionException
     */
    public void increasePriority(T elem) throws EmptyCollectionException {
        if (size == 0) {
            throw new EmptyCollectionException("CustomerServiceQueue");
        }
        LinearNode<T> current = front;
        for (int x = 0; x < size; x++) {
            if (current.getElement().compareTo(elem) == 0
                    && current.getPrev() != null) {
                //Takes the element out
                T temp = this.remove(x);
                //Replaces the element one spot closer to the front.
                this.add(x - 1, temp);
            }
            current = current.getNext();
        }
    }

    /**
     * Inserts an element at a given index.
     *
     * @param index , for the place the element will be at
     * @param elem , for the new element to be entered
     */
    public void add(int index, T elem)
            throws IndexOutOfBoundsException {

        LinearNode<T> temp = new LinearNode<>(elem);
        LinearNode<T> curr = front;
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("CustomerServiceQueue");
        }
        if (size == 0) {
            front = temp;
            back = temp;
            size++;
            return;
        } else if (index == 0) {
            temp.setNext(front);
            front.setPrev(temp);
            front = temp;
            size++;
            return;
        } else if (index == size) {
            temp.setPrev(back);
            back.setNext(temp);
            back = temp;
            size++;
            return;
        } else {
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            temp.setNext(curr);
            temp.setPrev(curr.getPrev());
            curr.getPrev().setNext(temp);
            curr.setPrev(temp);
            size++;
        }

    }

    /**
     * Removes an element from a given index.
     *
     * @param index , which element is to be removed
     * @return T element
     * @throws EmptyCollectionException
     * @throws IndexOutOfBoundsException
     */
    public T remove(int index)
            throws EmptyCollectionException, IndexOutOfBoundsException {

        if (size == 0) {
            throw new EmptyCollectionException("CustomerServiceQueue");
        }

        if (index == size || index > size || index < 0) {
            throw new IndexOutOfBoundsException("CustomerServiceQueue");
        }
        T result = null;
        LinearNode<T> curr = front;

        for (int i = 0; i < index; i++) {

            curr = curr.getNext();
        }
        // check special case of removing only element
        if ((curr == front) && (curr == back)) {
            result = curr.getElement();
            front = null;
            back = null;
            size--;
            return result;
         // check special case of removing first element
        } else if (curr == front) {
            result = curr.getElement();
            front = curr.getNext();
            front.setPrev(null);
            size--;
            return result;
        // check special case of removing last element
        }  else if (curr == back) {
            result = curr.getElement();
            back.setNext(null);
            back = curr.getPrev();
            size--;
            return result;
         // normal case, removing in middle
        } else {
            result = curr.getElement();
            curr.getPrev().setNext(curr.getNext());
            curr.getNext().setPrev(curr.getPrev());
            size--;
            return result;
        }

    }

    /**
     * Returns a String representing the queue and each of its elements.
     *
     * @return str
     */
    @Override

    public String toString() {
        if (this.isEmpty()) {
            return "There is no one in line!";
        }
        String str = "Currently in line: ";
        LinearNode<T> current = front;
        for (int i = 0; i < size; i++) {
            str += "\n" + current.getElement().toString();
            current = current.getNext();
        }
        return str;
    }
}
