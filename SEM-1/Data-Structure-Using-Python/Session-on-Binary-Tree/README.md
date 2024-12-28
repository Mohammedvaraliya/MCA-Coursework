# Binary Search Tree Explanation

### **Example Walkthrough of the Binary Tree Operations Program:**

The program implements the following binary tree operations:

1. **Creating a binary tree** (with root and its children).
2. **Level-order traversal** of the binary tree.
3. **In-order traversal** of the binary tree.
4. **Pre-order traversal** of the binary tree.
5. **Post-order traversal** of the binary tree.

**Example Input:**

- We will create the following binary tree:

```
        10
       /  \
      5    20
     / \   /
    3   7 15
```

### **Step 1: Creating the Binary Tree**

1. The program prompts the user to enter the **root value**:

   ```
   Enter root value:
   ```

   - The user inputs `10`, so the root node of the tree is created with value `10`.

2. The program then recursively asks for the **left and right children** of each node, starting with the root node (`10`):

   - It prompts the user for the left child of `10`:

     ```
     Enter left child of 10 (enter -1 if no left child):
     ```

     - The user inputs `5`, so the left child of the root (`10`) is created with value `5`.

   - It prompts the user for the right child of `10`:

     ```
     Enter right child of 10 (enter -1 if no right child):
     ```

     - The user inputs `20`, so the right child of `10` is created with value `20`.

3. The program continues by recursively inserting children for the node `5` (left child of `10`):

   - It prompts the user for the left child of `5`:

     ```
     Enter left child of 5 (enter -1 if no left child):
     ```

     - The user inputs `3`, so the left child of `5` is created with value `3`.

   - It prompts the user for the right child of `5`:

     ```
     Enter right child of 5 (enter -1 if no right child):
     ```

     - The user inputs `7`, so the right child of `5` is created with value `7`.

4. Next, the program recursively inserts children for node `20` (right child of `10`):

   - It prompts the user for the left child of `20`:

     ```
     Enter left child of 20 (enter -1 if no left child):
     ```

     - The user inputs `15`, so the left child of `20` is created with value `15`.

   - It prompts the user for the right child of `20`:

     ```
     Enter right child of 20 (enter -1 if no right child):
     ```

     - The user inputs `-1`, meaning `20` does not have a right child.

### **Step 2: Displaying the Binary Tree**

After creating the tree, the program prints the tree structure (using the `binarytree` module):

```
Binary Tree:
    ______10
   /       \
  5         20
 / \       /
3   7     15
```

### **Step 3: Level-order Traversal**

Level-order traversal visits nodes level by level, starting from the root. It uses a breadth-first traversal approach:

- **Level 1 (Root):** The program prints `10` (the root).
- **Level 2:** The program prints `5 20` (children of `10`).
- **Level 3:** The program prints `3 7 15` (children of `5` and `20`).

So the **Level-order traversal output** is:

```
Level-order Traversal:
10 5 20 3 7 15
```

### **Step 4: In-order Traversal**

In-order traversal visits nodes in the following order: Left Subtree → Root → Right Subtree.

- **Left Subtree of 10:** Visit the left subtree of `5`:
  - **Left Subtree of 5:** Visit `3`.
  - **Visit 5**.
  - **Right Subtree of 5:** Visit `7`.
- **Visit 10**.
- **Right Subtree of 10:** Visit the left subtree of `20`:
  - **Left Subtree of 20:** Visit `15`.

So the **In-order traversal output** is:

```
In-order Traversal:
3 5 7 10 15 20
```

### **Step 5: Pre-order Traversal**

Pre-order traversal visits nodes in the following order: Root → Left Subtree → Right Subtree.

- **Visit 10**.
- **Left Subtree of 10:** Visit `5`, then:
  - **Left Subtree of 5:** Visit `3`.
  - **Right Subtree of 5:** Visit `7`.
- **Right Subtree of 10:** Visit `20`, then:
  - **Left Subtree of 20:** Visit `15`.

So the **Pre-order traversal output** is:

```
Pre-order Traversal:
10 5 3 7 20 15
```

### **Step 6: Post-order Traversal**

Post-order traversal visits nodes in the following order: Left Subtree → Right Subtree → Root.

- **Left Subtree of 10:** Visit `3`, then `7`, then `5`.
- **Right Subtree of 10:** Visit `15`, then `20`.
- **Visit 10**.

So the **Post-order traversal output** is:

```
Post-order Traversal:
3 7 5 15 20 10
```

### **Final Output:**

```
Binary Tree:
    ______10
   /       \
  5         20
 / \       /
3   7     15

Level-order Traversal:
10 5 20 3 7 15

In-order Traversal:
3 5 7 10 15 20

Pre-order Traversal:
10 5 3 7 20 15

Post-order Traversal:
3 7 5 15 20 10
```
