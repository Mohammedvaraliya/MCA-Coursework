class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

class SinglyLinkedList:
    def __init__(self):
        self.head = None

    def is_empty(self):
        if self.head is None:
            return True
        else:
            return False
    
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
    
    def insert_at_sorted_pos(self, data):
        new_node = Node(data)

        # Case 1: If the list is empty, insert the new node as the head
        if self.head is None:
            self.head = new_node
            return
        
        # Case 2: If the new node should be inserted before the head (sorted order)
        if self.head.data >= data:
            new_node.next = self.head
            self.head = new_node
            return
        
        cur_node = self.head
        while cur_node.next and cur_node.next.data < data:
            cur_node = cur_node.next
        
        new_node.next = cur_node.next
        cur_node.next = new_node

    def display_list_iterative(self):
        cur_node = self.head
        while cur_node:
            print(cur_node.data, end=" -> ")
            cur_node = cur_node.next
        print("None")


if __name__ == "__main__":

    llist = SinglyLinkedList()
    llist.append(1)
    llist.append(5)
    llist.append(10)
    llist.append(15)
    llist.append(17)

    # A method display() to print all elements in the list.
    llist.display_list_iterative()

    # A method insert_at_sorted_pos() to insert a new element in a sorted list at sorted position.
    llist.insert_at_sorted_pos(16)

    # A method display() to print all elements in the list after inserting node at a sorted position.
    llist.display_list_iterative()