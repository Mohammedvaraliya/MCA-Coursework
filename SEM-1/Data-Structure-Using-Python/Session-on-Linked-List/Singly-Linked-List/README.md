# Singly Linked List Explanation

## **What is a Singly Linked List?**

A **Singly Linked List** is a type of linked list where each node has a pointer (`next`) that points to the next node in the list. It allows us to traverse the list starting from the head node and going to the last node, but we can only traverse in one direction (from start to end).

## **Characteristics of Singly Linked List:**

- **Head**: A reference to the first node in the list.
- **Tail**: There is no explicit reference to the last node in the list, but it is the node where the `next` pointer is `None`.
- **Traversal**: We can traverse the list from the head to the last node by following the `next` pointers.

## **Walkthrough of the Code:**

### **1. The `Node` Class**

```python
class Node:
    def __init__(self, data):
        self.data = data
        self.next = None
```

- The **`Node` class** is used to create individual nodes of the linked list.
- Each **node** contains:
  - **`data`**: The actual data (value) you want to store in the node.
  - **`next`**: A reference to the next node in the list. Initially, it is set to `None`, as the node does not point to anything yet.

This class represents a single element in the linked list.

### **2. The `SinglyLinkedList` Class**

```python
class SinglyLinkedList:
    def __init__(self):
        self.head = None
```

- The **`SinglyLinkedList` class** manages the linked list.
- The list starts with an empty state, with `head = None`, indicating there are no nodes in the list initially.

Now, we can go through each method in the `SinglyLinkedList` class.

### **3. Appending a Node (`append` method)**

```python
def append(self, data):
    new_node = Node(data)

    if self.head is None:
        self.head = new_node
        return

    last_node = self.head
    while last_node.next:
        last_node = last_node.next
    last_node.next = new_node
```

- **Step 1**: We create a new node with the given `data`.
- **Step 2**: If the list is empty (`head` is `None`), the new node becomes the head of the list.
- **Step 3**: If the list is not empty, we traverse the list to find the last node (`last_node`).
- **Step 4**: Once we reach the last node (where `last_node.next` is `None`), we update its `next` pointer to point to the new node, effectively adding it to the end of the list.

### **4. Prepending a Node (`prepend` method)**

```python
def prepend(self, data):
    new_node = Node(data)

    if self.head is None:
        self.head = new_node
        return

    cur_node = self.head
    self.head = new_node
    new_node.next = cur_node
```

- **Step 1**: We create a new node with the given `data`.
- **Step 2**: If the list is empty (`head` is `None`), the new node becomes the head of the list.
- **Step 3**: If the list is not empty, we insert the new node at the front of the list:
  - We set the `next` pointer of the new node to point to the current head node (`cur_node`).
  - We update the `head` to point to the new node, making it the first node in the list.

### **5. Inserting a Node After a Given Node (`insert_after` method)**

```python
def insert_after(self, key, data):
    cur = self.head
    while cur:
        if cur.next is None and cur.data == key:
            self.append(data)
            return
        elif cur.data == key:
            new_node = Node(data)
            nxt = cur.next
            new_node.next = nxt
            cur.next = new_node
        cur = cur.next

    if cur is None:
        print("Previous Node is not present in the list")
        return
```

- **Step 1**: We traverse the list to find the node that matches the given `key`.
- **Step 2**: Once we find the node (`cur.data == key`), we create a new node with the given `data`.
  - We store the next node of the current node (`nxt = cur.next`) in a variable.
  - We set the `next` pointer of the new node to `nxt`, which is the node after the current node.
  - We update the `next` pointer of the current node (`cur.next`) to point to the new node, thus inserting the new node after the current node.
- **Step 3**: If the key is not found, we print a message indicating that the node was not found.

### **6. Deleting a Node (`delete_node` method)**

```python
def delete_node(self, key):
    cur_node = self.head
    if cur_node and cur_node.data == key:
        self.head = cur_node.next
        cur_node = None
        return

    prev = None
    while cur_node and cur_node.data != key:
        prev = cur_node
        cur_node = cur_node.next

    if cur_node is None:
        print("The Node is not present in the list")
        return

    prev.next = cur_node.next
    cur_node = None
```

- **Step 1**: If the node to be deleted is the head node (`cur_node.data == key`), we update the `head` to point to the next node.
- **Step 2**: If the node is not the head, we traverse the list to find the node with the given `key`.
- **Step 3**: Once we find the node, we update the `next` pointer of the previous node (`prev.next`) to point to the node after the current node, effectively removing the current node from the list.

### **7. Deleting a Node at a Specific Position (`delete_node_at_pos` method)**

```python
def delete_node_at_pos(self, pos):
    cur_node = self.head
    if pos == 0:
        self.head = cur_node.next
        cur_node = None
        return

    prev = None
    count = 0
    while cur_node and count != pos:
        prev = cur_node
        count += 1
        cur_node = cur_node.next

    if cur_node is None:
        print("The Node is not present in the list")
        return

    prev.next = cur_node.next
    cur_node = None
```

- **Step 1**: If the position to delete is 0 (the first node), we simply update the `head` to point to the next node.
- **Step 2**: If the position is not 0, we traverse the list to find the node at the specified position.
- **Step 3**: We remove the node at the specified position by updating the `next` pointer of the previous node (`prev.next = cur_node.next`).

### **8. Printing the List (`print_list` method)**

```python
def print_list(self):
    cur_node = self.head
    while cur_node:
        print(cur_node.data, end=" -> ")
        cur_node = cur_node.next
    print("None")
```

- This method prints the entire linked list, starting from the head node.
- It traverses the list, printing the `data` of each node, followed by an arrow (`->`), until it reaches the end of the list (where `cur_node.next` is `None`).

### **9. Calculating the Length of the List (`len_iterative` and `len_recursive` methods)**

```python
def len_iterative(self):
    count = 0
    cur_node = self.head
    while cur_node:
        count += 1
        cur_node = cur_node.next
    return count

def len_recursive(self, node):
    if node is None:
        return 0
    return 1 + self.len_recursive(node.next)
```

- **`len_iterative`**: This method uses an iterative approach to count the number of nodes in the list by traversing the list node by node.
- **`len_recursive`**: This method uses recursion to count the nodes

### **Walkthrough with an Example:**

Let's go through the following code and see how it operates:

```python
llist = SinglyLinkedList()
```

- A new **`SinglyLinkedList`** object, `llist`, is created.
- The `__init__` method is called, setting `llist.head = None`, which means the list is empty initially.

Now, we call:

```python
llist.append(1)
```

### **Appending `1` to the List:**

1. **Step 1**: A new **`Node`** object is created with `data = 1` and `next = None`:

   - This node represents the value `1`.

2. **Step 2**: Since the list is empty (`self.head = None`), the newly created node becomes the head of the list:
   - `self.head = new_node` (Now, the list contains a single node: `1 -> None`).

So, the linked list looks like this after appending `1`:

```
head -> [1] -> None
```

Next, we call:

```python
llist.append(2)
```

### **Appending `2` to the List:**

1. **Step 1**: A new **`Node`** object is created with `data = 2` and `next = None`:

   - This node represents the value `2`.

2. **Step 2**: The list is not empty (the `head` points to the node with data `1`), so we traverse the list to find the last node:
   - We start at the `head` node (`1`). Since `1.next` is `None`, it is the last node.
3. **Step 3**: We update the `next` pointer of the last node (node with data `1`) to point to the newly created node:
   - `last_node.next = new_node` (Now, the list contains two nodes: `1 -> 2 -> None`).

So, the linked list looks like this after appending `2`:

```
head -> [1] -> [2] -> None
```

Next, we call:

```python
llist.append(3)
```

### **Appending `3` to the List:**

1. **Step 1**: A new **`Node`** object is created with `data = 3` and `next = None`:

   - This node represents the value `3`.

2. **Step 2**: The list is not empty, so we traverse the list to find the last node:

   - We start at the `head` node (`1`), move to the next node (`2`), and find that `2.next` is `None`. Therefore, node `2` is the last node.

3. **Step 3**: We update the `next` pointer of the last node (node with data `2`) to point to the newly created node:
   - `last_node.next = new_node` (Now, the list contains three nodes: `1 -> 2 -> 3 -> None`).

So, the linked list looks like this after appending `3`:

```
head -> [1] -> [2] -> [3] -> None
```

Next, we call:

```python
llist.append(4)
```

### **Appending `4` to the List:**

1. **Step 1**: A new **`Node`** object is created with `data = 4` and `next = None`:

   - This node represents the value `4`.

2. **Step 2**: The list is not empty, so we traverse the list to find the last node:

   - We start at the `head` node (`1`), move to `2`, then `3`, and finally find that `3.next` is `None`. Therefore, node `3` is the last node.

3. **Step 3**: We update the `next` pointer of the last node (node with data `3`) to point to the newly created node:
   - `last_node.next = new_node` (Now, the list contains four nodes: `1 -> 2 -> 3 -> 4 -> None`).

So, the linked list looks like this after appending `4`:

```
head -> [1] -> [2] -> [3] -> [4] -> None
```

Finally, we call:

```python
llist.print_list()
```

### **Printing the List:**

1. The method starts at the `head` node and prints the `data` of each node, followed by `->` to indicate the connection between nodes.
2. It traverses the list node by node:
   - Prints `1 ->`, then moves to the next node (`2`).
   - Prints `2 ->`, then moves to the next node (`3`).
   - Prints `3 ->`, then moves to the next node (`4`).
   - Prints `4 ->`, then moves to the next node (`None`), which signals the end of the list.
3. Finally, it prints `None` to show the end of the list.

The output is:

```
1 -> 2 -> 3 -> 4 -> None
```
