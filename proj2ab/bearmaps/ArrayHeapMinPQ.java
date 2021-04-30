package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private PriorityNode<T>[] heap;
    private HashMap<T, Integer> itemToIndex;
    private int size;

    public ArrayHeapMinPQ() {
        heap = (PriorityNode<T>[]) new PriorityNode[16];
        itemToIndex = new HashMap<>();
        heap[0] = null;
        size = 0;
    }

    /**
     * Add item in the PQ.
     * @param item
     * @param priority
     */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("You add a existed item in this PQ!");
        }
        size += 1;
        PriorityNode<T> newItem = new PriorityNode<>(item, priority);
        heap[size] = newItem;
        itemToIndex.put(item, size);
        swimUp(size, parent(size));

        if ((double) size / heap.length > 0.75) {
            upsize();
        }
    }

    /**
     * Check whether the PQ contains item or not.
     * @param item
     * @return
     */
    @Override
    public boolean contains(T item) {
        return itemToIndex.containsKey(item);
    }

    /**
     * Return first item.
     * @return
     */
    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("The PQ is empty!");
        }
        return heap[1].item;
    }

    /**
     * Return PQ size.
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Remove first item and return it.
     * @return
     */
    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("The PQ is empty!");
        }
        T res = heap[1].item;
        itemToIndex.remove(res); //Remove smallest item in the map.

        heap[1] = heap[size];
        heap[size] = null;
        size -= 1;
        if (size > 0) {
            itemToIndex.put(heap[1].item, 1); //Initialize first item before swap.
        }
        if (size > 1) {
            swimDown(1, leftChild(1), rightChild(1));
        }
        return res;
    }

    /**
     * Change priority of specific item in the min PQ.
     * @param item
     * @param priority
     */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("Not exist in the PQ!");
        }
        int index = itemToIndex.get(item);
        heap[index] = new PriorityNode<>(item, priority);
        swimUp(index, parent(index));
        if (size > 1) {
            swimDown(index, leftChild(index), rightChild(index));
        }
    }

    /**
     * Check whether added item is valid or not from leaf to foot.
     * If not, swap parent to satisfy the rules.
     * @param index
     * @param parentIndex
     */
    private void swimUp(int index, int parentIndex) {
        if (parentIndex == 0 || heap[index].priority >= heap[parentIndex].priority) {
            return;
        }
        if (heap[index].priority < heap[parentIndex].priority) {
            swap(index, parentIndex);
            swimUp(parentIndex, parent(parentIndex));
        }
    }

    /**
     * Check whether the PQ is valid or not form root to leaf.
     * If not, swap children to satisfy the rules.
     * @param index
     * @param leftChildIndex
     * @param rightChildIndex
     */
    private void swimDown(int index, int leftChildIndex, int rightChildIndex) {
        if (leftChildIndex > size && rightChildIndex > size) {
            return;
        }
        if (leftChildIndex <= size && rightChildIndex > size) {
            if (heap[index].priority > heap[leftChildIndex].priority) {
                swap(index, leftChildIndex);
            }
            return;
        }
        double leftChildPriority = heap[leftChildIndex].priority;
        double rightChildPriority = heap[rightChildIndex].priority;
        if (heap[index].priority <= leftChildPriority && heap[index].priority <= rightChildPriority) {
            return;
        }
        if (leftChildPriority < rightChildPriority) {
            swap(index, leftChildIndex);
            swimDown(leftChildIndex, leftChild(leftChildIndex), rightChild(leftChildIndex));
        } else {
            swap(index, rightChildIndex);
            swimDown(rightChildIndex, leftChild(rightChildIndex), rightChild(rightChildIndex));
        }
    }

    /**
     * Resize based array if loading > 75%.
     */
    private void upsize() {
        PriorityNode<T>[] newHeap = (PriorityNode<T>[]) new PriorityNode[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    /**
     * Swap item and its parent once item's priority is larger than parent's priority.
     * @param index
     * @param targetIndex
     */
    private void swap(int index, int targetIndex) {
        PriorityNode<T> item = heap[index];
        PriorityNode<T> target = heap[targetIndex];
        heap[targetIndex] = item;
        heap[index] = target;
        itemToIndex.put(item.item, targetIndex);
        itemToIndex.put(target.item, index);
    }

    /**
     * Find parent index in the heap.
     * @param index
     * @return
     */
    private int parent(int index) {
        return index / 2;
    }

    /**
     * Find left child in the heap.
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2;
    }

    /**
     * Find right child in the heap.
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 1;
    }

    private class PriorityNode<T> {
        T item;
        double priority;

        public PriorityNode(T i, double p) {
            item = i;
            priority = p;
        }
    }
}
