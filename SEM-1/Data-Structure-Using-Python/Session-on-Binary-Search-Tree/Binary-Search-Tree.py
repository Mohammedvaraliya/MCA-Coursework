from binarytree import Node

class BinarySearchTree:
    def __init__(self):
        self.root = None

    # Insertion (Recursive)
    def insert(self, value):
        if self.root is None:
            self.root = Node(value)
        else:
            self._insert_recursive(value, self.root)

    def _insert_recursive(self, value, cur_node):
        if value < cur_node.value:
            if cur_node.left is None:
                cur_node.left = Node(value)
            else:
                self._insert_recursive(value, cur_node.left)
        elif value > cur_node.value:
            if cur_node.right is None:
                cur_node.right = Node(value)
            else:
                self._insert_recursive(value, cur_node.right)
        else:
            print("Value already present in the tree.")

    # Iterative Insertion
    def insert_iterative(self, value):
        if self.root is None:
            self.root = Node(value)
            return
        current = self.root
        while current:
            if value < current.value:
                if current.left is None:
                    current.left = Node(value)
                    return
                current = current.left
            elif value > current.value:
                if current.right is None:
                    current.right = Node(value)
                    return
                current = current.right
            else:
                print("Value already present in the tree.")
                return

    # Search (Recursive)
    def find(self, value):
        if self.root:
            return self._find_recursive(value, self.root)
        return False

    def _find_recursive(self, value, cur_node):
        if cur_node is None:
            return False
        if value < cur_node.value:
            return self._find_recursive(value, cur_node.left)
        elif value > cur_node.value:
            return self._find_recursive(value, cur_node.right)
        return True

    # Iterative Search
    def find_iterative(self, value):
        current = self.root
        while current:
            if value < current.value:
                current = current.left
            elif value > current.value:
                current = current.right
            else:
                return True
        return False

    # Deletion (Recursive)
    def delete(self, value):
        self.root = self._delete_recursive(self.root, value)

    def _delete_recursive(self, cur_node, value):
        if cur_node is None:
            return cur_node
        
        # Traverse the tree
        if value < cur_node.value:
            cur_node.left = self._delete_recursive(cur_node.left, value)
        elif value > cur_node.value:
            cur_node.right = self._delete_recursive(cur_node.right, value)
        else:
            # Node to be deleted found
            if cur_node.left is None:
                return cur_node.right
            elif cur_node.right is None:
                return cur_node.left
            # Node has two children, get the inorder successor (smallest in the right subtree)
            min_larger_node = self._get_min(cur_node.right)
            cur_node.value = min_larger_node.value
            cur_node.right = self._delete_recursive(cur_node.right, min_larger_node.value)
        
        return cur_node

    # Get the minimum value node in a tree
    def _get_min(self, cur_node):
        while cur_node.left is not None:
            cur_node = cur_node.left
        return cur_node

    # Iterative Deletion
    def delete_iterative(self, value):
        self.root = self._delete_iterative(self.root, value)

    def _delete_iterative(self, root, value):
        parent = None
        current = root

        # Find the node to be deleted
        while current is not None and current.value != value:
            parent = current
            if value < current.value:
                current = current.left
            else:
                current = current.right

        if current is None:
            print(f"Node with value {value} not found.")
            return root
        
        # Node has at most one child
        if current.left is None or current.right is None:
            new_node = current.left if current.left is not None else current.right
            if parent is None:
                return new_node
            if parent.left == current:
                parent.left = new_node
            else:
                parent.right = new_node
        else:
            successor = self._get_min(current.right)
            current.value = successor.value
            current.right = self._delete_iterative(current.right, successor.value)
        
        return root

    # In-order Traversal (Recursive)
    def inorder(self):
        self._inorder_recursive(self.root)
        print()  # for newline

    def _inorder_recursive(self, cur_node):
        if cur_node:
            self._inorder_recursive(cur_node.left)
            print(cur_node.value, end=' ')
            self._inorder_recursive(cur_node.right)

    # Pre-order Traversal (Recursive)
    def preorder(self):
        self._preorder_recursive(self.root)
        print()  # for newline

    def _preorder_recursive(self, cur_node):
        if cur_node:
            print(cur_node.value, end=' ')
            self._preorder_recursive(cur_node.left)
            self._preorder_recursive(cur_node.right)

    # Post-order Traversal (Recursive)
    def postorder(self):
        self._postorder_recursive(self.root)
        print()  # for newline

    def _postorder_recursive(self, cur_node):
        if cur_node:
            self._postorder_recursive(cur_node.left)
            self._postorder_recursive(cur_node.right)
            print(cur_node.value, end=' ')

    # Level-order Traversal (Iterative)
    def level_order_traversal(self):
        if not self.root:
            return
        queue = [self.root]
        while queue:
            node = queue.pop(0)
            print(node.value, end=" ")

            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
        print()  # for newline


if __name__ == "__main__":
    bst = BinarySearchTree()

    bst.insert(30)
    bst.insert(20)
    bst.insert(40)
    bst.insert(10)
    bst.insert(25)
    bst.insert(35)
    bst.insert(50)

    print("Binary Search Tree:")
    print(bst.root)

    print("Binary Search Tree (In-order Traversal):")
    bst.inorder()

    print("\nBinary Search Tree (Pre-order Traversal):")
    bst.preorder()

    print("\nBinary Search Tree (Post-order Traversal):")
    bst.postorder()

    print("\nBinary Search Tree (Level-order Traversal):")
    bst.level_order_traversal()

    print("\nSearch 25 (Recursive):", bst.find(25))
    print("Search 100 (Recursive):", bst.find(100))
    print("Search 25 (Iterative):", bst.find_iterative(25))
    print("Search 100 (Iterative):", bst.find_iterative(100))

    print("\nBefore Deletion (In-order Traversal):")
    bst.inorder()

    print("\nDeleting 25 (Recursive):")
    bst.delete(25)
    bst.inorder()

    print("\nDeleting 100 (Iterative):")
    bst.delete_iterative(100)
    bst.inorder()
