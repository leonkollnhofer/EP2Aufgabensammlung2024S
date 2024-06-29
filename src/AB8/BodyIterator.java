package AB8;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator over elements of type 'Body'.
 *
 * PLEASE, DO NOT CHANGE THIS FILE!
 */
public interface BodyIterator extends Iterator<Body> {

    /**
     * Returns 'true' if the iteration has more elements, 'false' otherwise.
     *
     * @return 'true' if the iteration has more elements, 'false' otherwise.
     */
    public boolean hasNext();

    /**
     * Returns the next element of the iteration. Throws a 'java.util.NoSuchElementException'
     * if the iteration has no more elements.
     *
     * @return the next element of the iteration.
     * @throws NoSuchElementException if the iteration has no more elements.
     */
    public Body next();

    /**
     * Removes from the list the last element that was returned by 'next()' (optional operation).
     * This call can only be made once per call to 'next()'. The behavior of an iterator is
     * unspecified if the underlying collection is modified while the iteration is in progress
     * in any way other than by calling this method.
     *
     * @throws UnsupportedOperationException if the remove operation is not supported by this
     * iterator (for example the iterator of class 'Body' does not support 'remove()').
     * @throws IllegalStateException if the next method has not yet been called, or the remove
     * method has already been called after the last call to the next method.
     */
    public void remove();
}
