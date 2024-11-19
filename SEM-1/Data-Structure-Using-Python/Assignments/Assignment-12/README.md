# Assignment 12

## **Questions**

---

1. **Checking if the linked list is sorted.**
2. **Removing duplicates from sorted linked list.**
3. **Reversing a linked list.**

---

## Solution (Notion URL)

[Assignment-12 (Linked List)]()

## Explanation

### 1. **Checking if the Linked List is Sorted** (`check_sorted` function)

This function checks whether the linked list is sorted in **non-decreasing order** (i.e., the elements should be in increasing order or equal to the previous element).

#### Steps:

1. **Initialization:**  
   A pointer `cur_node` is set to the head of the list, and a variable `prev_data` is initialized to 0. This will keep track of the data of the previous node for comparison purposes.

2. **Traverse the list:**

   - While `cur_node` is not `None` (meaning we haven't reached the end of the list), the function compares the current node’s data (`cur_node.data`) with the `prev_data`.

   - If `cur_node.data >= prev_data`, it means the list is still sorted up to this point, so the function continues by updating `prev_data` to `cur_node.data` and moves to the next node (`cur_node = cur_node.next`).

   - If at any point `cur_node.data < prev_data`, it means the list is **not sorted** (since a node’s value is less than the previous node's value). In this case, the function immediately returns `False`.

3. **Return `True`:**  
   If the entire list is traversed without finding any violations (i.e., every node has a value greater than or equal to the previous node), the function returns `True`, indicating that the list is sorted.

### 2. **Removing Duplicates from a Sorted Linked List** (`removing_duplicates_from_sorted` function)

Since the list is sorted, duplicates will always appear consecutively. This function removes any duplicate nodes from the sorted list.

#### Steps:

1. **Initialization:**  
   A pointer `cur_node` is set to the head of the list.

2. **Traverse the list:**

   - The function checks whether `cur_node` is not `None` and whether `cur_node.next` exists (meaning there’s at least one more node).

   - If `cur_node.data == cur_node.next.data`, it means that the current node has a duplicate, so the function removes the duplicate node by setting `cur_node.next` to `cur_node.next.next`. This effectively bypasses the next node, removing it from the list.

   - If `cur_node.data != cur_node.next.data`, it means there’s no duplicate, so the function simply moves to the next node (`cur_node = cur_node.next`).

3. **End of list:**  
   The function continues this process until the end of the list is reached, and at that point, all duplicates have been removed.

### 3. **Reversing the Linked List** (`reversing_list` function)

This function reverses the entire linked list, so that the last node becomes the first node and vice versa.

#### Steps:

1. **Initialization:**  
   The function initializes two pointers:

   - `prev` is set to `None` (because the new head of the list will eventually point to `None`).
   - `cur_node` is set to the head of the list.

2. **Reverse the list:**

   - While `cur_node` is not `None` (i.e., there are still nodes to process), the function performs the following steps for each node:
     1. Store the next node (`nxt = cur_node.next`) so that we don't lose the reference to the rest of the list.
     2. Change `cur_node.next` to point to `prev`. This effectively reverses the direction of the link for the current node.
     3. Move the `prev` pointer to the current node (`prev = cur_node`).
     4. Move `cur_node` to the next node in the original list (`cur_node = nxt`).

3. **Update head:**  
   After the loop finishes, `cur_node` will be `None` (meaning we've reached the end of the original list). The `prev` pointer will now point to the new head of the reversed list, so we set `self.head = prev`.

### Walkthrough

#### Initial Linked List:

The list after all the append operations will look like this:

```
1 -> 10 -> 20 -> 20 -> 30 -> 40 -> None
```

### **1. Checking if the Linked List is Sorted** (`check_sorted()`)

The goal of this function is to check if the linked list is sorted in **non-decreasing order**.

#### Step-by-Step Walkthrough:

- **Initial list:**  
  `1 -> 10 -> 20 -> 20 -> 30 -> 40 -> None`

- **Variables:**

  - `cur_node` starts at the head of the list (`1`).
  - `prev_data` is initialized to `float('-inf')` (negative infinity), which ensures that the first node's value is always greater than prev_data.

- **Iteration 1:**

  - `cur_node` points to `1`.
  - `prev_data` is `-inf`. Since `1 >= -inf`, we update `prev_data` to `1` and move to the next node (`cur_node = cur_node.next`).

- **Iteration 2:**

  - `cur_node` points to `10`.
  - `prev_data` is `1`. Since `10 >= 1`, we update `prev_data` to `10` and move to the next node (`cur_node = cur_node.next`).

- **Iteration 3:**

  - `cur_node` points to `20`.
  - `prev_data` is `10`. Since `20 >= 10`, we update `prev_data` to `20` and move to the next node (`cur_node = cur_node.next`).

- **Iteration 4:**

  - `cur_node` points to the next `20`.
  - `prev_data` is `20`. Since `20 >= 20`, we update `prev_data` to `20` and move to the next node (`cur_node = cur_node.next`).

- **Iteration 5:**

  - `cur_node` points to `30`.
  - `prev_data` is `20`. Since `30 >= 20`, we update `prev_data` to `30` and move to the next node (`cur_node = cur_node.next`).

- **Iteration 6:**

  - `cur_node` points to `40`.
  - `prev_data` is `30`. Since `40 >= 30`, we update `prev_data` to `40` and move to the next node (`cur_node = cur_node.next`).

- **End of list:**  
  At this point, `cur_node` is `None`, which means we have traversed the entire list without encountering any violations (i.e., the list is sorted).

- **Result:**  
  Since the list has been traversed without finding any out-of-order nodes, the function returns `True`, indicating that the list is sorted.

**Output:**

```
True
```

### **2. Removing Duplicates from a Sorted Linked List** (`removing_duplicates_from_sorted()`)

Since the linked list is sorted, duplicates will appear consecutively. This function removes duplicate nodes in such a way that only one instance of each value remains.

#### Step-by-Step Walkthrough:

- **Initial list:**  
  `1 -> 10 -> 20 -> 20 -> 30 -> 40 -> None`

- **Variables:**

  - `cur_node` starts at the head of the list (`1`).

- **Iteration 1:**

  - `cur_node` points to `1`.
  - `cur_node.next` points to `10`. Since `1 != 10`, we move to the next node (`cur_node = cur_node.next`).

- **Iteration 2:**

  - `cur_node` points to `10`.
  - `cur_node.next` points to `20`. Since `10 != 20`, we move to the next node (`cur_node = cur_node.next`).

- **Iteration 3:**

  - `cur_node` points to `20`.
  - `cur_node.next` points to another `20`. Since `20 == 20`, we remove the duplicate by setting `cur_node.next = cur_node.next.next`. This removes the second `20` node.
  - The list now becomes: `1 -> 10 -> 20 -> 30 -> 40 -> None`.
  - We do not move to the next node but stay at the current `20` since we’ve already made a change to the `next` pointer.

- **Iteration 4:**

  - `cur_node` points to `20` (after the removal).
  - `cur_node.next` points to `30`. Since `20 != 30`, we move to the next node (`cur_node = cur_node.next`).

- **Iteration 5:**

  - `cur_node` points to `30`.
  - `cur_node.next` points to `40`. Since `30 != 40`, we move to the next node (`cur_node = cur_node.next`).

- **Iteration 6:**

  - `cur_node` points to `40`.
  - `cur_node.next` is `None`, indicating the end of the list.

- **End of list:**  
  After traversing the list, all duplicates have been removed, and the function ends.

- **Result:**  
  The list after removing duplicates is:  
  `1 -> 10 -> 20 -> 30 -> 40 -> None`

**Output:**

```
1 -> 10 -> 20 -> 30 -> 40 -> None
```

### **3. Reversing the Linked List** (`reversing_list()`)

This function reverses the entire linked list so that the last node becomes the first node, and vice versa.

#### Step-by-Step Walkthrough:

- **Initial list:**  
  `1 -> 10 -> 20 -> 30 -> 40 -> None`

- **Variables:**

  - `prev` is initialized to `None` (since the new head of the list will point to `None`).
  - `cur_node` starts at the head of the list (`1`).

- **Iteration 1:**

  - `cur_node` points to `1`.
  - We store the next node (`nxt = cur_node.next`), which points to `10`.
  - We reverse the `cur_node`'s pointer: `cur_node.next = prev`, so `1.next = None`.
  - Update `prev` to point to `1` (`prev = cur_node`), and move `cur_node` to `nxt` (which points to `10`).

- **Iteration 2:**

  - `cur_node` points to `10`.
  - We store the next node (`nxt = cur_node.next`), which points to `20`.
  - We reverse the `cur_node`'s pointer: `cur_node.next = prev`, so `10.next = 1`.
  - Update `prev` to point to `10` (`prev = cur_node`), and move `cur_node` to `nxt` (which points to `20`).

- **Iteration 3:**

  - `cur_node` points to `20`.
  - We store the next node (`nxt = cur_node.next`), which points to `30`.
  - We reverse the `cur_node`'s pointer: `cur_node.next = prev`, so `20.next = 10`.
  - Update `prev` to point to `20` (`prev = cur_node`), and move `cur_node` to `nxt` (which points to `30`).

- **Iteration 4:**

  - `cur_node` points to `30`.
  - We store the next node (`nxt = cur_node.next`), which points to `40`.
  - We reverse the `cur_node`'s pointer: `cur_node.next = prev`, so `30.next = 20`.
  - Update `prev` to point to `30` (`prev = cur_node`), and move `cur_node` to `nxt` (which points to `40`).

- **Iteration 5:**

  - `cur_node` points to `40`.
  - We store the next node (`nxt = cur_node.next`), which is `None`.
  - We reverse the `cur_node`'s pointer: `cur_node.next = prev`, so `40.next = 30`.
  - Update `prev` to point to `40` (`prev = cur_node`), and move `cur_node` to `nxt` (which is `None`).

- **End of list:**  
  After the loop, `cur_node` is `None`, and `prev` points to the new head of the reversed list, which is `40`.

- **Result:**  
  The list after reversing is:  
  `40 -> 30 -> 20 -> 10 -> 1 -> None`

**Output:**

```
40 -> 30 -> 20 -> 10 -> 1 -> None
```

---
