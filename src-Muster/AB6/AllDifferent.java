package AB6;

/**
 * This class represents an ALL DIFFERENT condition.
 */
//
// TODO: define further classes and methods, if needed.
//
public class AllDifferent implements Condition //TODO: uncomment clause.
 {
     //TODO: define missing parts of this class.
    private IntVarSet set;

     /**
      * Initializes this 'AllDifferent' constraint object.
      *
      * @param set a set of variables which are required to have unique values
      *            across them. This set defines the scope of the constraint.
      */
    public AllDifferent(IntVarSet set) {
        //TODO: implement constructor.
        this.set = set;
    }

    @Override
    public Condition and(Condition c) {
        return new AND(this, c);
    }

    @Override
    public Condition not() {
        return new NOT(this);
    }

    @Override
    public IntVarSet getVarSet() {
        IntVarConstTreeMap treeMap = new IntVarConstTreeMap();

        for (IntVar v : set) {
            treeMap.put(v, null);
        }

        return treeMap.keySet();
    }

    @Override
    public boolean getValue(IntVarConstMap assignments) {
        //TODO: implement method.
        for (IntVar v1 : set) {
            for (IntVar v2 : set) {
                if (v1.equals(v2)) continue;
                if (v1.assignValue(assignments).equals(v2.assignValue(assignments))) {
                    return false;
                }
            }
        }
        return true;
    }
}
