package org.example;

/**
 * Реализация стека (LIFO) с использованием MyArrayList.
 *
 * @param <T> тип элементов
 */
public class MyStack<T extends Comparable<T>> {

    private final MyArrayList<T> data;

    public MyStack() {
        data = new MyArrayList<>();
    }

    /**
     * Добавляет элемент в стек
     */
    public void push(T item) {
        data.addLast(item);
    }

    /**
     * Удаляет и возвращает верхний элемент стека
     */
    public T pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return data.remove(data.size() - 1);
    }

    /**
     * Возвращает верхний элемент стека без удаления
     */
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return data.get(data.size() - 1);
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}