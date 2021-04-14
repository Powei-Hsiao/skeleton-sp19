package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Return capacity of tje bounded queue.
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Return elements numbers already in the bounded queue.
     */
    @Override
    public int fillCount() {
        return this.fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        if (last == this.capacity() - 1) {
            last = 0;
        } else {
            last += 1;
        }
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override

    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T res = rb[first];
        rb[first] = null;
        if (first == this.capacity() - 1) {
            first = 0;
        } else {
            first += 1;
        }
        fillCount -= 1;
        return res;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T> {
        private int ini;
        public BufferIterator() {
            ini = 0;
        }

        @Override
        public boolean hasNext() {
            return ini < capacity();
        }

        @Override
        public T next() {
            T r = rb[ini];
            ini += 1;
            return r;
        }
    }

    @Override
    public boolean equals(Object o){
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (this.fillCount() != other.fillCount()) {
            return false;
        }
        if (this.capacity() != other.capacity()) {
            return false;
        }
        for (int i = 0; i < this.capacity(); i += 1) {
            if (rb[i] != other.rb[i]) {
                return false;
            }
        }
        return true;
    }
}
    // TODO: Remove all comments that say TODO when you're done.
