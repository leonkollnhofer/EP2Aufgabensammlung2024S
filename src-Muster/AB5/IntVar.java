package AB5;

/**
 * This class represents a free variable which can take on integer values. Each object of
 * this class represents a different variable (regardless of the name). This means that
 * for two 'IntVar' reference values 'a' and 'b', a.equals(b) == true only if 'a' and 'b'
 * refer to the same object (a == b has value 'true').
 */
//
// TODO: define further classes, if needed.
//
public class IntVar implements IntVarTerm //TODO: uncomment clause.
{
    private final String name;

    /**
     * Initializes this variable with a specified name.
     * @param name, the name != null.
     */
    public IntVar(String name) {

        this.name = name;
    }

    /**
     * Returns the name of this variable.
     * @return the name of this variable.
     */
    public String getName() {

        return name;
    }

    @Override
    /**
     * Returns a readable representation of 'this', which is the name of this variable.
     * @return a readable representation of 'this'.
     */
    public String toString() {

        return name;
    }

    //TODO: define missing parts of this class.
    @Override
    public LinearExpression plus(LinearExpression e) {

        if (e.getClass() == this.getClass()) {
            if (e.equals(this)) {
                return new ConstVarProduct(new IntConst(2), this);
            }
            return new SumOfTerms(this, (IntVar) e);
        }
        return e.plus(this);
    }

    @Override
    public LinearExpression plus(IntConst c) {

        if (c.isZero()) {
            return this;
        }
        return new SumOfTerms(this, c);
    }

    @Override
    public LinearExpression negate() {
        return new ConstVarProduct(ONE.negate(), this);
    }

    @Override
    public LinearExpression times(IntConst c) {
        if (c.negate().plus(ONE).isZero()) {
            return this;
        }
        if (c.isZero()) {
            return c;
        }
        return new ConstVarProduct(c, this);
    }

    @Override
    public LinearExpression assignValue(IntVarConstMap map) {

        IntConst c = map.get(this);
        if (c != null) {
            return c;
        }
        return this;
    }

    @Override
    public IntVarIterator iterator() {
        return new VarIntVarIterator(this) ;
    }

    @Override
    public IntVar getVar() {
        return this;
    }

    @Override
    public IntConst getCoeff() {
        return ONE;
    }

    /*
    @Override
    public LinearExpression plus(IntVarTerm t) {
        if (t.getVar().equals(this)) {
            IntConst c = t.getCoeff().plus(new IntConst(1));
            if (c.isZero()) {
                return ZERO;
            }
            if (c.plus(ONE).isZero()) {
                return this;
            }
            return new ConstVarProduct(c, this);
        }
        return new SumOfTerms(this, t);
    }
    */
}

class VarIntVarIterator implements IntVarIterator {

    private boolean consumed = false;
    private IntVar var;

    public VarIntVarIterator(IntVar v) {
        var = v;
    }

    @Override
    public boolean hasNext() {
        return !consumed;
    }

    @Override
    public IntVar next() {
        IntVar toReturn = var;
        if (!consumed) {
            consumed = true;
            return toReturn;
        }
        return null;
    }
}

// TODO: define further classes, if needed, either here or in a separate file.