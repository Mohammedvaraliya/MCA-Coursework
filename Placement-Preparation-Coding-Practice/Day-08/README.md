# Leetcode Questions Solved on Day 8 Placement Training:

1. **Delete Node in a Linked List**
2. **Count Node**
3. **Even Odd Node**
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
