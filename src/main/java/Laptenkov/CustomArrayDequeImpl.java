package Laptenkov;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;

/**
 * Класс CustomArrayDequeImpl<T> двунаправленная очередь на основе цикличного массива.
 * Класс CustomArrayDequeImpl реализует интерфейс CustomArrayDeque<T>
 * Класс CustomArrayDequeImpl может хранить объекты любого типа
 * Класс CustomArrayDequeImpl имеет фиксированный размер.
 * Динамическое расширение не предусмотрено
 * @param <T>
 */
public class CustomArrayDequeImpl<T> implements CustomArrayDeque<T> {

    private static final int DEFAULT_CAPACITY = 8;
    private Object[] data = new Object[DEFAULT_CAPACITY];
    int size;
    int head;
    int tail;

    /**
     * Конструктор пустого объекта {@link CustomArrayDeque}
     * двунаправленной очереди на основе цикличного массива.
     */
    CustomArrayDequeImpl() {
    }

    /**
     * Конструктор не пустого объекта {@link CustomArrayDeque}
     * двунаправленной очереди на основе цикличного массива
     * размера capacity.
     */
    CustomArrayDequeImpl(int capacity) {
        data = new Object[capacity + 1];
        size = 0;
        head = 0;
        tail = 0;
    }

    /**
     * Метод {@link CustomArrayDeque#size()} возвращает размер связанного списка
     * объекта {@link CustomArrayDeque}.
     *
     * @return целое число типа {@link int}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод {@link CustomArrayDeque#isEmpty()} возвращает булево значение
     * при проверке связанного списка объекта {@link CustomArrayDeque}.
     *
     * @return <code>true</code> если размер не нулевой,
     * <code>false</code> если размер нулевой.
     */
    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * Метод {@link CustomArrayDeque#add(Object)}
     * добавляет объект в конец очереди (tail).
     *
     * @param item объект типа Т для добавления.
     * @throws IllegalStateException если очередь заполнена.
     * @return объект типа {@link boolean}
     * <code>true</code> если добавление прошло удачно,
     * <code>false</code> если добавить не удалось.
     */
    @Override
    public boolean add(T item) {
        if (isFull()) {
            throw new IllegalStateException("Очередь заполнена!");
        }

        addLast(item);
        return true;
    }

    /**
     * Метод {@link CustomArrayDeque#add(Object)}
     * добавляет объект в начало очереди (head).
     *
     * @param item объект типа Т для добавления.
     * @throws IllegalStateException если очередь заполнена.
     */
    @Override
    public void addFirst(T item) {
        if (isFull()) {
            throw new IllegalStateException("Очередь заполнена!");
        }

        if (head - 1 < 0) {
            head = data.length - 1;
        } else {
            head--;
        }
        data[head] = item;
        size++;

    }

    /**
     * Метод {@link CustomArrayDeque#add(Object)}
     * добавляет объект в конец очереди (tail).
     *
     * @param item объект типа Т для добавления.
     * @throws IllegalStateException если очередь заполнена.
     */
    @Override
    public void addLast(T item) {
        if (isFull()) {
            throw new IllegalStateException("Очередь заполнена!");
        }

        data[tail] = item;
        size++;
        tail++;
        if (tail >= data.length) {
            tail = 0;
        }
    }

    /**
     * Метод {@link CustomArrayDeque#offerFirst(Object)}
     * добавляет объект в начало очереди (head).
     *
     * @param item объект типа Т для добавления.
     * @return false если очередь пустая.
     */
    @Override
    public boolean offerFirst(T item) {
        if (isFull()) {
            return false;
        }
        addFirst(item);
        return true;
    }

    /**
     * Метод {@link CustomArrayDeque#offerLast(Object)}
     * добавляет объект в конец очереди (tail).
     *
     * @param item объект типа Т для добавления.
     * @return false если очередь заполнена.
     */
    @Override
    public boolean offerLast(T item) {
        if (isFull()) {
            return false;
        }
        addLast(item);
        return true;
    }

    /**
     * Метод {@link CustomArrayDeque#getFirst()}
     * не безопасно возвращает объект из начала очереди (head).
     *
     * @throws NoSuchElementException если очередь пустая.
     */
    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пустая!");
        }
        return (T) data[head];
    }

    /**
     * Метод {@link CustomArrayDeque#getLast()}
     * не безопасно возвращает объект из конца очереди (tail).
     *
     * @throws NoSuchElementException если очередь пустая.
     */
    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пустая!");
        }
        int tmp = tail -1;
        if (tmp < 0) {
            tmp = data.length - 1;
        }
        return (T) data[tmp];
    }

    /**
     * Метод {@link CustomArrayDeque#peekFirst()}
     * безопасно возвращает объект из начала очереди (head).
     *
     * @return возвращает null если очередь пустая.
     */
    @Override
    public T peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return getFirst();
    }

    /**
     * Метод {@link CustomArrayDeque#peekLast()}
     * безопасно возвращает объект из конца очереди (tail).
     *
     * @return возвращает null если очередь пустая.
     */
    @Override
    public T peekLast() {
        if (isEmpty()) {
            return null;
        }
        return getLast();
    }

    /**
     * Метод {@link CustomArrayDeque#pollLast()}
     * безопасно удаляет объект из начала очереди (first).
     *
     * @return объект типа Т если очередь не пустая или null если очередь  пустая.
     */
    @Override
    public T pollFirst() {
        if (isEmpty()) {
            return null;
        }
        T tmp = (T) data[head];
        data[head] = null;
        head++;
        size--;
        if (head == data.length) {
            head = 0;
        }
        return tmp;
    }

    /**
     * Метод {@link CustomArrayDeque#pollLast()}
     * безопасно удаляет объект из конца очереди (tail).
     *
     * @return объект типа Т если очередь не пустая или null если очередь  пустая.
     */
    @Override
    public T pollLast() {
        if (isEmpty()) {
            return null;
        }
        T tmp = (T) data[tail - 1];
        data[tail - 1] = null;
        tail--;
        size--;
        if (tail == data.length) {
            tail = 0;
        }
        return tmp;
    }

    /**
     * Метод {@link CustomArrayDeque#removeLast()}
     * не безопасно удаляет объект из начала очереди (first).
     *
     * @return объект типа Т если очередь не пустая.
     * @throws NoSuchElementException если очередь пустая.
     */
    @Override
    public T removeFirst() {
        T tmp = pollFirst();
        if (tmp == null)
            throw new NoSuchElementException("Очередь пустая!");
        return tmp;
    }

    /**
     * Метод {@link CustomArrayDeque#removeLast()}
     * не безопасно удаляет объект из конца очереди (tail).
     *
     * @return объект типа Т если очередь не пустая.
     * @throws NoSuchElementException если очередь пустая.
     */
    @Override
    public T removeLast() {
        T tmp = pollLast();
        if (tmp == null)
            throw new NoSuchElementException("Очередь пустая!");
        return tmp;
    }

    /**
     * Метод {@link CustomArrayDeque#toString()}
     * возвращает строковое представление очереди {@link CustomArrayDeque}
     *
     * @return объект типа String в формате '[ element1 element2 ... elementN ]
     * или [ ] если очередь пустая.
     */
    @Override
    public String toString() {
        StringBuilder cb = new StringBuilder();

        cb.append("[");
        int tmp = head;
        while (tmp != tail) {
            cb.append(" " + data[tmp]);
            tmp++;
            if (tmp == data.length) {
                tmp = 0;
            }
        }
        cb.append(" ]");

        return cb.toString();
    }

    /**
     * Метод {@link CustomArrayDeque<T>#toArray()}
     * возвращает копию текущего объекта
     * {@link CustomArrayDeque<T>}.
     *
     * @return объект типа {@link CustomArrayDeque<T>}.
     */
    @Override
    public Object[] toArray() {
        Object[] newData = new Object[size];

        int tmp = head;
        for (int i = 0; i < size; i++) {
            newData[i] = data[tmp++];
            if (tmp == data.length) {
                tmp = 0;
            }
        }
        return newData;
    }

    /**
     * Метод {@link CustomArrayDeque<T>#isFull()}
     * возвращает булево значение в зависимости от заполненности очереди
     * {@link CustomArrayDeque<T>}.
     *
     * @return объект типа {@link boolean}
     * <code>true</code> если очередь заполнена,
     * <code>false</code> если очередь не заполнена.
     */
    private boolean isFull() {
        if (head == 0 && tail == data.length - 1) {
            return true;
        }

        if (head == tail + 1) {
            return true;
        }

        return false;
    }

}