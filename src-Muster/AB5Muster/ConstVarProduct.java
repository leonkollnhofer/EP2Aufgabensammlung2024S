package AB5Muster;

import java.util.Objects;

/**
 * This class represents a product of a constant coefficient and a variable (i.e. a linear term).
 */
//
// TODO: define further classes, if needed.
//
public class ConstVarProduct implements IntVarTerm // TODO: uncomment clause.
{

    // TODO: declare variables.
    private IntVar var;
    private IntConst coeff;

    /**
     * Initialized this product of 'coeff' and 'var' (for example 3x is such a product).
     *
     * @param coeff the coefficient of the term which is not 0 or 1,
     *              coeff != null && coeff.isZero() == false &&
     *              coeff.plus(new IntConst(-1)).isZero == false.
     * @param var   the variable in the term, var != null.
     */
    public ConstVarProduct(IntConst coeff, IntVar var) {

        // TODO: implement constructor.
        this.var = var;
        this.coeff = coeff;
    }

    //TODO: define missing parts of this class.
    @Override
    public IntVarIterator iterator() {
        return new ProductIntVarIterator(var);
    }

    @Override
    public LinearExpression plus(LinearExpression e) {
        if (e.getClass() == this.getClass()) {
            return plus((ConstVarProduct) e);
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

    private LinearExpression plus(ConstVarProduct t) {
        if (t.var.equals(this.var)) {
            var coeffSum = coeff.plus(t.coeff);
            if (coeffSum.isZero()) {
                return coeffSum;
            }
            return new ConstVarProduct(coeffSum, var);
        }
        return new SumOfTerms(this, t);
    }

    @Override
    public LinearExpression negate() {
        if (coeff.plus(ONE).isZero()) {
            return var;
        }
        return new ConstVarProduct(coeff.negate(), var);
    }

    @Override
    public LinearExpression times(IntConst c) {
        if (c.isZero()) {
            return c;
        }
        c = c.times(coeff);
        if (c.plus(ONE.negate()).isZero()) {
            return var;
        }
        return new ConstVarProduct(c, var);
    }

    @Override
    public LinearExpression assignValue(IntVarConstMap varValues) {
        if (!varValues.containsKey(var)) {
            return this;
        }
        IntConst c = varValues.get(var);
        return c.times(coeff);
    }

    @Override
    public IntVar getVar() {
        return var;
    }

    @Override
    public IntConst getCoeff() {
        return coeff;
    }

    public String toString() {
        if (coeff.equals(ONE.negate())) {
            return "-" + var;
        }
        return "" + coeff + var;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstVarProduct other = (ConstVarProduct) o;

        if (!Objects.equals(var, other.var)) return false;
        return Objects.equals(coeff, other.coeff);
    }

    @Override
    public int hashCode() {

        return Objects.hash(coeff, var);
    }
}

// TODO: define further classes, if needed, either here or in a separate file.
class ProductIntVarIterator implements IntVarIterator {

    private boolean consumed = false;
    private final IntVar v;

    public ProductIntVarIterator(IntVar v) {
        this.v = v;
    }

    @Override
    public boolean hasNext() {
        return !consumed;
    }

    @Override
    public IntVar next() {
        if (!consumed) {
            consumed = true;
            return v;
        }
        return null;
    }
};
