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

    def deleteDuplicates(self, head):
        res = head
        while head and head.next:
            if head.val == head.next.val:
                head.next = head.next.next
            else:
                head = head.next
        return res


if __name__ == "__main__":
    llist = SinglyLinkedList()
    llist.head = ListNode(1)
    llist.head.next = ListNode(1)
    llist.head.next.next = ListNode(2)

    print("List:")
    llist.print_list()

    llist.head = llist.deleteDuplicates(llist.head)

    print("Updated List:")
    llist.print_list()
