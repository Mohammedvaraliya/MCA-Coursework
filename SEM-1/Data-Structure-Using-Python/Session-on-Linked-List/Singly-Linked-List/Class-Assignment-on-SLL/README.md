# Class Assignment on Singly Linked List

## **Questions**

---

1. **Delete the very first Node.**
2. **Delete inbetween Node with data as key.**

---

## Explanation

The goal of this `delete_node` method is to delete a node from the linked list, given a specific value (`key`) that matches the data of the node to be deleted. The method handles three main cases for node deletion:

1. **Case 1: Deleting the Head Node**
2. **Case 2: Deleting an Intermediate Node (Node that is not the head)**
3. **Case 3: The Node is Not Found in the List**

### Case 1: Deleting the Head Node

This case occurs when the node to be deleted is the **first (head) node** of the linked list. The logic for this case is:

- **Step 1:** Check if the `head` node contains the value that matches the `key`. This is done by checking if `cur_node.data == key`.
- **Step 2:** If the `key` matches the `data` of the head node, then:
  - Update the `head` to point to the **next node** (`self.head = cur_node.next`), effectively removing the current head node from the list.
  - Print a message indicating the head node has been deleted.
  - Finally, set `cur_node = None` to clean up the reference to the deleted node (though in Python, this is optional as garbage collection will take care of it).

#### Code:

```python
if cur_node and cur_node.data == key:
    self.head = cur_node.next
    print(f"Deleted the Head node ({cur_node.data})")
    cur_node = None
    return
```

#### Example:

If the list is `8 -> 3 -> 7 -> 9 -> 6` and we call `delete_node(8)`, the head node (8) will be deleted and the new head will be the node with data `3`.

### Case 2: Deleting an Intermediate Node (Not the Head Node)

If the node to be deleted is **not** the head node, the method must traverse the list to find the node with the matching `key`. Here's the logic:

- **Step 1:** Set up two pointers:
  - `prev` (previous node): Initially set to `None`.
  - `cur_node` (current node): Start from the head of the list (`self.head`).
- **Step 2:** Traverse the list by moving `cur_node` one step at a time (`cur_node = cur_node.next`). While traversing, update `prev` to point to the current node, and `cur_node` to the next node.

- **Step 3:** If `cur_node.data` matches the `key` during the traversal:
  - Update `prev.next` to point to `cur_node.next`, effectively bypassing `cur_node` and removing it from the list. This links the previous node to the node that comes after `cur_node`.
  - Print a message indicating the node has been deleted.
  - Set `cur_node = None` to free up the reference to the deleted node.

#### Code:

```python
prev = None
while cur_node and cur_node.data != key:
    prev = cur_node
    cur_node = cur_node.next

if cur_node is None:
    print("The Node is not present in the list")
    return

prev.next = cur_node.next
print(f"Deleted the node ({cur_node.data})")
cur_node = None
```

#### Example:

If the list is `3 -> 7 -> 9 -> 6` and we call `delete_node(9)`, we traverse the list until `cur_node` points to the node with data `9`. We then set `prev.next = cur_node.next` to bypass the node with data `9`, resulting in the list `3 -> 7 -> 6`.

### Case 3: The Node is Not Found in the List

This case occurs when the node with the specified `key` is **not found** in the list. In this case, the method simply prints a message indicating that the node is not present and returns.

- After traversing the list, if `cur_node` is `None`, it means we reached the end of the list without finding a matching node, so we print the message `"The Node is not present in the list"`.

### Example Scenario Walkthrough:

1. **Initial List:**  
   `8 -> 3 -> 7 -> 9 -> 6`

2. **Delete Head Node (8):**  
   The head node `8` is deleted. The list becomes:

   ```
   3 -> 7 -> 9 -> 6
   ```

3. **Delete Node (9):**  
   Traverse the list, find the node with value `9`, and delete it. The list becomes:
   ```
   3 -> 7 -> 6
   ```
