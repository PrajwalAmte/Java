import java.util.*;

public class ListDemo {
    public static void main(String[] args) {
        // ArrayList: backed by dynamic array, O(1) access, O(n) insert/delete at index
        List<String> list = new ArrayList<>(Arrays.asList("Banana", "Apple", "Cherry", "Date"));
        list.add("Elderberry");
        list.add(1, "Avocado");      // insert at index
        list.remove("Banana");
        list.remove(0);              // remove by index

        System.out.println("ArrayList: " + list);
        System.out.println("Get index 1: " + list.get(1));
        System.out.println("Contains Cherry: " + list.contains("Cherry"));
        System.out.println("Size: " + list.size());

        Collections.sort(list);
        System.out.println("Sorted: " + list);
        System.out.println("BinarySearch Cherry: " + Collections.binarySearch(list, "Cherry"));

        Collections.reverse(list);
        System.out.println("Reversed: " + list);

        // subList (view, not copy)
        System.out.println("SubList [1,3): " + list.subList(1, 3));

        // LinkedList: doubly-linked, O(1) insert/delete at ends, O(n) access
        LinkedList<Integer> linked = new LinkedList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6));
        linked.addFirst(0);
        linked.addLast(10);
        System.out.println("\nLinkedList: " + linked);
        System.out.println("First: " + linked.peekFirst() + ", Last: " + linked.peekLast());
        System.out.println("Poll (remove first): " + linked.poll());

        // Using LinkedList as a Stack (LIFO)
        Deque<String> stack = new ArrayDeque<>();
        stack.push("A"); stack.push("B"); stack.push("C");
        System.out.println("\nStack: " + stack);
        System.out.println("Pop: " + stack.pop());
        System.out.println("Peek: " + stack.peek());

        // Using Deque as Queue (FIFO)
        Deque<String> queue = new ArrayDeque<>();
        queue.offer("First"); queue.offer("Second"); queue.offer("Third");
        System.out.println("\nQueue: " + queue);
        System.out.println("Poll: " + queue.poll());
        System.out.println("Peek: " + queue.peek());
    }
}
