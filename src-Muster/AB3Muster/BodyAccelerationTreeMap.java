package AB3Muster;

import AB1Muster.Vector3;
import AB2Muster.Body;

/**
 * This data structure maps bodies to vectors ('Vector3' objects). It is implemented
 * as a binary search tree where bodies are sorted based on their masses.
 * The map allows multiple bodies with the same mass as long as they are not identical.
 * There is no limit on the number of key-value pairs stored in the map.
 */
//
// TODO: define further classes and methods for the implementation of the binary search tree, if
//  needed.
//
public class BodyAccelerationTreeMap {

    //TODO: declare variables.
    private TreeNode root;


    /**
     * Adds a new key-value association to this map. If the key already exists in this map,
     * the value is replaced and the old value is returned. Otherwise, 'null' is returned.
     * @param key a body != null.
     * @param value the vector to be associated with the key (can also be 'null').
     * @return the old value if the key already existed in this map, or 'null' otherwise.
     */
    public Vector3 put(Body key, Vector3 value) {

        // TODO: implement method.
        if (root == null) {
            root = new TreeNode(key, value, null, null);
            return null;
        }
        return root.add(key, value);
    }

    /**
     * Returns the value associated with the specified key, i.e. the vector
     * associated with the specified body. Returns 'null' if the key is not contained in this map.
     * @param key a body != null.
     * @return the value associated with the specified key (or 'null' if the key is not contained in
     * this map).
     */
    public Vector3 get(Body key) {

        // TODO: implement method.
        if (!containsKey(key)) {
            return null;
        }
        return root.get(key);
    }

    /**
     * Returns 'true' if this map contains a mapping for the specified key.
     * @param key a body != null.
     * @return 'true' if this map contains a mapping for the specified key, or 'false' otherwise.
     */
    public boolean containsKey(Body key) {

        // TODO: implement method.
        if (root == null) {
            return false;
        }
        return root.containsKey(key);
    }

    /**
     *  Returns a readable representation of this map, in which key-value pairs are listed in
     *  descending order according to the mass of the bodies.
     */
    public String toString() {

        // TODO: implement method.
        if (root == null) {
            return "";
        }
        return root.toString();
    }
}

// TODO: define further classes, if needed (either here or in a separate file).
class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private Body key;
    private Vector3 value;

    TreeNode(Body key, Vector3 value, TreeNode left, TreeNode right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    Vector3 add(Body key, Vector3 value) {
        if (key == this.key) {
            Vector3 oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        if (key.getMass() < this.key.getMass()) {
            if (left == null) {
                left = new TreeNode(key, value, null, null);
                return null;
            } else {
                return left.add(key, value);
            }
        } else {
            if (right == null) {
                right = new TreeNode(key, value, null, null);
                return null;
            } else {
                return right.add(key, value);
            }
        }
    }

    Vector3 get(Body key) {
        if (key == this.key) {
            return value;
        }

        if (key.getMass() < this.key.getMass()) {
            if (left == null) {
                return null;
            }
            return left.get(key);
        } else {
            if (right == null) {
                return null;
            }
            return right.get(key);
        }

    }

    public String toString() {
        String result;
        result = right == null ? "" : right.toString();
        result += "(" + this.key + "=" + this.value + ")\n";
        result += left == null ? "" : left.toString();
        return result;
    }

    boolean containsKey(Body key) {
        if (key == this.key) {
            return true;
        }
        if (key.getMass() < this.key.getMass()) {
            if (left == null) {
                return false;
            }
            return left.containsKey(key);
        } else {
            if (right == null) {
                return false;
            }
            return right.containsKey(key);
        }
    }
}