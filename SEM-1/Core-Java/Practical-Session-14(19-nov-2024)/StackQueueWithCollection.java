import java.util.*;

public class StackQueueWithCollection {

    public static void main(String[] args) {

        List<Integer> stack = new ArrayList<>();

        stack.add(10);
        stack.add(20);
        stack.add(30);
        stack.add(40);

        System.out.println("Stack operations:");
        System.out.println("Top Element of Stack: " + stack.get(stack.size() - 1));

        int poppedElement = stack.remove(stack.size() - 1);
        System.out.println("Popped Element: " + poppedElement);

        System.out.println("Stack after Pop: " + stack);

        System.out.println("Is the Stack empty? " + stack.isEmpty());

        System.out.println("Size of the Stack: " + stack.size());

        System.out.println("\n");

        Queue<Integer> queue = new LinkedList<>();

        queue.add(100);
        queue.add(200);
        queue.add(300);
        queue.add(400);

        System.out.println("Queue operations:");
        System.out.println("Front Element of Queue: " + queue.peek());

        int dequeuedElement = queue.poll();
        System.out.println("Dequeued Element: " + dequeuedElement);

        System.out.println("Queue after Dequeue: " + queue);

        System.out.println("Is the Queue empty? " + queue.isEmpty());

        System.out.println("Size of the Queue: " + queue.size());
    }
}