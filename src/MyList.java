package org.example;

import java.util.Iterator;

/**
 * Интерфейс пользовательского списка с поддержкой итерации.
 *
 * @param <T> тип элементов, должен реализовывать Comparable
 */
public interface MyList<T extends Comparable<T>> extends Iterable<T> {

    void add(T item);
    T set(int index, T item); // ← исправлено
    void add(int index, T item);
    void addFirst(T item);
    void addLast(T item);

    T get(int index);
    T getFirst();
    T getLast();

    T remove(int index);
    void removeFirst();
    void removeLast();

    void sort();

    int indexOf(Object object);
    int lastIndexOf(Object object);

    boolean exists(Object object);
    Object[] toArray();

    void clear();
    int size();
}