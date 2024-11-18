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

    def prepend(self, data):
        new_node = Node(data)

        if self.head is None:
            self.head = new_node
            return

        cur_node = self.head
        self.head = new_node
        new_node.next = cur_node

    def display_list_iterative(self):
        cur_node = self.head
        while cur_node:
            print(cur_node.data, end=" -> ")
            cur_node = cur_node.next
        print("None")

    def cal_total_node(self):
        sum = 0
        cur_node = self.head
        while cur_node:
          sum += cur_node.data
          cur_node = cur_node.next
        
        return sum

    def node_count(self):
        total = 0
        cur_node = self.head
        while cur_node:
          total += 1
          cur_node = cur_node.next
        
        return total

    def max_node(self):
        max = 0
        cur_node = self.head
        while cur_node:
          if cur_node.data > max:
            max = cur_node.data
            cur_node = cur_node.next
          else:
            cur_node = cur_node.next
        
        return max

    def search_node(self, node):
        index = 1
        cur_node = self.head
        while cur_node:
            if cur_node.data == node:
                print(f"\nNode {node} found at index {index}")
                return
            index += 1
            cur_node = cur_node.next
        
        print(f"\nNode {node} is not present in the singly linked list")


if __name__ == "__main__":

    llist = SinglyLinkedList()
    llist.append(2)
    llist.append(5)
    llist.append(8)
    llist.append(1)

    # A method display() to print all elements in the list.
    llist.display_list_iterative()

    # A method calculate_total() to calculate sum of all the elements.
    print("\nSum of all nodes in singly linked list are: ", llist.cal_total_node())

    # A method node_count() for counting nodes in a linked list.
    print("\nTotal num of Nodes present in the singly linked list are: ", llist.node_count())
    
    # A method max() for finding the node that holds maximum value in a linked list.
    print("\nNode with maximum data in the singly linked list is: ", llist.max_node())

    # A method search() to find a node with a specified value.
    llist.search_node(1)