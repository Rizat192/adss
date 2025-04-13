package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Двусвязная реализация списка.
 * @param <T> тип элементов, должен реализовывать Comparable
 */
public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {

    /**
     * Внутренний класс для узлов списка
     */
    private class MyNode {
        T value;
        MyNode next;
        MyNode prev;

        MyNode(T value) {
            this.value = value;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // ---------- Методы интерфейса ----------

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void addFirst(T item) {
        MyNode node = new MyNode(item);
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode node = new MyNode(item);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    @Override
    public void add(int index, T item) {
        checkPositionIndex(index);
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            MyNode curr = getNode(index);
            MyNode newNode = new MyNode(item);
            MyNode prev = curr.prev;

            newNode.next = curr;
            newNode.prev = prev;
            prev.next = newNode;
            curr.prev = newNode;
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return getNode(index).value;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) throw new IllegalStateException("List is empty");
        return head.value;
    }

    @Override
    public T getLast() {
        if (isEmpty()) throw new IllegalStateException("List is empty");
        return tail.value;
    }

    @Override
    public T set(int index, T item) {
        checkElementIndex(index);
        MyNode node = getNode(index);
        T old = node.value;
        node.value = item;
        return old;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        MyNode node = getNode(index);
        return unlink(node);
    }

    @Override
    public void removeFirst() {
        if (isEmpty()) throw new IllegalStateException("List is empty");
        remove(0);
    }

    @Override
    public void removeLast() {
        if (isEmpty()) throw new IllegalStateException("List is empty");
        remove(size - 1);
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public int indexOf(Object object) {
        int idx = 0;
        MyNode current = head;
        while (current != null) {
            if (object == null ? current.value == null : object.equals(current.value)) return idx;
            current = current.next;
            idx++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int idx = size - 1;
        MyNode current = tail;
        while (current != null) {
            if (object == null ? current.value == null : object.equals(current.value)) return idx;
            current = current.prev;
            idx--;
        }
        return -1;
    }

    @Override
    public void clear() {
        MyNode curr = head;
        while (curr != null) {
            MyNode next = curr.next;
            curr.prev = null;
            curr.next = null;
            curr = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public void sort() {
        if (size < 2) return;

        boolean swapped;
        do {
            swapped = false;
            MyNode curr = head;
            while (curr != null && curr.next != null) {
                if (curr.value.compareTo(curr.next.value) > 0) {
                    T tmp = curr.value;
                    curr.value = curr.next.value;
                    curr.next.value = tmp;
                    swapped = true;
                }
                curr = curr.next;
            }
        } while (swapped);
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        MyNode curr = head;
        int i = 0;
        while (curr != null) {
            arr[i++] = curr.value;
            curr = curr.next;
        }
        return arr;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T val = current.value;
                current = current.next;
                return val;
            }
        };
    }

    // ---------- Вспомогательные методы ----------

    private boolean isEmpty() {
        return size == 0;
    }

    private MyNode getNode(int index) {
        MyNode curr;
        if (index < size / 2) {
            curr = head;
            for (int i = 0; i < index; i++) curr = curr.next;
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) curr = curr.prev;
        }
        return curr;
    }

    private T unlink(MyNode node) {
        T val = node.value;
        MyNode prev = node.prev;
        MyNode next = node.next;

        if (prev == null) head = next;
        else prev.next = next;

        if (next == null) tail = prev;
        else next.prev = prev;

        node.prev = null;
        node.next = null;
        size--;
        return val;
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