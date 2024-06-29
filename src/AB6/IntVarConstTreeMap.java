package AB6;

/**
 * This data structure maps variables ('IntVar' objects) to constants ('IntConst' objects).
 * It is implemented as a binary search tree where variables are sorted lexicographically according
 * their name using the 'compareTo' method of String. There is no limit on the number of
 * key-value mappings stored in the map.
 */
//
// TODO: define further classes and methods for the implementation of the binary search tree, if
//  needed.
//
public class IntVarConstTreeMap //implements IntVarConstMap //TODO: uncomment clause.
{

    //TODO: declare variables.

    public IntVarConstTreeMap() {

        //TODO: implement constructor.
    }

    /**
     * Constructs this map as a copy of the specified 'map'. This map has the same key-value mappings
     * as 'map'. However, if 'map' is changed later, it will not affect 'this' and vice versa.
     *
     * @param map the map from which key-value mappings are copied to this new map, map != null.
     */
    public IntVarConstTreeMap(IntVarConstTreeMap map) {

        //TODO: implement constructor.
    }
}

// TODO: define further classes, if needed (either here or in a separate file).

