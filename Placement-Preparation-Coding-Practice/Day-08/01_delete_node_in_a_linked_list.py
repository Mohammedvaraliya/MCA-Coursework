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
            return "Invalid position"
        
        # If the position to delete is the head node
        if pos == 0:
            self.head = self.head.next
            return

        cur = self.head
        count = 0

        # Traverse the list to find the node before the node to delete
        while cur is not None and count < pos - 1:
            cur = cur.next
            count += 1

        # If the position is out of bounds or the next node doesn't exist
        if cur is None or cur.next is None:
            return "Position not found"
        
        # Delete the node by skipping over it
        cur.next = cur.next.next

    def deleteNode(self, node):
        """
        Delete the node in the middle of the list, given only access to that node.
        This method is applicable only when the node has a next node.
        """
        if node is None or node.next is None:
            return "Can't delete the last node this way"
        
        # Copy the value of the next node to the current node and delete the next node
        node.val = node.next.val
        node.next = node.next.next


if __name__ == "__main__":
    llist = SinglyLinkedList()
    llist.head = ListNode(1)
    llist.head.next = ListNode(2)
    llist.head.next.next = ListNode(3)
    llist.head.next.next.next = ListNode(4)
    llist.head.next.next.next.next = ListNode(5)

    print("Linked List:")
    llist.print_list()

    # Test delete by node (middle node)
    print("Deleting the node with value 3 using deleteNode:")
    node_to_delete = llist.head.next.next  # Node with value 3
    llist.deleteNode(node_to_delete)
    llist.print_list()

    # Test delete with invalid position
    print("Deleting at an invalid position (-1):")
    print(llist.delete_by_pos(-1))  # Invalid position
    llist.print_list()

    print("Deleting at a position out of range (10):")
    print(llist.delete_by_pos(10))  # Out of range
    llist.print_list()
