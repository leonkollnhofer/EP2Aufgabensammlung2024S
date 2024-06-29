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
    private ListNode head;
    private ListNode tail;
    private int size;

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
        ListNode current = list.head;
        if (current == null) {
            return;
        }
        while (current != null) {
            addLast(current.getBody());
            current = current.getNext();
        }
    }

    /**
     * Inserts the specified element 'b' at the beginning of this list.
     *
     * @param b the body that is added (b can also be 'null').
     */
    public void addFirst(Body b) {

        // TODO: implement method.
        if (head == null) {
            head = tail = new ListNode(b, null);
        } else {
            head = new ListNode(b, head);
        }
        size++;
    }

    /**
     * Appends the specified element 'b' to the end of this list.
     *
     * @param b the body that is added (b can also be 'null').
     */
    public void addLast(Body b) {

        // TODO: implement method.
        if (head == null) {
            head = tail = new ListNode(b, null);
        } else {
            ListNode n = new ListNode(b, null);
            tail.setNext(n);
            tail = n;
        }
        size++;
    }

    /**
     * Returns the last element in this list (at the end of the list) without removing it.
     * Returns 'null' if the list is empty.
     */
    public Body getLast() {

        // TODO: implement method.
        if (head == null) {
            return null;
        }
        return tail.getBody();
    }

    /**
     * Returns the first element in this list (at the beginning of the list) without removing it.
     * Returns 'null' if the list is empty.
     */
    public Body getFirst() {

        // TODO: implement method.
        if (head == null) {
            return null;
        }
        return head.getBody();
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
        if (head == null) {
            return null;
        }
        Body toReturnBody = head.getBody();
        size--;
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return toReturnBody;
    }

    /**
     * Retrieves and removes the last element in this list, that is, the element with the highest
     * index. Returns 'null' if the list is empty.
     *
     * @return the last element in this list, or 'null' if the list is empty.
     */
    public Body pollLast() {

        // TODO: implement method.
        if (tail != null) {
            size--;
            ListNode current = head;
            if (current == tail) {
                Body toReturnBody = tail.getBody();
                tail = head = null;
                return toReturnBody;
            }
            while (current.getNext() != null) {
                if (current.getNext() == tail) {
                    current.setNext(null);
                    Body toReturnBody = tail.getBody();
                    tail = current;
                    return toReturnBody;
                }
                current = current.getNext();
            }
        }
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
        if (i == 0) {
            addFirst(b);
        } else if (i == size()) {
            addLast(b);
        } else {
            ListNode predecessor = head;
            for (;i != 1; i--) {
                predecessor = predecessor.getNext();
            }
            predecessor.setNext(new ListNode(b, predecessor.getNext()));
            size++;
        }
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
        ListNode current = head;
        for (;i > 0; i--) {
            current = current.getNext();
        }
        return current.getBody();
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
        ListNode current = head;
        for (int i = 0; current != null; current = current.getNext(), i++) {
            if (current.getBody() == b) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the number of entries in this list (including 'null' entries).
     */
    public int size() {

        // TODO: implement method.
        return size;
    }
}

// TODO: define further classes, if needed (either here or in a separate file).

class ListNode {
    private final Body b;
    private ListNode next;

    ListNode(Body b, ListNode next) {

        this.b = b;
        this.next = next;
    }

    Body getBody() {
        return b;
    }

    ListNode getNext() {

        return next;
    }

    void setNext(ListNode node) {

        next = node;
    }
}
