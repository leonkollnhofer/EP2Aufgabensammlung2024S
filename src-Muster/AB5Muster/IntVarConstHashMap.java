package AB5Muster;

/**
 * This data structure maps variables ('IntVar' objects) to constants ('IntConst' objects).
 * It is implemented as hash map. The map allows multiple variables with the
 * same name as long as they are not identical. There is no limit on the number of key-value
 * mappings stored in the map. This class implements 'IntVarConstMap'.
 */
//
// TODO: define further classes and methods for the implementation of this class, if
//  needed.
//
public class IntVarConstHashMap implements IntVarConstMap //TODO: uncomment clause.
{

    // TODO: declare variables.
    private IntVar[] keys;
    private IntConst[] values;
    private int size;


    /**
     * Initializes this map as an empty map.
     */
    public IntVarConstHashMap() {

        //TODO: implement constructor.
        keys = new IntVar[16];
        values = new IntConst[16];
    }

    /**
     * Initializes this map as an independent copy of the specified map. Later changes of 'this'
     * will not affect 'map' and vice versa.
     */
    public IntVarConstHashMap(IntVarConstHashMap map) {

        //TODO: implement constructor.
        this.keys = map.keys.clone();
        this.values = map.values.clone();
        this.size = map.size;
    }

    //TODO: define missing parts of this class.
    @Override
    public IntConst get(IntVar key) {

        int point_hash = key.hashCode();
        int index = Math.abs(point_hash) % keys.length;

        while (keys[index] != null) {
            if (key.equals(keys[index])) {
                return values[index];
            }
            index = (index + 1) % keys.length;
        }

        return null;
    }

    @Override
    public IntConst put(IntVar key, IntConst value) {

        int point_hash = key.hashCode();
        int index = Math.abs(point_hash) % keys.length;

        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                IntConst oldValue = values[index];
                values[index] = value;
                return oldValue;
            }
            index = (index + 1) % keys.length;
        }

        //new key
        values[index] = value;
        keys[index] = key;
        size++;
        if (((size + 1.) / keys.length) > 0.5) {
            reHash();
        }
        return null;
    }

    @Override
    public IntConst remove(IntVar key) {

        int point_hash = key.hashCode();
        int index = Math.abs(point_hash) % keys.length;

        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                IntConst toReturn = values[index];
                keys[index] = null;
                values[index] = null;
                index = (index + 1) % keys.length;
                //rehash all continuous entries after deleted element
                while (keys[index] != null) {
                    IntVar k = keys[index];
                    IntConst v = values[index];
                    keys[index] = null;
                    values[index] = null;
                    size--;
                    put(k, v);
                    index = (index + 1) % keys.length;
                }
                size--;
                return toReturn;
            }
            index = (index + 1) % keys.length;
        }
        return null;
    }

    @Override
    public boolean containsKey(IntVar key) {

        int point_hash = key.hashCode();
        int index = Math.abs(point_hash) % keys.length;

        while (keys[index] != null) {
            if (key.equals(keys[index])) {
                return true;
            }
            index = (index + 1) % keys.length;
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public IntVarQueue keyQueue() {

        IntVarQueue queue = new IntVarQueue();
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) queue.add(keys[i]);
        }
        return queue;
    }

    //doubles the capacity and performs a rehash of all entries
    private void reHash() {
        IntVar[] oldKeys = keys;
        IntConst[] oldValues = values;
        keys = new IntVar[oldKeys.length * 2];
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

