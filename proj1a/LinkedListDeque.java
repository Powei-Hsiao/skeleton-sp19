public class LinkedListDeque<AnyType> {
    public class ItemNode {
        public ItemNode prev;
        public AnyType item;
        public ItemNode next;
        public ItemNode(AnyType x, ItemNode p, ItemNode n) {
            prev = p;
            item = x;
            next = n;
        }
    }
    private ItemNode sentinel;
    private int size;
    public LinkedListDeque() {
        size = 0;
        sentinel = new ItemNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    public LinkedListDeque(AnyType item) {
        size = 1;
        sentinel = new ItemNode(null, null, null);
        sentinel.next = new ItemNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
    }
    public  LinkedListDeque(LinkedListDeque other) {
        size = other.size();
        sentinel = new ItemNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        for (int i = 0; i < other.size(); i += 1) {
            addLast((AnyType) other.get(i));
        }
    }
    public void addFirst(AnyType item) {
        size = size + 1;
        sentinel.next = new ItemNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }
    public  void addLast(AnyType item) {
        size = size +1;
        sentinel.prev = new ItemNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    /**
     * Check whether the linked list is empty or not.
     * @return true or false
     */
    public boolean isEmpty() {
        if (sentinel == sentinel.next) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return size of linked list
     */
    public int size() {
        return size;
    }

    /**
     * Print out every elements in linked list.
     */
    public void printDeque() {
        int n = size;
        if (!this.isEmpty()) {
            while (n > 0) {
                System.out.println(sentinel.next.item);
                sentinel = sentinel.next;
                n = n - 1;
            }
        } else {
            System.out.println("This is an empty LinkedListDeque.");
        }
    }

    /**
     * Get the item from the specific index in linked list.
     * @param index index of linked list
     * @return index item in linked list
     */
    public AnyType get(int index) {
        if (this.isEmpty()) {
            return null;
        }
        if (index < this.size && index >= 0){
            ItemNode p = sentinel.next;
            while (index != 0) {
                p = p.next;
                index = index - 1;
            }
            return p.item;
        } else {
            return null;
        }
    }
    public AnyType helper(int index, ItemNode p) {
        if (index == 0) {
            return p.item;
        } else {
            return helper(index - 1, p.next);
        }
    }
    public AnyType getRecursive(int index) {
        if (this.isEmpty()) {
            return null;
        }
        if (index < this.size && index >= 0){
            ItemNode p = sentinel.next;
            return helper(index, p);
        } else {
            return null;
        }
    }
    public AnyType removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        size = size - 1;
        ItemNode temp = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        temp.next = null;
        temp.prev = null;
        return temp.item;
    }
    public AnyType removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        size = size - 1;
        ItemNode temp = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        temp.next = null;
        temp.prev = null;
        return temp.item;
    }
}
