# Leetcode Questions Solved on Day 7 Placement Training:

1. **Merge Two Sorted Lists**

## 01. Merge Two Sorted Lists

[LeetCode Problem URL](https://leetcode.com/problems/merge-two-sorted-lists/?envType=problem-list-v2&envId=linked-list)

You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

![img1](https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg)

```bash
Example 1:

Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
```

```bash
Example 2:

Input: list1 = [], list2 = []
Output: []
```

```bash
Example 3:

Input: list1 = [], list2 = [0]
Output: [0]
```

### Explanation

This problem requires merging two sorted linked lists into a single sorted linked list. The key is to traverse both lists simultaneously, comparing their current nodes and appending the smaller one to the result list.

#### Approach Explanation

1. Why This Approach?

   To solve the problem efficiently, we use the **Two-Pointer technique** to traverse both sorted lists simultaneously and compare their current nodes. This allows us to **merge the lists in a single pass**, avoiding unnecessary overhead.

   This approach **preserves the sorted order** and works in **linear time**, making it ideal for large linked lists.

2. Problem-Solving Pattern

   - **Two Pointers**
   - **Greedy Merge**
   - **Linked List Manipulation**

   This is a classic merge operation, similar to the one used in the merge step of **Merge Sort**.

3. Efficiency and Elegance

   Compared to brute-force approaches that might collect values into arrays and sort them later, this solution:

   - **Does not require extra storage for values**
   - **Avoids additional sorting**
   - **Maintains constant space (excluding output)**

   We achieve an elegant and efficient solution by directly manipulating the `next` pointers of the nodes.

#### Step-by-Step Walkthrough

1. Let’s walk through the merging of the following two sorted linked lists:

   ```
   list1 = [1 -> 2 -> 4]
   list2 = [1 -> 3 -> 4]
   ```

2. Initialization

   - We create a `dummy` node to act as the starting point of our merged list.
   - We use a `tail` pointer to build the result list by attaching nodes to it.

3. Iterative Merging

   | Step | list1.val | list2.val | Action         | Merged List So Far    |
   | ---- | --------- | --------- | -------------- | --------------------- |
   | 1    | 1         | 1         | list2 appended | 1                     |
   | 2    | 1         | 3         | list1 appended | 1 → 1                 |
   | 3    | 2         | 3         | list1 appended | 1 → 1 → 2             |
   | 4    | 4         | 3         | list2 appended | 1 → 1 → 2 → 3         |
   | 5    | 4         | 4         | list2 appended | 1 → 1 → 2 → 3 → 4     |
   | 6    | 4         | None      | list1 appended | 1 → 1 → 2 → 3 → 4 → 4 |

4. At the end of the loop, we return `dummy.next` which points to the head of the merged list.

#### Time and Space Complexity

| Metric    | Complexity | Explanation                                                                 |
| --------- | ---------- | --------------------------------------------------------------------------- |
| **Time**  | $O(n + m)$ | We traverse each of the two input lists once, where `n` and `m` are lengths |
| **Space** | $O(1)$     | We perform merging in-place (excluding output list nodes already given)     |

> The solution only creates one dummy node. All other nodes are reused from the input lists.

#### Summary

- We used a **greedy** and **in-place two-pointer** strategy to merge two sorted linked lists.
- The solution is efficient, clean, and leverages standard linked list operations.
- Time complexity is **O(n + m)** and space complexity is **O(1)**, making it optimal.
- This pattern is highly reusable for other merging and comparison problems involving lists or arrays.

---

---

## 02. Merge Two Sorted Lists

[LeetCode Problem URL](https://leetcode.com/problems/merge-two-sorted-lists/?envType=problem-list-v2&envId=linked-list)

Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

![img1](https://assets.leetcode.com/uploads/2021/01/04/list1.jpg)

```bash
Example 1:

Input: head = [1,1,2]
Output: [1,2]
```

![img2](https://assets.leetcode.com/uploads/2021/01/04/list2.jpg)

```bash
Example 2:

Input: head = [1,1,2,3,3]
Output: [1,2,3]
```

### Explanation

---

---
