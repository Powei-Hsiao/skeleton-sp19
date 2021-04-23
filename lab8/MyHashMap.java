import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V>{
    private int initialSize;
    private double loadFactor;
    private int size;
    private HashSet<K> keySet;
    private Node<K, V>[] hashArray;


    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize,double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.keySet = new HashSet<>();
        this.hashArray = (Node<K, V>[]) new Node[initialSize];
    }

    /**
     * Linked nodes in buckets.
     * @param <K>
     * @param <V>
     */
    private static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node<K, V> get(K key) {
            if (this.key.equals(key)) {
                return this;
            }
            if (this.next == null) {
                return null;
            }
            return next.get(key);
        }
    }

    /**
     * Calculate index to decide which bucket stores mapping relation.
     * @param key
     * @param m
     * @return
     */
    private int keyToHashIndex(K key, int m) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /**
     * Find entry node of the key.
     * @param key
     * @return
     */
    private Node<K, V> find(K key) {
        int index = keyToHashIndex(key, initialSize);
        return hashArray[index];
    }

    /**
     * Increase hash array size once nodes number / size > load factor.
     */
    private void resize() {
        /*MyHashMap<K, V> temp = new MyHashMap<>(initialSize * 2);
        Set<K> keys = keySet();
        for (K k : keys) {
            temp.put(k, get(k));
        }
        this.hashArray = temp.hashArray;
        this.initialSize = temp.initialSize;*/

        Node<K, V>[] newHashArray = (Node<K, V>[]) new Node[initialSize * 2];
        for (int i = 0; i < initialSize; i += 1) {
            if(hashArray[i] != null) {
                Node<K, V> entry = hashArray[i];
                while (entry != null) {
                    int newIndex = keyToHashIndex(entry.key, newHashArray.length);
                    Node<K, V> newEntry = newHashArray[newIndex];
                    newHashArray[newIndex] = new Node<>(entry.key, entry.value, newEntry);
                    entry = entry.next;
                }
            }
        }
        this.hashArray = newHashArray;
        this.initialSize = initialSize * 2;
    }

    @Override
    public boolean containsKey(K key) {
        return this.get(key) != null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        Node<K, V> entry = find(key);
        if (entry == null) {
            return null;
        }
        Node<K, V> res = entry.get(key);
        if (res == null) {
            return null;
        }
        return res.value;
    }

    @Override
    public void put(K key, V value) {
        Node<K, V> entry = find(key);
        int index = keyToHashIndex(key, initialSize);
        if (!containsKey(key)) {
            hashArray[index] = new Node<>(key, value, entry);
            size += 1;
            keySet.add(key);
            if ((double)size / initialSize > loadFactor) {
                resize();
            }
        } else {
            Node<K, V> findOut = entry.get(key);
            findOut.value = value;
        }
    }

    @Override
    public void clear() {
        size = 0;
        keySet = new HashSet<>();
        initialSize = 16;
        hashArray = (Node<K, V>[]) new Node[16];

    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
