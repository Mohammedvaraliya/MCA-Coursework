class Node:
    def __init__(self, data):
        self.data = data
        self.prev = None
        self.next = None

class DoublyLinkedList:
    def __init__(self):
        self.head = None
    
    def insert_at_end(self, data):
        new_node = Node(data)
        if self.head is None:
            self.head = new_node
            return
        last_node = self.head
        while last_node.next:
            last_node = last_node.next
        last_node.next = new_node
        new_node.prev = last_node

    def insert_at_beginning(self, data):
        new_node = Node(data)
        if self.head is None:
            self.head = new_node
            return
        new_node.next = self.head
        self.head.prev = new_node
        self.head = new_node

    def insert_after(self, prev_data, data):
        new_node = Node(data)
        current_node = self.head
        while current_node:
            if current_node.data == prev_data:
                new_node.next = current_node.next
                new_node.prev = current_node
                if current_node.next:
                    current_node.next.prev = new_node
                current_node.next = new_node
                return
            current_node = current_node.next
        return "Node with the given data not found"

    def delete_node(self, key):
        if self.head is None:
            return "List is empty"
        current_node = self.head
        while current_node:
            if current_node.data == key:
                if current_node.prev:
                    current_node.prev.next = current_node.next
                if current_node.next:
                    current_node.next.prev = current_node.prev
                if current_node == self.head:
                    self.head = current_node.next
                return
            current_node = current_node.next
        return "Node not found"

    def delete_at_pos(self, pos):
        if self.head is None:
            return "List is empty"
        current_node = self.head
        count = 0
        while current_node:
            if count == pos:
                if current_node.prev:
                    current_node.prev.next = current_node.next
                if current_node.next:
                    current_node.next.prev = current_node.prev
                if current_node == self.head:
                    self.head = current_node.next
                return
            count += 1
            current_node = current_node.next
        return "Position out of range"

    def display(self):
        if self.head is None:
            print("List is empty")
            return
        current_node = self.head
        while current_node:
            print(current_node.data, end=" <-> ")
            current_node = current_node.next
        print("None")

if __name__ == "__main__":

    dll = DoublyLinkedList()

    dll.insert_at_end(10)
    dll.insert_at_end(20)
    dll.insert_at_end(30)
    dll.display()

    dll.insert_at_beginning(5)
    dll.display()

    dll.insert_after(10, 15)
    dll.display()

    dll.delete_node(20)
    dll.display()

    dll.delete_at_pos(2)
    dll.display()