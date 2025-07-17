class Queue(object):
    def __init__(self):
        self.items = []

    def enqueue(self, item):
        self.items.insert(0, item)

    def dequeue(self):
        if not self.is_empty():
            return self.items.pop()

    def is_empty(self):
        return len(self.items) == 0

    def peek(self):
        if not self.is_empty():
            return self.items[-1].value

    def __len__(self):
        return self.size()

    def size(self):
        return len(self.items)
    

class Node(object):
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None

class BinaryTree(object):
    def __init__(self, root):
        self.root = Node(root)

    def print_tree(self, traversal_type, iterative=False):
        if traversal_type == "levelorder":
            return self.levelorder_print(self.root).rstrip("-")
        elif traversal_type == "inorder":
            if iterative:
                return self.inorder_iterative(self.root).rstrip("-")
            else:
                return self.inorder_print(self.root).rstrip("-")
        elif traversal_type == "preorder":
            if iterative:
                return self.preorder_iterative(self.root).rstrip("-")
            else:
                return self.preorder_print(self.root).rstrip("-")
        elif traversal_type == "postorder":
            if iterative:
                return self.postorder_iterative(self.root).rstrip("-")
            else:
                return self.postorder_print(self.root).rstrip("-")
        else:
            print("Traversal type " + str(traversal_type) + " is not supported.")
            return False

    def levelorder_print(self, start):
        if start is None:
            return ""

        queue = Queue()
        queue.enqueue(start)

        traversal = ""
        while len(queue) > 0:
            node = queue.dequeue()
            traversal += str(node.value) + "-"
            if node.left:
                queue.enqueue(node.left)
            if node.right:
                queue.enqueue(node.right)
        return traversal

    def inorder_print(self, start):
        if start is None:
            return ""
        traversal = ""
        traversal += self.inorder_print(start.left)
        traversal += str(start.value) + "-"
        traversal += self.inorder_print(start.right)
        return traversal

    def preorder_print(self, start):
        if start is None:
            return ""
        traversal = ""
        traversal += str(start.value) + "-"
        traversal += self.preorder_print(start.left)
        traversal += self.preorder_print(start.right)
        return traversal

    def postorder_print(self, start):
        if start is None:
            return ""
        traversal = ""
        traversal += self.postorder_print(start.left)
        traversal += self.postorder_print(start.right)
        traversal += str(start.value) + "-"
        return traversal

    def inorder_iterative(self, root):
        stack = []
        traversal = ""
        current = root

        while current is not None or stack:
            # Reach the left most Node of the current Node
            while current is not None:
                stack.append(current)
                current = current.left

            # Current must be None at this point
            current = stack.pop()
            traversal += str(current.value) + "-"

            # Visit the right subtree
            current = current.right
        return traversal

    def preorder_iterative(self, root):
        if root is None:
            return ""
        stack = [root]
        traversal = ""
        while stack:
            node = stack.pop()
            traversal += str(node.value) + "-"
            # Push right first so left is processed first
            if node.right:
                stack.append(node.right)
            if node.left:
                stack.append(node.left)
        return traversal

    def postorder_iterative(self, root):
        if root is None:
            return ""
        stack1 = [root]
        stack2 = []
        traversal = ""

        # Postorder is Left -> Right -> Root
        # We use two stacks to reverse the process
        while stack1:
            node = stack1.pop()
            stack2.append(node)
            if node.left:
                stack1.append(node.left)
            if node.right:
                stack1.append(node.right)

        while stack2:
            node = stack2.pop()
            traversal += str(node.value) + "-"
        return traversal

        


    
'''
    Level-Order Traversal
    
                    1
                  /   \
                 2      3
                /  \   / \      
               4    5 6   7 

    1-2-3-4-5-
'''

        

if __name__ == "__main__":

    tree = BinaryTree(1)
    tree.root.left = Node(2)
    tree.root.right = Node(3)
    tree.root.left.left = Node(4)
    tree.root.left.right = Node(5)
    tree.root.right.left = Node(6)
    tree.root.right.right = Node(7)

    print("Levelorder (only iterative):", tree.print_tree("levelorder"))

    print("Inorder Recursive:", tree.print_tree("inorder"))
    print("Inorder Iterative:", tree.print_tree("inorder", iterative=True))

    print("Preorder Recursive:", tree.print_tree("preorder"))
    print("Preorder Iterative:", tree.print_tree("preorder", iterative=True))

    print("Postorder Recursive:", tree.print_tree("postorder"))
    print("Postorder Iterative:", tree.print_tree("postorder", iterative=True))

