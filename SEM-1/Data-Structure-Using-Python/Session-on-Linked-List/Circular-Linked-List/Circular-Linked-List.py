class Node:
    def __init__(self, data):
        self.data = data
        self.next = None


class CircularLinkedList:
    def __init__(self):
        self.head = None

    def append(self, data):
        new_node = Node(data)
        if not self.head:
            self.head = new_node
            new_node.next = self.head
        else:
            cur_node = self.head
            while cur_node.next != self.head:
                cur_node = cur_node.next
            cur_node.next = new_node
            new_node.next = self.head

    def prepend(self, data):
        new_node = Node(data)
        if not self.head:
            self.head = new_node
            new_node.next = self.head
        else:
            cur_node = self.head
            while cur_node.next != self.head:
                cur_node = cur_node.next
            cur_node.next = new_node
            new_node.next = self.head
            self.head = new_node

    def insert_after(self, key, data):
        cur_node = self.head
        if not cur_node:
            print("List is empty.")
            return
        while True:
            if cur_node.data == key:
                new_node = Node(data)
                new_node.next = cur_node.next
                cur_node.next = new_node
                return
            cur_node = cur_node.next
            if cur_node == self.head:
                break
        print(f"Node with data {key} not found.")

    def delete_by_value(self, key):
        if not self.head:
            print("List is empty.")
            return

        cur_node = self.head
        prev_node = None

        # If the node to be deleted is the head node
        if cur_node.data == key:
            if cur_node.next == self.head:  # Single node case
                self.head = None
                return
            else:
                while cur_node.next != self.head:
                    cur_node = cur_node.next
                cur_node.next = self.head.next
                self.head = self.head.next
                return

        # Traverse the list to find the node
        while cur_node.next != self.head and cur_node.data != key:
            prev_node = cur_node
            cur_node = cur_node.next

        if cur_node.data == key:
            prev_node.next = cur_node.next
        else:
            print(f"Node with value {key} not found.")

    def print_list(self):
        if not self.head:
            print("List is empty.")
            return
        cur_node = self.head
        while True:
            print(cur_node.data, end=" -> ")
            cur_node = cur_node.next
            if cur_node == self.head:
                break
        print("HEAD")

    def len_iterative(self):
        if not self.head:
            return 0
        count = 0
        cur_node = self.head
        while True:
            count += 1
            cur_node = cur_node.next
            if cur_node == self.head:
                break
        return count

    def len_recursive(self, node, count=0):
        if not self.head:
            return 0
        if node.next == self.head:  # Base case
            return count + 1
        return self.len_recursive(node.next, count + 1)




if __name__ == "__main__":
    
    cll = CircularLinkedList()

    cll.append(1)
    cll.append(2)
    cll.append(3)
    cll.print_list()

    cll.prepend(0)
    cll.print_list()

    cll.insert_after(2, 2.5)
    cll.print_list()

    cll.delete_by_value(2)
    cll.print_list()

    print("Length (Iterative):", cll.len_iterative())
    print("Length (Recursive):", cll.len_recursive(cll.head))