# Heap Sort Explanation

## Problem Statement

### **Aim**:

To implement Heap Sort using the concept of Heapify in Python, arranging elements in ascending order by repeatedly building a max heap and extracting the root.

### **Concept**:

Heap Sort is a comparison-based sorting technique based on a binary heap data structure. A **binary heap** is a complete binary tree where each node satisfies the heap property:

1. **Max Heap**: Each parent node is greater than or equal to its children.
2. **Min Heap**: Each parent node is less than or equal to its children.

We use a Max Heap to sort the array in ascending order. The **Heapify** function is used to maintain the max heap property. In each iteration, the largest element (root) is swapped to the end of the array, and the heap size is reduced, repeating this process to sort the array.

### Code Breakdown

#### 1. `heapify(arr, n, i)` function:

This function is used to maintain the heap property (a binary tree property) of the array. It is called recursively to fix the heap structure starting from a specific index `i`.

- **Parameters**:

  - `arr`: The array that is being heapified.
  - `n`: The size of the heap (which is the length of the array or a portion of it).
  - `i`: The index in the array from which we start checking to restore the heap property.

- **Steps inside `heapify`**:
  1. **Initialize** `largest = i`: Assume the current element is the largest.
  2. **Find the left and right children** of the element at index `i`:
     - `left = 2 * i + 1`
     - `right = 2 * i + 2`
  3. **Compare the left child** with the current largest element:
     - If the left child exists and is greater than the current largest element, set `largest = left`.
  4. **Compare the right child** with the current largest element:
     - If the right child exists and is greater than the current largest element, set `largest = right`.
  5. **If the largest element is not the current element** (`i`):
     - Swap the current element with the largest element.
     - **Recursively call** `heapify` on the affected subtree (which may have been swapped).

#### 2. `heap_sort(arr)` function:

This is the main function for sorting the array using the heap sort algorithm.

- **Steps inside `heap_sort`**:

  1. **Building the Max Heap**:

     - To sort the array, we first need to build a max-heap.
     - A max-heap is a binary tree where the value of each node is greater than or equal to its children, ensuring that the root node is the largest element.

     - The max-heap is built by calling `heapify` for each non-leaf node, starting from the last non-leaf node and moving upwards to the root.

     - This step ensures that the array satisfies the heap property, with the largest element at the root.

  2. **Why do we start from the middle of the array?**

     - A binary tree is a complete binary tree, and for an array representation of a binary tree, **the last non-leaf node** (starting from index `n//2 - 1`) is the largest index that has children. This is because nodes beyond `n//2` are leaf nodes (they have no children).
     - Starting from the last non-leaf node ensures that we first work on nodes that have children and adjust their subtrees before adjusting the higher-level nodes (closer to the root). This is the most efficient way to build the heap because each call to `heapify` adjusts the heap locally and moves upwards to the root.

  3. **Sorting the array**:
     - After the max-heap is built, the largest element (root of the heap) is at index 0.
     - We swap the root (largest element) with the last element of the array, reducing the heap size by 1 (`i` decreases by 1 after each iteration).
     - We then call `heapify` again on the new root to ensure the remaining elements still satisfy the heap property.
     - This process continues until the heap size is reduced to 1 (when only one element is left).

### Walkthrough with the Example:

```python
arr = [50, 30, 20, 15, 10, 8, 16]
```

### Step 1: `heap_sort()` - Building the Max Heap

We start by building a **max heap** from the input array. A **max heap** is a complete binary tree where every parent node is greater than or equal to its children. The largest value should be at the root (index 0).

#### Step 1a: Starting from the last non-leaf node

The length of the array is `7`, so the last non-leaf node is at index `n // 2 - 1`, which is:

```python
n // 2 - 1 = 7 // 2 - 1 = 2
```

We start heapifying from index `2` (the node with value `20`) and move upwards.

#### Step 1b: Heapifying from index `2` (value 20)

- **Left child** (index 5): value `8`
- **Right child** (index 6): value `16`
- The current node value is `20`.
- Since both children (`8` and `16`) are smaller than `20`, **no swapping is needed**.

The array remains unchanged:

```python
[50, 30, 20, 15, 10, 8, 16]
```

#### Step 1c: Heapifying from index `1` (value 30)

- **Left child** (index 3): value `15`
- **Right child** (index 4): value `10`
- The current node value is `30`.
- Both children (`15` and `10`) are smaller than `30`, so **no swapping is needed**.

The array remains unchanged:

```python
[50, 30, 20, 15, 10, 8, 16]
```

#### Step 1d: Heapifying from index `0` (value 50)

- **Left child** (index 1): value `30`
- **Right child** (index 2): value `20`
- The current node value is `50`.
- Both children (`30` and `20`) are smaller than `50`, so **no swapping is needed**.

At this point, the array is already a valid max heap:

```python
[50, 30, 20, 15, 10, 8, 16]
```

### Step 2: Sorting the Array

Once the max heap is built, the root of the heap (index 0) contains the largest element. To sort the array in ascending order, we repeatedly swap the root with the last element in the unsorted portion of the array and reduce the heap size by one. After each swap, we call `heapify()` on the root to maintain the max heap property.

#### Step 2a: Swap root with last element (index 6)

- **Swap root (50)** with **last element (16)**.
- Array after the swap:
  ```python
  [16, 30, 20, 15, 10, 8, 50]
  ```
- Now, the heap size is reduced to `6` (because we removed `50`).

#### Step 2b: Heapify the root (index 0) after swap

- **Left child** (index 1): value `30`
- **Right child** (index 2): value `20`
- The current root is `16`.
- The largest child is `30` (left child), so swap `16` with `30`.
- Array after the swap:
  ```python
  [30, 16, 20, 15, 10, 8, 50]
  ```

Now we need to heapify the subtree rooted at index `1`.

- **Left child** (index 3): value `15`
- **Right child** (index 4): value `10`
- The current node is `16`.
- The largest child is `15` (left child), so swap `16` with `15`.
- Array after the swap:
  ```python
  [30, 15, 20, 16, 10, 8, 50]
  ```

Now, we need to heapify the subtree rooted at index `3` (which has value `16`).

- **Left child** (index 7) and **Right child** (index 8) are out of bounds, so **no further swaps** are needed.

After heapifying, the array is:

```python
[30, 15, 20, 16, 10, 8, 50]
```

#### Step 2c: Swap root with last element (index 5)

- **Swap root (30)** with **last element (8)**.
- Array after the swap:
  ```python
  [8, 15, 20, 16, 10, 30, 50]
  ```
- Heap size is reduced to `5`.

#### Step 2d: Heapify the root (index 0) after swap

- **Left child** (index 1): value `15`
- **Right child** (index 2): value `20`
- The current root is `8`.
- The largest child is `20` (right child), so swap `8` with `20`.
- Array after the swap:
  ```python
  [20, 15, 8, 16, 10, 30, 50]
  ```

Now, we need to heapify the subtree rooted at index `2` (which has value `8`).

- **Left child** (index 5) and **Right child** (index 6) are out of bounds, so **no further swaps** are needed.

After heapifying, the array is:

```python
[20, 15, 8, 16, 10, 30, 50]
```

#### Step 2e: Swap root with last element (index 4)

- **Swap root (20)** with **last element (10)**.
- Array after the swap:
  ```python
  [10, 15, 8, 16, 20, 30, 50]
  ```
- Heap size is reduced to `4`.

#### Step 2f: Heapify the root (index 0) after swap

- **Left child** (index 1): value `15`
- **Right child** (index 2): value `8`
- The current root is `10`.
- The largest child is `15` (left child), so swap `10` with `15`.
- Array after the swap:
  ```python
  [15, 10, 8, 16, 20, 30, 50]
  ```

Now, we need to heapify the subtree rooted at index `1` (which has value `10`).

- **Left child** (index 3): value `16`
- **Right child** (index 4) is out of bounds, so the largest child is `16`.
- Swap `10` with `16`.
- Array after the swap:
  ```python
  [15, 16, 8, 10, 20, 30, 50]
  ```

Now, we need to heapify the subtree rooted at index `3` (which has value `10`).

- **Left child** (index 7) and **Right child** (index 8) are out of bounds, so **no further swaps** are needed.

After heapifying, the array is:

```python
[15, 16, 8, 10, 20, 30, 50]
```

#### Step 2g: Swap root with last element (index 3)

- **Swap root (15)** with **last element (10)**.
- Array after the swap:
  ```python
  [10, 16, 8, 15, 20, 30, 50]
  ```
- Heap size is reduced to `3`.

#### Step 2h: Heapify the root (index 0) after swap

- **Left child** (index 1): value `16`
- **Right child** (index 2): value `8`
- The current root is `10`.
- The largest child is `16` (left child), so swap `10` with `16`.
- Array after the swap:
  ```python
  [16, 10, 8, 15, 20, 30, 50]
  ```

Now, we need to heapify the subtree rooted at index `1` (which has value `10`).

- **Left child** (index 3) and **Right child** (index 4) are out of bounds, so **no further swaps** are needed.

After heapifying, the array is:

```python
[16, 10, 8, 15, 20, 30, 50]
```

#### Step 2i: Swap root with last element (index 2)

- **Swap root (16)** with **last element (8)**.
- Array after the swap:
  ```python
  [8, 10, 16, 15, 20, 30, 50]
  ```
- Heap size is reduced to `2`.

#### Step 2j: Heapify the root (

index 0) after swap

- **Left child** (index 1): value `10`
- **Right child** (index 2) is out of bounds, so the largest child is `10`.
- Swap `8` with `10`.
- Array after the swap:
  ```python
  [10, 8, 16, 15, 20, 30, 50]
  ```

Now, we need to heapify the subtree rooted at index `1` (which has value `8`).

- **Left child** (index 3) and **Right child** (index 4) are out of bounds, so **no further swaps** are needed.

After heapifying, the array is:

```python
[10, 8, 16, 15, 20, 30, 50]
```

### Step 3: Final Sorted Array

Now, only one element (`10`) remains in the heap, and the array is fully sorted:

```python
[8, 10, 15, 16, 20, 30, 50]
```
