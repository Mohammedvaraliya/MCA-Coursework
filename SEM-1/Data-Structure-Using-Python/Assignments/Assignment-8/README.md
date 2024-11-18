# Assignment 8

## **Questions**

---

1. **Code of Recursive function for displaying/traversing all nodes of a link list.**
2. **Time & Space Complexity of above program.**

---

## Solution (Notion URL)

[Assignment-8 (Linked List)](https://mohammed-varaliya.notion.site/Assignment-9-Linked-List-13c9033ff71580e9a0dcccc42d703130?pvs=4)

## Explanation:

The Python code implements a **Singly Linked List** with methods to append, prepend, and print the list (both iteratively and recursively). Here's a breakdown of the code, followed by a walkthrough of how insertion and traversal are done:

### 1. **Class `Node`:**

- The `Node` class represents a node in the linked list. Each node has two properties:
  - **`data`**: Stores the data value of the node.
  - **`next`**: Points to the next node in the list (initially set to `None`).

### 2. **Class `SinglyLinkedList`:**

- The `SinglyLinkedList` class manages the linked list operations.
  - **`head`**: Points to the first node of the list (initially set to `None`).

### Methods:

#### **a. `append(data)` Method:**

- **Purpose**: Adds a new node with the provided `data` at the **end** of the list.
- **How it works**:
  1.  **Create New Node**: A new node is created with the given data.
  2.  **Check if List is Empty**: If the list is empty (i.e., `head` is `None`), the new node becomes the `head` of the list.
  3.  **Traverse to the End**: If the list is not empty, we start from the `head` and traverse until we reach the last node (i.e., the node whose `next` is `None`).
  4.  **Insert the New Node**: Once the last node is found, the new node is linked to the last node by setting `last_node.next = new_node`.

#### **b. `prepend(data)` Method:**

- **Purpose**: Adds a new node with the provided `data` at the **beginning** of the list.
- **How it works**:
  1.  **Create New Node**: A new node is created with the given data.
  2.  **Check if List is Empty**: If the list is empty, the new node becomes the `head` of the list.
  3.  **Insert Before the Current Head**: If the list is not empty, the `next` of the new node is set to the current `head`, and the new node becomes the new `head`.

#### **c. `print_list()` Method (Iterative Traversal):**

- **Purpose**: Prints all nodes in the list in a **iterative** manner (using a loop).
- **How it works**:
  1.  **Start from Head**: We initialize `cur_node` as the `head` of the list.
  2.  **Traverse**: While `cur_node` is not `None` (i.e., not at the end of the list), print the `data` of the current node and move to the next node by setting `cur_node = cur_node.next`.
  3.  **End**: After traversing the entire list, we print `None` to indicate the end of the list.

#### **d. `print_list_recursive(node)` Method (Recursive Traversal):**

- **Purpose**: Prints all nodes in the list in a **recursive** manner.
- **How it works**:
  1.  **Base Case**: If the current `node` is `None` (i.e., the end of the list), return the string `"None"`.
  2.  **Recursive Case**: Otherwise, return the string of the current node’s `data` concatenated with the result of the recursive call for the next node (`node.next`).
  3.  **Traverse**: This continues recursively, unwinding once the end of the list is reached, and concatenating each node's data into a string representation.

### Walkthrough of Operations:

#### **Step 1: Insertion**

- **Inserting Values**: We are inserting values 1, 2, 3, and 4 into the list using the `append` method.

  **a. Insert 1**:

  - The list is empty, so `head` is `None`.
  - A new node with `data = 1` is created.
  - Since the list is empty, this new node becomes the `head`.

  **b. Insert 2**:

  - A new node with `data = 2` is created.
  - We start from the `head` (which has `data = 1`) and traverse the list to find the last node.
  - Since the `next` of the node with `data = 1` is `None`, we insert the new node with `data = 2` at the end.

  **c. Insert 3**:

  - A new node with `data = 3` is created.
  - We start from the `head` and traverse the list until the last node (`data = 2`).
  - We insert the new node after the node with `data = 2`.

  **d. Insert 4**:

  - A new node with `data = 4` is created.
  - We start from the `head` and traverse the list until the last node (`data = 3`).
  - We insert the new node after the node with `data = 3`.

#### **Step 2: Traversal (Iterative)**

- **Output of `print_list()`**:
  After inserting the values 1, 2, 3, and 4, the list looks like this: `1 -> 2 -> 3 -> 4 -> None`.

  - Starting from the `head` (`data = 1`), we print the data (`1`), and then move to the next node (`data = 2`).
  - Continue this process until we reach the last node (`data = 4`), and then print `None` to indicate the end of the list.

  **Output**:

  ```bash
  1 -> 2 -> 3 -> 4 -> None
  ```

#### **Step 3: Traversal (Recursive)**

- **Output of `print_list_recursive(llist.head)`**:
  The `print_list_recursive` method is called with the `head` of the list. It works recursively to concatenate the string representation of each node’s data.

  - First, it prints the `data` of the `head` node (`1`), then recursively prints the data of the next node (`2`), and so on, until it reaches the last node.
  - Finally, it concatenates the string `"None"` to indicate the end of the list.

  **Output**:

  ```bash
  1 -> 2 -> 3 -> 4 -> None
  ```

---

### Final Output:

```bash
1 -> 2 -> 3 -> 4 -> None
1 -> 2 -> 3 -> 4 -> None
```
