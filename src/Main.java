package org.example;

/**
 * Тестирование пользовательских структур данных.
 */
public class Main {
    public static void main(String[] args) {
        // ---------------- MyArrayList ----------------
        System.out.println("=== MyArrayList ===");
        MyArrayList<Integer> arrayList = new MyArrayList<>();
        arrayList.add(10);
        arrayList.add(0, 5);
        arrayList.addLast(20);
        arrayList.addFirst(1);
        arrayList.set(2, 15);
        System.out.println("ArrayList size: " + arrayList.size());
        System.out.println("ArrayList get(2): " + arrayList.get(2));

        // ✅ Удаляем элемент по значению безопасно
        arrayList.removeByValue(15);

        System.out.println("After remove 15: " + arrayList.size());

        System.out.print("ArrayList values: ");
        for (Integer val : arrayList) {
            System.out.print(val + " ");
        }
        System.out.println("\n");

        // ---------------- MyLinkedList ----------------
        System.out.println("=== MyLinkedList ===");
        MyList<String> linkedList = new MyLinkedList<>();
        linkedList.add("Java");
        linkedList.addFirst("Hello");
        linkedList.addLast("World");
        linkedList.add(1, "Data");
        System.out.println("LinkedList size: " + linkedList.size());
        System.out.println("First: " + linkedList.getFirst());
        System.out.println("Last: " + linkedList.getLast());
        linkedList.removeFirst();
        linkedList.removeLast();

        System.out.print("LinkedList values: ");
        for (String val : linkedList) {
            System.out.print(val + " ");
        }
        System.out.println("\n");

        // ---------------- MyStack ----------------
        System.out.println("=== MyStack ===");
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack peek: " + stack.peek());
        System.out.println("Stack pop: " + stack.pop());
        System.out.println("Stack size after pop: " + stack.size());

        // ---------------- MyQueue ----------------
        System.out.println("\n=== MyQueue ===");
        MyQueue<String> queue = new MyQueue<>();
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        System.out.println("Queue peek: " + queue.peek());
        System.out.println("Queue dequeue: " + queue.dequeue());
        System.out.println("Queue size after dequeue: " + queue.size());

        // ---------------- MyPriorityQueue ----------------
        System.out.println("\n=== MyPriorityQueue ===");
        MyPriorityQueue<Integer> pq = new MyPriorityQueue<>();
        pq.offer(30);
        pq.offer(10);
        pq.offer(20);
        pq.offer(5);

        System.out.println("Peek (min): " + pq.peek());
        System.out.println("Poll (remove min): " + pq.poll());
        System.out.println("New min after poll: " + pq.peek());
    }
}