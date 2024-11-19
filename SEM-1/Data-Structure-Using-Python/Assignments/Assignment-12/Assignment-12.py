class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

class SinglyLinkedList:
    def __init__(self):
        self.head = None

    def append(self, data):
        new_node = Node(data)

        if self.head is None:
            self.head = new_node
            return

        last_node = self.head
        while last_node.next:
            last_node = last_node.next
        last_node.next = new_node
    
    def check_sorted(self):
        cur_node = self.head
        prev_data = float('-inf') # Use negative infinity as initial value
        while cur_node:
            if cur_node.data >= prev_data:
                prev_data = cur_node.data
                cur_node = cur_node.next
            elif cur_node.data < prev_data:
                return False
        return True
    
    def removing_duplicates_from_sorted(self):
        cur_node = self.head
        while cur_node and cur_node.next:
            if cur_node.data == cur_node.next.data:
                cur_node.next = cur_node.next.next
            else:
                cur_node = cur_node.next
    
    def reversing_list(self):
        prev = None
        cur_node = self.head
        while cur_node:
            nxt = cur_node.next
            cur_node.next = prev

            prev = cur_node
            cur_node = nxt

        self.head = prev

    def display_list_iterative(self):
        cur_node = self.head
        while cur_node:
            print(cur_node.data, end=" -> ")
            cur_node = cur_node.next
        print("None")


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

    # Cheking whether the list is sorted or not
    print(llist.check_sorted())

    # Removing duplicate nodes from sorted list
    llist.removing_duplicates_from_sorted()

    # A method display() to print all elements in the list.
    llist.display_list_iterative()

    # Reversing the entire list
    llist.reversing_list()

    # A method display() to print all elements in the list.
    llist.display_list_iterative()