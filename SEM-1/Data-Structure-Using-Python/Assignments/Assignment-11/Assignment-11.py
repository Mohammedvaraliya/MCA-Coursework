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
    
    def insert_after(self, key, data):
        cur_node = self.head
        while cur_node:
            if cur_node.next is None and cur_node.data == key:
                self.append(data)
                return
            elif cur_node.data == key:
                new_node = Node(data)
                nxt = cur_node.next
                new_node.next = nxt
                cur_node.next = new_node
            cur_node = cur_node.next

        if cur_node is None:
            print("Previous Node is not present in the list")
            return

    def display_list_iterative(self):
        cur_node = self.head
        while cur_node:
            print(cur_node.data, end=" -> ")
            cur_node = cur_node.next
        print("None")


if __name__ == "__main__":

    llist = SinglyLinkedList()
    llist.append(2)
    llist.append(5)
    llist.append(8)
    llist.append(1)

    # A method display() to print all elements in the list.
    llist.display_list_iterative()

    # A method is_empty() to check if the list is empty.
    print("\nCheck whether the singly linked list is empty or not: ", llist.is_empty())

    # A method prepend() to insert a new element at the start of the list
    llist.prepend(0)

    # A method display() to print all elements in the list after inserting node from start.
    llist.display_list_iterative()

    # A method append() to insert a new element at the end of the list.
    llist.append(10)

    # A method display() to print all elements in the list after inserting node from end.
    llist.display_list_iterative()

    # A method insert_after() to insert a new element after a specified node.
    llist.insert_after(5, 6)

    # A method display() to print all elements in the list after inserting node after other node.
    llist.display_list_iterative()