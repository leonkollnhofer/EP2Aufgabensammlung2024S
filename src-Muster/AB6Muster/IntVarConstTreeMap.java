package AB6Muster;

import java.util.Objects;

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
public class IntVarConstTreeMap implements IntVarConstMap //TODO: uncomment clause.
{

    //TODO: declare variables.
    private TreeNode[] root;
    private int size;

    public IntVarConstTreeMap() {
        //TODO: implement constructor.
        root = new TreeNode[1];
    }

    /**
     * Constructs this map as a copy of the specified 'map'. This map has the same key-value mappings
     * as 'map'. However, if 'map' is changed later, it will not affect 'this' and vice versa.
     *
     * @param map the map from which key-value mappings are copied to this new map, map != null.
     */
    public IntVarConstTreeMap(IntVarConstTreeMap map) {

        //TODO: implement constructor.
        root = new TreeNode[1];
        if (map.root[0] != null) {
            this.root[0] = new TreeNode(map.root[0]);
        }
        this.size = map.size;
    }


    /**
     * Adds a new key-value association to this map. If the key already exists in this map,
     * the value is replaced and the old value is returned. Otherwise, 'null' is returned.
     *
     * @param key   a variable != null.
     * @param value the constant to be associated with the key (can also be 'null').
     * @return the old value if the key already existed in this map, or 'null' otherwise.
     */
    public IntConst put(IntVar key, IntConst value) {

        //TODO: implement method.
        if (root[0] == null) {
            root[0] = new TreeNode(key, value, null, null);
            size++;
            return null;
        }

        if (!containsKey(key)) {
            size++;
        }
        return root[0].put(key, value);
    }


    @Override
    public IntConst remove(IntVar key) {
        if (root[0] == null) {
            return null;
        }
        IntConst[] toReturn = new IntConst[1];
        int[] newSize = new int[] {size};
        root[0] = root[0].remove(key, newSize, toReturn);
        size = newSize[0];
        return toReturn[0];
    }

    /**
     * Returns the value associated with the specified key, i.e. the constant associated with the
     * specified variable. Returns 'null' if the key is not contained in this map.
     *
     * @param key a variable != null.
     * @return the value associated with the specified key (or 'null' if the key is not contained in
     * this map).
     */
    public IntConst get(IntVar key) {

        //TODO: implement method.
        if (root[0] == null) {
            return null;
        }

        return root[0].get(key);
    }

    /**
     * Returns 'true' if this map contains a mapping for the specified key.
     *
     * @param key a variable != null.
     * @return 'true' if this map contains a mapping for the specified key, or 'false' otherwise.
     */
    public boolean containsKey(IntVar key) {

        //TODO: implement method.
        if (root[0] == null) {
            return false;
        }

        return root[0].containsKey(key);
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map.
     */
    public int size() {

        //TODO: implement method.
        return size;
    }

    @Override
    public IntVarSet keySet() {
        return new TreeMapIntVarKeySet(this, root);
    }



    @Override
    public String toString() {
        if (root[0] == null) {
            return "[]";
        }
        return "[" + root[0].toString() +"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntVarConstTreeMap that)) return false;
        if (this.size() != that.size()) {
            return false;
        }

        for (IntVar k: this.keySet()) {
            if (!that.containsKey(k)) {
                return false;
            }
            if (!get(k).equals(that.get(k))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(root[0], size);
    }
}

// TODO: define further classes, if needed (either here or in a separate file).

class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private IntVar key;
    private IntConst value;

    TreeNode(IntVar key, IntConst value, TreeNode left, TreeNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.value = value;
    }

    /**
     * Copy-Constructor: initializes a copy of 'node' recursively.
     *
     * @param node
     */
    TreeNode(TreeNode node) {
        this.key = node.key;

        if (node.left != null) this.left = new TreeNode(node.left);
        if (node.right != null) this.right = new TreeNode(node.right);

        this.value = node.value;
    }

    IntConst put(IntVar p, IntConst value) {

        if (this.key == p) {
            IntConst toReturn = this.value;
            this.value = value;
            return toReturn;
        }

        if (key.getName().compareTo(p.getName()) > 0) {
            if (left == null) {
                left = new TreeNode(p, value, null, null);
                return null;
            } else {
                return left.put(p, value);
            }
        } else { // key.compareTo(p) <= 0
            if (right == null) {
                right = new TreeNode(p, value, null, null);
                return null;
            } else {
                return right.put(p, value);
            }
        }
    }

    IntConst get(IntVar p) {
        if (key == p) {
            return value;
        }

        if (key.getName().compareTo(p.getName()) > 0) {
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

    boolean containsKey(IntVar key) {
        if (this.key == key) {
            return true;
        }
        if (this.key.getName().compareTo(key.getName()) > 0) {
            return left != null && left.containsKey(key);
        } else {
            return right != null && right.containsKey(key);
        }
    }

    public IntVar step(TreeIterator iter, boolean next) {
        TreeNode n = next ? right : this;
        while (n != null) {
            new TreeIterator(n, iter);
            n = n.left;
        }
        return this.getKey();
    }

    public String toString() {
        String result;
        result = left == null ? "" : left.toString();
        result += (result.isEmpty() ? "" : ", ") + this.key + "=" + value;
        result += right == null ? "" : ", " + right.toString();
        return result;
    }

    public TreeNode remove(IntVar e, int[] cnt, IntConst[] toReturn) {
        int cmp = this.key.getName().compareTo(e.getName());
        if (cmp > 0) {
            if (left != null) {
                left = left.remove(e, cnt, toReturn);
            }
        } else {
            if (this.key == e) {
                toReturn[0] = this.value;
                cnt[0]--;
                if (right == null) {
                    return left;
                }
                TreeNode p = right;
                while (p.left != null) {
                    p = p.left;
                }
                p.left = left;
                return right;
            } else {
                if (right != null) {
                    right = right.remove(e, cnt, toReturn);
                }
            }
        }
        return this;
    }

    public IntVar getKey() {
        return key;
    }
}

class TreeIterator implements IntVarIterator {

    private TreeNode node;
    private TreeIterator parent;

    public TreeIterator() {
    }

    public TreeIterator(TreeNode n, TreeIterator p) {
        this.node = p.node;
        p.node = n;
        this.parent = p.parent;
        p.parent = this;
    }

    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public IntVar next() {

        if (node == null) return null;
        TreeNode toReturn = node;
        node = parent.node;
        parent = parent.parent;
        return toReturn.step(this, true);
    }
}

class TreeMapIntVarKeySet implements IntVarSet {

    private IntVarConstTreeMap treeMap;
    private TreeNode[] root;

    public TreeMapIntVarKeySet(IntVarConstTreeMap treeMap, TreeNode[] root) {

        this.treeMap = treeMap;
        this.root = root;
    }

    @Override
    public void add(IntVar v) {
        if (!this.contains(v)) treeMap.put(v, null);
    }

    @Override
    public boolean contains(IntVar v) {
        return treeMap.containsKey(v);
    }

    @Override
    public int size() {
        return treeMap.size();
    }

    @Override
    public IntVarIterator iterator() {
        TreeIterator iterator = new TreeIterator();
        if (root[0] != null) {
            root[0].step(iterator, false);
        }
        return iterator;
    }
}
