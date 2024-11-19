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

### Walkthrough of the Program

```python
if __name__ == "__main__":

    llist = SinglyLinkedList()
    llist.append(1)
    llist.append(10)
    llist.append(20)
    llist.append(20)
    llist.append(30)
    llist.append(40)

    # A method display() to print all elements in the list.
    llist.display_list_iterative()
```

- We create a linked list `llist` and append values to it: `1 -> 10 -> 20 -> 20 -> 30 -> 40`.
- We then display the list, which prints: `1 -> 10 -> 20 -> 20 -> 30 -> 40 -> None`.

```python
    # Checking whether the list is sorted or not
    print(llist.check_sorted())
```

- The `check_sorted()` function is called, and it checks if the list is sorted. Since the elements are in non-decreasing order, it returns `True` and prints `True`.

```python
    # Removing duplicates from sorted list
    llist.removing_duplicates_from_sorted()

    # A method display() to print all elements in the list.
    llist.display_list_iterative()
```

- The `removing_duplicates_from_sorted()` function is called. Since the list contains a duplicate `20`, it removes one of them. The list now becomes: `1 -> 10 -> 20 -> 30 -> 40`.
- We display the list, which prints: `1 -> 10 -> 20 -> 30 -> 40 -> None`.

```python
    # Reversing the list
    llist.reversing_list()

    # A method display() to print all elements in the list.
    llist.display_list_iterative()
```

- The `reversing_list()` function is called. It reverses the list so that the last node becomes the first node. The list becomes: `40 -> 30 -> 20 -> 10 -> 1`.
- We display the list, which prints: `40 -> 30 -> 20 -> 10 -> 1 -> None`.
