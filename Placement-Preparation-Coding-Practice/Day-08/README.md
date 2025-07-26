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

## 04. Copy List with Random Pointer

[LeetCode Problem URL](https://leetcode.com/problems/copy-list-with-random-pointer/)

A linked list of length `n` is given such that each node contains an additional random pointer, which could point to any node in the list, or `null`.

Construct a deep copy of the list. The deep copy should consist of exactly `n` brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the `next` and `random` pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes `X` and `Y` in the original list, where `X.random --> Y`, then for the corresponding two nodes `x` and `y` in the copied list, `x.random --> y`.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of `[val, random_index]` where:

`val`: an integer representing `Node.val`
`random_index`: the index of the node (range from `0` to `n-1`) that the `random` pointer points to, or `null` if it does not point to any node.
Your code will only be given the `head` of the original linked list.

![Img1](https://assets.leetcode.com/uploads/2019/12/18/e1.png)

```bash
Example 1:

Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
```

![Img2](https://assets.leetcode.com/uploads/2019/12/18/e2.png)

```bash
Example 2:

Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
```

![Img3](https://assets.leetcode.com/uploads/2019/12/18/e3.png)

```bash
Example 3:

Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
```

### Explanation

This problem requires us to create a deep copy of a linked list where each node has an additional random pointer that can point to any node in the list or be `null`. The challenge is to ensure that the new list maintains the same structure and relationships as the original list.

#### Approach Explanation

1. Why This Approach?

   This problem requires duplicating both the **structure** and **cross-links (random pointers)** of the original list. A one-pass clone of nodes would lose the `random` reference details.

   To handle this, we use a **two-pass strategy**:

   - First pass: Create all new nodes and map original nodes to new nodes.
   - Second pass: Reassign the correct `next` and `random` pointers using the mapping.

   This approach ensures a **true deep copy** while maintaining simplicity and clarity.

2. Problem-Solving Pattern

   - **HashMap / Dictionary mapping** between original nodes and copied nodes.
   - **Two-pass traversal** (common in linked list cloning problems).
   - Ensures O(n) time with no cyclic dependencies.

3. Why This Is Efficient and Elegant

   Other approaches attempt in-place interleaving and pointer reassignments, but they sacrifice readability and are prone to bugs. This dictionary-based method is:

   - Intuitive
   - Robust
   - Efficient in practice

#### Step-by-Step Walkthrough

1. Example Input:

   ```python
   head = [[17,null],[13,0],[11,4],[10,2],[1,0]]
   ```

   Which means:

   - `Node1: val=17, random=None`
   - `Node2: val=13, random=Node1`
   - `Node3: val=11, random=Node5`
   - `Node4: val=10, random=Node3`
   - `Node5: val=1,  random=Node1`

2. Step 1: Clone Nodes Without Setting Pointers

   ```python
   current = head
   while current:
      old_to_new[current] = Node(current.val)
      current = current.next
   ```

3. Now we have:

   ```python
   old_to_new = {
      Node1_original: Node1_copy,
      Node2_original: Node2_copy,
      ...
   }
   ```

4. Step 2: Assign `next` and `random` Pointers

   ```python
   current = head
   while current:
      copy_node = old_to_new[current]
      copy_node.next = old_to_new.get(current.next)
      copy_node.random = old_to_new.get(current.random)
      current = current.next
   ```

5. Each copy now gets its `next` and `random` pointers based on the mapping. For instance:

   - If `Node2_original.random = Node1_original`, then:

   - `Node2_copy.random = old_to_new[Node1_original] = Node1_copy`

6. Final Result:

   The new list has:

   - Nodes with same values.
   - Correct `next` chain.
   - Correct `random` references — **to new copies only**, not to any original node.

#### Time and Space Complexity Analysis

| Metric           | Value  | Explanation                                                              |
| ---------------- | ------ | ------------------------------------------------------------------------ |
| Time Complexity  | $O(n)$ | We visit each node twice (once to copy values, once to assign pointers). |
| Space Complexity | $O(n)$ | A dictionary of size `n` is used to store mapping from original to copy. |

#### Summary

- We used a **dictionary** to map original nodes to their respective copies.
- Ensured that the new list is structurally identical but made up of **entirely new nodes**.
- Efficiently handled both `next` and `random` assignments using a **two-pass method**.
- Time and space complexities are optimal for this problem class.

---

---

## Rotate List

[LeetCode Problem URL](https://leetcode.com/problems/rotate-list/)

Given the `head` of a linked list, rotate the list to the right by k places.

![Img1](https://assets.leetcode.com/uploads/2020/11/13/rotate1.jpg)

```bash
Example 1:

Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
```

![Img2](https://assets.leetcode.com/uploads/2020/11/13/roate2.jpg)

```bash
Example 2:

Input: head = [0,1,2], k = 4
Output: [2,0,1]
```

### Explanation

This problem requires us to rotate a linked list to the right by `k` places. The challenge is to achieve this efficiently, especially when `k` can be larger than the length of the list.

#### Approach Explanation

1. Why This Approach?

   The key idea is to **convert the linked list temporarily into a circular list**, then break it at the correct position to achieve the rotation efficiently.

   This avoids repeated traversals that would be inefficient for large lists or large values of `k`.

2. Problem-Solving Pattern Used

   - **Two-pointer technique**
   - **Modular arithmetic**
   - **Circular linked list manipulation**
   - **Linked list pointer reconfiguration**

3. Efficiency and Elegance

   This approach achieves the desired rotation in **a single traversal for length** and another traversal for positioning, with no extra space, thus making it both time and space optimal.

#### Step-by-Step Walkthrough

1. Input:

   ```python
   head = [1, 2, 3, 4, 5]
   k = 2
   ```

2. Step 1: Handle edge cases

   - If `head` is `None`, has only one node, or `k == 0` → return head immediately.

3. Step 2: Determine length and connect tail to head (form a circle)

   - Traverse list:

     - `length = 5`
     - `tail = node with val 5`

   - Connect tail to head:

   - `tail.next = head` (circular link formed)

4. Step 3: Calculate effective rotation steps

   ```python
   k = k % length  # 2 % 5 = 2
   steps_to_new_tail = length - k - 1 = 2
   ```

5. We now need to stop 2 steps before the original tail to get the new tail node.

6. Step 4: Find new tail and break the circle

   - Starting at head (`1`), move 2 steps:

     - Step 1: `2`
     - Step 2: `3` → this becomes the **new tail**

   - `new_head = new_tail.next = 4`
   - Break the cycle: `new_tail.next = None`

7. Final Output:

   ```python
   Rotated list = [4, 5, 1, 2, 3]
   ```

#### Time and Space Complexity

| Complexity Metric | Value  | Explanation                                                 |
| ----------------- | ------ | ----------------------------------------------------------- |
| Time Complexity   | $O(n)$ | One pass to get length + one pass to find new head and tail |
| Space Complexity  | $O(1)$ | Constant extra space; rotation done in-place                |

#### Summary

- This solution rotates the linked list efficiently with **O(n)** time and **O(1)** space.
- The list is temporarily made circular to facilitate smooth rotation.
- Modular arithmetic ensures we handle `k` values larger than list size.

---

---
