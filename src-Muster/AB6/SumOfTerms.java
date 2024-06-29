package AB6;

import AB5.IntVarConstHashMap;
import AB5.IntVarQueue;

import java.util.Objects;

/**
 * This class represents a linear expression consisting of more than one term.
 * For example, 3x - y + 5 consists of multiple terms. 'SumOfTerms' provides an iterator over all
 * variables occurring in 'this'. The order of the iteration is not specified.
 * In this example it iterates over elements 'x' and 'y'. This class implements 'LinearExpression'.
 */
//
// TODO: define further classes, if needed.
//
public class SumOfTerms implements LinearExpression // TODO: uncomment clause.
{
    //TODO: declare variables.
    private IntVarConstTreeMap map;
    private IntConst c = new IntConst(0);

    //TODO: define private constructors as needed.


    //private copy constructor
    private SumOfTerms(SumOfTerms toCopy) {
        this.map = new IntVarConstTreeMap(toCopy.map);
        this.c = toCopy.c;
    }

    private SumOfTerms(IntVarConstTreeMap map, IntConst c) {

        this.map = new IntVarConstTreeMap(map);
        this.c = c;
    }

    /**
     * Initializes 'this' as a sum of two terms, each with a variable.
     *
     * @param t1 the first term in this sum.
     * @param t2 the second term in this sum.
     *           The following conditions holds: t1.getVar().equals(t2.getVar()) == false.
     */
    public SumOfTerms(IntVarTerm t1, IntVarTerm t2) {

        //TODO: implement constructor.
        this.map = new IntVarConstTreeMap();
        this.map.put(t1.getVar(), t1.getCoeff());
        this.map.put(t2.getVar(), t2.getCoeff());
    }

    /**
     * Initializes 'this' as a sum of a term with a variable and a constant.
     *
     * @param t the term != null.
     * @param c a constant != null, for which c.isZero() == false.
     */
    public SumOfTerms(IntVarTerm t, IntConst c) {

        //TODO: implement constructor.
        this.map = new IntVarConstTreeMap();
        this.map.put(t.getVar(), t.getCoeff());
        this.c = c;
    }


    //TODO: implement missing parts of this class.
    public LinearExpression plus(LinearExpression e) {

        if (e.getClass() == this.getClass()) {
            return plus((SumOfTerms) e);
        }
        return e.plus(this);

    }

    @Override
    public LinearExpression plus(IntConst c) {
        c = c.plus(this.c);
        if (c.isZero() && map.size() == 1) {
            IntVarSet set = map.keySet();
            IntVar v = set.iterator().next();
            if (map.get(v).plus(ONE.negate()).isZero()) {
                return v;
            }
        }
        return new SumOfTerms(new IntVarConstTreeMap(map), c);
    }

    @Override
    public LinearExpression plus(IntVarTerm t) {
        IntVar v = t.getVar();
        IntVarConstTreeMap resultMap = new IntVarConstTreeMap(this.map);
        IntConst c = resultMap.get(v);
        c = c == null ? ZERO : c;
        c = c.plus(t.getCoeff());
        if (c.isZero()) {
            resultMap.remove(v);
        } else {
            resultMap.put(v, c);
        }
        if (resultMap.size() == 0) {
            return this.c;
        } else {
            if (this.c.isZero() && resultMap.size() == 1) {
                v = resultMap.keySet().iterator().next();
                if (resultMap.get(v).plus(ONE.negate()).isZero()) {
                    return v;
                }
                return new ConstVarProduct(resultMap.get(v), v);
            }
            return new SumOfTerms(resultMap, this.c);
        }
    }

    public LinearExpression plus(SumOfTerms sum) {
        IntVarConstTreeMap resultMap = new IntVarConstTreeMap(this.map);
        IntVarSet set = sum.map.keySet();
        for (IntVar v : set) {
            IntConst c = this.map.get(v);
            c = c == null ? new IntConst(0) : c;
            c = c.plus(sum.map.get(v));
            if (!c.isZero()) {
                resultMap.put(v, c);
            } else {
                resultMap.remove(v);
            }
        }

        // add constant term
        IntConst c = this.c.plus(sum.c);

        // consolidate type
        if (resultMap.size() == 0) {
            return c;
        } else {
            if (c.isZero() && resultMap.size() == 1) {
                set = resultMap.keySet();
                for (IntVar v : set) {
                    if (this.map.get(v).plus(new IntConst(-1)).isZero()) {
                        return v;
                    }
                    return new ConstVarProduct(resultMap.get(v), v);
                }
            }
            return new SumOfTerms(resultMap, c);
        }
    }

    public SumOfTerms negate() {

        SumOfTerms result = new SumOfTerms(map, c);
        IntVarSet set = result.map.keySet();
        for (IntVar v : set) {
            result.map.put(v, map.get(v).negate());
        }
        result.c = result.c.negate();
        return result;
    }

    @Override
    public LinearExpression times(IntConst c) {

        LinearExpression result = this.c.times(c);
        for (IntVar v : this) {
            IntConst coeff = this.map.get(v);
            result = result.plus(new ConstVarProduct(coeff, v).times(c));
        }
        return result;

    }

    @Override
    public LinearExpression assignValue(IntVarConstMap varValues) {

        LinearExpression result = new SumOfTerms(this);

        for (IntVar v : varValues.keySet()) {
            IntConst c = varValues.get(v);
            IntConst toBeAdded = map.get(v);
            if (toBeAdded != null) {
                toBeAdded = toBeAdded.times(c);
                result = result.plus(toBeAdded);
                result = result.plus(v.times(map.get(v)).negate());
            }
        }
        return result;

    }

    @Override
    public String toString() {

        String result = "";

        for (IntVar v : this) {
            IntConst coeff = map.get(v);
            if (!coeff.isZero()) {
                String s = coeff.toString();
                if (coeff.plus(new IntConst(1)).isZero()) {
                    s = "-";
                } else {
                    if (coeff.plus(new IntConst(-1)).isZero()) {
                        s = "";
                    }
                }
                if (new IntConst(0).lessThan(coeff)) {
                    result += (result.isEmpty() ? "" : "+") + s;
                } else {
                    result += s;
                }
                result += v.toString();
            }
        }
        if (!c.isZero()) {
            if (new IntConst(0).lessThan(c)) {
                result += (result.isEmpty() ? "" : "+") + c;
            } else {
                result += c;
            }
        }

        return result.isEmpty() ? "0" : result;
    }

    @Override
    public IntVarIterator iterator() {
        return this.map.keySet().iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SumOfTerms intVars)) return false;
        return Objects.equals(map, intVars.map) && Objects.equals(c, intVars.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, c);
    }
}

// TODO: define further classes, if needed, either here or in a separate file.
