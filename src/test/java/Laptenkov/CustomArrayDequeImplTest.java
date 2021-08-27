package Laptenkov;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования public методов класса {@link CustomArrayDeque<Object>}.
 *
 * @author habatoo
 */
class CustomArrayDequeImplTest {

    private CustomArrayDequeImpl<String> customEmptyArrayDeque;
    private CustomArrayDequeImpl<String> customNotEmptyArrayDeque;

    /**
     * Инициализация экземпляров тестируемого класса {@link CustomArrayDeque}.
     */
    @BeforeEach
    void setUp() {
        customEmptyArrayDeque = new CustomArrayDequeImpl<>();

        customNotEmptyArrayDeque = new CustomArrayDequeImpl<>();
        customNotEmptyArrayDeque.addLast("first");
        customNotEmptyArrayDeque.addLast("second");
        customNotEmptyArrayDeque.addLast("third");
        customNotEmptyArrayDeque.addLast("fourth");
        customNotEmptyArrayDeque.addLast("firth");
        customNotEmptyArrayDeque.addLast("last");
    }

    /**
     * Очистка экземпляров тестируемого класса {@link CustomArrayDeque}.
     */
    @AfterEach
    void tearDown() {
        customEmptyArrayDeque = null;
        customNotEmptyArrayDeque = null;
    }

    /**
     * Проверяет создание пустого экземпляра {@link CustomArrayDeque}.
     * Сценарий, при котором конструктор отрабатывает пустую коллекцию, проверяет размер коллекции
     * равный 0 и отображение коллекции.
     */
    @Test
    public void customArrayDequeImpl_Test() {
        customEmptyArrayDeque = new CustomArrayDequeImpl<>();
        Assertions.assertEquals(0, customEmptyArrayDeque.size());
        Assertions.assertEquals(
                "[ ]", customEmptyArrayDeque.toString());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#size_Test()}
     * проверяет метод {@link CustomArrayDeque#size()}.
     * Проверяет размер не пустого экземпляра {@link CustomArrayDeque}.
     * Сценарий, при котором пустой экземпляр возвращает величину
     * объекта равную 0.
     */
    @Test
    void size_Test() {
        Assertions.assertEquals(0, customEmptyArrayDeque.size());
        Assertions.assertEquals(6, customNotEmptyArrayDeque.size());
    }

    /**
     * Метод  {@link CustomArrayDequeImplTest#isEmpty_Test()}
     * проверяет метод {@link CustomArrayDeque#isEmpty()}.
     * Проверяет на пустоту экземпляр объекта {@link CustomArrayDeque}.
     * Сценарий, при котором пустой экземпляр возвращает <code>true</code>,
     * не пустой экземпляр возвращает <code>false</code>.
     */
    @Test
    void isEmpty_Test() {
        Assertions.assertEquals(true, customEmptyArrayDeque.isEmpty());
        Assertions.assertEquals(false, customNotEmptyArrayDeque.isEmpty());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#addSuccess_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#add(Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т и возвращает <code>true</code>.
     */
    @Test
    void addSuccess_Test() {
        Assertions.assertEquals(0, customEmptyArrayDeque.size());
        Assert.assertEquals(Arrays.asList(), Arrays.asList(customEmptyArrayDeque.toArray()));
        Assertions.assertEquals(true, customEmptyArrayDeque.add("Test"));
        Assertions.assertEquals(1, customEmptyArrayDeque.size());
        Assert.assertEquals(Arrays.asList("Test"), Arrays.asList(customEmptyArrayDeque.toArray()));
        Assertions.assertEquals(true, customEmptyArrayDeque.add("Last"));
        Assertions.assertEquals(2, customEmptyArrayDeque.size());
        Assert.assertEquals(Arrays.asList("Test", "Last"), Arrays.asList(customEmptyArrayDeque.toArray()));
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#addFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#add(Object)}.
     * Сценарий, при котором объект не успешно добавляется в заполненную очередь
     * выбрасывает IllegalStateException.
     */
    @Test
    void addFail_Test() {
        customNotEmptyArrayDeque.add("Test");
        Throwable throwable = Assertions.assertThrows(
                IllegalStateException.class, () -> customNotEmptyArrayDeque.add("Fail"));
        Assertions.assertEquals(
                "Очередь заполнена!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#addFirstSuccess_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#addFirst(Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т в head и возвращает <code>true</code>.
     */
    @Test
    void addFirstSuccess_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        customNotEmptyArrayDeque.addFirst("Test");
        Assert.assertEquals(
                Arrays.asList("Test", "first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#addFirstFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#addFirst(Object)}.
     * Сценарий, при котором объект не успешно отрабатывает добавление
     * не пустого объекта типа Т в head в заполненную очередь
     * и выбрасывает IllegalStateException.
     */
    @Test
    void addFirstFail_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        customNotEmptyArrayDeque.add("Test");
        Throwable throwable = Assertions.assertThrows(
                IllegalStateException.class, () -> customNotEmptyArrayDeque.add("Fail"));
        Assertions.assertEquals(
                "Очередь заполнена!", throwable.getMessage());

    }

    /**
     * Метод {@link CustomArrayDequeImplTest#addLastSuccess_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#addLast(Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т в head и возвращает <code>true</code>.
     */
    @Test
    void addLastSuccess_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        customNotEmptyArrayDeque.addLast("Test");
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last", "Test"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#addLastFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#addLast(Object)}.
     * Сценарий, при котором объект не успешно отрабатывает добавление
     * не пустого объекта типа Т в tail в заполненную очередь
     * и выбрасывает IllegalStateException.
     */
    @Test
    void addLastFail_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        customNotEmptyArrayDeque.addLast("Test");
        Throwable throwable = Assertions.assertThrows(
                IllegalStateException.class, () -> customNotEmptyArrayDeque.addLast("Fail"));
        Assertions.assertEquals(
                "Очередь заполнена!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#offerFirstSuccess_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#offerFirst(Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т в head и возвращает <code>true</code>.
     */
    @Test
    void offerFirstSuccess_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assert.assertEquals(true, customNotEmptyArrayDeque.offerFirst("Test"));
        Assert.assertEquals(
                Arrays.asList("Test", "first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#offerFirstFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#offerFirst(Object)}.
     * Сценарий, при котором объект не успешно отрабатывает добавление
     * не пустого объекта типа Т в head и возвращает <code>false</code>.
     */
    @Test
    void offerFirstFail_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assert.assertEquals(true, customNotEmptyArrayDeque.offerFirst("Test"));
        Assert.assertEquals(false, customNotEmptyArrayDeque.offerFirst("Fail"));
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#offerLasSuccess_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#offerLast(Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т в tail и возвращает <code>true</code>.
     */
    @Test
    void offerLasSuccess_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assert.assertEquals(true, customNotEmptyArrayDeque.offerLast("Test"));
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last", "Test"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#offerLastFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#offerLast(Object)}.
     * Сценарий, при котором объект не успешно отрабатывает добавление
     * не пустого объекта типа Т в tail и возвращает <code>false</code>.
     */
    @Test
    void offerLastFail_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assert.assertEquals(true, customNotEmptyArrayDeque.offerLast("Test"));
        Assert.assertEquals(false, customNotEmptyArrayDeque.offerLast("Fail"));
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#getFirstFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#getFirst()}.
     * Сценарий, при котором метод успешно отрабатывает
     * обращение к объекту из начала не пустой очереди (head)
     * и возвращает объект типа Т.
     */
    @Test
    void getFirstSuccess_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assert.assertEquals("first", customNotEmptyArrayDeque.getFirst());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#getFirstFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#getFirst()}.
     * Сценарий, при котором метод не успешно отрабатывает
     * обращение к объекту из начала пустой очереди (head)
     * и выбрасывает NoSuchElementException.
     */
    @Test
    void getFirstFail_Test() {
        Throwable throwable = Assertions.assertThrows(
                NoSuchElementException.class, () -> customEmptyArrayDeque.getFirst());
        Assertions.assertEquals(
                "Очередь пустая!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#getLastSuccess_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#getLast()}.
     * Сценарий, при котором метод успешно отрабатывает
     * обращение к объекту из конца не пустой очереди (tail)
     * и возвращает объект типа Т.
     */
    @Test
    void getLastSuccess_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assert.assertEquals("last", customNotEmptyArrayDeque.getLast());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#getLastFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#getLast()}.
     * Сценарий, при котором объект не успешно отрабатывает добавление
     * не пустого объекта типа Т из tail в пустой очереди
     * и выбрасывает NoSuchElementException.
     */
    @Test
    void getLastFail_Test() {
        Throwable throwable = Assertions.assertThrows(
                NoSuchElementException.class, () -> customEmptyArrayDeque.getLast());
        Assertions.assertEquals(
                "Очередь пустая!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#peekFirstSuccess_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#peekFirst()}.
     * Сценарий, при котором метод успешно отрабатывает
     * обращение к объекту из начала не пустой очереди (head)
     * и возвращает объект типа Т.
     */
    @Test
    void peekFirstSuccess_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assert.assertEquals("first", customNotEmptyArrayDeque.peekFirst());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#peekFirstFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#peekFirst()}.
     * Сценарий, при котором метод не успешно отрабатывает
     * обращение к объекту из начала пустой очереди (head)
     * и возвращает null.
     */
    @Test
    void peekFirstFail_Test() {
        Assert.assertEquals(Arrays.asList(), Arrays.asList(customEmptyArrayDeque.toArray()));
        Assert.assertEquals(null, customEmptyArrayDeque.peekFirst());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#peekFirstFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#peekLast()}.
     * Сценарий, при котором метод не успешно отрабатывает
     * обращение к объекту из начала пустой очереди (tail)
     * и возвращает объект типа Т.
     */
    @Test
    void peekLastSuccess_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assert.assertEquals("last", customNotEmptyArrayDeque.peekLast());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#peekFirstFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#peekLast()}.
     * Сценарий, при котором метод не успешно отрабатывает
     * обращение к объекту из начала пустой очереди (tail)
     * и возвращает объект типа Т.
     */
    @Test
    void peekLastFail_Test() {
        Assert.assertEquals(Arrays.asList(), Arrays.asList(customEmptyArrayDeque.toArray()));
        Assert.assertEquals(null, customEmptyArrayDeque.peekLast());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#pollFirstSuccess_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#pollFirst()}.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого объекта типа Т из head и возвращает удаленный объект.
     */
    @Test
    void pollFirstSuccess_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assertions.assertEquals("first", customNotEmptyArrayDeque.pollFirst());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#pollFirstFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#pollFirst()}.
     * Сценарий, при котором объект не успешно отрабатывает удаление
     * не пустого объекта типа Т из head из пустой очереди
     * и возвращает null.
     */
    @Test
    void pollFirstFail_Test() {
        Assert.assertEquals(Arrays.asList(), Arrays.asList(customEmptyArrayDeque.toArray()));
        Assertions.assertEquals(null, customEmptyArrayDeque.pollFirst());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#pollFirstSuccess_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#pollLast()}.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого объекта типа Т из tail и возвращает удаленный объект.
     */
    @Test
    void pollLastSuccess_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assertions.assertEquals("last", customNotEmptyArrayDeque.pollLast());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#pollLastFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#pollLast()}.
     * Сценарий, при котором объект не успешно отрабатывает удаление
     * не пустого объекта типа Т из tail из пустой очереди
     * и возвращает null.
     */
    @Test
    void pollLastFail_Test() {
        Assert.assertEquals(Arrays.asList(), Arrays.asList(customEmptyArrayDeque.toArray()));
        Assertions.assertEquals(null, customEmptyArrayDeque.pollLast());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#removeFirstSuccess_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#removeFirst()}.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого объекта типа Т из head и возвращает удаленный объект.
     */
    @Test
    void removeFirstSuccess_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assertions.assertEquals("first", customNotEmptyArrayDeque.removeFirst());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#removeLastFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#removeFirst()}.
     * Сценарий, при котором объект не успешно отрабатывает удаление
     * не пустого объекта типа Т из head из пустой очереди
     * и выбрасывает NoSuchElementException.
     */
    @Test
    void removeFirstFail_Test() {
        Assert.assertEquals(Arrays.asList(),
                Arrays.asList(customEmptyArrayDeque.toArray()));
        Throwable throwable = Assertions.assertThrows(
                NoSuchElementException.class, () -> customEmptyArrayDeque.removeFirst());
        Assertions.assertEquals("Очередь пустая!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#removeLastSuccess_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#removeLast()}.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого объекта типа Т из  tail и возвращает удаленный объект.
     */
    @Test
    void removeLastSuccess_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assertions.assertEquals("last", customNotEmptyArrayDeque.removeLast());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#removeLastFail_Test()}
     * Проверяет проверяет метод {@link CustomArrayDeque#removeLast()}.
     * Сценарий, при котором объект не успешно отрабатывает удаление
     * не пустого объекта типа Т из tail из пустой очереди
     * и выбрасывает NoSuchElementException.
     */
    @Test
    void removeLastFail_Test() {
        Assert.assertEquals(Arrays.asList(),
                Arrays.asList(customEmptyArrayDeque.toArray()));
        Throwable throwable = Assertions.assertThrows(
                NoSuchElementException.class, () -> customEmptyArrayDeque.removeLast());
        Assertions.assertEquals("Очередь пустая!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#toString_Test()}
     * Проверяет отображение экземпляр объекта {@link CustomArrayDeque}
     * методом {@link CustomArrayDeque#toString()}.
     * Сценарий, при котором не пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    public void toString_Test() {
        Assertions.assertEquals(
                "[ first second third fourth firth last ]", customNotEmptyArrayDeque.toString());
        Assertions.assertEquals("[ ]", customEmptyArrayDeque.toString());
    }

    /**
     * Метод {@link CustomArrayDequeImplTest#toArray_Test} проверяет
     * метод {@link CustomArrayDeque#toArray} на
     * создание копии очереди {@link CustomArrayDeque}.
     * Сценарий проверяет идентичность созданной копии и исходной очереди по элементно.
     */
    @Test
    void toArray_Test() {
        Assert.assertEquals(
                Arrays.asList("first", "second", "third", "fourth", "firth", "last"),
                Arrays.asList(customNotEmptyArrayDeque.toArray()));
        Assert.assertEquals(Arrays.asList(),
                Arrays.asList(customEmptyArrayDeque.toArray()));
    }

}