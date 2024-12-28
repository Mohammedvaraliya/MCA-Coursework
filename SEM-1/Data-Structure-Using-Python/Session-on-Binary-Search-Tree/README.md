# Binary Search Tree Explanation

### **Example Walkthrough of the Binary Search Tree Operations Program:**

This program implements a **Binary Search Tree (BST)** with various operations, including:

1. Insertion (both recursive and iterative).
2. Search (both recursive and iterative).
3. Deletion (both recursive and iterative).
4. Traversals (in-order, pre-order, post-order, level-order).

#### **Step 1: Insertion into the Binary Search Tree**

Let's insert the following values into the BST: `30, 20, 40, 10, 25, 35, 50`.

1. **Insert 30:**
   - The BST is empty, so the root node becomes `30`.
2. **Insert 20:**

   - The value `20` is less than `30`, so it becomes the left child of `30`.

3. **Insert 40:**

   - The value `40` is greater than `30`, so it becomes the right child of `30`.

4. **Insert 10:**

   - The value `10` is less than `30` and also less than `20`, so it becomes the left child of `20`.

5. **Insert 25:**

   - The value `25` is less than `30` but greater than `20`, so it becomes the right child of `20`.

6. **Insert 35:**

   - The value `35` is greater than `30` and less than `40`, so it becomes the left child of `40`.

7. **Insert 50:**
   - The value `50` is greater than `30` and also greater than `40`, so it becomes the right child of `40`.

After inserting all values, the tree looks like this:

```
        30
       /  \
      20   40
     / \   / \
    10 25 35  50
```

#### **Step 2: In-order Traversal (Recursive)**

- **In-order traversal** visits nodes in the order: Left Subtree → Root → Right Subtree.
- Starting from the root (`30`), it visits nodes in the following order:
  1. Traverse the left subtree of `30`: Visit `10`, `20`, `25`.
  2. Visit `30`.
  3. Traverse the right subtree of `30`: Visit `35`, `40`, `50`.

Thus, the **In-order Traversal** output is:

```
In-order Traversal:
10 20 25 30 35 40 50
```

#### **Step 3: Pre-order Traversal (Recursive)**

- **Pre-order traversal** visits nodes in the order: Root → Left Subtree → Right Subtree.
- Starting from the root (`30`), it visits nodes in the following order:
  1. Visit `30`.
  2. Traverse the left subtree of `30`: Visit `20`, `10`, `25`.
  3. Traverse the right subtree of `30`: Visit `40`, `35`, `50`.

Thus, the **Pre-order Traversal** output is:

```
Pre-order Traversal:
30 20 10 25 40 35 50
```

#### **Step 4: Post-order Traversal (Recursive)**

- **Post-order traversal** visits nodes in the order: Left Subtree → Right Subtree → Root.
- Starting from the root (`30`), it visits nodes in the following order:
  1. Traverse the left subtree of `30`: Visit `10`, `25`, `20`.
  2. Traverse the right subtree of `30`: Visit `35`, `50`, `40`.
  3. Visit `30`.

Thus, the **Post-order Traversal** output is:

```
Post-order Traversal:
10 25 20 35 50 40 30
```

#### **Step 5: Level-order Traversal (Iterative)**

- **Level-order traversal** visits nodes level by level using a queue (breadth-first traversal).
- Starting from the root (`30`), it visits nodes in the following order:
  1. Visit the root (`30`).
  2. Visit the children of `30`: `20` and `40`.
  3. Visit the children of `20` and `40`: `10`, `25`, `35`, and `50`.

Thus, the **Level-order Traversal** output is:

```
Level-order Traversal:
30 20 40 10 25 35 50
```

#### **Step 6: Search Operations (Recursive and Iterative)**

1. **Search for 25 (Recursive):**

   - Starting from the root (`30`), `25` is less than `30`, so we move to the left child (`20`).
   - `25` is greater than `20`, so we move to the right child (`25`).
   - We find `25` in the tree, so the **Search (Recursive)** result is `True`.

2. **Search for 100 (Recursive):**

   - Starting from the root (`30`), `100` is greater than `30`, so we move to the right child (`40`).
   - `100` is greater than `40`, so we move to the right child (`50`).
   - `100` is greater than `50`, but there are no more nodes, so the **Search (Recursive)** result is `False`.

3. **Search for 25 (Iterative):**

   - Similar to the recursive search, but performed iteratively. The **Search (Iterative)** result is `True`.

4. **Search for 100 (Iterative):**
   - Similar to the recursive search, but performed iteratively. The **Search (Iterative)** result is `False`.

#### **Step 7: Deletion Operations (Recursive and Iterative)**

1. **Before Deletion (In-order Traversal):**

   - Perform an **In-order Traversal** before any deletions to show the current state of the tree:

   ```
   In-order Traversal:
   10 20 25 30 35 40 50
   ```

2. **Delete 25 (Recursive):**

   - The value `25` is a leaf node (it has no children), so it is simply removed.
   - The new tree is:

   ```
        30
       /  \
      20   40
     /   \  / \
    10    35  50
   ```

   After deleting `25`, the **In-order Traversal** is:

   ```
   In-order Traversal:
   10 20 30 35 40 50
   ```

3. **Delete 100 (Iterative):**

   - The value `100` does not exist in the tree, so the deletion fails. The tree remains unchanged.
   - The **In-order Traversal** after attempting the deletion is:

   ```
   In-order Traversal:
   10 20 30 35 40 50
   ```

### **Final Output:**

```
Binary Search Tree:
    ______30
   /       \
  20        40
 /  \      /  \
10   25   35   50

Binary Search Tree (In-order Traversal):
10 20 25 30 35 40 50

Binary Search Tree (Pre-order Traversal):
30 20 10 25 40 35 50

Binary Search Tree (Post-order Traversal):
10 25 20 35 50 40 30

Binary Search Tree (Level-order Traversal):
30 20 40 10 25 35 50

Search 25 (Recursive): True
Search 100 (Recursive): False
Search 25 (Iterative): True
Search 100 (Iterative): False

Before Deletion (In-order Traversal):
10 20 25 30 35 40 50

Deleting 25 (Recursive):
10 20 30 35 40 50

Deleting 100 (Iterative):
10 20 30 35 40 50
```
