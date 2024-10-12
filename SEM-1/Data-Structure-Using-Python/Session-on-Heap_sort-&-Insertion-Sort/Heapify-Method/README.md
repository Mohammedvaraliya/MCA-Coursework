## Problem Statement

**Apply the heapify method to build a heap for the following list.**

```
arr = [15, 5, 20, 1, 17, 10, 30]
```

### Walk through of the entire program step-by-step using the array `[15, 5, 20, 1, 17, 10, 30]`.

1. **Initialization**

   a. The program starts by calling the `build_heap` function with the array `arr = [15, 5, 20, 1, 17, 10, 30]`.

   ```python
   arr = [15, 5, 20, 1, 17, 10, 30]
   build_heap(arr)
   ```

2. **`build_heap` function**

   a. The length of the array `n = 7` (since there are 7 elements in the array).

   b. Now, the for-loop inside `build_heap` starts from the last non-leaf node and calls `heapify` on each node, moving upwards toward the root.

   c. The formula to get the last non-leaf node is `n // 2 - 1`. In this case, `n // 2 - 1 = 7 // 2 - 1 = 2`. So, the loop starts from index `2` and decrements to `0`.

   ```python
   for i in range(n//2 - 1, -1, -1):
       heapify(arr, n, i)
   ```

**Heapify process starts:**

3. **First `heapify` call: (i = 2)**

   a. We start with `i = 2`, the value at index `2` is `20`.

   b. **Left child:** `2 * i + 1 = 2 * 2 + 1 = 5` → `arr[5] = 10`

   c. **Right child:** `2 * i + 2 = 2 * 2 + 2 = 6` → `arr[6] = 30`

   d. Now, we compare the value at index `2` (which is `20`) with its left and right children (`10` and `30`).

   e. The right child `30` is greater than `20`, so we swap `20` with `30`.

   f. **After swapping:**

   ```python
   arr = [15, 5, 30, 1, 17, 10, 20]
   ```

   g. Since `20` was swapped with `30`, we've to recursively call `heapify` on index `6` (where `20` is now located). However, since index `6` is a leaf node (no children), the recursive call ends immediately.

4. **Second `heapify` call: (i = 1)**

   a. Next, the loop continues with `i = 1`, the value at index `1` is `5`.

   b. **Left child:** `2 * i + 1 = 2 * 1 + 1 = 3` → `arr[3] = 1`

   c. **Right child:** `2 * i + 2 = 2 * 1 + 2 = 4` → `arr[4] = 17`

   d. Now, we compare the value at index `1` (`5`) with its left and right children (`1` and `17`).

   e. The right child `17` is greater than `5`, so we swap `5` with `17`.

   f. **After swapping:**

   ```python
   arr = [15, 17, 30, 1, 5, 10, 20]
   ```

   g. Since `5` was swapped with `17`, we've to recursively call `heapify` on index `4` (where `5` is now located). However, index `4` is a leaf node, so the recursive call ends immediately.

5. **Third `heapify` call: (i = 0)**

   a. Finally, the loop continues with `i = 0`, the value at index `0` is `15`.

   b. **Left child:** `2 * i + 1 = 2 * 0 + 1 = 1` → `arr[1] = 17`

   c. **Right child:** `2 * i + 2 = 2 * 0 + 2 = 2` → `arr[2] = 30`

   d. Now, we compare the value at index `0` (`15`) with its left and right children (`17` and `30`).

   e. The right child `30` is greater than `15`, so we swap `15` with `30`.

   f. **After swapping:**

   ```python
   arr = [30, 17, 15, 1, 5, 10, 20]
   ```

   g. Since `15` was swapped with `30`, we've to recursively call `heapify` on index `2` (where `15` is now located).

6. **Recursive `heapify` call: (i = 2)**

   a. Now, we call `heapify` on index `2` (where `15` is located).

   b. **Left child:** `2 * i + 1 = 5` → `arr[5] = 10`

   c. **Right child:** `2 * i + 2 = 6` → `arr[6] = 20`

   d. We compare `15` with its children `10` and `20`.

   e. The right child `20` is greater than `15`, so we've to swap `15` with `20`.

   f. **After swapping:**

   ```python
   arr = [30, 17, 20, 1, 5, 10, 15]
   ```

   g. Since `15` is now at index `6`, and index `6` is a leaf node, the recursive call ends.

7. **Final Array**

   a. After all the heapify calls, the array is now a valid **Max-Heap**:

   ```python
   arr = [30, 17, 20, 1, 5, 10, 15]
   ```

   b. This is the final Max-Heap representation of the given list.
