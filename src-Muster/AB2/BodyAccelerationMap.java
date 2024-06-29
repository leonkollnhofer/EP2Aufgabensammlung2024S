package AB2;
import AB1.Vector3;
import java.util.Arrays;

/**
 * A map that associates a body with an acceleration vector. The number of
 * key-value pairs is not limited.
 */
public class BodyAccelerationMap {

    //TODO: declare variables.
    private Body[] keys;
    private Vector3[] values;
    private int size;

    /**
     * Initializes this map with an initial capacity.
     * @param initialCapacity specifies the size of the internal array. initialCapacity > 0.
     */
    public BodyAccelerationMap(int initialCapacity) {

        //TODO: define constructor.
        keys = new Body[initialCapacity];
        values = new Vector3[initialCapacity];
    }

    /**
     * Adds a new key-value association to this map. If the key already exists in this map,
     * the value is replaced and the old value is returned. Otherwise, 'null' is returned.
     * @param key a body != null.
     * @param acceleration the acceleration vector to be associated with the key.
     * @return the old value if the key already existed in this map, or 'null' otherwise.
     */
    public Vector3 put(Body key, Vector3 acceleration) {

        //TODO: implement method.
        if (size == keys.length) {
            doubleCapacity();
        }

        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (keys[middle] == key) {
                Vector3 toReturn = values[middle];
                values[middle] = acceleration;
                return toReturn;
            }
            if (keys[middle].getMass() == key.getMass())  {
                //different object, but equal mass -> do linear search
                int idx = middle - 1;
                while (idx >= 0 && keys[idx] != null && keys[idx].getMass() == key.getMass()) {
                    if (keys[idx] == key) {
                        Vector3 toReturn = values[idx];
                        values[idx] = acceleration;
                        return toReturn;
                    }
                    idx--;
                }
                idx = middle + 1;
                while (idx < keys.length && keys[idx] != null &&  keys[idx].getMass() == key.getMass()) {
                    if (keys[idx] == key) {
                        Vector3 toReturn = values[idx];
                        values[idx] = acceleration;
                        return toReturn;
                    }
                    idx++;
                }
            }
            if (keys[middle].getMass() < key.getMass()) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        // index where to insert: right + 1
        //shift to right
        for (int i = size; i >= right + 2; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[right + 1] = key;
        values[right + 1] = acceleration;
        size++;
        return null;

    }

    /**
     * Returns the value associated with the specified key, i.e. the acceleration vector
     * associated with the specified body. Returns 'null' if the key is not contained in this map.
     * @param key a body != null.
     * @return the value associated with the specified key (or 'null' if the key is not contained in
     * this map).
     */
    public Vector3 get(Body key) {

        //TODO: implement method.
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (keys[middle] == key) {
                return values[middle];
            }
            if (keys[middle].getMass() == key.getMass())  {
                //different object, but equal mass -> do linear search
                int idx = middle - 1;
                while (idx >= 0 && keys[idx] != null && keys[idx].getMass() == key.getMass()) {
                    if (keys[idx] == key) return values[idx];
                    idx--;
                }
                idx = middle + 1;
                while (idx < keys.length && keys[idx] != null &&  keys[idx].getMass() == key.getMass()) {
                    if (keys[idx] == key) return values[idx];
                    idx++;
                }
            }

            if (keys[middle].getMass() < key.getMass()) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return null;
    }

    /**
     * Doubles the size of the array used by this map. This method
     * is called if 'put' is called with a new key and all positions
     * of the array are already occupied.
     */
    private void doubleCapacity() {
        Body[] newKeys = new Body[keys.length * 2];
        Vector3[] newValues = new Vector3[values.length * 2];
        for (int i = 0; i < keys.length; i++) {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        }
        keys = newKeys;
        values = newValues;
    }
}
