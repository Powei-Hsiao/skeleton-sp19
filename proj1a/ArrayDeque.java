public class ArrayDeque<T> implements {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    //Create a empty deque array.
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }

    public ArrayDeque(ArrayDeque other) {
        size = other.size();
        items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, other.items.length);
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
    }

    //Increase size of items length to store additional objects.
    private void increSize() {
        T[] a = (T[]) new Object[items.length * 2];
        System.arraycopy(items, 0, a, 0, nextLast);
        System.arraycopy(items, nextLast, a, size + nextLast, size - nextLast);
        nextFirst = nextLast + size - 1;
        items = a;
    }

    //Decrease size of items length to release memory.
    private void decreSize() {
        T[] a = (T[]) new Object[items.length / 2];
        if (nextLast > nextFirst) {
            System.arraycopy(items, nextFirst + 1, a, 1, size);
            nextFirst = 0;
            nextLast = size +1;
        } else {
            System.arraycopy(items, nextFirst + 1, a, 1, items.length - nextFirst - 1);
            System.arraycopy(items, 0, a, items.length - nextFirst, nextLast);
            nextFirst = 0;
            nextLast = size +1;
        }
        items = a;
    }

    //Calculate load of items.
    private double getLoad() {
        double x = size;
        double y = items.length;
        return x / y;
    }

    /*Print all items in items for visualization*/
    public void printArrayDeque() {
        if (size == 0) {
            System.out.println("It's empty array deque.");
        } else {
            for (int i = 0; i < size; i += 1) {
                System.out.println(get(i));
            }
        }
    }

    //Add object in front of items.
    public void addFirst(T item) {
        if (size == items.length) {
            increSize();
        }
        items[nextFirst] = item;
        if (nextFirst > 0){
            nextFirst -= 1;
        } else {
            nextFirst = items.length - 1;
        }
        size = size + 1;
    }

    //Add object in the back of items.
    public void addLast(T item) {
        if (size == items.length) {
            increSize();
        }
        items[nextLast] = item;
        if (nextLast < items.length - 1) {
            nextLast += 1;
        } else {
            nextLast = 0;
        }
        size = size + 1;
    }

    //Get object from items by index.
    public T get(int index) {
        if (size == 0) {
            return null;
        }
        if (index < 0 || index >= size) {
            return null;
        } else {
            return items[(nextFirst + 1 + index) % items.length];
        }
    }

    //Return size of item.
    public int size() {
        return size;
    }

    //Remove object in front of items,
    public T removeFirst() {
        T item = items[(nextFirst + 1) % items.length];
        items[(nextFirst + 1) % items.length] = null;
        nextFirst = (nextFirst + 1) % items.length;
        size = size - 1;
        if (getLoad()< 0.25 && items.length >= 16) {
            decreSize();
        }
        return item;
    }

    //Remove object in back of items.
    public T removeLast() {
        T item;
        if (nextLast > 0) {
            item = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast = nextLast - 1;
        } else {
            item = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
        }
        size = size - 1;

        if (getLoad() < 0.25 && items.length >= 16) {
            decreSize();
        }
        return item;
    }

    //Check if the array is empty or not.
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
}
