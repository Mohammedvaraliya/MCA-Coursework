class ListNode:
    def __init__(self, val):
        self.val = val
        self.next = None

class SinglyLinkedList:
    def __init__(self):
        self.head = None

    def print_list(self):
        cur_node = self.head
        while cur_node:
            print(cur_node.val, end=" -> ")
            cur_node = cur_node.next
        print("None")

    def even_nodes(self):
        cur_node = self.head
        while cur_node:
            if cur_node.val % 2 == 0:
                print(cur_node.val, end=" -> ")
            cur_node = cur_node.next
        print("None")

    def odd_nodes(self):
        cur_node = self.head
        while cur_node:
            if cur_node.val % 2 != 0:
                print(cur_node.val, end=" -> ")
            cur_node = cur_node.next

        print("None")


if __name__ == "__main__":
    llist = SinglyLinkedList()
    llist.head = ListNode(1)
    llist.head.next = ListNode(2)
    llist.head.next.next = ListNode(3)
    llist.head.next.next.next = ListNode(4)
    llist.head.next.next.next.next = ListNode(5)

    print("Linked List:")
    llist.print_list()

    print("Even Nodes of the linked List:")
    llist.even_nodes()

    print("Odd Nodes of the linked List:")
    llist.odd_nodes()