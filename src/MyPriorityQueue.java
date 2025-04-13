package org.example;

/**
 * Приоритетная очередь (Min-Heap), реализованная вручную.
 *
 * @param <T> тип элементов
 */
public class MyPriorityQueue<T extends Comparable<T>> {

    private final MyArrayList<T> heap;

    public MyPriorityQueue() {
        heap = new MyArrayList<>();
    }

    /**
     * Добавляет элемент, поддерживая кучу (по возрастанию)
     */
    public void offer(T item) {
        heap.addLast(item);
        upHeap(heap.size() - 1);
    }

    /**
     * Удаляет и возвращает минимальный элемент
     */
    public T poll() {
        if (isEmpty()) throw new IllegalStateException("PriorityQueue is empty");

        T min = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);
            downHeap(0);
        }
        return min;
    }

    /**
     * Возвращает минимальный элемент без удаления
     */
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("PriorityQueue is empty");
        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }

    public int size() {
        return heap.size();
    }

    // -------- Вспомогательные методы --------

    private void upHeap(int idx) {
        while (idx > 0) {
            int parent = (idx - 1) / 2;
            T current = heap.get(idx);
            T parentVal = heap.get(parent);
            if (current.compareTo(parentVal) < 0) {
                heap.set(idx, parentVal);
                heap.set(parent, current);
                idx = parent;
            } else break;
        }
    }

    private void downHeap(int idx) {
        int size = heap.size();
        while (true) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int smallest = idx;

            if (left < size && heap.get(left).compareTo(heap.get(smallest)) < 0) smallest = left;
            if (right < size && heap.get(right).compareTo(heap.get(smallest)) < 0) smallest = right;

            if (smallest != idx) {
                T temp = heap.get(idx);
                heap.set(idx, heap.get(smallest));
                heap.set(smallest, temp);
                idx = smallest;
            } else break;
        }
    }
}