# Leetcode Questions Solved on Day 8 Placement Training:

1. **Delete Node in a Linked List**
2. **Count Node**
3. **Odd Even Linked List**
4. **Copy List with Random Pointer**
5. **Rotate List**

## 01. Delete Node in a Linked List

[LeetCode Problem URL](https://leetcode.com/problems/delete-node-in-a-linked-list/?envType=problem-list-v2&envId=linked-list)

There is a singly-linked list `head` and we want to delete a node `node` in it.

You are given the node to be deleted `node`. You will not be given access to the first node of `head`.

All the values of the linked list are unique, and it is guaranteed that the given node `node` is not the last node in the linked list.

Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:

- The value of the given node should not exist in the linked list.
- The number of nodes in the linked list should decrease by one.
- All the values before `node` should be in the same order.
- All the values after `node` should be in the same order.

Custom testing:

- For the input, you should provide the entire linked list head and the node to be given node. node should not be the last node of the list and should be an actual node in the list.
- We will build the linked list and pass the node to your function.
- The output will be the entire list after calling your function.

![Image 1](https://assets.leetcode.com/uploads/2020/09/01/node1.jpg)

```bash
Example 1:

Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
```

![Image 2](https://assets.leetcode.com/uploads/2020/09/01/node2.jpg)

```bash
Example 2:

Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
```

### Explanation

This problem requires us to delete a node from a singly linked list without having access to the head of the list. The key constraint is that we cannot traverse the list from the head, and we are guaranteed that the node to be deleted is not the last node.

#### Approach Explanation

1. Why This Approach?

   Since we don't have access to the **previous node** or the **head pointer**, we cannot simply traverse from the beginning and update links in the usual way.

   To delete the given `node`, we instead:

   - **Copy the value from the next node** into the current node.
   - **Bypass the next node** by adjusting pointers.

   This clever technique transforms the **current node into the next node**, effectively "deleting" the one we want.

2. Problem-Solving Pattern

   - **Pointer Manipulation**
   - **In-place Node Overwrite**
   - A variant of the **two-pointer technique**, although limited due to access restrictions.

   This solution is optimal given the constraints, using constant time and space, and no traversal from head.

3. Efficiency and Elegance

   This approach is elegant because:

   - It avoids traversal.
   - No extra memory is used.
   - It meets all constraints.
   - The node is deleted **logically**, not physically.

#### Step-by-Step Walkthrough

1. Let’s consider this linked list:

   ```
   Initial List: [1 → 2 → 3 → 4 → 5]
   Node to delete: 3
   ```

2. We are given a **direct reference to node `3`**, but **no access to head**.

3. Algorithm:

   ```python
   node.val = node.next.val
   node.next = node.next.next
   ```

4. Iteration Breakdown

   | Step | Operation                               | Resulting List           | Explanation                     |
   | ---- | --------------------------------------- | ------------------------ | ------------------------------- |
   | 1    | Copy `node.next.val` → `node.val`       | \[1 → 2 → **4** → 4 → 5] | Value of node `3` becomes `4`   |
   | 2    | Skip next: `node.next = node.next.next` | \[1 → 2 → 4 → 5]         | The original `4` is now skipped |

   Node `3` (now with value `4`) has replaced the next node and linked to the node after it.

5. Final Output

   ```
   Updated List: [1 → 2 → 4 → 5]
   ```

   The original `3` is logically deleted.

#### Time and Space Complexity

| Metric               | Complexity | Explanation                                                      |
| -------------------- | ---------- | ---------------------------------------------------------------- |
| **Time Complexity**  | $O(1)$     | Only local pointer and value manipulation is used; no traversal. |
| **Space Complexity** | $O(1)$     | No additional space is required beyond a few pointers.           |

#### Summary

- This solution handles deletion without head access, by **overwriting the given node**.
- It works **only when the node to delete is not the last node**.
- Offers **constant time and space complexity**.
- Elegant and efficient under given constraints.

---

---

## 02. Count Node

[LeetCode Problem URL](https://leetcode.com/problems/delete-node-in-a-)

---

---

## 03. Odd Even Linked List

[LeetCode Problem URL](https://leetcode.com/problems/odd-even-linked-list/)

Given the `head` of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in `O(1)` extra space complexity and `O(n)` time complexity.

![Img1](https://assets.leetcode.com/uploads/2021/03/10/oddeven-linked-list.jpg)

```bash
Example 1:

Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]
```

![Img2](https://assets.leetcode.com/uploads/2021/03/10/oddeven2-linked-list.jpg)

```bash
Example 2:

Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]
```

### Explanation

This problem requires us to rearrange a singly linked list such that all nodes at odd indices come before nodes at even indices, while maintaining the relative order of nodes in each group. The challenge is to do this in-place with constant space complexity.

#### Approach Explanation

1. Why This Approach?

   To rearrange the list while maintaining relative ordering of odd and even nodes, we traverse the list only once while maintaining two pointers:

   - One for the odd-indexed nodes.
   - One for the even-indexed nodes.

   This allows us to **relink the nodes in-place** using a single traversal and constant space.

2. Problem-Solving Pattern

   - **Pattern Used**: Two Pointers (Odd-Even separation)
   - **Type**: Linked List pointer manipulation
   - **Goal**: Partition the list into odd and even sequences while preserving relative order.

#### Step-by-Step Walkthrough

1. Example: Input `head = [1, 2, 3, 4, 5]`

2. Initial Structure:

   ```
   Index:  1   2   3   4   5
   Node:   1 → 2 → 3 → 4 → 5
   ```

3. **Initialization**:

   - `odd = head` → points to 1
   - `even = head.next` → points to 2
   - `even_head = even` → stores the start of even list (needed later)

4. **Iteration 1**:

   - `odd.next = even.next` → 1's next becomes 3
   - `odd = odd.next` → move odd to 3
   - `even.next = odd.next` → 2's next becomes 4
   - `even = even.next` → move even to 4

5. **List so far**:

   ```
   Odd List: 1 → 3
   Even List: 2 → 4
   Remaining: 5
   ```

6. **Iteration 2**:

   - `odd.next = even.next` → 3's next becomes 5
   - `odd = odd.next` → move odd to 5
   - `even.next = odd.next` → 4’s next is now None (end)
   - `even = even.next` → even becomes None (loop ends)

7. **Final Merge**:

   - `odd.next = even_head` → connect odd list to start of even list

8. **Result**:

   ```
   1 → 3 → 5 → 2 → 4
   ```

#### Time and Space Complexity Analysis

| Metric           | Value  | Explanation                                                            |
| ---------------- | ------ | ---------------------------------------------------------------------- |
| Time Complexity  | $O(n)$ | Single traversal of the entire list (each node visited once).          |
| Space Complexity | $O(1)$ | No extra space used other than a few pointers; list modified in-place. |

#### Summary

- This approach achieves **in-place rearrangement** by separating odd and even indexed nodes with constant space.
- No values are changed—only pointer manipulation is used.

---

---
