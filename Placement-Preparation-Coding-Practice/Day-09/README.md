# Leetcode Questions Solved on Day 9 Placement Training:

1. **Flatten a Multilevel Doubly Linked List**
2. **Linked List Cycle II**
3. **Score of Parentheses**
4. **Stack Infix to Postfix**

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

## 02. Linked List Cycle II

[LeetCode Problem URL](https://leetcode.com/problems/linked-list-cycle-ii/)

Given the `head` of a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer. Internally, `pos` is used to denote the index of the node that tail's `next` pointer is connected to (0-indexed). It is `-1` if there is no cycle. Note that `pos` is not passed as a parameter.

Do not modify the linked list.

![img1](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

```bash
Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

![img2](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

```bash
Example 2:

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

![img3](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

```bash
Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
```

### Explanation

This problem requires us to detect the starting node of a cycle in a linked list, if it exists. The solution must efficiently identify the cycle without modifying the list or using extra space.

#### Approach Explanation

1. Why This Approach

   Iv'e used **Floyd’s Cycle Detection Algorithm (also called the Tortoise and Hare algorithm)**. It efficiently detects the presence of a cycle and determines its starting node with:

   - O(n) time complexity
   - O(1) space complexity
   - No modifications to the linked list structure

2. Pattern Used

   - **Fast and Slow Pointer Technique (Two-Pointer Technique)**

   This is a classic technique for detecting cycles in linked structures.

3. How This Approach Works

   Floyd’s Cycle Detection Algorithm works in two phases:

   - **Phase 1:** Detect whether a cycle exists using two pointers: `slow` (moves 1 step) and `fast` (moves 2 steps). If a cycle exists, they will meet inside the cycle.
   - **Phase 2:** Once a cycle is detected, place one pointer (`p`) at the head and move both `p` and `slow` one step at a time. They will meet at the **starting point of the cycle**.

   > After they meet in the loop, starting one pointer from head and one from the meeting point — both moving 1 step at a time — will always meet at the start of the cycle.

#### Step-by-Step Walkthrough (with Example)

1. Let's use the input: `head = [3, 2, 0, -4]`, with `pos = 1`. That means the tail connects back to the node with value `2`.

2. Linked List Structure (Cycle Exists)

   ```
   3 -> 2 -> 0 -> -4
       ↑         ↓
       ← ← ← ← ←
   ```

3. Step-by-Step Execution Table

   | Step | `slow` value | `fast` value | Phase                              |
   | ---- | ------------ | ------------ | ---------------------------------- |
   | 1    | 3            | 3            | Init                               |
   | 2    | 2            | 0            | Moving                             |
   | 3    | 0            | 2            | Moving                             |
   | 4    | -4           | -4           | **Meeting Point** (Cycle Detected) |

4. We now place `p = head = 3`, and move both `p` and `slow` one step at a time.

   | Step | `p` value | `slow` value | Comment           |
   | ---- | --------- | ------------ | ----------------- |
   | 1    | 3         | -4           |                   |
   | 2    | 2         | 2            | Cycle begins here |

#### Time and Space Complexity

| Complexity Type  | Value  | Explanation                                    |
| ---------------- | ------ | ---------------------------------------------- |
| Time Complexity  | $O(n)$ | Each pointer traverses the list at most twice. |
| Space Complexity | $O(1)$ | No extra space is used except pointers.        |

---

---

## 03. Score of Parentheses

[LeetCode Problem URL](https://leetcode.com/problems/score-of-parentheses/)

Given a balanced parentheses string `s`, return the score of the string.

The score of a balanced parentheses string is based on the following rule:

- `"()"` has score `1`.
- `AB` has score `A + B`, where `A` and `B` are balanced parentheses strings.
- `(A)` has score `2 * A`, where `A` is a balanced parentheses string.

```bash
Example 1:

Input: s = "()"
Output: 1
```

```bash
Example 2:

Input: s = "(())"
Output: 2
```

```bash
Example 3:

Input: s = "()()"
Output: 2
```

#### Explanation

This problem requires us to compute the score of a balanced parentheses string based on specific rules. The challenge is to handle nested structures and ensure that scores are aggregated correctly.

#### Approach Explanation

1. Why This Approach?

   This solution uses a **stack** to simulate the structure of the parentheses. The stack helps:

   - Keep track of nested structures.
   - Aggregate scores from child expressions efficiently.
   - Ensure linear time traversal without recursion or complicated parsing.

2. Pattern Used

   - **Stack-Based Parsing**
   - This is not traditional parsing, but a simulation technique based on the nesting of parentheses using a **LIFO** structure.

3. Why It’s Efficient

   Compared to building expression trees or recursion (which may lead to stack overflow or redundant computation), this approach:

   - Requires **only one pass** through the string.
   - Uses **constant time operations** per character.
   - Requires only **O(n)** space at most.

#### Step-by-Step Walkthrough (With Table)

1. Let’s use the string: `s = "()(())"`

1. Simulation Table

   | Step | Character | Stack Before | Operation                                  | Stack After |
   | ---- | --------- | ------------ | ------------------------------------------ | ----------- |
   | 1    | `(`       | `[0]`        | Push `0` (new frame)                       | `[0, 0]`    |
   | 2    | `)`       | `[0, 0]`     | Pop `0`, max(2×0, 1) = 1 → add to previous | `[1]`       |
   | 3    | `(`       | `[1]`        | Push `0`                                   | `[1, 0]`    |
   | 4    | `(`       | `[1, 0]`     | Push `0`                                   | `[1, 0, 0]` |
   | 5    | `)`       | `[1, 0, 0]`  | Pop `0`, max(2×0, 1) = 1 → add to previous | `[1, 1]`    |
   | 6    | `)`       | `[1, 1]`     | Pop `1`, max(2×1, 1) = 2 → add to previous | `[3]`       |

1. **Final Score = 3**

#### Key Decision Logic:

- If top is `0`, then it's a simple `()`, and we push `1`.
- If not, it's a nested structure. Multiply the accumulated score by 2.
- The `max(2 * inner_score, 1)` handles both cases:
  - For `()`, it returns `1`.
  - For nested structures, it doubles the score.

#### Time and Space Complexity

| Complexity Type | Value  | Explanation                                                 |
| --------------- | ------ | ----------------------------------------------------------- |
| Time            | $O(n)$ | We process each character in the input string exactly once. |
| Space           | $O(n)$ | Stack can grow to size proportional to input depth (n).     |

#### Summary

- This is a **stack-based parsing simulation** problem.
- The score accumulates based on structure nesting.
- `max(2 * inner_score, 1)` elegantly handles both basic and nested cases.

---

---

## 04. Stack Infix to Postfix and Prefix

Given an infix expression, convert it to postfix and prefix expressions using a stack-based approach.

Rule of operator precedence:

`Precedence:   ^ > * = / > + = -`
`Associativity: Left to Right for +, -, *, /; Right to Left for ^`

Rule of postfix and prefix:

`Postfix:  Operands followed by operators (e.g., ab+)`
`Prefix:   Operators followed by operands (e.g., +ab)`

```bash
Example 1:

Input Infix Expression: (a+b)*(c+e)
Output:
Postfix Expression: ab+ce+*
Prefix Expression: *+ab+ce
```

```bash
Example 2:

Input Infix Expression: (a+b)*(c+e)/(d+f)
Output:
Postfix Expression: ab+ce+d+f/*
Prefix Expression: /*+ab+ce+d+f
```

```bash
Example 3:

Input Infix Expression: a+b*c-d/e
Output:
Postfix Expression: abc*+de/-
Prefix Expression: -+a*b/cd
```

### Explanation

This problem requires converting infix expressions to postfix and prefix formats using a stack-based approach. The key is to handle operator precedence and associativity correctly while processing the expression.

#### Approach Explanation

1. Why This Approach?

   We use **stacks** to process operators based on precedence and associativity rules. Stack-based parsing avoids recursion and efficiently handles nested parentheses.

1. Patterns Used

   - Stack-based evaluation
   - Operator precedence and associativity rules
   - Expression reversal (for prefix conversion)

1. Efficiency

   - Handles expressions in **linear time**
   - No recursion, just clean iterative control using a stack

#### Infix to Postfix: Step-by-Step Walkthrough

1. Let’s walk through this expression:

2. **Input:** `(a + b) * (c + e)`

3. Step-wise Table:

   | Step | Character | Stack           | Postfix Output |
   | ---- | --------- | --------------- | -------------- |
   | 1    | `(`       | (`(`)           |                |
   | 2    | `a`       | (`(`)           | `a`            |
   | 3    | `+`       | (`(`, `+`)      | `a`            |
   | 4    | `b`       | (`(`, `+`)      | `ab`           |
   | 5    | `)`       | (`(`, `+`)      | `ab+`          |
   | 6    | `*`       | (`*`)           | `ab+`          |
   | 7    | `(`       | (`*`, `(`)      | `ab+`          |
   | 8    | `c`       | (`*`, `(`)      | `ab+c`         |
   | 9    | `+`       | (`*`, `(`, `+`) | `ab+c`         |
   | 10   | `e`       | (`*`, `(`, `+`) | `ab+ce`        |
   | 11   | `)`       | (`*`, `(`, `+`) | `ab+ce+`       |
   | 12   | End       |                 | `ab+ce+*`      |

4. Final Postfix: `ab+ce+*`

#### Infix to Prefix: Step-by-Step Walkthrough

1. We use 3 steps:

2. **Reverse** the expression and swap brackets
   `(a + b) * (c + e)` → `(e + c) * (b + a)`

3. **Convert to Postfix** using same method
   Postfix of modified expression = `ec+ba+*`

4. **Reverse Postfix** to get Prefix
   Reverse of `ec+ba+*` = `*+ab+ce`

5. Final Prefix: `*+ab+ce`

#### Time & Space Complexity

| Metric           | Complexity | Explanation                                         |
| ---------------- | ---------- | --------------------------------------------------- |
| Time Complexity  | **O(n)**   | Every character is processed once                   |
| Space Complexity | **O(n)**   | Stack and result strings store up to `n` characters |

---

---
