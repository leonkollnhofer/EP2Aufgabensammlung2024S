package AB6Muster;

/**
 * This class represents a Boolean AND-combination.
 */
//
// TODO: define further classes and methods, if needed.
//
public class AND implements Condition //TODO: uncomment clause.
{

    //TODO: define missing parts of this class.
    final private Condition c1, c2;

    /**
     * Initializes 'this' as the Boolean combination 'c1 AND c2'.
     * @param c1 the first operand, c1 != null.
     * @param c2 the second operand, c2 != null.
     */
    public AND(Condition c1, Condition c2) {
        //TODO: implement constructor.
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public IntVarSet getVarSet() {
        IntVarConstTreeMap treeMap = new IntVarConstTreeMap();

        for (IntVar v : c1.getVarSet()) {
            treeMap.put(v, null);
        }

        for (IntVar v : c2.getVarSet()) {
            treeMap.put(v, null);
        }

        return new IntVarSet() {
            @Override
            public void add(IntVar v) {
                treeMap.put(v, null);
            }

            @Override
            public boolean contains(IntVar v) {
                return treeMap.containsKey(v);
            }

            @Override
            public int size() {
                return treeMap.size();
            }

            @Override
            public IntVarIterator iterator() {
                return treeMap.keySet().iterator();
            }
        };
    }

    @Override
    public boolean getValue(IntVarConstMap assignments) {

        return c1.getValue(assignments) && c2.getValue(assignments);
    }
}

