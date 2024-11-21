class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

class SinglyLinkedList:
    def __init__(self):
        self.head = None

    def append(self, data):
        new_node = Node(data)

        if self.head is None:
            self.head = new_node
            return

        last_node = self.head
        while last_node.next:
            last_node = last_node.next
        last_node.next = new_node
    
    def merge_sorted(self, llist):
        p = self.head
        q = llist.head
        s = None

        if not p:
            return q
        if not q:
            return p

        if p and q:
            if p.data <= q.data:
                s = p
                p = s.next
            else:
                s = q
                q = s.next
            new_head = s

        while p and q:
            if p.data <= q.data:
                s.next = p
                s = p
                p = s.next
            else:
                s.next = q
                s = q
                q = s.next

        if not p:
            s.next = q
        if not q:
            s.next = p
        
        return new_head

    def display_list_iterative(self):
        cur_node = self.head
        while cur_node:
            print(cur_node.data, end=" -> ")
            cur_node = cur_node.next
        print("None")


if __name__ == "__main__":

    llist1 = SinglyLinkedList()
    llist1.append(1)
    llist1.append(5)
    llist1.append(10)
    llist1.append(15)

    llist2 = SinglyLinkedList()
    llist2.append(2)
    llist2.append(8)
    llist2.append(13)
    llist2.append(18)
    llist2.append(20)

    llist1.display_list_iterative()
    print("\n")
    llist2.display_list_iterative()

    llist1.merge_sorted(llist2)
    print("\n")
    llist1.display_list_iterative()