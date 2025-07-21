class AVLNode:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None
        self.height = 1  # height of the node (default is 1 for a leaf node)

class AVLTree:
    def insert(self, root, key):
        # 1. Perform the normal BST insertion
        if not root:
            return AVLNode(key)
        
        if key < root.key:
            root.left = self.insert(root.left, key)
        else:
            root.right = self.insert(root.right, key)

        # 2. Update height of this ancestor node
        root.height = 1 + max(self.get_height(root.left), self.get_height(root.right))

        # 3. Get the balance factor
        balance = self.get_balance(root)

        # 4. If this node becomes unbalanced, then there are 4 cases

        # Left Left Case
        if balance > 1 and key < root.left.key:
            return self.right_rotate(root)

        # Right Right Case
        if balance < -1 and key > root.right.key:
            return self.left_rotate(root)

        # Left Right Case
        if balance > 1 and key > root.left.key:
            root.left = self.left_rotate(root.left)
            return self.right_rotate(root)

        # Right Left Case
        if balance < -1 and key < root.right.key:
            root.right = self.right_rotate(root.right)
            return self.left_rotate(root)

        return root

    def delete(self, root, key):
        # STEP 1: PERFORM STANDARD BST DELETE
        if not root:
            return root
        
        if key < root.key:
            root.left = self.delete(root.left, key)
        elif key > root.key:
            root.right = self.delete(root.right, key)
        else:
            # Node with only one child or no child
            if root.left is None:
                return root.right
            elif root.right is None:
                return root.left
            
            # Node with two children: Get the inorder successor (smallest in the right subtree)
            root.key = self.min_value_node(root.right).key

            # Delete the inorder successor
            root.right = self.delete(root.right, root.key)

        # STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = 1 + max(self.get_height(root.left), self.get_height(root.right))

        # STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether it became unbalanced)
        balance = self.get_balance(root)

        # STEP 4: IF THE NODE BECAME UNBALANCED, THEN PERFORM ROTATIONS

        # Left Left Case
        if balance > 1 and self.get_balance(root.left) >= 0:
            return self.right_rotate(root)

        # Right Right Case
        if balance < -1 and self.get_balance(root.right) <= 0:
            return self.left_rotate(root)

        # Left Right Case
        if balance > 1 and self.get_balance(root.left) < 0:
            root.left = self.left_rotate(root.left)
            return self.right_rotate(root)

        # Right Left Case
        if balance < -1 and self.get_balance(root.right) > 0:
            root.right = self.right_rotate(root.right)
            return self.left_rotate(root)

        return root

    def left_rotate(self, z):
        y = z.right
        T2 = y.left

        # Perform rotation
        y.left = z
        z.right = T2

        # Update heights
        z.height = 1 + max(self.get_height(z.left), self.get_height(z.right))
        y.height = 1 + max(self.get_height(y.left), self.get_height(y.right))

        # Return the new root
        return y

    def right_rotate(self, z):
        y = z.left
        T3 = y.right

        # Perform rotation
        y.right = z
        z.left = T3

        # Update heights
        z.height = 1 + max(self.get_height(z.left), self.get_height(z.right))
        y.height = 1 + max(self.get_height(y.left), self.get_height(y.right))

        # Return the new root
        return y

    def get_height(self, node):
        if not node:
            return 0
        return node.height

    def get_balance(self, node):
        if not node:
            return 0
        return self.get_height(node.left) - self.get_height(node.right)

    def min_value_node(self, node):
        if node is None or node.left is None:
            return node
        return self.min_value_node(node.left)

    # A utility function to do inorder tree traversal
    def inorder_traversal(self, root):
        if root:
            self.inorder_traversal(root.left)
            print(root.key, end=" ")
            self.inorder_traversal(root.right)

    # A utility function to print the tree structure (for visualization)
    def print_tree(self, root, level=0, prefix="Root: "):
        if root is not None:
            print(" " * (level * 4) + prefix + str(root.key))
            if root.left or root.right:
                if root.left:
                    self.print_tree(root.left, level + 1, "L--- ")
                else:
                    print(" " * ((level + 1) * 4) + "L--- None")
                if root.right:
                    self.print_tree(root.right, level + 1, "R--- ")
                else:
                    print(" " * ((level + 1) * 4) + "R--- None")


# Example usage of the AVL Tree
if __name__ == "__main__":
    avl_tree = AVLTree()
    root = None

    # Insert nodes
    keys = [20, 15, 25, 10, 5, 1, 30]
    for key in keys:
        root = avl_tree.insert(root, key)

    print("AVL Tree after insertions:")
    avl_tree.print_tree(root)

    # Inorder Traversal of the AVL tree
    print("\nInorder Traversal of AVL Tree:")
    avl_tree.inorder_traversal(root)
    print()

    # Delete a node
    root = avl_tree.delete(root, 15)
    print("\nAVL Tree after deleting node 15:")
    avl_tree.print_tree(root)

    # Inorder Traversal of the AVL tree after deletion
    print("\nInorder Traversal of AVL Tree after deletion:")
    avl_tree.inorder_traversal(root)
    print()
