package AB6;

/**
 * This class represents an ALL DIFFERENT condition.
 */
//
// TODO: define further classes and methods, if needed.
//
public class AllDifferent // implements Condition //TODO: uncomment clause.
{

    //TODO: define missing parts of this class.

    /**
     * Initializes this 'AllDifferent' constraint object.
     *
     * @param set a set of variables which are required to have unique values
     *            across them. This set defines the scope of the constraint.
     */
    public AllDifferent(IntVarSet set) {

        //TODO: implement constructor.
    }

    /**
     * Evaluates the condition based on current assignments.
     *
     * This method checks if the values assigned to the variables in the intersection
     * of this condition's variable set and the keys of the provided map are all unique.
     * Only variables that are both in the internal set and the key set of 'assignments'
     * are considered in the evaluation (all other variables are not considered when checking the
     * condition).
     *
     * @param assignments A map (IntVarConstMap) where keys are variables and values are their
     *                    assigned integer values. This map contains current assignments that
     *                    may or may not fully cover all variables in the problem.
     * @return true, if all considered variables have unique values, false otherwise.
     */
    boolean getValue(IntVarConstMap assignments) {

        //TODO: implement method.
        return false;
    }
}
