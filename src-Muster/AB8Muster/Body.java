package AB8Muster;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class represents celestial bodies like stars, planets, asteroids, teapots, etc..
 * This class implements 'HierarchicalSystem'.
 */
//
// TODO: Complete this class definition. You can use the Java-Collection-Framework.
//       You can define further classes and methods for the implementation.
//
public class Body implements HierarchicalSystem {

    private String name;
    private double mass;
    private Vector3 massCenter; // position of the center of mass == barycenter
    private Vector3 currentMovement;

    /**
     * Initializes this body.
     *
     * @param name the name of this body, name != null.
     * @param mass the mass of this body, mass > 0.
     * @param massCenter the barycenter of this body, massCenter != null.
     * @param currentMovement the current velocity vector, current movement != null.
     */
    public Body(String name, double mass, Vector3 massCenter, Vector3 currentMovement) {

        this.name = name;
        this.mass = mass;
        this.massCenter = massCenter;
        this.currentMovement = currentMovement;
    }

    /**
     * Initializes this body with name, mass and velocity respectively position of (0,0,0).
     *
     * @param name the name of this body, name != null.
     * @param mass the mass of this body, mass > 0.
     */
    public Body(String name, double mass) {

        this(name, mass, new Vector3(0,0,0), new Vector3(0,0,0));
    }

    /**
     * Returns the distance between the centers of mass of this body and the specified body 'b'.
     *
     * @param b the specified body, b != null.
     * @return the distance between the centers of mass of this body and the specified body 'b'.
     */
    public double distanceTo(Body b) {

        return this.massCenter.distanceTo(b.massCenter);
    }

    /**
     * Returns the acceleration vector of 'this' that results from the gravitational interaction
     * with the specified body 'b'. The returned vector is computed according to Newton's laws of
     * gravitation.
     *
     * @param b the specified body, b != null.
     * @return the acceleration vector.
     */
    public Vector3 acceleration(Body b) {

        Vector3 direction = b.massCenter.minus(this.massCenter);
        double distance = direction.length();
        direction.normalize();
        double length = Simulation.G * b.mass / (distance * distance);
        return direction.times(length);
    }

    /**
     * Accelerates this body for one second according to the specified 'acceleration' vector
     * and updates the current movement accordingly.
     *
     * @param acceleration the acceleration vector, acceleration != null.
     */
    public void accelerate(Vector3 acceleration) {

        this.currentMovement = this.currentMovement.plus(acceleration);
        this.massCenter = this.massCenter.plus(this.currentMovement);
    }

    //@Override
    /**
     * Returns the approximate radius of this body.
     * (It is assumed that the radius r is related to the mass m of the body by
     * r = Math.pow(m, 0.5), where m and r measured in solar mass units.)
     *
     * @return the radius of this body.
     */
    public double getRadius() {

        return SpaceDraw.massToRadius(this.mass);
    }

    @Override
    public int getNumberOfBodies() {
        return 1;
    }

    @Override
    public Deque<Body> asOrderedList(SystemComparator comp) {

        LinkedList<Body> list = new LinkedList<>();
        list.add(this);
        return list;
    }

    /**
     * Returns a string with the name and mass of this body separated by ':'.
     * The string ends with the mass unit "kg.".
     * Example:
     * "Earth: 5.972E24 kg."
     *
     * @return 'this' represented as a string.
     */
    public String toString() {

        return name +": " + mass + " kg.";
    }

    //@Override
    /**
     * @return the name of this body.
     */
    public String getName() {

        return name;
    }

    //@Override
    /**
     * @return the mass of this body.
     */
    public double getMass() {

        return mass;
    }

    //@Override
    /**
     * @return the barycenter of 'this'.
     */
    public Vector3 getCenter() {

        return massCenter;
    }

    /**
     * @return the current movement of 'this'.
     */
    public Vector3 getCurrentMovement() {

        return currentMovement;
    }

    @Override
    public BodyIterator iterator() {
        return new SingleBodyIterator(this);
    }

    public void setState(Vector3 massCenter, Vector3 currentMovement) {
        this.currentMovement = currentMovement;
        this.massCenter = massCenter;
    }
}

// TODO: define further classes, if needed (either here or in a separate file).
class SingleBodyIterator implements BodyIterator {

    private Body body;
    private boolean consumed;

    public SingleBodyIterator(Body body) {
        this.body = body;
    }

    /**
     * Returns 'true' if the iteration has more elements, 'false' otherwise.
     *
     * @return 'true' if the iteration has more elements, 'false' otherwise.
     */
    @Override
    public boolean hasNext() {
        return !consumed;
    }

    /**
     * Returns the next element of the iteration. Throws a 'java.util.NoSuchElementException'
     * if the iteration has no more elements.
     *
     * @return the next element of the iteration.
     * @throws NoSuchElementException if the iteration has no more elements.
     */
    @Override
    public Body next() {
        if (consumed) throw new NoSuchElementException();
        consumed = true;
        return body;
    }

    /**
     * Removes from the list the last element that was returned by 'next()' (optional operation).
     * This call can only be made once per call to 'next()'. The behavior of an iterator is
     * unspecified if the underlying collection is modified while the iteration is in progress
     * in any way other than by calling this method.
     *
     * @throws UnsupportedOperationException if the remove operation is not supported by this
     *                                       iterator (for example the iterator of class 'Body' does not support 'remove()').
     * @throws IllegalStateException         if the next method has not yet been called, or the remove
     *                                       method has already been called after the last call to the next method.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("class body can not remove bodies.");

    }
}

