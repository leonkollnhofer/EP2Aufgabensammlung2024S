package AB8;

import java.util.*;

/**
 * Represents a hierarchical system consisting of more than one subsystem (i.e., implying more
 * than one body).
 */
//
// TODO: Complete this class definition. You can use the Java-Collection-Framework.
//       You can define further classes and methods for the implementation.
//
public class MultiBody implements HierarchicalSystem {

    //TODO: private variables and methods if needed.
    //TODO: missing parts of this class.

    private final List<HierarchicalSystem> subsystems = new LinkedList<>();

    /**
     * Initializes this system with more than one subsystem.
     *
     * @param subsystems an array of components of this system (bodies or subsystems),
     *                   subsystems != null && subsystems.length > 1.
     *                   Refer to Java Varargs documentation for more details:
     *                   https://docs.oracle.com/javase/8/docs/technotes/guides/language/varargs.html
     */
    public MultiBody(HierarchicalSystem... subsystems) {

        //TODO: implement constructor.
        Collections.addAll(this.subsystems, subsystems);
    }

    @Override
    public String getName() {
        return asOrderedList(new DistanceComparator(getCenter())).getFirst().getName();
    }

    @Override
    public double getMass() {

        double overallMass = 0;
        for (HierarchicalSystem s : subsystems) {
            overallMass += s.getMass();
        }
        return overallMass;
    }

    @Override
    public Vector3 getCenter() {
        Vector3 center = new Vector3(0, 0, 0);
        double overallMass = 0;
        for (HierarchicalSystem s : subsystems) {
            center = center.plus(s.getCenter().times(s.getMass()));
            overallMass += s.getMass();
        }
        return center.times(1 / overallMass);
    }

    @Override
    public double getRadius() {

        Vector3 center = getCenter();
        Deque<Body> list = asOrderedList(new DistanceComparator(center));
        double distance = 0;

        for (Body body : list) {
            distance = Math.max(distance, body.getCenter().distanceTo(center) + body.getRadius());
        }

        return distance;
    }

    @Override
    public int getNumberOfBodies() {

        int n = 0;
        for (HierarchicalSystem s : subsystems) {
            n += s.getNumberOfBodies();
        }
        return n;
    }

    @Override
    public Deque<Body> asOrderedList(SystemComparator comp) {

        LinkedList<Body> list = new LinkedList<>();

        for (Body b : this) {
            list.add(b);
        }

        list.sort(comp);

        return list;
    }

    @Override
    public BodyIterator iterator() {

        return new MultiBodyIterator(subsystems);
    }

    @Override
    public String toString() {
        return toString("");
    }

    private String toString(String indent) {
        TreeSet<HierarchicalSystem> subsystemsOrdered =
                new TreeSet<>(new DistanceComparator(getCenter())
                        .thenComparing(new MassComparator().reversed())
                        .thenComparing(new NameComparator())
                );
        subsystemsOrdered.addAll(subsystems);

        Iterator<HierarchicalSystem> iterator = subsystemsOrdered.iterator();
        String result = toString(iterator.next(), "");

        while (iterator.hasNext()) {
            result += "\n" + toString(iterator.next(), indent + "   ");
        }

        return result;
    }

    private static String toString(HierarchicalSystem system, String indent) {
        if (system.getNumberOfBodies() == 1) {
            return indent + system;
        } else {
            return indent + ((MultiBody) system).toString(indent);
        }
    }
}

class MultiBodyIterator implements BodyIterator {
    private final Iterator<HierarchicalSystem> systemIter;
    private BodyIterator currentIter;
    private HierarchicalSystem currentSystem;
    private boolean hasLast;

    public MultiBodyIterator(List<HierarchicalSystem> subsystems) {
        systemIter = subsystems.iterator();
        currentSystem = systemIter.next();
        currentIter = currentSystem.iterator();
    }

    @Override
    public boolean hasNext() {
        return currentIter.hasNext() || systemIter.hasNext();
    }

    @Override
    public Body next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        if (!currentIter.hasNext()) {
            currentSystem = systemIter.next();
            currentIter = currentSystem.iterator();
        }

        hasLast = true;
        return currentIter.next();
    }

    @Override
    public void remove() {
        if (!hasLast) {
            throw new IllegalStateException();
        }

        if (currentSystem.getNumberOfBodies() == 1) {
            systemIter.remove();

            if (systemIter.hasNext()) {
                currentSystem = systemIter.next();
                currentIter = currentSystem.iterator();
            }
        } else {
            currentIter.remove();
        }

        hasLast = false;
    }
}
