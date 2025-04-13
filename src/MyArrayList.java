package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация пользовательского динамического массива.
 *
 * @param <T> тип элементов, должен быть Comparable
 */
public class MyArrayList<T extends Comparable<T>> implements MyList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        elements = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T item) {
        ensureCapacity(size + 1);
        elements[size++] = item;
    }

    @Override
    public void add(int index, T item) {
        checkPositionIndex(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = item;
        size++;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(item);
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return elements[index];
    }

    @Override
    public T getFirst() {
        if (isEmpty()) throw new IllegalStateException("List is empty");
        return elements[0];
    }

    @Override
    public T getLast() {
        if (isEmpty()) throw new IllegalStateException("List is empty");
        return elements[size - 1];
    }

    @Override
    public T set(int index, T item) {
        checkElementIndex(index);
        T old = elements[index];
        elements[index] = item;
        return old;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        T removed = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return removed;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(size - 1);
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object == null ? elements[i] == null : object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (object == null ? elements[i] == null : object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (elements[j].compareTo(elements[j + 1]) > 0) {
                    T tmp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = tmp;
                }
            }
        }
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = elements[i];
        }
        return arr;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return elements[cursor++];
            }
        };
    }

    // ✅ Новый безопасный метод удаления по значению
    public boolean removeByValue(T object) {
        int index = indexOf(object);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    // ---------- Вспомогательные методы ----------
    private boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(minCapacity, elements.length * 2);
            @SuppressWarnings("unchecked")
            T[] newArr = (T[]) new Comparable[newCapacity];
            for (int i = 0; i < size; i++) {
                newArr[i] = elements[i];
            }
            elements = newArr;
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Insert position invalid: " + index);
    }
}