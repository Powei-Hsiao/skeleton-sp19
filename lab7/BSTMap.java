import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    /**
     * Nested class Node.
     */
    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K k, V v) {
            this.key = k;
            this.value = v;
            this.left = null;
            this.right = null;
        }
    }

    private int size;
    private Node root;

    /**
     * Initialize empty BSTMap.
     */
    public BSTMap() {
        size = 0;
        root = null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        this.size = 0;
        this.root = null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        return get(key, root);
    }

    private V get(K key, Node root) {
        if (root == null) {
            return null;
        }
        int r = key.compareTo(root.key);
        if (r > 0) {
            return get(key, root.right);
        } else if (r < 0) {
            return get(key, root.left);
        } else {
            return root.value;
        }
    }

    /**
     *  Returns true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(K key){
        return get(key) != null;
    }

    /**
     * Associates the specified value with the specified key in this map.
     */
    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private Node put(K key, V value, Node root) {
        if (root == null) {
            size += 1;
            return new Node(key, value);
        }
        int r = key.compareTo(root.key);
        if (r > 0) {
            root.right = put(key, value, root.right);
        } else if (r < 0) {
            root.left = put(key, value, root.left);
        } else {
            root.value = value;
        }
        return root;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
