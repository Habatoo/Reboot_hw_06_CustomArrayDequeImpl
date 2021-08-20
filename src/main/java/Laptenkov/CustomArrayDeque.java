package Laptenkov;

/**
 * Simple array deque. Can store any objects.
 */
public interface CustomArrayDeque<T> {

    int size();

    boolean isEmpty();

    /**
     * Add item to tail.
     *
     * @throws IllegalStateException if deque is full
     */
    boolean add(T item);

    /**
     * Add item to head.
     *
     * @throws IllegalStateException if deque is full
     */
    void addFirst(T item);

    /**
     * Add item to tail.
     *
     * @throws IllegalStateException if deque is full
     */
    void addLast(T item);

    /**
     * Try to add item to head.
     *
     * @return null if deque is full
     */
    boolean offerFirst(T item);

    /**
     * Try to add item to tail.
     *
     * @return null if deque is full
     */
    boolean offerLast(T item);

    /**
     * Get first element.
     *
     * @throws java.util.NoSuchElementException if empty
     */
    T getFirst();

    /**
     * Get last element.
     *
     * @throws java.util.NoSuchElementException if empty
     */
    T getLast();

    /**
     * Peek first element.
     *
     * @return null if empty
     */
    T peekFirst();

    /**
     * Peek last element.
     *
     * @return null if empty
     */
    T peekLast();

    /**
     * Poll first element in list.
     *
     * @return null if empty
     */
    T pollFirst();

    /**
     * Poll last element in list.
     *
     * @return null if empty
     */
    T pollLast();

    /**
     * Remove first element in list
     *
     * @throws java.util.NoSuchElementException if empty
     */
    T removeFirst();

    /**
     * Remove last element in list
     *
     * @throws java.util.NoSuchElementException if empty
     */
    T removeLast();

    /**
     * Get content in format '[ element1 element2 ... elementN ] or [] if empty.
     */
    String toString();

    /**
     * Copy list to array.
     */
    Object[] toArray();
}
