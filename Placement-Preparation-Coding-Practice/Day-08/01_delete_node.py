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

    def delete_by_pos(self, pos):
        if self.head is None:
            return "List is empty"
        
        if pos < 0:
            return "Invalid"
        
        if pos == 0:
            self.head = self.head.next
            return

        cur = self.head
        count = 0

        while cur is not None and count < pos - 1:
            cur = cur.next
            count += 1

        if cur is None or cur.next is None:
            return "Not Found"
        
        cur.next = cur.next.next


if __name__ == "__main__":
    llist = SinglyLinkedList()
    llist.head = ListNode(1)
    llist.head.next = ListNode(2)
    llist.head.next.next = ListNode(3)
    llist.head.next.next.next = ListNode(4)
    llist.head.next.next.next.next = ListNode(5)

    print("Linked List:")
    llist.print_list()

    print("Deleting the node at pos 2:")

    print(llist.delete_by_pos(-9))

    print("After Deling the linked List:")
    llist.print_list()