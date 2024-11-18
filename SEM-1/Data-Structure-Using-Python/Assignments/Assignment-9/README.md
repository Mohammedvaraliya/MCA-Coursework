# Assignment 9

## **Questions**

---

1. **Code of Recursive function for counting number of nodes present in a link list.**
2. **Code of Recursive function for calculating total of all node values in a link list.**

---

## Solution (Notion URL)

[Assignment-9 (Linked List)](https://mohammed-varaliya.notion.site/Assignment-8-Linked-List-13c9033ff7158037a2cad6818b536ed8?pvs=4)

## Explanation:

The code is an implementation of a **Singly Linked List** with methods for printing the list and performing recursive operations like calculating the **length** and the **sum** of the list's elements.

### Breakdown of the Code:

1. **Class `Node`**:

   - The `Node` class represents a single node in the linked list. Each node has:
     - **`data`**: Stores the data value of the node.
     - **`next`**: A pointer to the next node in the list (initially set to `None`).

2. **Class `SinglyLinkedList`**:

   - The `SinglyLinkedList` class manages the list operations such as adding nodes (`append`, `prepend`) and performing recursive operations like calculating the length and sum.

3. **Methods**:

   #### **`print_list()` Method**:

   - **Purpose**: Prints all the elements of the list in a **iterative** manner.
   - **How it works**:
     - Start from the `head` node.
     - Traverse the list by following the `next` pointer and print the `data` of each node.
     - Once the end of the list is reached (`cur_node == None`), print `None` to indicate the end of the list.

   #### **`len_recursive(node)` Method**:

   - **Purpose**: Calculates the **length** of the list using **recursion**.
   - **How it works**:
     1. **Base Case**: If the `node` is `None` (i.e., we've reached the end of the list), return `0`.
     2. **Recursive Case**: Otherwise, return `1` (for the current node) plus the result of the recursive call for the next node (`node.next`).
     3. This continues until all nodes have been counted, and the total length is returned.

   #### **`sum_recursive(node)` Method**:

   - **Purpose**: Calculates the **sum** of all the `data` values in the list using **recursion**.
   - **How it works**:
     1. **Base Case**: If the `node` is `None` (i.e., the end of the list is reached), return `0` as the sum.
     2. **Recursive Case**: Otherwise, return the `data` of the current node plus the result of the recursive call for the next node (`node.next`).
     3. This continues until all nodes have been processed, and the total sum of the list is returned.

### Walkthrough of Operations:

#### **Step 1: Insertion**

- We use the `append` method to insert values **1**, **2**, **3**, and **4** into the list.
- **Appending** works as follows:
  - The list is initially empty (`head = None`).
  - When the first node (`1`) is appended, it becomes the `head`.
  - Subsequent `append` operations add new nodes at the end of the list, following the same pattern as explained earlier in the `append` method.

#### **Step 2: Print List**

- **Operation**: `print_list()`
- After inserting the nodes, we print the list using the `print_list()` method.

  - Starting from the `head`, we print each node's data (`1 -> 2 -> 3 -> 4`), followed by `None` to indicate the end of the list.

  **Output**:

  ```bash
  1 -> 2 -> 3 -> 4 -> None
  ```

#### **Step 3: Calculate Length Recursively**

- **Operation**: `len_recursive(llist.head)`
- The `len_recursive()` method is called with the `head` of the list to calculate the length of the list recursively.

  - **First Call**: The first node has `data = 1`, so we add `1` and recursively call `len_recursive(node.next)` (node with `data = 2`).
  - **Second Call**: The second node has `data = 2`, so we add `1` again and recursively call `len_recursive(node.next)` (node with `data = 3`).
  - **Third Call**: The third node has `data = 3`, so we add `1` again and recursively call `len_recursive(node.next)` (node with `data = 4`).
  - **Fourth Call**: The fourth node has `data = 4`, so we add `1` again and recursively call `len_recursive(node.next)` (which is `None`).
  - **Base Case**: When `node = None`, we return `0`, and the recursive calls start unwinding. The final result is `1 + 1 + 1 + 1 = 4`, which is the length of the list.

  **Output**:

  ```bash
  4
  ```

#### **Step 4: Calculate Sum Recursively**

- **Operation**: `sum_recursive(llist.head)`
- The `sum_recursive()` method is called with the `head` of the list to calculate the sum of the list's elements recursively.

  - **First Call**: The first node has `data = 1`, so we add `1` and recursively call `sum_recursive(node.next)` (node with `data = 2`).
  - **Second Call**: The second node has `data = 2`, so we add `2` and recursively call `sum_recursive(node.next)` (node with `data = 3`).
  - **Third Call**: The third node has `data = 3`, so we add `3` and recursively call `sum_recursive(node.next)` (node with `data = 4`).
  - **Fourth Call**: The fourth node has `data = 4`, so we add `4` and recursively call `sum_recursive(node.next)` (which is `None`).
  - **Base Case**: When `node = None`, we return `0`, and the recursive calls start unwinding. The final result is `1 + 2 + 3 + 4 = 10`, which is the sum of the list's elements.

  **Output**:

  ```bash
  10
  ```
