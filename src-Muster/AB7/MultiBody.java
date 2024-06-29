package AB7;

import java.util.*;

/**
 * Represents a hierarchical system consisting of more than one subsystem (i.e., implying more
 * than one body).
 */
public class MultiBody implements HierarchicalSystem {
    private Set<HierarchicalSystem> sub = new HashSet<>();

    /**
     * Initializes this system with more than one subsystem.
     *
     * @param subsystems an array of components of this system (bodies or subsystems),
     *                   subsystems != null && subsystems.length > 1.
     *                   <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/language/varargs.html">Refer to Java Varargs documentation for more details</a>
     */
    public MultiBody(HierarchicalSystem... subsystems) {
        for (HierarchicalSystem s : subsystems) {
            sub.add(s);
        }

        this.sub = new TreeSet<>(new DistanceComparator(getCenter()));
        for (HierarchicalSystem s : subsystems) {
            sub.add(s);
        }
    }

    @Override
    public String getName() {
        return this.sub.iterator().next().getName();
    }

    @Override
    public double getMass() {
        double overallMass = 0;
        for (HierarchicalSystem s : sub) {
            overallMass += s.getMass();
        }
        return overallMass;
    }

    @Override
    public Vector3 getCenter() {
        Vector3 center = new Vector3(0, 0, 0);
        double overallMass = 0;
        for (HierarchicalSystem s : sub) {
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
        for (HierarchicalSystem s : sub) {
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
        return new MultiBodyIterator(sub);
    }

    @Override
    public String toString() {
        return toString("");
    }

    private String toString(String indent) {
        String result = "";
        boolean first = true;
        for (HierarchicalSystem s : sub) {
            if (first) {
                result += toString(s, "");
                first = false;
            } else {
                result += toString(s, indent + "   ");
            }
        }
        return result;
    }

    private static String toString(HierarchicalSystem system, String indent) {
        if (system.getNumberOfBodies() == 1) {
            return indent + system + "\n";
        } else {
            return indent + ((MultiBody) system).toString(indent);
        }
    }
}

class MultiBodyIterator implements BodyIterator {
    private final Iterator<HierarchicalSystem> sub;
    private BodyIterator current;

    public MultiBodyIterator(Set<HierarchicalSystem> sub) {
        this.sub = sub.iterator();
        current = this.sub.next().iterator();
    }

    @Override
    public boolean hasNext() {
        return current.hasNext() || this.sub.hasNext();
    }

    @Override
    public Body next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (!current.hasNext()) {
            current = this.sub.next().iterator();
        }
        return current.next();
    }
}
