package AB4Muster;

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
    private TreeNode root;
    private int size;

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
        if (map.root != null) {
            this.root = new TreeNode(map.root);
        }
        this.size = map.size;
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
        for (int i = 0; i < toCopy.size(); i++) {
            IntVar v = toCopy.pollFirst();
            if (map.containsKey(v)) {
                this.put(v, map.get(v));
            }
            toCopy.addLast(v);
        }
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
        if (root == null) {
            root = new TreeNode(key, value, null, null);
            size++;
            return null;
        }

        boolean[] added = new boolean[]{false};

        IntConst toReturn = root.put(key, value, added);
        if (added[0]) size++;
        return toReturn;
    }

    /**
     * Returns the value associated with the specified key, i.e. the constant associated with the
     * specified variable. Returns 'null' if the key is not contained in this map.
     * @param key a variable != null.
     * @return the value associated with the specified key (or 'null' if the key is not contained in
     * this map).
     */
    public IntConst get(IntVar key) {

        //TODO: implement method.
        if (root == null) {
            return null;
        }

        return root.get(key);
    }

    /**
     * Returns 'true' if this map contains a mapping for the specified key.
     * @param key a variable != null.
     * @return 'true' if this map contains a mapping for the specified key, or 'false' otherwise.
     */
    public boolean containsKey(IntVar key) {

        //TODO: implement method.
        if (root == null) {
            return false;
        }

        return root.containsKey(key);
    }

    /**
     * Returns the number of key-value mappings in this map.
     * @return the number of key-value mappings in this map.
     */
    public int size() {

        //TODO: implement method.
        return size;
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
        IntVarDoublyLinkedList result = new IntVarDoublyLinkedList();

        if (root == null) {
            return result;
        }

        root.addToList(result);
        return result;
    }
}

// TODO: define further classes, if needed (either here or in a separate file).

class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private IntVar key;
    private IntConst value;

    public TreeNode(IntVar key, IntConst value, TreeNode left, TreeNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.value = value;
    }

    /**
     * Copy-Constructor: initializes a copy of 'node' recursively.
     * @param node
     */
    public TreeNode(TreeNode node) {
        this.key = node.key;

        if (node.left != null) this.left = new TreeNode(node.left);
        if (node.right != null) this.right = new TreeNode(node.right);

        this.value = node.value;
    }

    //Precondition: added[0] == false
    IntConst put(IntVar p, IntConst value, boolean[] added) {

        if (this.key == p) {
            IntConst toReturn = this.value;
            this.value = value;
            return toReturn;
        }

        if (key.getName().compareTo(p.getName()) < 0) {
            if (left == null) {
                left = new TreeNode(p, value,null, null);
                added[0] = true;
                return null;
            } else {
                return left.put(p, value, added);
            }
        } else { // key.compareTo(p) >= 0
            if (right == null) {
                right = new TreeNode(p, value,null, null);
                added[0] = true;
                return null;
            } else {
                return right.put(p, value, added);
            }
        }
    }

    public IntConst get(IntVar p) {
        if (key == p) {
            return value;
        }

        if (key.getName().compareTo(p.getName()) < 0) {
            if (left == null) {
                return null;
            }
            return left.get(p);
        } else {
            if (right == null) {
                return null;
            }
            return right.get(p);
        }
    }

    public boolean containsKey(IntVar key) {
        if (this.key == key) {
            return true;
        }
        if (this.key.getName().compareTo(key.getName()) < 0) {
            return left != null && left.containsKey(key);
        } else {
            return right != null && right.containsKey(key);
        }
    }

    public void addToList(IntVarDoublyLinkedList list) {

        if (left != null) {
            left.addToList(list);
        }
        list.addFirst(this.key);
        if (right != null) {
            right.addToList(list);
        }
    }

    public String toString() {
        String result;
        result = right == null ? "" : right.toString();
        result += (result.isEmpty() ? "" : ", ") + this.key+"="+ value;
        result += left == null ? "" :  ", " + left.toString();
        return result;
    }

}
