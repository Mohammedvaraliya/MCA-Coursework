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

#### Approach Explanation

1. Why This Approach?

   The brute-force solution checks every possible pair of lines to find the maximum area, which is inefficient for large inputs.

   To solve this optimally, we use the **Two Pointer Technique**, which enables us to solve the problem in linear time. This is both efficient and elegant, avoiding unnecessary computations.

2. Pattern Used

   - **Two Pointer Technique**
   - The left and right pointers start at both ends of the array and move toward each other, shrinking the width and choosing the direction based on the shorter height to maximize area potential.

3. Why This Is Efficient

   - It avoids nested loops (as in brute-force).
   - It guarantees every potential widest container is evaluated with at least one pointer moved.
   - Reduces time complexity from $O(n²)$ to $O(n)$, which is optimal for this problem.

#### Step-by-Step Walkthrough

1. Input:

   ```python
   height = [1,8,6,2,5,4,8,3,7]
   ```

2. Initialization:

   - `left = 0`, `right = 8`, `res = 0`

3. Iterations:

   1. `left = 0`, `right = 8`: min(1,7)=1 → area = 8×1 = 8 → max = 8
      Move left pointer (since height\[0] < height\[8])

   2. `left = 1`, `right = 8`: min(8,7)=7 → area = 7×7 = 49 → max = 49
      Move right pointer (since height\[1] > height\[8])

   3. `left = 1`, `right = 7`: min(8,3)=3 → area = 6×3 = 18 → max = 49
      Move right pointer

   4. `left = 1`, `right = 6`: min(8,8)=8 → area = 5×8 = 40 → max = 49
      Move right pointer

   5. `left = 1`, `right = 5`: min(8,4)=4 → area = 4×4 = 16 → max = 49
      Move right pointer

   6. `left = 1`, `right = 4`: min(8,5)=5 → area = 3×5 = 15 → max = 49
      Move right pointer

   7. `left = 1`, `right = 3`: min(8,2)=2 → area = 2×2 = 4 → max = 49
      Move right pointer

   8. `left = 1`, `right = 2`: min(8,6)=6 → area = 1×6 = 6 → max = 49
      Move right pointer

   9. `left = 1`, `right = 1`: loop ends

4. Final Result:

   ```python
   Maximum area = 49
   ```

#### Time and Space Complexity

| Method                | Time Complexity | Space Complexity | Explanation                     |
| --------------------- | --------------- | ---------------- | ------------------------------- |
| Brute-force           | $O(n²)$         | $O(1)$           | Nested loops to try all pairs   |
| Two-pointer (Optimal) | $O(n)$          | $O(1)$           | Single-pass scan from both ends |

#### Summary

- The brute-force approach checks all pairs, which is inefficient for large inputs.
- The two-pointer approach leverages greedy principles to move the pointer pointing to the smaller height inward, thereby optimizing the area.
- This is a classic and efficient way to solve container problems using the **Two-Pointer Technique**.
- The optimal solution is **linear in time and constant in space**, which is ideal for real-time or large-scale inputs.

---

---

## 03. Arrays: Buy and Sell Stock

[Leetcode Problem URL](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)

You are given an array `prices` where `prices[i]` is the price of a given stock on the `ith` day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return `0`.

```bash
Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
```

```bash
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
```

### Explanation

We will use a **greedy algorithm** to solve this problem efficiently. The goal is to find the maximum profit by tracking the minimum price seen so far and calculating potential profits as we iterate through the prices.

#### Approach Explanation

1. Why This Approach?

   A brute-force method using two nested loops checks every pair of days to find the maximum profit. However, this is inefficient for large inputs.

   Instead, an **efficient one-pass approach** is used where we keep track of:

   - The **minimum price so far**
   - The **maximum profit so far**

   This allows us to evaluate profit opportunities as we scan through the prices **only once**.

2. Pattern Used

   - **Greedy Algorithm**
   - **Single Pass Scan**
   - You make the locally optimal choice (buy at the lowest price so far) in hope that it leads to the global optimum (maximum overall profit).

3. Why This Is Efficient

   - It avoids unnecessary comparisons.
   - It works in linear time and uses constant space.
   - It’s intuitive, making it ideal for both interviews and production code.

#### Step-by-Step Walkthrough

1. Let’s go through the code using this example:

   ```python
   prices = [7, 1, 5, 3, 6, 4]
   ```

2. Initialization:

   - `min_price = 7` (set to prices\[0])
   - `max_profit = 0`

3. Iteration:

   | Day | Price | min_price   | Current Profit (price - min_price) | max_profit  |
   | --- | ----- | ----------- | ---------------------------------- | ----------- |
   | 0   | 7     | 7           | 0                                  | 0           |
   | 1   | 1     | 1 (updated) | 0                                  | 0           |
   | 2   | 5     | 1           | 4                                  | 4 (updated) |
   | 3   | 3     | 1           | 2                                  | 4           |
   | 4   | 6     | 1           | 5                                  | 5 (updated) |
   | 5   | 4     | 1           | 3                                  | 5           |

4. Final Result:

   - **Maximum Profit = 5**

#### Time and Space Complexity

| Method             | Time Complexity | Space Complexity | Explanation                                                 |
| ------------------ | --------------- | ---------------- | ----------------------------------------------------------- |
| Brute-force        | $O(n²)$         | $O(1)$           | Compares all pairs `(i, j)` where `i < j`                   |
| Optimized (Greedy) | $O(n)$          | $O(1)$           | Single scan; tracks min and max profit using constant space |

#### Summary

- The brute-force approach is easy to understand but not suitable for large inputs.
- The optimal solution uses a **greedy approach** to continuously track the lowest buying price and update maximum profit accordingly.
- This solution is **efficient**, **clean**, and **ideal for interview coding problems**.
- Time complexity is **O(n)**, and space complexity is **O(1)**, making it a top-tier solution.

---

---
