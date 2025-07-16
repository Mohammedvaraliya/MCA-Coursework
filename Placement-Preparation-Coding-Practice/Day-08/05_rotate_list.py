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

    def rotateRight(self, head: 'ListNode', k: int) -> 'ListNode':
        if not head or not head.next or k == 0:
            return head

        length = 1
        tail = head
        while tail.next:
            tail = tail.next
            length += 1

        tail.next = head

        k = k % length
        steps_to_new_tail = length - k - 1
        new_tail = head
        for _ in range(steps_to_new_tail):
            new_tail = new_tail.next

        new_head = new_tail.next
        new_tail.next = None

        return new_head


if __name__ == "__main__":
    llist = SinglyLinkedList()
    llist.head = ListNode(1)
    llist.head.next = ListNode(2)
    llist.head.next.next = ListNode(3)
    llist.head.next.next.next = ListNode(4)
    llist.head.next.next.next.next = ListNode(5)

    k = 2

    print("Original Linked List:")
    llist.print_list()

    llist.head = llist.rotateRight(llist.head, k)

    print("Rotated Linked List:")
    llist.print_list()
