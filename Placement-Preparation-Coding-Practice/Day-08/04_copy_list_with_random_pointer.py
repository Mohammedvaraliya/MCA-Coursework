class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = x
        self.next = next
        self.random = random

class LinkedList:
    def __init__(self):
        self.head = None

    def print_list(self):
        cur = self.head
        nodes = []
        while cur:
            random_val = cur.random.val if cur.random else None
            nodes.append(f"[Val: {cur.val}, Random: {random_val}]")
            cur = cur.next
        print(" -> ".join(nodes) + " -> None")

class Solution:
    def copyRandomList(self, head: 'Node') -> 'Node':
        if not head:
            return None

        old_to_new = {}

        current = head
        while current:
            old_to_new[current] = Node(current.val)
            current = current.next

        current = head
        while current:
            copy_node = old_to_new[current]
            copy_node.next = old_to_new.get(current.next)
            copy_node.random = old_to_new.get(current.random)
            current = current.next

        return old_to_new[head]


if __name__ == "__main__":
    n1 = Node(1)
    n2 = Node(2)
    n3 = Node(3)
    n4 = Node(4)
    n5 = Node(5)

    n1.next = n2
    n2.next = n3
    n3.next = n4
    n4.next = n5

    n1.random = n3
    n2.random = n1
    n3.random = n3
    n4.random = None
    n5.random = n2

    original_list = LinkedList()
    original_list.head = n1

    print("Original Linked List:")
    original_list.print_list()

    sol = Solution()
    copied_head = sol.copyRandomList(original_list.head)

    copied_list = LinkedList()
    copied_list.head = copied_head

    print("\nCopied Linked List:")
    copied_list.print_list()
