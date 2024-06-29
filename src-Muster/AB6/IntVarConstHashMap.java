package AB6;

/**
 * This data structure maps variables ('IntVar' objects) to constants ('IntConst' objects).
 * It is implemented as hash map. The map allows multiple variables with the
 * same name as long as they are not identical. There is no limit on the number of key-value
 * mappings stored in the map.
 */
//
// TODO: define further classes and methods for the implementation of the binary search tree, if
//  needed.
//
public class IntVarConstHashMap implements IntVarConstMap //TODO: uncomment clause.
{

    // TODO: declare variables.
    private IntVar[][] keys;
    private IntConst[] values;
    private int size;

    /**
     * Initializes this map as an empty map.
     */
    public IntVarConstHashMap() {

        //TODO: implement constructor.
        keys = new IntVar[1][16];
        values = new IntConst[16];
    }

    /**
     * Initializes this map as an independent copy of the specified map. Later changes of 'this'
     * will not affect 'map' and vice versa.
     */
    public IntVarConstHashMap(IntVarConstHashMap map) {

        //TODO: implement constructor.
        this.keys =  new IntVar[1][];
        this.keys[0] = map.keys[0].clone();
        this.values = map.values.clone();
        this.size = map.size;

    }

    //TODO: define missing parts of this class.
    @Override
    public IntConst get(IntVar key) {

        int point_hash = key.hashCode();
        int index = Math.abs(point_hash) % keys[0].length;

        while (keys[0][index] != null) {
            if (key.equals(keys[0][index])) {
                return values[index];
            }
            index = (index + 1) % keys[0].length;
        }

        return null;
    }

    @Override
    public IntConst put(IntVar key, IntConst value) {

        int point_hash = key.hashCode();
        int index = Math.abs(point_hash) % keys[0].length;

        while (keys[0][index] != null) {
            if (keys[0][index].equals(key)) {
                IntConst oldValue = values[index];
                values[index] = value;
                return oldValue;
            }
            index = (index + 1) % keys[0].length;
        }

        //new key
        values[index] = value;
        keys[0][index] = key;
        size++;
        if (((size + 1.) / keys[0].length) > 0.5) {
            reHash();
        }
        return null;
    }

    @Override
    public IntConst remove(IntVar key) {

        int point_hash = key.hashCode();
        int index = Math.abs(point_hash) % keys[0].length;

        while (keys[0][index] != null) {
            if (keys[0][index].equals(key)) {
                IntConst toReturn = values[index];
                keys[0][index] = null;
                values[index] = null;
                index = (index + 1) % keys[0].length;
                //rehash all continuous entries after deleted element
                while (keys[0][index] != null) {
                    IntVar k = keys[0][index];
                    IntConst v = values[index];
                    keys[0][index] = null;
                    values[index] = null;
                    size--;
                    put(k, v);
                    index = (index + 1) % keys[0].length;
                }
                size--;
                return toReturn;
            }
            index = (index + 1) % keys[0].length;
        }
        return null;
    }

    @Override
    public boolean containsKey(IntVar key) {

        int point_hash = key.hashCode();
        int index = Math.abs(point_hash) % keys[0].length;

        while (keys[0][index] != null) {
            if (key.equals(keys[0][index])) {
                return true;
            }
            index = (index + 1) % keys[0].length;
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public IntVarSet keySet() {
        return new HashMapKeySet(this, keys);
    }

    //doubles the capacity and performs a rehash of all entries
    private void reHash() {
        IntVar[] oldKeys = keys[0];
        IntConst[] oldValues = values;
        keys[0] = new IntVar[oldKeys.length * 2];
        values = new IntConst[oldValues.length * 2];
        size = 0;
        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }
}

// TODO: define further classes, if needed, either here or in a separate file.

class HashMapKeySet implements IntVarSet {

    private IntVarConstHashMap map;
    IntVar[][] keys;

    public HashMapKeySet(IntVarConstHashMap map, IntVar[][] keys) {
        this.map = map;
        this.keys = keys;
    }

    /**
     * Returns an iterator over elements of type 'IntVar'.
     *
     * @return an iterator over elements of type 'IntVar'.
     */
    @Override
    public IntVarIterator iterator() {
        return new HashMapKeySetIterator(keys);
    }

    /**
     * Adds the specified element 'v' to this set. If 'v' is already contained in this set,
     * the methods has no effect.
     *
     * @param v the variable that is added, v != null.
     */
    @Override
    public void add(IntVar v) {
        if (contains(v)) return;
        map.put(v, null);
    }

    /**
     * Returns 'true' if and only if 'v' is contained in this set. More formally, returns
     * 'true' if and only if this set contains an element 'e' such that e == m.
     * (There can be at most one such element.)
     *
     * @param v the element to be checked, v != null.
     * @return 'true' if and only if 'v' is contained in this set.
     */
    @Override
    public boolean contains(IntVar v) {
        return map.containsKey(v);
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the number of elements in this set.
     */
    @Override
    public int size() {
        return map.size();
    }
}

class HashMapKeySetIterator implements IntVarIterator {
    private IntVar[][] keys;
    int index;

    public HashMapKeySetIterator(IntVar[][] keys) {
        this.keys = keys;
        while (index < keys[0].length && keys[0][index] == null) index++;
    }

    @Override
    public boolean hasNext() {
        return index < keys[0].length;
    }

    @Override
    public IntVar next() {
        IntVar toReturn = keys[0][index++];
        while (index < keys[0].length && keys[0][index] == null) index++;

        return toReturn;
    }
}

