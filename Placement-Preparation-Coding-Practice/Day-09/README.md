# Leetcode Questions Solved on Day 9 Placement Training:

1. **Flatten a Multilevel Doubly Linked List**

## 01. Flatten a Multilevel Doubly Linked List

[LeetCode Problem URL](https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/)

You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an additional child pointer. This child pointer may or may not point to a separate doubly linked list, also containing these special nodes. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure as shown in the example below.

Given the `head` of the first level of the list, flatten the list so that all the nodes appear in a single-level, doubly linked list. Let `curr` be a node with a child list. The nodes in the child list should appear after `curr` and before `curr.next` in the flattened list.

Return the `head` of the flattened list. The nodes in the list must have all of their child pointers set to null.

![img1](https://assets.leetcode.com/uploads/2021/11/09/flatten11.jpg)

```bash
Example 1:

Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]
Explanation: The multilevel linked list in the input is shown.
After flattening the multilevel linked list it becomes:
```

![img1.2](https://assets.leetcode.com/uploads/2021/11/09/flatten12.jpg)

![img2](https://assets.leetcode.com/uploads/2021/11/09/flatten2.1jpg)

```bash
Example 2:

Input: head = [1,2,null,3]
Output: [1,3,2]
Explanation: The multilevel linked list in the input is shown.
After flattening the multilevel linked list it becomes:
```

![img2.2](https://assets.leetcode.com/uploads/2021/11/24/list.jpg)

```bash
Example 3:

Input: head = []
Output: []
Explanation: There could be empty list in the input.
```

### Explanation

This problem requires us to flatten a multilevel doubly linked list into a single-level list while maintaining the order of nodes. The key is to traverse the list in a depth-first manner, ensuring that child nodes are inserted immediately after their parent nodes.

#### Approach Explanation

1. Why This Approach?

   The most natural way to flatten a multilevel linked list is to **traverse it in depth-first order**, inserting child nodes immediately after their parent. To do this efficiently and in-place, we use a **stack** to manage traversal without recursion.

2. Problem-Solving Pattern Used

   - **Iterative Depth-First Traversal**
   - **Linked List Pointer Manipulation**
   - **Stack-based Backtracking**

3. Efficiency of the Approach

   - Avoids recursive calls, which could lead to stack overflow for deep nesting.
   - Preserves the **O(1) auxiliary space** for node creation, using only a `stack` for traversal.
   - Guarantees a **single pass** through each node.

#### Step-by-Step Walkthrough

1. Let’s walk through **Example 1**:

2. Input:

   ```plaintext
   [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
   ```

3. This represents:

   - Main list: 1–2–3–4–5–6
   - Node 3 has child: 7–8–9–10
   - Node 8 has child: 11–12

4. **Initialization**:

   - Create a dummy node to act as a placeholder.
   - Push `head` (node 1) onto the stack.

5. **While stack is not empty**:

   - Pop the top node from the stack.
   - Connect it to the previous node (`prev`).
   - If `next` exists, push it on stack.
   - If `child` exists, push it on stack and set `child = None` to flatten.
   - Move `prev` to current.

6. Which forms this multi-level list:

   ```
   1 - 2 - 3 - 4 - 5 - 6
            |
            7 - 8 - 9 - 10
                  |
               11 - 12
   ```

7. Algorithm Initialization

   ```python
   stack = [head]  # stack = [1]
   prev = dummy    # dummy is a node with value 0
   ```

8. Step 1

   - **Stack:** `[1]`
   - **Pop:** `1`
   - **Link:** `prev.next = 1`, `1.prev = prev`
   - **Push next:** `2` → `stack = [2]`
   - **Child:** None
   - **Move `prev` to 1**

9. Step 2

   - **Stack:** `[2]`
   - **Pop:** `2`
   - **Link:** `1.next = 2`, `2.prev = 1`
   - **Push next:** `3` → `stack = [3]`
   - **Child:** None
   - **Move `prev` to 2**

10. Step 3

    - **Stack:** `[3]`
    - **Pop:** `3`
    - **Link:** `2.next = 3`, `3.prev = 2`
    - **Push next:** `4` → `stack = [4]`
    - **Child:** `7` → Push to stack → `stack = [4, 7]`
    - **Set `3.child = None`**
    - **Move `prev` to 3**

11. Step 4

    - **Stack:** `[4, 7]`
    - **Pop:** `7`
    - **Link:** `3.next = 7`, `7.prev = 3`
    - **Push next:** `8` → `stack = [4, 8]`
    - **Child:** None
    - **Move `prev` to 7**

12. Step 5

    - **Stack:** `[4, 8]`
    - **Pop:** `8`
    - **Link:** `7.next = 8`, `8.prev = 7`
    - **Push next:** `9` → `stack = [4, 9]`
    - **Child:** `11` → Push to stack → `stack = [4, 9, 11]`
    - **Set `8.child = None`**
    - **Move `prev` to 8**

13. Step 6

    - **Stack:** `[4, 9, 11]`
    - **Pop:** `11`
    - **Link:** `8.next = 11`, `11.prev = 8`
    - **Push next:** `12` → `stack = [4, 9, 12]`
    - **Child:** None
    - **Move `prev` to 11**

14. Step 7

    - **Stack:** `[4, 9, 12]`
    - **Pop:** `12`
    - **Link:** `11.next = 12`, `12.prev = 11`
    - **Push next:** None
    - **Child:** None
    - **Move `prev` to 12**

15. Step 8

    - **Stack:** `[4, 9]`
    - **Pop:** `9`
    - **Link:** `12.next = 9`, `9.prev = 12`
    - **Push next:** `10` → `stack = [4, 10]`
    - **Child:** None
    - **Move `prev` to 9**

16. Step 9

    - **Stack:** `[4, 10]`
    - **Pop:** `10`
    - **Link:** `9.next = 10`, `10.prev = 9`
    - **Push next:** None
    - **Child:** None
    - **Move `prev` to 10**

17. Step 10

    - **Stack:** `[4]`
    - **Pop:** `4`
    - **Link:** `10.next = 4`, `4.prev = 10`
    - **Push next:** `5` → `stack = [5]`
    - **Child:** None
    - **Move `prev` to 4**

18. Step 11

    - **Stack:** `[5]`
    - **Pop:** `5`
    - **Link:** `4.next = 5`, `5.prev = 4`
    - **Push next:** `6` → `stack = [6]`
    - **Child:** None
    - **Move `prev` to 5**

19. Step 12

    - **Stack:** `[6]`
    - **Pop:** `6`
    - **Link:** `5.next = 6`, `6.prev = 5`
    - **Push next:** None
    - **Child:** None
    - **Move `prev` to 6**

20. Final Step

    - **Stack:** `[]` (empty)
    - **Remove dummy node**
    - **Return `dummy.next` which is the head of the flattened list**

21. Final Flattened List

    ```
    1 – 2 – 3 – 7 – 8 – 11 – 12 – 9 – 10 – 4 – 5 – 6
    ```

22. Each node's `child` pointer is set to `null`.

#### Time and Space Complexity Analysis

| Metric               | Complexity | Explanation                                                                |
| -------------------- | ---------- | -------------------------------------------------------------------------- |
| **Time Complexity**  | $O(n)$     | Each node is visited exactly once                                          |
| **Space Complexity** | $O(n)$     | Stack stores nodes temporarily during traversal (worst case: all in stack) |

#### Summary

- This approach flattens a multilevel doubly linked list using **iterative DFS and a stack**.
- Ensures **O(n) time**, handles **any depth**, and **preserves order**.
- Nodes are modified in-place, so child pointers are reset to `None`.

---

---
