package AB8;

/**
 * This class represents a comparator based on the name of a hierarchical system.
 */
public class NameComparator implements SystemComparator {

    @Override
    /**
     * Compares its two systems for order based on their names (string order).
     * See further details in 'SystemComparator'.
     *
     * @param s1 the first hierarchical system to be compared, s1 != null.
     * @param s2 the second hierarchical system to be compared, s1 != null.
     * @return a negative integer, zero, or a positive integer as the first argument
     * is less than, equal to, or greater than the second.
     */
    public int compare(HierarchicalSystem b1, HierarchicalSystem b2) {

        //TODO: implement method.
        return 0;
    }
}
