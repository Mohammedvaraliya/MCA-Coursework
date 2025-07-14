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

    def merge_sorted(self, llist):
        p = self.head
        q = llist.head
        s = None

        if not p:
            return q
        if not q:
            return p

        if p.val <= q.val:
            s = p
            p = p.next
        else:
            s = q
            q = q.next
        new_head = s

        while p and q:
            if p.val <= q.val:
                s.next = p
                s = p
                p = p.next
            else:
                s.next = q
                s = q
                q = q.next

        if not p:
            s.next = q
        if not q:
            s.next = p

        return new_head


if __name__ == "__main__":
    llist_1 = SinglyLinkedList()
    llist_1.head = ListNode(1)
    llist_1.head.next = ListNode(2)
    llist_1.head.next.next = ListNode(4)

    llist_2 = SinglyLinkedList()
    llist_2.head = ListNode(1)
    llist_2.head.next = ListNode(3)
    llist_2.head.next.next = ListNode(4)

    print("List 1:")
    llist_1.print_list()
    print("List 2:")
    llist_2.print_list()
    print("Merged List:")

    merged_head = llist_1.merge_sorted(llist_2)

    current = merged_head
    while current:
        print(current.val, end=" -> ")
        current = current.next
    print("None")
