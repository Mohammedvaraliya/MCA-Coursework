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

    def concatenating_2_ll(self, head1, head2):

        if head1 is None:
            return head2

        cur_node = head1
        while cur_node.next is not None:
            cur_node = cur_node.next

        cur_node.next = head2

        return head1

    def display_list_iterative(self):
        cur_node = self.head
        while cur_node:
            print(cur_node.data, end=" -> ")
            cur_node = cur_node.next
        print("None")


if __name__ == "__main__":

    llist1 = SinglyLinkedList()
    llist1.append(1)
    llist1.append(5)
    llist1.append(10)
    llist1.append(15)

    llist2 = SinglyLinkedList()
    llist2.append(2)
    llist2.append(8)
    llist2.append(13)
    llist2.append(18)
    llist2.append(20)

    llist1.display_list_iterative()
    print("\n")
    llist2.display_list_iterative()

    llist1.concatenating_2_ll(llist1.head, llist2.head)
    print("\n")
    llist1.display_list_iterative()