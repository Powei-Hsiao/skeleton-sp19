import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class MyTrieSet implements TrieSet61B{
    private Node root;

    public MyTrieSet() {
        root = new Node();
    }

    /**
     * Clears all items out of Trie.
     */
    @Override
    public void clear(){
        root = new Node();
    }

    /**
     * Returns true if the Trie contains KEY, false otherwise.
     * @param key
     * @return
     */
    @Override
    public boolean contains(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i += 1) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            } else {
                curr = curr.map.get(c);
            }
        }
        return curr.isKey;
    }

    /**
     * Add string in the trie.
     * Reference from https://sp19.datastructur.es/materials/lab/lab9/lab9
     * @param key
     */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false)); // cannot understand why not jut use new Node() directly?
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    /**
     * Track all valid strings under prefix.
     * @param prefix
     * @return
     */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i += 1) {
            char c = prefix.charAt(i);
            curr = curr.map.get(c);
        }
        List<String> collect = new ArrayList<>();
        if (curr.isKey) {
            collect.add(prefix);
        }
        for(char c : curr.map.keySet()) {
            colHelp(prefix + c, collect, curr.map.get(c));
        }
        return collect;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Helpful function to iterate all nodes under prefix string.
     * @param prefixPlusOne
     * @param collect
     * @param nextNode
     */
    private void colHelp(String prefixPlusOne, List<String> collect, Node nextNode) {
        if (nextNode.isKey) {
            collect.add(prefixPlusOne);
        }
        for (char c : nextNode.map.keySet()) {
            colHelp(prefixPlusOne + c, collect, nextNode.map.get(c));
        }
    }

    private static class Node {
        private Boolean isKey;
        private HashMap<Character, Node> map;

        private Node() {
            isKey = false;
            map = new HashMap<Character, Node>();
        }

        private Node(char c, boolean b) {
            isKey = b;
            map = new HashMap<Character, Node>();
        }
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
    }
}
