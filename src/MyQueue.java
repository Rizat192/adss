package org.example;

/**
 * Реализация очереди (FIFO) на базе MyLinkedList.
 *
 * @param <T> тип элементов
 */
public class MyQueue<T extends Comparable<T>> {

    private final MyLinkedList<T> data;

    public MyQueue() {
        data = new MyLinkedList<>();
    }

    /**
     * Добавляет элемент в конец очереди
     */
    public void enqueue(T item) {
        data.addLast(item);
    }

    /**
     * Удаляет и возвращает первый элемент очереди
     */
    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        return data.remove(0);
    }

    /**
     * Возвращает первый элемент без удаления
     */
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        return data.get(0);
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}