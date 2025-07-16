class Node:
    def __init__(self, val, prev=None, next=None, child=None):
        self.val = val
        self.prev = prev
        self.next = next
        self.child = child

class LinkedList:
    def __init__(self):
        self.head = None

    def print_list(self):
        cur_node = self.head
        while cur_node:
            print(cur_node.val, end=" -> ")
            cur_node = cur_node.next
        print("None")

class Solution:
    def flatten(self, head: 'Node') -> 'Node':
        if not head:
            return head

        dummy = Node(0)
        prev = dummy
        stack = [head]

        while stack:
            curr = stack.pop()

            prev.next = curr
            curr.prev = prev

            if curr.next:
                stack.append(curr.next)
            if curr.child:
                stack.append(curr.child)
                curr.child = None

            prev = curr

        dummy.next.prev = None
        return dummy.next

def build_multilevel_linked_list(arr):
    if not arr:
        return None

    nodes = [None if val is None else Node(val) for val in arr]

    i = 0
    n = len(arr)
    heads_stack = []

    for i in range(5):
        nodes[i].next = nodes[i+1]
        nodes[i+1].prev = nodes[i]

    for i in range(9, 12):
        nodes[i].next = nodes[i+1]
        nodes[i+1].prev = nodes[i]

    nodes[15].next = nodes[16]
    nodes[16].prev = nodes[15]

    nodes[2].child = nodes[9]

    nodes[10].child = nodes[15]

    return nodes[0]

if __name__ == "__main__":
    head1 = [1,2,3,4,5,6,None,None,None,7,8,9,10,None,None,11,12]

    original_list = LinkedList()
    original_list.head = build_multilevel_linked_list(head1)

    print("Original Linked List:")
    original_list.print_list()

    sol = Solution()
    flatten = sol.flatten(original_list.head)

    print("Flattened Linked List:")
    cur = flatten
    while cur:
        print(cur.val, end=" -> ")
        cur = cur.next
    print("None")