package AB2Muster;

/**
 * A queue of bodies. A collection designed for holding bodies prior to processing.
 * The bodies of the queue can be accessed in a FIFO (first-in-first-out) manner,
 * i.e., the body that was first inserted by 'add' is retrieved first by 'poll'.
 * The number of elements of the queue is not limited.
 */
public class BodyQueue {

    //TODO: declare variables.

    private int c;
    private Body[] es;
    private int head, tail;

    /**
     * Doubles the size of the array used by this queue. This method is called if 'add' is called
     * and all positions of the array are already occupied.
     */
    private void doubleCapacity() {
        c = 2*c;

        Body[] newes = new Body[c];

        int i = 0, j = 0;
        while (i < head) {
            newes[j++] = es[i++];
        }

        j = head += es.length;

        while (i < es.length) {
            newes[j++] = es[i++];
        }

        es = newes;
    }

    /**
     * Initializes this queue with an initial capacity.
     * @param initialCapacity the length of the internal array in the beginning,
     *                        initialCapacity > 0.
     */
    public BodyQueue(int initialCapacity) {

        //TODO: define constructor.
        c = initialCapacity;
        es = new Body[c];
    }

    /**
     * Initializes this queue as an independent copy of the specified queue.
     * Calling methods of this queue will not affect the specified queue
     * and vice versa.
     * @param q the queue from which elements are copied to the new queue, q != null.
     */
    public BodyQueue(BodyQueue q) {

        //TODO: define constructor.
        this.es = q.es.clone();
        this.c = q.c;
        this.head = q.head;
        this.tail = q.tail;
    }

    /**
     * Adds the specified body 'b' to this queue.
     * @param b the element that is added to the queue.
     */
    public void add(Body b) {

        //TODO: implement method.
        es[tail] = b;
        tail = (tail + 1) % c;

        if (tail == head) {
            doubleCapacity();
        }
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns 'null' if this queue is empty.
     * @return the head of this queue (or 'null' if this queue is empty).
     */
    public Body poll() {

        //TODO: implement method.
        Body result = es[head];
        es[head] = null;
        if(tail != head) {
            head = (head + 1) % c;
        }
        return result;
    }

    /**
     * Returns the number of bodies in this queue.
     * @return the number of bodies in this queue.
     */
    public int size() {

        //TODO: implement method.
        if (head <= tail) {
            return tail - head;
        }
        return c - head + tail;
    }
}
