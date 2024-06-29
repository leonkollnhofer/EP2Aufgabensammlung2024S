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
    private MyDoubleListNode head;
    private MyDoubleListNode tail;

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
        if (head == null) {
            head = tail = new MyDoubleListNode(v, null, null);
        } else if (head == tail) {
            head = new MyDoubleListNode(v, null, head);
            tail.setPrev(head);
        } else {
            head = new MyDoubleListNode(v, null, head);
            head.getNext().setPrev(head);
        }
    }

    /**
     * Appends the specified element 'v' to the end of this list.
     * @param v the variable that is added ('v' can also be 'null').
     */
    public void addLast(IntVar v) {

        //TODO: implement method.
        if (head == null) {
            head = tail = new MyDoubleListNode(v, null, null);
        } else if (head == tail) {
            tail = new MyDoubleListNode(v, tail, null);
            head.setNext(tail);
        }
        else {
            tail = new MyDoubleListNode(v, tail, null);
            tail.getPrev().setNext(tail);
        }
    }

    /**
     * Returns the last element in this list (at the end of the list) without removing it.
     * Returns 'null' if the list is empty.
     */
    public IntVar getLast() {

        //TODO: implement method.
        if (head == null) {
            return null;
        }
        return tail.getRaster();
    }

    /**
     * Returns the first element in this list (at the beginning of the list) without removing it.
     * Returns 'null' if the list is empty.
     */
    public IntVar getFirst() {

        //TODO: implement method.
        if (head == null) {
            return null;
        }
        return head.getRaster();
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
        if (head == null) {
            return null;
        }
        IntVar toReturnRaster = head.getRaster();
        head = head.getNext();
        if (head == null) {
            tail = null;
        } else {
            head.setPrev(null);
        }
        return toReturnRaster;
    }

    /**
     * Retrieves and removes the last element in this list, that is, the element with the highest
     * index. Returns 'null' if the list is empty.
     * @return the last element in this list, or 'null' if the list is empty.
     */
    public IntVar pollLast() {

        //TODO: implement method.
        if (head == null) {
            return null;
        }
        IntVar toReturnRaster = tail.getRaster();
        tail = tail.getPrev();
        if (tail == null) {
            head = null;
        } else {
            tail.setNext(null);
        }
        return toReturnRaster;
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
        if (i == 0) {
            addFirst(v);
        } else if (i == size()) {
            addLast(v);
        } else {
            head.add(v, i);
        }
    }

    /**
     * Returns the element with the specified index in this list. Returns 'null' if the list is
     * empty.
     * @param i the list index of the element to be retrieved, i >= 0 && i < size().
     * @return the retrieved element at the specified position.
     */
    public IntVar get(int i) {

        //TODO: implement method.
        return head.get(i);
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param i the index of the element to be replaced, i >= 0 && i < size().
     * @param v the new element to be set at the specified index ('v' can also be 'null').
     * @return the element that was replaced.
     */
    public IntVar set(int i, IntVar v) {

        //TODO: implement method.
        return head.set(i, v);
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
        if (head == tail || i == size() - 1) {
            return pollLast(); // Assuming preconditions hold
        }
        if (i == 0) {
            return pollFirst();
        }
        return head.remove(i);
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
        if (tail == null) {
            return -1;
        }
        return tail.lastIndexOf(v, size() - 1);
    }

    /**
     * Returns the number of entries in this list (including 'null' entries).
     * @return the number of entries in this list (including 'null' entries).
     */
    public int size() {

        //TODO: implement method.
        if (head == null) {
            return 0;
        }
        return head.size();
    }
}

// TODO: define further classes, if needed (either here or in a separate file).

class MyDoubleListNode {
    private IntVar b;
    private MyDoubleListNode next;
    private MyDoubleListNode prev;

    MyDoubleListNode(IntVar b, MyDoubleListNode prev, MyDoubleListNode next) {
        this.b = b;
        this.prev = prev;
        this.next = next;
    }

    IntVar getRaster() {
        return b;
    }

    //Precondition: inner index
    void add(IntVar b, int i) {
        if (i == 0) {
            //insert
            MyDoubleListNode newNode = new MyDoubleListNode(b, prev, this);
            prev.next = newNode;
            this.prev = newNode;
        } else {
            next.add(b, i - 1);
        }
    }

    // Precondition: 'i' is a valid index.
    IntVar get(int i) {
        if (i == 0) {
            return b;
        } else {
            return next.get(i - 1);
        }
    }

    // Precondition: 'i' is a valid index.
    IntVar set(int i, IntVar var) {
        if (i == 0) {
            IntVar result = b;
            this.b = var;
            return result;
        } else {
            return next.set(i - 1, var);
        }
    }

    IntVar remove(int i) {
        if (i == 0) {
            this.prev.next = this.next;
            this.next.prev = this.prev;
            return this.b;
        } else {
            return next.remove(i - 1);
        }
    }

    int lastIndexOf(IntVar var, int i) {
        if (var == this.b) {
            return i;
        }
        if (prev == null) {
            return -1;
        }
        return prev.lastIndexOf(var, i - 1);
    }

    int size() {
        if (next == null) {
            return 1;
        }
        return 1 + next.size();
    }

    public MyDoubleListNode getNext() {
        return next;
    }

    public MyDoubleListNode getPrev() {
        return prev;
    }

    void setNext(MyDoubleListNode node) {
        next = node;
    }

    void setPrev(MyDoubleListNode node) {
        prev = node;
    }

}
