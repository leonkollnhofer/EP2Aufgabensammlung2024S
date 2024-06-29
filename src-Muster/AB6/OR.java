package AB6;

/**
 * This class represents a Boolean OR-combination.
 */
//
// TODO: define further classes and methods, if needed.
//
public class OR implements Condition //TODO: uncomment clause.
{
    //TODO: define missing parts of this class.
    private Condition c1, c2;

    /**
     * Initializes 'this' as the Boolean combination 'c1 OR c2'.
     * @param c1 the first operand, c1 != null.
     * @param c2 the second operand, c2 != null.
     */
    public OR(Condition c1, Condition c2) {

        //TODO: implement constructor.
       this.c1 = c1;
       this.c2 = c2;
    }

    public IntVarSet getVarSet() {
        IntVarConstTreeMap treeMap = new IntVarConstTreeMap();
        for (IntVar v: c1.getVarSet()) {
            treeMap.put(v, null);
        }
        for (IntVar v: c2.getVarSet()) {
            treeMap.put(v, null);
        }
        return new IntVarSet() {
            @Override
            public void add(IntVar v) {
                treeMap.put(v, null);
            }

            @Override
            public boolean contains(IntVar v) {
                return treeMap.containsKey(v) ;
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
        return c1.getValue(assignments) || c2.getValue(assignments);
    }
}
