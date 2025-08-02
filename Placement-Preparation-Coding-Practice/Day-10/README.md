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

---

---

## 02. Circular Queue Data Structure

A **Circular Queue** is a linear data structure that follows the **FIFO** (First In First Out) principle, but unlike a traditional queue, it wraps around upon reaching the end, forming a **circle**. This structure helps avoid wastage of space that occurs in linear queues after multiple enqueue and dequeue operations.

The circular queue is implemented using a fixed-size list in Python, along with two pointers:

- `front`: Points to the element at the front of the queue.
- `rear`: Points to the most recently inserted element at the rear.

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

Q.size_of_queue()
Output:
3

Q.peek()
Output:
8
```

### Explanation:

This implementation of a circular queue provides an efficient way to manage a fixed-size queue with wrap-around behavior, allowing for continuous use of the allocated space.

1. Functionalities

   1. **enqueue(item)**: Adds an item to the queue.
   2. **dequeue()**: Removes and returns the front item from the queue.
   3. **peek()**: Returns the front item without removing it.
   4. **display()**: Shows the current state of the queue.
   5. **size_of_queue()**: Returns the number of elements currently in the queue.
   6. **is_empty()**: Checks whether the queue is empty.

1. Why Circular Queue Approach?

   1. **Efficient Space Usage**: Reuses freed-up space after dequeues, unlike linear queues.
   2. **Fixed Memory**: Operates within a fixed-size array, making it space-predictable.
   3. **Real-world Use Cases**: Circular buffers are common in OS scheduling, streaming data buffers, etc.
   4. **Avoids Shifting**: No need to shift elements manually like in arrays or linear queues.

1. Problem Solving Pattern

   - **Two Pointer Technique**: Used to manage front and rear positions.
   - **Modular Arithmetic**: Used to wrap around the index using `(index + 1) % size`.
   - **Simulation**: Mimics real queue behavior using Python arrays.

#### Step-by-Step Walkthrough

1. Let's walk through the operations using the following input sequence:

   ```python
   Q.enqueue(5)
   Q.enqueue(6)
   Q.enqueue(7)
   Q.enqueue(8)
   Q.display()
   Q.dequeue()
   Q.display()
   Q.size_of_queue()
   Q.peek()
   ```

2. Assume a queue of size 5.

3. Initial State

   | Operation | front | rear | Queue State                     | Output / Notes          |
   | --------- | ----- | ---- | ------------------------------- | ----------------------- |
   | Init      | -1    | -1   | \[None, None, None, None, None] | Empty queue initialized |

4. Enqueue Operations

   | Operation  | front | rear | Queue State                  | Output      |
   | ---------- | ----- | ---- | ---------------------------- | ----------- |
   | enqueue(5) | 0     | 0    | \[5, None, None, None, None] | Enqueued: 5 |
   | enqueue(6) | 0     | 1    | \[5, 6, None, None, None]    | Enqueued: 6 |
   | enqueue(7) | 0     | 2    | \[5, 6, 7, None, None]       | Enqueued: 7 |
   | enqueue(8) | 0     | 3    | \[5, 6, 7, 8, None]          | Enqueued: 8 |

5. Display Queue

   | Operation | Queue State         | Output                              |
   | --------- | ------------------- | ----------------------------------- |
   | display() | \[5, 6, 7, 8, None] | Circular Queue: \[5, 6, 7, 8, None] |

6. Dequeue Operation

   | Operation | front | rear | Queue State            | Output      |
   | --------- | ----- | ---- | ---------------------- | ----------- |
   | dequeue() | 1     | 3    | \[None, 6, 7, 8, None] | Dequeued: 5 |

7. Display After Dequeue

   | Operation | Queue State            | Output                                 |
   | --------- | ---------------------- | -------------------------------------- |
   | display() | \[None, 6, 7, 8, None] | Circular Queue: \[None, 6, 7, 8, None] |

8. Size and Peek

   | Operation       | front | rear | Output |
   | --------------- | ----- | ---- | ------ |
   | size_of_queue() | 1     | 3    | 3      |
   | peek()          | 1     | 3    | 6      |

#### Time and Space Complexity Analysis

| Operation       | Time Complexity | Explanation                                           |
| --------------- | --------------- | ----------------------------------------------------- |
| enqueue()       | $O(1)$          | Direct index update using modulo arithmetic.          |
| dequeue()       | $O(1)$          | Removes element at `front` and adjusts pointer.       |
| peek()          | $O(1)$          | Constant time access to the front element.            |
| display()       | $O(n)$          | Traverses up to `n` elements depending on fill state. |
| size_of_queue() | $O(1)$          | Calculated using formula without iteration.           |

| Resource        | Space Complexity | Explanation                                                |
| --------------- | ---------------- | ---------------------------------------------------------- |
| Queue Storage   | $O(n)$           | Fixed array of size `n`.                                   |
| Auxiliary Space | $O(1)$           | Constant additional space for pointers and helper methods. |

---

---

## 03. Binary Tree All Traversals

A **Binary Tree** is a hierarchical data structure in which each node has at most two children, referred to as the left child and the right child. This structure is widely used in computer science for various applications, including searching, sorting, and representing hierarchical data.

```bash
Example 1:

Input:
        1
      /   \
     2      3
    /  \   / \
   4    5 6   7
Output:
In-order Traversal: 4 2 5 1 6 3 7
Pre-order Traversal: 1 2 4 5 3 6 7
Post-order Traversal: 4 5 2 6 7 3 1
```

```bash
Example 2:

Input:
        10
       /  \
      5    15
     / \     \
    3   7     20
Output:
In-order Traversal: 3 5 7 10 15 20
Pre-order Traversal: 10 5 3 7 15 20
Post-order Traversal: 3 7 5 20 15 10
```

### Explanation:

1. **Node Structure**: Each node contains a value and pointers to its left and right children.
2. **Traversal Methods**: The tree can be traversed in different orders:
   - **In-order**: Left, Root, Right
   - **Pre-order**: Root, Left, Right
   - **Post-order**: Left, Right, Root
3. **Height**: The height of a binary tree is the length of the longest path from the root to a leaf node.
4. **Balanced vs Unbalanced**: A balanced binary tree has heights of left and right subtrees differing by at most one, while an unbalanced tree can have significant height differences.

#### Approach Explanation

1. Why This Approach?

   We implemented both recursive and iterative versions of tree traversals:

   - Recursive traversal leverages the natural recursive structure of trees.
   - Iterative traversal demonstrates traversal without stack overflow and with controlled memory usage.

2. Problem-Solving Pattern

   - **Divide and Conquer**: Each traversal recursively (or iteratively) divides the tree into subtrees and processes them in order.
   - **Stack-based Iteration**: Iterative approaches mimic recursion using explicit stacks.
   - **Queue for Level-order**: Breadth-first traversal uses a queue to explore level by level.

3. Efficiency and Elegance

   - Recursive methods are elegant and intuitive.
   - Iterative methods offer better control over space (no deep recursion stack) and are suitable for larger trees.

#### Step-by-Step Walkthrough

1. We’ll use the following binary tree:

   ```
        1
      /   \
     2     3
    / \   / \
   4   5 6   7
   ```

2. Example Node Connections

   ```python
   tree = BinaryTree(1)
   tree.root.left = Node(2)
   tree.root.right = Node(3)
   tree.root.left.left = Node(4)
   tree.root.left.right = Node(5)
   tree.root.right.left = Node(6)
   tree.root.right.right = Node(7)
   ```

3. In-order Traversal (Left, Root, Right)

   1. **Recursive:**

      ```text
      Traverse left of 1 → left of 2 → left of 4 → print 4 → back to 2 → print 2 → right of 2 → print 5 → back to 1 → print 1 → right of 1 → print 6 → print 3 → print 7
      ```

   2. **Result:** `4 2 5 1 6 3 7`

   3. **Iterative Table:**

      | Step | Stack (Top → Bottom) | Current Node | Output        |
      | ---- | -------------------- | ------------ | ------------- |
      | 1    |                      | 1            |               |
      | 2    | 1                    | 2            |               |
      | 3    | 1, 2                 | 4            |               |
      | 4    | 1, 2, 4              | None         |               |
      | 5    | 1, 2                 | 4            | 4             |
      | 6    | 1, 2                 | None         | 4             |
      | 7    | 1                    | 2            | 4, 2          |
      | 8    | 1                    | 5            | 4, 2          |
      | 9    | 1, 5                 | None         | 4, 2          |
      | 10   | 1                    | 5            | 4, 2, 5       |
      | 11   |                      | 1            | 4, 2, 5, 1    |
      | 12   |                      | 3            | 4, 2, 5, 1    |
      | ...  |                      | ...          | ...           |
      | End  |                      |              | 4 2 5 1 6 3 7 |

4. Pre-order Traversal (Root, Left, Right)

   1. **Recursive Result:** `1 2 4 5 3 6 7`

   2. **Iterative Table:**

      | Step | Stack      | Visited Node | Output        |
      | ---- | ---------- | ------------ | ------------- |
      | 1    | \[1]       | 1            | 1             |
      | 2    | \[3, 2]    | 2            | 1 2           |
      | 3    | \[3, 5, 4] | 4            | 1 2 4         |
      | 4    | \[3, 5]    | 5            | 1 2 4 5       |
      | 5    | \[3]       | 3            | 1 2 4 5 3     |
      | 6    | \[7, 6]    | 6            | 1 2 4 5 3 6   |
      | 7    | \[7]       | 7            | 1 2 4 5 3 6 7 |

5. Post-order Traversal (Left, Right, Root)

   1. **Recursive Result:** `4 5 2 6 7 3 1`

   2. **Iterative (2 Stack) Table:**

   | Step | Stack1        | Stack2           | Output        |
   | ---- | ------------- | ---------------- | ------------- |
   | 1    | \[1]          | \[]              |               |
   | 2    | \[2, 3]       | \[1]             |               |
   | 3    | \[4, 5, 6, 7] | \[1, 3, 2]       |               |
   | ...  | \[]           | \[1, 3, 2, 5...] | 4 5 2 6 7 3 1 |

6. Level-order Traversal (Breadth-First)

   1. **Result:** `1 2 3 4 5 6 7`

   2. **Queue Table:**

      | Step | Queue         | Output        |
      | ---- | ------------- | ------------- |
      | 1    | \[1]          |               |
      | 2    | \[2, 3]       | 1             |
      | 3    | \[4, 5, 6, 7] | 1 2 3         |
      | 4    | \[]           | 1 2 3 4 5 6 7 |

#### Time and Space Complexity Analysis

| Traversal Type         | Time Complexity | Space Complexity | Explanation                               |
| ---------------------- | --------------- | ---------------- | ----------------------------------------- |
| In-order (Recursive)   | $O(n)$          | $O(h)$           | h = height of tree due to recursion stack |
| Pre-order (Iterative)  | $O(n)$          | $O(n)$           | Stack holds nodes temporarily             |
| Post-order (Iterative) | $O(n)$          | $O(n)$           | Uses two stacks to reverse traversal      |
| Level-order            | $O(n)$          | $O(n)$           | Queue stores all nodes at each level      |

---

---
