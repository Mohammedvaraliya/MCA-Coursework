class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

def build_linked_list(arr, pos=-1):
    """
    Builds a linked list from array arr.
    If pos >= 0, creates a cycle by connecting the tail to the node at index pos.
    """
    if not arr:
        return None

    head = ListNode(arr[0])
    current = head
    nodes = [head]

    for val in arr[1:]:
        node = ListNode(val)
        current.next = node
        current = node
        nodes.append(node)

    if pos != -1:
        current.next = nodes[pos]

    return head

class Solution:
    def detectCycle_1(self, head: 'ListNode') -> 'ListNode':
        visited = set()
        cur = head
        while cur:
            if cur in visited:
                return cur
            visited.add(cur)
            cur = cur.next
        return None
    
    def detectCycle(self, head: 'ListNode') -> 'ListNode':
        if not head or not head.next:
            return None
        slow = head
        fast = head

        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if slow == fast:
                break

        if not fast or not fast.next:
            return None 

        p = head
        while p != slow:
            slow = slow.next
            p = p.next
        return slow


if __name__ == "__main__":
    head1 = build_linked_list([3,2,0,-4], pos=1)
    head2 = build_linked_list([1,2], pos=0)

    sol = Solution()

    sol1 = sol.detectCycle(head1)
    print(sol1.val)

    sol2 = sol.detectCycle(head2)
    print(sol2.val)
