# Leetcode Questions Solved on Day 10 Placement Training:

1. **General Queue Data Structure**

## 01. General Queue Data Structure

Queue is FIFO(First In First Out) or LILO(Last In Last Out) data structure.

The problem requires implementing a general queue data structure that supports basic operations such as `enqueue`, `dequeue`, checking if the queue `is empty` and `display` queue elements.

The queue is implemented using a `deque` (double-ended queue) from the `collections` module in Python, which allows for efficient appending and popping of elements from both ends.

Append and Pop operations, append element to the right side of the queue and pop element from the left side of the queue.

```bash
Example 1:

Input:
Q.enqueue(5)
Q.enqueue(6)
Q.enqueue(7)
Q.enqueue(8)
Q.display()
Output:
Queue elements: [5, 6, 7, 8]
Q.dequeue()
Q.display()
Output:
Queue elements: [6, 7, 8]
Q.dequeue()
Q.display()
Output:
Queue elements: [7, 8]
Q.dequeue()
Q.display()
Output:
Queue elements: [8]
Q.dequeue()
Q.display()
Output:
Queue is empty
```

### Explanation

---

---
