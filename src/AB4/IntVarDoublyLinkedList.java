package AB4;

/**
 * A list of 'IntVar' objects implemented as a doubly linked list. The number of elements of the 
 * list is not limited. The list may also contain entries of 'null'.
 */
//
// TODO: define further classes and methods for the implementation of the doubly linked list, if
//  needed.
//
public class IntVarDoublyLinkedList {

    //TODO: declare variables.

    /**
     * Initializes 'this' as an empty list.
     */
    public IntVarDoublyLinkedList() {

        //TODO: implement constructor.
    }

    /**
     * Inserts the specified element 'v' at the beginning of this list.
     * @param v the variable that is added ('v' can also be 'null').
     */
    public void addFirst(IntVar v) {

        //TODO: implement method.
    }

    /**
     * Appends the specified element 'v' to the end of this list.
     * @param v the variable that is added ('v' can also be 'null').
     */
    public void addLast(IntVar v) {

        //TODO: implement method.
    }

    /**
     * Returns the last element in this list (at the end of the list) without removing it.
     * Returns 'null' if the list is empty.
     */
    public IntVar getLast() {

        //TODO: implement method.
        return null;
    }

    /**
     * Returns the first element in this list (at the beginning of the list) without removing it.
     * Returns 'null' if the list is empty.
     */
    public IntVar getFirst() {

        //TODO: implement method.
        return null;
    }

    /**
     * Retrieves and removes the first element in this list, that is, the element with index 0.
     * Indices of subsequent elements are decremented accordingly. Returns 'null' if the list is
     * empty.
     *
     * @return the first element in this list, or 'null' if the list is empty.
     */
    public IntVar pollFirst() {

        //TODO: implement method.
        return null;
    }

    /**
     * Retrieves and removes the last element in this list, that is, the element with the highest
     * index. Returns 'null' if the list is empty.
     * @return the last element in this list, or 'null' if the list is empty.
     */
    public IntVar pollLast() {

        //TODO: implement method.
        return null;
    }


    /**
     * Inserts the specified element at the specified position in this list, such that a
     * following invocation of get(i) would return 'v'. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (adds one to their indices).
     * @param i the position of the new element in the list, i >= 0 && i <= size().
     * @param v the body that is added ('v' can also be 'null').
     */
    public void add(int i, IntVar v) {

        //TODO: implement method.
    }

    /**
     * Returns the element with the specified index in this list. Returns 'null' if the list is
     * empty.
     * @param i the list index of the element to be retrieved, i >= 0 && i < size().
     * @return the retrieved element at the specified position.
     */
    public IntVar get(int i) {

        //TODO: implement method.
        return null;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param i the index of the element to be replaced, i >= 0 && i < size().
     * @param v the new element to be set at the specified index ('v' can also be 'null').
     * @return the element that was replaced.
     */
    public IntVar set(int i, IntVar v) {

        //TODO: implement method.
        return null;
    }

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent
     * elements to the left (subtracts one from their indices). Returns the element that was
     * removed from the list.
     * @param i the index of the element to be removed, i >= 0 && i < size().
     * @return the removed element.
     */
    public IntVar remove(int i) {

        //TODO: implement method.
        return null;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if
     * this list does not contain the element. More formally, returns the highest index i such
     * that v == get(i), or -1 if there is no such index.
     * @param v the element to search for ('v' can also be 'null').
     * @return the index of the last occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     */
    public int lastIndexOf(IntVar v) {

        //TODO: implement method.
        return -2;
    }

    /**
     * Returns the number of entries in this list (including 'null' entries).
     * @return the number of entries in this list (including 'null' entries).
     */
    public int size() {

        //TODO: implement method.
        return -1;
    }
}

// TODO: define further classes, if needed (either here or in a separate file).
