# Linked List Explanation

## **What is a Linked List?**

A **linked list** is a data structure used to store a collection of elements. Unlike arrays or lists, a linked list does not store elements in contiguous memory locations. Instead, each element (or node) contains two parts:

- **Data**: The actual value stored in the node.
- **Next**: A reference (or pointer) to the next node in the list. If there is no next node (i.e., it's the last node), this pointer is `None`.

The linked list can grow or shrink dynamically without needing to allocate or deallocate memory blocks for all elements at once.

### **Types of Linked Lists**

- **Singly Linked List**: Each node points to the next node, and traversal is only possible in one direction (from the head node to the last node).
- **Doubly Linked List**: Each node has two pointers: one pointing to the next node and another pointing to the previous node, allowing for traversal in both directions.
- **Circular Linked List**: Similar to a singly linked list, but the last node's `next` pointer points back to the first node, creating a loop. This means there is no `None` at the end, and traversal can continue indefinitely from the head node. Circular linked lists can be either singly or doubly linked.
