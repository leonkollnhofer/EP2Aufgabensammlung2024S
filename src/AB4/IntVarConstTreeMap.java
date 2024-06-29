package AB4;

/**
 * This data structure maps variables ('IntVar' objects) to constants ('IntConst' objects).
 * It is implemented as a binary search tree where variables are sorted lexicographically according
 * their name using the 'compareTo' method of String. The map allows multiple variables with the
 * same name as long as they are not identical. There is no limit on the number of key-value
 * mappings stored in the map.
 */
//
// TODO: define further classes and methods for the implementation of the binary search tree, if
//  needed.
//
public class IntVarConstTreeMap {

    //TODO: declare variables.

    public IntVarConstTreeMap() {

        //TODO: implement constructor.
    }

    /**
     * Constructs this map as a copy of the specified 'map'. This map has the same key-value mappings
     * as 'map'. However, if 'map' is changed later, it will not affect 'this' and vice versa.
     * @param map the map from which key-value mappings are copied to this new map, map != null.
     */
    public IntVarConstTreeMap(IntVarConstTreeMap map) {

        //TODO: implement constructor.
    }

    /**
     * Constructs this map by copying only those key-value mappings from 'map' for which the key is
     * contained in the specified list. Elements of 'toCopy' which are not keys in 'map' are
     * ignored.
     * @param map the map from which key-value mappings are copied to this new map, map != null.
     * @param toCopy the list of keys specifying which key-value mappings to copy, toCopy != null.
     */
    public IntVarConstTreeMap(IntVarConstTreeMap map, IntVarDoublyLinkedList toCopy) {

        //TODO: implement constructor.
    }


    /**
     * Adds a new key-value association to this map. If the key already exists in this map,
     * the value is replaced and the old value is returned. Otherwise, 'null' is returned.
     * @param key a variable != null.
     * @param value the constant to be associated with the key (can also be 'null').
     * @return the old value if the key already existed in this map, or 'null' otherwise.
     */
    public IntConst put(IntVar key, IntConst value) {

        //TODO: implement method.
        return null;
    }

    /**
     * Returns the value associated with the specified key, i.e. the vector
     * associated with the specified body. Returns 'null' if the key is not contained in this map.
     * @param key a variable != null.
     * @return the value associated with the specified key (or 'null' if the key is not contained in
     * this map).
     */
    public IntConst get(IntVar key) {

        //TODO: implement method.
        return null;
    }

    /**
     * Returns 'true' if this map contains a mapping for the specified key.
     * @param key a variable != null.
     * @return 'true' if this map contains a mapping for the specified key, or 'false' otherwise.
     */
    public boolean containsKey(IntVar key) {

        //TODO: implement method.
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     * @return the number of key-value mappings in this map.
     */
    public int size() {

        //TODO: implement method.
        return -1;
    }

    /**
     * Returns a new list with all the keys of this map. The list is ordered ascending according to
     * the key order relation. (This means that for any two variables x and y, x has a lower
     * index than y in the returned list, if x.getName().compareTo(y.getName()) < 0.
     * If x.getName().compareTo(y.getName()) > 0, y has a lower index than x in the returned list.
     * If there are variables with equal names, they are consecutive entries in the returned list.)
     * @return an ordered list of keys.
     */
    public IntVarDoublyLinkedList keyList() {

        //TODO: implement method.
        return null;
    }
}

// TODO: define further classes, if needed (either here or in a separate file).
