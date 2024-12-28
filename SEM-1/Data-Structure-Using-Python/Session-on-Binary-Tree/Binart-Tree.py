from binarytree import Node

def create():
    print("Enter root value: ")
    x = int(input())
    root = Node(x)

    def insert_children(node):
        if node:
            print(f"Enter left child of {node.value} (enter -1 if no left child): ")
            x = int(input())
            if x != -1:
                node.left = Node(x)
                insert_children(node.left)

            print(f"Enter right child of {node.value} (enter -1 if no right child): ")
            x = int(input())
            if x != -1:
                node.right = Node(x)
                insert_children(node.right)

    insert_children(root)
    return root


def level_order_traversal(root):
    def height(node):
        if not node:
            return 0
        return 1 + max(height(node.left), height(node.right))

    def print_level(node, level):
        if not node:
            return
        if level == 1:
            print(node.value, end=" ")
        elif level > 1:
            print_level(node.left, level - 1)
            print_level(node.right, level - 1)

    h = height(root)
    for i in range(1, h + 1):
        print_level(root, i)

def inorder_traversal(root):
    if root:
        inorder_traversal(root.left)
        print(root.value, end=" ")
        inorder_traversal(root.right)

def preorder_traversal(root):
    if root:
        print(root.value, end=" ")
        preorder_traversal(root.left)
        preorder_traversal(root.right)

def postorder_traversal(root):
    if root:
        postorder_traversal(root.left)
        postorder_traversal(root.right)
        print(root.value, end=" ")



if __name__ == "__main__":

    root = create()

    print("\nBinary Tree:")
    print(root)

    print("\nLevel-order Traversal:")
    level_order_traversal(root)

    print("\nIn-order Traversal:")
    inorder_traversal(root)

    print("\nPre-order Traversal:")
    preorder_traversal(root)

    print("\nPost-order Traversal:")
    postorder_traversal(root)