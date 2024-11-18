# Assignment 10

## **Questions**

---

1. **A method display() to print all elements in the list.**
2. **A method calculate_total() to calculate sum of all the elements.**
3. **A method node_count() for counting nodes in a linked list.**
4. **A method max() for finding the node that holds maximum value in a linked list.**
5. **A method search() to find a node with a specified value.**

---

## Solution (Notion URL)

[Assignment-10 (Linked List)](https://mohammed-varaliya.notion.site/Assignment-10-Linked-List-1429033ff7158089b471ddb63b6793c6?pvs=4)

## Explanation

The code is an implementation of a **Singly Linked List** with several operations to manipulate and query the list. These operations include calculating the total of all node values, counting the nodes, finding the node with the maximum value, and searching for a node with a specific value.

### Breakdown of the Code:

1. **Class `Node`**:

   - Represents a single node in the linked list.
   - Each node has:
     - **`data`**: The value stored in the node.
     - **`next`**: A pointer to the next node in the list (initially set to `None`).

2. **Class `SinglyLinkedList`**:
   - Manages the singly linked list, supporting various operations.

### Methods:

#### **`display_list_iterative()` Method**:

- **Purpose**: Prints all the elements of the list iteratively.
- **How it works**:
  - Start from the `head` node.
  - Traverse the list node by node, printing each node's `data`.
  - Once the end of the list is reached (i.e., `cur_node == None`), print `None` to indicate the end of the list.

#### **`cal_total_node()` Method**:

- **Purpose**: Calculates the **sum** of all nodes in the list.
- **How it works**:
  - Start from the `head` node.
  - Initialize a `sum` variable to `0`.
  - Traverse the list, adding each node's `data` value to the `sum`.
  - Once the end of the list is reached, return the `sum` which represents the total of all node values in the list.

#### **`node_count()` Method**:

- **Purpose**: Counts the total number of nodes in the list.
- **How it works**:
  - Start from the `head` node.
  - Initialize a `total` variable to `0`.
  - Traverse the list, incrementing the `total` for each node encountered.
  - Once the end of the list is reached, return the `total`, which is the number of nodes in the list.

#### **`max_node()` Method**:

- **Purpose**: Finds the **node with the maximum value** in the list.
- **How it works**:
  - Start from the `head` node.
  - Initialize a `max` variable to `0` (or a small number).
  - Traverse the list and compare each node's `data` value with the current `max`.
  - If a node's `data` is larger than the current `max`, update `max` to that node's `data`.
  - Once the end of the list is reached, return the `max`, which is the maximum node value in the list.

#### **`search_node()` Method**:

- **Purpose**: Searches for a node with a specific value in the list.
- **How it works**:
  - Start from the `head` node and initialize an `index` variable to `1`.
  - Traverse the list, comparing each node's `data` with the given `node` value.
  - If a match is found, print the node's index and return.
  - If the traversal completes and no match is found, print that the node is not present in the list.

### Walkthrough of Operations:

#### **Step 1: Insertion (Using `append`)**

- The nodes with values **2**, **5**, **8**, and **1** are appended to the list using the `append` method. This creates a singly linked list:
  ```
  2 -> 5 -> 8 -> 1 -> None
  ```

#### **Step 2: Display List**

- **Operation**: `display_list_iterative()`
- The `display_list_iterative()` method is called to print the list. It starts from the `head` node (which holds `2`) and iterates through the list, printing the data of each node followed by `->`. Once the end of the list is reached, it prints `None`.

  **Output**:

  ```bash
  2 -> 5 -> 8 -> 1 -> None
  ```

#### **Step 3: Calculate Total Sum of Nodes**

- **Operation**: `cal_total_node()`
- The `cal_total_node()` method calculates the sum of all node values:

  - Start from the `head` node (with data `2`).
  - Add `2` to the sum, then move to the next node (with data `5`).
  - Add `5` to the sum, then move to the next node (with data `8`).
  - Add `8` to the sum, then move to the next node (with data `1`).
  - Add `1` to the sum, then reach the end of the list.
  - The total sum is `2 + 5 + 8 + 1 = 16`.

  **Output**:

  ```bash
  Sum of all nodes in singly linked list are:  16
  ```

#### **Step 4: Count Total Number of Nodes**

- **Operation**: `node_count()`
- The `node_count()` method counts the total number of nodes in the list:

  - Start from the `head` node (with data `2`).
  - Increment the `total` by `1`, then move to the next node (with data `5`).
  - Increment the `total` by `1`, then move to the next node (with data `8`).
  - Increment the `total` by `1`, then move to the next node (with data `1`).
  - Increment the `total` by `1`, then reach the end of the list.
  - The total count is `4` nodes.

  **Output**:

  ```bash
  Total num of Nodes present in the singly linked list are:  4
  ```

#### **Step 5: Find Maximum Node**

- **Operation**: `max_node()`
- The `max_node()` method finds the node with the maximum value in the list:

  - Start from the `head` node (with data `2`).
  - Set `max` to `2` initially.
  - Move to the next node (with data `5`). Since `5 > 2`, update `max` to `5`.
  - Move to the next node (with data `8`). Since `8 > 5`, update `max` to `8`.
  - Move to the next node (with data `1`). Since `1 < 8`, leave `max` unchanged.
  - The maximum value is `8`.

  **Output**:

  ```bash
  Node with maximum data in the singly linked list is:  8
  ```

#### **Step 6: Search for Node**

- **Operation**: `search_node(1)`
- The `search_node()` method searches for a node with the value `1`:

  - Start from the `head` node (with data `2`). The value doesn't match `1`, so move to the next node.
  - Move to the next node (with data `5`). The value doesn't match `1`, so move to the next node.
  - Move to the next node (with data `8`). The value doesn't match `1`, so move to the next node.
  - Move to the next node (with data `1`). The value matches `1`, so print the index (`4`).

  **Output**:

  ```bash
  Node 1 found at index 4
  ```

#### **Step 7: Node Not Found (Example)**:

- **Operation**: `search_node(10)`
- If you call `search_node(10)`, it would iterate through the list and print:
  ```bash
  Node 10 is not present in the singly linked list
  ```

### Final Output:

```bash
2 -> 5 -> 8 -> 1 -> None
Sum of all nodes in singly linked list are:  16

Total num of Nodes present in the singly linked list are:  4

Node with maximum data in the singly linked list is:  8

Node 1 found at index 4
```
