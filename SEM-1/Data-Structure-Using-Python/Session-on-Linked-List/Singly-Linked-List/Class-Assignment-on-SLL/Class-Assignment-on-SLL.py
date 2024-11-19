class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

class SinglyLinkedList:
    def __init__(self):
        self.head = None
    
    def prepend(self, data):
        new_node = Node(data)
        if self.head is None:
            self.head = new_node
            return

        cur_node = self.head
        self.head = new_node
        new_node.next = cur_node

    def append(self, data):
        new_node = Node(data)

        if self.head is None:
            self.head = new_node
            return

        last_node = self.head
        while last_node.next:
            last_node = last_node.next
        last_node.next = new_node
    
    def delete_node(self, key):
        # Case 1: If the node is the first (head) node
        cur_node = self.head
        if cur_node and cur_node.data == key:
            self.head = cur_node.next
            print(f"Deleted the Head node ({cur_node.data})")
            cur_node = None
            return
        
        # Case 2: If the node is inbetween node
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

    def display_list_iterative(self):
        cur_node = self.head
        while cur_node:
            print(cur_node.data, end=" -> ")
            cur_node = cur_node.next
        print("None")


if __name__ == "__main__":

    llist = SinglyLinkedList()
    llist.append(8)
    llist.append(3)
    llist.append(7)
    llist.append(9)
    llist.append(6)

    # A method display() to print all elements in the list.
    llist.display_list_iterative()

    # Deleting the first (head) node from the list
    llist.delete_node(8)

    # A method display() to print all elements in the list after deleting head node.
    llist.display_list_iterative()

    # Deleting an Intermediate Node (Not the Head Node)
    llist.delete_node(9)

    # A method display() to print all elements in the list after deleting inbetween node.
    llist.display_list_iterative()