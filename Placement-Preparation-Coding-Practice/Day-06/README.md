# Leetcode Questions Solved on Day 4 Placement Training:

1. **Kth Largest Element in an Array**

## 01. Kth Largest Element in an Array

[LeetCode Problem URL](https://leetcode.com/problems/kth-largest-element-in-an-array/)

Given an integer array `nums` and an integer `k`, return the `kth` largest element in the array.

Note that it is the `kth` largest element in the sorted order, not the `kth` distinct element.

Can you solve it without sorting?

```bash
Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
```

```bash
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
```

### Explanation

To solve this problem without sorting the array, I've used min-heap (also known as a priority queue) to efficiently find the kth largest element. Here’s how we can do it:

#### Approach Explanation

1. Why This Approach?

   The naive solution would be to **sort the entire array** and return the kᵗʰ largest element. This is simple but not optimal for large datasets, especially when sorting isn't necessary to get just one element.

   A better approach uses a **Min Heap** of size `k` to efficiently track the k largest elements seen so far. This allows us to extract the kᵗʰ largest element without sorting the entire array.

2. Problem-Solving Pattern Used

   - **Heap (Priority Queue)** pattern.
   - This is a classic use-case for a **Min Heap** where we maintain the top `k` largest elements.
   - Python's `heapq` module implements a min-heap by default.

3. Why This Is Efficient and Elegant

   - Instead of sorting `n` elements (which is O(n log n)), we only keep the top `k` elements in the heap.
   - Each insertion and removal operation from the heap takes O(log k) time.
   - Hence, this solution is far more scalable for large arrays.

#### Step-by-Step Walkthrough

1. Let's walk through the example:

   ```python
   nums = [3, 2, 3, 1, 2, 4, 5, 5, 6], k = 4
   ```

   We use a **min heap** (`heap[]`) to store the largest `k = 4` elements. The smallest among them (at `heap[0]`) will be the 4ᵗʰ largest overall.

1. Iterations:

   1. `num = 3` → heap = \[3]
   2. `num = 2` → heap = \[2, 3]
   3. `num = 3` → heap = \[2, 3, 3]
   4. `num = 1` → heap = \[1, 2, 3, 3]
   5. `num = 2` → heap = \[1, 2, 3, 3, 2] → size > k ⇒ pop smallest ⇒ heap = \[2, 2, 3, 3]
   6. `num = 4` → heap = \[2, 2, 3, 3, 4] ⇒ size > k ⇒ pop ⇒ heap = \[2, 3, 3, 4]
   7. `num = 5` → heap = \[2, 3, 3, 4, 5] ⇒ pop ⇒ heap = \[3, 3, 5, 4]
   8. `num = 5` → heap = \[3, 3, 5, 4, 5] ⇒ pop ⇒ heap = \[3, 4, 5, 5]
   9. `num = 6` → heap = \[3, 4, 5, 5, 6] ⇒ pop ⇒ heap = \[4, 5, 5, 6]

1. Final Heap:

   ```python
   [4, 5, 5, 6]
   ```

   - The smallest of the top 4 elements is `heap[0] = 4`, which is the **4ᵗʰ largest element**.

#### Time and Space Complexity Analysis

| Method                      | Time Complexity  | Space Complexity | Notes                                                          |
| --------------------------- | ---------------- | ---------------- | -------------------------------------------------------------- |
| Sorting                     | $O(n \ log \ n)$ | $O(1)$ or $O(n)$ | Built-in sort; space depends on language's sort implementation |
| Min Heap (Optimal Approach) | $O(n \ log \ k)$ | $O(k)$           | Maintains a heap of size `k` throughout the traversal of array |

#### Summary

- The Min Heap approach efficiently solves the problem without sorting the entire array.
- It works well even when the input is large and only a small portion of the result is needed (the kᵗʰ largest).
- Clean and effective, this method is widely used in top-k problems, streaming data, and real-time analytics.

---

---

## 02. Container With Most Water

[LeetCode Problem URL](https://leetcode.com/problems/container-with-most-water/)

You are given an integer array `height` of length `n`. There are n vertical lines drawn such that the two endpoints of the `ith` line are (i, 0) and (`i, height[i]`).

Find two lines that together with the `x-axis` form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

![IMG](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/17/question_11.jpg)

```bash
Example 1:

Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
```

```bash
Example 2:

Input: height = [1,1]
Output: 1
```

### Explanation

---

---
