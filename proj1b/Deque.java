public interface Deque<AnyType> {

    void addFirst(AnyType item);

    void addLast(AnyType item);

    default boolean isEmpty() {
        return this.size() == 0;
    }

    int size();

    void printDeque();

    AnyType get(int index);

    AnyType getRecursive(int index);

    AnyType removeFirst();

    AnyType removeLast();
}
