## Problem Statement

**Apply the heapify method to build a heap for the following list.**

```
arr = [15, 5, 20, 1, 17, 10, 30]
```

### Walk through of the entire program step-by-step using the array `[15, 5, 20, 1, 17, 10, 30]`.

1. **Initialization**

   1. The program starts by calling the `build_heap` function with the array `arr = [15, 5, 20, 1, 17, 10, 30]`.

      ```python
      arr = [15, 5, 20, 1, 17, 10, 30]
      build_heap(arr)
      ```

2. **`build_heap` function**

   1. The length of the array `n = 7` (since there are 7 elements in the array).
   2. Now, the for-loop inside `build_heap` starts from the last non-leaf node and calls `heapify` on each node, moving upwards toward the root.
   3. The formula to get the last non-leaf node is `n // 2 - 1`. In this case, `n // 2 - 1 = 7 // 2 - 1 = 2`. So, the loop starts from index `2` and decrements to `0`.

      ```python
      for i in range(n//2 - 1, -1, -1):
          heapify(arr, n, i)
      ```

**Heapify process starts:**

1. **First `heapify` call: (i = 2)**

   1. We start with `i = 2`, the value at index `2` is `20`.
   2. **Left child:** `2 * i + 1 = 2 * 2 + 1 = 5` → `arr[5] = 10`
   3. **Right child:** `2 * i + 2 = 2 * 2 + 2 = 6` → `arr[6] = 30`
   4. Now, we compare the value at index `2` (which is `20`) with its left and right children (`10` and `30`).
   5. The right child `30` is greater than `20`, so we swap `20` with `30`.
   6. **After swapping:**

      ```python
      arr = [15, 5, 30, 1, 17, 10, 20]
      ```

   7. Since `20` was swapped with `30`, we've to recursively call `heapify` on index `6` (where `20` is now located). However, since index `6` is a leaf node (no children), the recursive call ends immediately.

2. **Second `heapify` call: (i = 1)**

   1. Next, the loop continues with `i = 1`, the value at index `1` is `5`.
   2. **Left child:** `2 * i + 1 = 2 * 1 + 1 = 3` → `arr[3] = 1`
   3. **Right child:** `2 * i + 2 = 2 * 1 + 2 = 4` → `arr[4] = 17`
   4. Now, we compare the value at index `1` (`5`) with its left and right children (`1` and `17`).
   5. The right child `17` is greater than `5`, so we swap `5` with `17`.
   6. **After swapping:**

      ```python
      arr = [15, 17, 30, 1, 5, 10, 20]
      ```

   7. Since `5` was swapped with `17`, we've to recursively call `heapify` on index `4` (where `5` is now located). However, index `4` is a leaf node, so the recursive call ends immediately.

3. **Third `heapify` call: (i = 0)**

   1. Finally, the loop continues with `i = 0`, the value at index `0` is `15`.
   2. **Left child:** `2 * i + 1 = 2 * 0 + 1 = 1` → `arr[1] = 17`
   3. **Right child:** `2 * i + 2 = 2 * 0 + 2 = 2` → `arr[2] = 30`
   4. Now, we compare the value at index `0` (`15`) with its left and right children (`17` and `30`).
   5. The right child `30` is greater than `15`, so we swap `15` with `30`.
   6. **After swapping:**

      ```python
      arr = [30, 17, 15, 1, 5, 10, 20]
      ```

   7. Since `15` was swapped with `30`, we've to recursively call `heapify` on index `2` (where `15` is now located).

4. **Recursive `heapify` call: (i = 2)**

   1. Now, we call `heapify` on index `2` (where `15` is located).
   2. **Left child:** `2 * i + 1 = 5` → `arr[5] = 10`
   3. **Right child:** `2 * i + 2 = 6` → `arr[6] = 20`
   4. We compare `15` with its children `10` and `20`.
   5. The right child `20` is greater than `15`, so we've to swap `15` with `20`.
   6. **After swapping:**

      ```python
      arr = [30, 17, 20, 1, 5, 10, 15]
      ```

   7. Since `15` is now at index `6`, and index `6` is a leaf node, the recursive call ends.

5. **Final Array**

   1. After all the heapify calls, the array is now a valid **Max-Heap**:

      ```python
      arr = [30, 17, 20, 1, 5, 10, 15]
      ```

   2. This is the final Max-Heap representation of the given list.

---
