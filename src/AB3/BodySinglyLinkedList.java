package AB3;

import AB2.Body;

/**
 * A list of bodies implemented as a singly linked list. The number of elements of the list is
 * not limited.
 */
//
// TODO: define further classes and methods for the implementation of the singly linked list, if
//  needed.
//
public class BodySinglyLinkedList {

    //TODO: declare variables.

    /**
     * Initializes 'this' as an empty list.
     */
    public BodySinglyLinkedList() {

        // TODO: implement constructor.
    }

    /**
     * Constructor: initializes this list as an independent copy of the specified list.
     * Calling methods of this list will not affect the specified list
     * and vice versa.
     *
     * @param list the list from which elements are copied to the new list, list != null.
     */
    public BodySinglyLinkedList(BodySinglyLinkedList list) {

        // TODO: implement constructor.
    }

    /**
     * Inserts the specified element 'b' at the beginning of this list.
     *
     * @param b the body that is added (b can also be 'null').
     */
    public void addFirst(Body b) {

        // TODO: implement method.
    }

    /**
     * Appends the specified element 'b' to the end of this list.
     *
     * @param b the body that is added (b can also be 'null').
     */
    public void addLast(Body b) {

        // TODO: implement method.
    }

    /**
     * Returns the last element in this list (at the end of the list) without removing it.
     * Returns 'null' if the list is empty.
     */
    public Body getLast() {

        // TODO: implement method.
        return null;
    }

    /**
     * Returns the first element in this list (at the beginning of the list) without removing it.
     * Returns 'null' if the list is empty.
     */
    public Body getFirst() {

        // TODO: implement method.
        return null;
    }

    /**
     * Retrieves and removes the first element in this list, that is, the element with index 0.
     * Indices of subsequent elements are decremented accordingly. Returns 'null' if the list is
     * empty.
     *
     * @return the first element in this list, or 'null' if the list is empty.
     */
    public Body pollFirst() {

        // TODO: implement method.
        return null;
    }

    /**
     * Retrieves and removes the last element in this list, that is, the element with the highest
     * index. Returns 'null' if the list is empty.
     *
     * @return the last element in this list, or 'null' if the list is empty.
     */
    public Body pollLast() {

        // TODO: implement method.
        return null;
    }

    /**
     * Inserts the specified element at the specified position in this list, such that a
     * following invocation of get(i) would return 'b'. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param i the position of the new element in the list, i >= 0 && i <= size().
     * @param b the body that is added (b can also be 'null').
     */
    public void add(int i, Body b) {

        // TODO: implement method.
    }

    /**
     * Returns the element with the specified index in this list. Returns 'null' if the list is
     * empty.
     *
     * @param i the list index of the element to be retrieved, i >= 0 && i < size().
     * @return the retrieved element at the specified position.
     */
    public Body get(int i) {

        // TODO: implement method.
        return null;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if
     * this list does not contain the element. More formally, returns the lowest index i such
     * that b == get(i), or -1 if there is no such index.
     *
     * @param b the body to search for.
     * @return the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     */
    public int indexOf(Body b) {

        // TODO: implement method.
        return -2;
    }

    /**
     * Returns the number of entries in this list (including 'null' entries).
     */
    public int size() {

        // TODO: implement method.
        return -1;
    }
}

// TODO: define further classes, if needed (either here or in a separate file).
