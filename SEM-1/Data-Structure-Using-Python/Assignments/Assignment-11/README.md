# Assignment 11

## **Questions**

---

1. **A method is_empty() to check if the list is empty.**
2. **A method prepend() to insert a new element at the start of the list**
3. **A method append() to insert a new element at the end of the list.**
4. **A method insert_after() to insert a new element after a specified node.**
5. **A method insert_at_sorted_pos() to insert a new element in a sorted list at sorted position.**

---

## Solution (Notion URL)

[Assignment-11 (Linked List)](https://mohammed-varaliya.notion.site/Assignment-11-Linked-List-1429033ff71580aebb80e8cc74fea864?pvs=4)

## Explanation:

This code have an implementation of a **Singly Linked List** with various operations to manipulate the list. The operations include:

1. **Appending nodes** to the list.
2. **Prepending nodes** to the list (inserting at the beginning).
3. **Inserting nodes after a specified node**.
4. **Inserting nodes in sorted order**.
5. Checking if the list is empty.
6. Displaying the list iteratively.

### Breakdown of the Code:

#### **Class `Node`**:

- Represents a single node in the linked list.
- **Attributes**:
  - `data`: Stores the value of the node.
  - `next`: Points to the next node in the list (or `None` if it's the last node).

#### **Class `SinglyLinkedList`**:

- Manages the singly linked list and supports various operations (methods) to manipulate the list.
- **Attributes**:
  - `head`: Points to the first node in the list.

### Methods:

#### **`is_empty()` Method**:

- **Purpose**: Checks if the linked list is empty.
- **How it works**:
  - If `self.head` is `None`, the list is empty, and the method returns `True`.
  - If `self.head` points to a node, the list is not empty, and the method returns `False`.

#### **`prepend()` Method**:

- **Purpose**: Adds a new node with the given `data` at the beginning (head) of the list.
- **How it works**:
  - Create a new node with the given `data`.
  - If the list is empty (i.e., `self.head is None`), the new node becomes the `head`.
  - If the list is not empty, set the new node's `next` pointer to the current `head`, then update `self.head` to point to the new node.

#### **`append()` Method**:

- **Purpose**: Adds a new node with the given `data` to the end (tail) of the list.
- **How it works**:
  - Create a new node with the given `data`.
  - If the list is empty (i.e., `self.head is None`), set the new node as the `head`.
  - Otherwise, traverse the list until the last node (where `node.next is None`), and append the new node by setting the last node's `next` pointer to the new node.

#### **`insert_after()` Method**:

- **Purpose**: Inserts a new node with the given `data` after a node that contains the specified `key` value.
- **How it works**:
  - Start from the `head` node and traverse the list.
  - If a node with the `key` value is found:
    - If the node is the last one (`cur_node.next is None`), append the new node at the end.
    - Otherwise, create a new node and insert it after the found node by updating the `next` pointers.
  - If no node with the `key` is found, print a message indicating that the key is not in the list.

#### **`insert_at_sorted_pos()` Method**:

- **Purpose**: Inserts a new node with the given `data` into the list while maintaining sorted order.
- **How it works**:
  - If the list is empty, simply insert the new node at the `head`.
  - If the new node's `data` is smaller than or equal to the `head` node's `data`, insert the new node before the `head`.
  - Otherwise, traverse the list, and insert the new node between the nodes where the node's `data` is greater than the current node's `data` but smaller than the next node's `data`.

#### **`display_list_iterative()` Method**:

- **Purpose**: Prints all elements of the list iteratively.
- **How it works**:
  - Start from the `head` node and traverse through the list.
  - For each node, print its `data`, followed by `" -> "`.
  - Once the end of the list is reached (`cur_node == None`), print `"None"` to indicate the end of the list.

### Walkthrough of Operations:

Let's break down the sequence of operations in the `__main__` section of the code:

#### **Step 1: Initial List Creation and Display**

- The list starts as an empty list. The following nodes are added:
  - `llist.append(1)` → Appends `1` to the list.
  - `llist.append(5)` → Appends `5` to the list.
  - `llist.append(10)` → Appends `10` to the list.
  - `llist.append(15)` → Appends `15` to the list.
  - `llist.append(17)` → Appends `17` to the list.

The list now looks like this:

```
1 -> 5 -> 10 -> 15 -> 17 -> None
```

The `display_list_iterative()` method is then called, printing the list:

```bash
1 -> 5 -> 10 -> 15 -> 17 -> None
```

#### **Step 2: Check if the List is Empty**

- The `is_empty()` method is called to check if the list is empty.
- Since the list contains nodes, it will return `False`.

```bash
Check whether the singly linked list is empty or not:  False
```

#### **Step 3: Prepend a Node**

- The `prepend(0)` method is called to insert `0` at the beginning of the list.
- The list becomes:
  ```
  0 -> 1 -> 5 -> 10 -> 15 -> 17 -> None
  ```

The `display_list_iterative()` method is called again to print the updated list:

```bash
0 -> 1 -> 5 -> 10 -> 15 -> 17 -> None
```

#### **Step 4: Append a Node**

- The `append(10)` method is called to add `10` at the end of the list.
- The list becomes:
  ```
  0 -> 1 -> 5 -> 10 -> 15 -> 17 -> 10 -> None
  ```

The `display_list_iterative()` method is called again to print the updated list:

```bash
0 -> 1 -> 5 -> 10 -> 15 -> 17 -> 10 -> None
```

#### **Step 5: Insert After a Specific Node**

- The `insert_after(5, 6)` method is called to insert `6` after the node containing `5`.
- The list becomes:
  ```
  0 -> 1 -> 5 -> 6 -> 10 -> 15 -> 17 -> 10 -> None
  ```

The `display_list_iterative()` method is called again to print the updated list:

```bash
0 -> 1 -> 5 -> 6 -> 10 -> 15 -> 17 -> 10 -> None
```

#### **Step 6: Insert a Node at Sorted Position**

- The `insert_at_sorted_pos(16)` method is called to insert `16` into the list while maintaining the sorted order.
- The list becomes:
  ```
  0 -> 1 -> 5 -> 6 -> 10 -> 15 -> 16 -> 17 -> 10 -> None
  ```

The `display_list_iterative()` method is called again to print the updated list:

```bash
0 -> 1 -> 5 -> 6 -> 10 -> 15 -> 16 -> 17 -> 10 -> None
```
