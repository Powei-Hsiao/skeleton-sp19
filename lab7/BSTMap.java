import java.security.Key;
import java.util.HashSet;
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

    /**
     *  Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        this.keySet(root, keys);
        return keys;
    }

    private void keySet(Node root, Set keys) {
        if (root != null) {
            keys.add(root.key);
            keySet(root.left, keys);
            keySet(root.right, keys);
        }
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     */
    @Override
    public V remove(K key) {
        V res = get(key);
        if (res == null) {
            return null;
        }
        root = remove(root, key);
        size -= 1;
        return res;
    }

    private Node remove(Node root, K key) {
        if (root == null) {
            return null;
        }
        int r = key.compareTo(root.key);
        if (r > 0) {
            root.right = remove(root.right, key);
        } else if (r < 0) {
            root.left = remove(root.left, key);
        } else if (r == 0) {
            if (root.left == null && root.right ==null) {
                return null;
            } else if (root.left != null && root.right != null) { // Remove min node from right hand side.
                Node temp = root;
                root = minNode(temp.right);
                root.right = removeMin(temp.right);
                root.left = temp.left;
            } else {
                if (root.left == null) {
                    return root.right;
                } else {
                    return root.left;
                }
            }
        }
        return root;
    }

    /**
     * Return min Node from parent node.
     * @param temp
     * @return
     */
    private Node minNode(Node temp) {
        if (temp.left == null) {
            return temp;
        } else {
            return minNode(temp.left);
        }
    }

    /**
     * Find min Node and remove it from parent node.
     * @param temp
     * @return
     */
    private Node removeMin(Node temp) {
        if (temp.left == null) {
            return temp.right;
        }
        temp.left = removeMin(temp.left);
        return temp;
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
