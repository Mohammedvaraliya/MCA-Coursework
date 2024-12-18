class Node:
    def __init__(self, data=None):
        self.data = data
        self.left = None
        self.right = None

class BinarySearchTree:
    def __init__(self):
        self.root = None

    # Insertion (Recursive)
    def insert(self, data):
        if self.root is None:
            self.root = Node(data)
        else:
            self._insert_recursive(data, self.root)

    def _insert_recursive(self, data, cur_node):
        if data < cur_node.data:
            if cur_node.left is None:
                cur_node.left = Node(data)
            else:
                self._insert_recursive(data, cur_node.left)
        elif data > cur_node.data:
            if cur_node.right is None:
                cur_node.right = Node(data)
            else:
                self._insert_recursive(data, cur_node.right)
        else:
            print("Value already present in the tree.")

    # Iterative Insertion
    def insert_iterative(self, data):
        if self.root is None:
            self.root = Node(data)
            return
        current = self.root
        while current:
            if data < current.data:
                if current.left is None:
                    current.left = Node(data)
                    return
                current = current.left
            elif data > current.data:
                if current.right is None:
                    current.right = Node(data)
                    return
                current = current.right
            else:
                print("Value already present in the tree.")
                return

    # Search (Recursive)
    def find(self, data):
        if self.root:
            return self._find_recursive(data, self.root)
        return False

    def _find_recursive(self, data, cur_node):
        if cur_node is None:
            return False
        if data < cur_node.data:
            return self._find_recursive(data, cur_node.left)
        elif data > cur_node.data:
            return self._find_recursive(data, cur_node.right)
        return True

    # Iterative Search
    def find_iterative(self, data):
        current = self.root
        while current:
            if data < current.data:
                current = current.left
            elif data > current.data:
                current = current.right
            else:
                return True
        return False

    # Deletion (Recursive)
    def delete(self, data):
        self.root = self._delete_recursive(self.root, data)

    def _delete_recursive(self, cur_node, data):
        if cur_node is None:
            return cur_node
        
        # Traverse the tree
        if data < cur_node.data:
            cur_node.left = self._delete_recursive(cur_node.left, data)
        elif data > cur_node.data:
            cur_node.right = self._delete_recursive(cur_node.right, data)
        else:
            # Node to be deleted found
            if cur_node.left is None:
                return cur_node.right
            elif cur_node.right is None:
                return cur_node.left
            
            # Node has two children, get the inorder successor (smallest in the right subtree)
            min_larger_node = self._get_min(cur_node.right)
            cur_node.data = min_larger_node.data
            cur_node.right = self._delete_recursive(cur_node.right, min_larger_node.data)
        
        return cur_node

    # Get the minimum value node in a tree
    def _get_min(self, cur_node):
        while cur_node.left is not None:
            cur_node = cur_node.left
        return cur_node

    # Iterative Deletion
    def delete_iterative(self, data):
        self.root = self._delete_iterative(self.root, data)

    def _delete_iterative(self, root, data):
        parent = None
        current = root

        # Find the node to be deleted
        while current is not None and current.data != data:
            parent = current
            if data < current.data:
                current = current.left
            else:
                current = current.right

        if current is None:
            print(f"Node with data {data} not found.")
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
            current.data = successor.data
            current.right = self._delete_iterative(current.right, successor.data)
        
        return root

    def inorder(self):
        self._inorder_recursive(self.root)

    def _inorder_recursive(self, cur_node):
        if cur_node:
            self._inorder_recursive(cur_node.left)
            print(cur_node.data, end=' ')
            self._inorder_recursive(cur_node.right)

if __name__ == "__main__":
    bst = BinarySearchTree()

    # Test Insertion
    bst.insert(4)
    bst.insert(2)
    bst.insert(8)
    bst.insert(5)
    bst.insert(10)

    # Test Search
    print("Search 8 (Recursive):", bst.find(8))
    print("Search 15 (Iterative):", bst.find_iterative(15))

    print("\nBefore Deletion:")
    bst.inorder()
    print("\nDeleting 5 (Recursive):")
    bst.delete(5)
    bst.inorder()

    print("\nDeleting 8 (Iterative):")
    bst.delete_iterative(8)
    bst.inorder()
