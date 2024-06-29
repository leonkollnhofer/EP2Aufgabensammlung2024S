package AB6;

/**
 * This class represents IS EQUAL condition. It represents the constraint that
 * two expressions are equal when their variables are assigned to specific values
 * (using the same variable assignments in both expressions).
 */
//
// TODO: define further classes and methods, if needed.
//
public class IsEqual implements Condition //TODO: uncomment clause.
{
    //TODO: define missing parts of this class.
    private LinearExpression e1;
    private LinearExpression e2;

    /**
     * Initializes 'this' with two linear expressions.
     * @param e1 the first expression, e1 != null.
     * @param e2 the second expression, e2 != null.
     */
    public IsEqual(LinearExpression e1, LinearExpression e2) {

        //TODO: implement constructor.
        this.e1 = e1;
        this.e2 = e2;
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
        for(IntVar v: e1) {
            treeMap.put(v, null);
        }
        for(IntVar v: e2) {
            treeMap.put(v, null);
        }
        return treeMap.keySet();
    }

    /**
     * Returns e1.assignValue(assignments).equals(e2.assignValue(assignments)).
     * @param assignments the map with variable assignments, assignments != null.
     * @return e1.assignValue(assignments).equals(e2.assignValue(assignments)).
     */
    @Override
    public boolean getValue(IntVarConstMap assignments) {

        //TODO: implement method.
        return e1.assignValue(assignments).equals(e2.assignValue(assignments));
    }
}
