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
    
    # Create all nodes first
    nodes = [None if val is None else Node(val) for val in arr]
    
    i = 0
    n = len(arr)
    current_level_head = None
    prev_node = None
    parent_stack = []
    
    while i < n:
        if arr[i] is None:
            # Count consecutive Nones
            none_count = 0
            while i < n and arr[i] is None:
                none_count += 1
                i += 1
            
            if i < n and none_count > 0:
                # Find the parent node (none_count positions back from current)
                parent = prev_node
                for _ in range(none_count):
                    if parent:
                        parent = parent.prev
                
                if parent:
                    parent.child = nodes[i]
                    parent_stack.append(prev_node)  # Save current level tail
                    prev_node = None  # Reset for new level
        else:
            # Connect nodes in current level
            if prev_node is None:
                # First node in current level
                current_level_head = nodes[i]
            else:
                prev_node.next = nodes[i]
                nodes[i].prev = prev_node
            
            prev_node = nodes[i]
            i += 1
    
    return nodes[0] if nodes else None

if __name__ == "__main__":

    sol = Solution()

    # Example 1
    head1 = [1,2,3,4,5,6,None,None,None,7,8,9,10,None,None,11,12]
    original_list1 = LinkedList()
    original_list1.head = build_multilevel_linked_list(head1)
    print("Original Linked List:")
    original_list1.print_list()

    flatten1 = sol.flatten(original_list1.head)
    print("Flattened Linked List:")
    cur1 = flatten1
    while cur1:
        print(cur1.val, end=" -> ")
        cur1 = cur1.next
    print("None")

    # Example 2
    head2 = [1,2,None,3]
    original_list2 = LinkedList()
    original_list2.head = build_multilevel_linked_list(head2)
    print("\nOriginal Linked List:")
    original_list2.print_list()

    flatten2 = sol.flatten(original_list2.head)
    print("Flattened Linked List:")
    cur2 = flatten2
    while cur2:
        print(cur2.val, end=" -> ")
        cur2 = cur2.next
    print("None")