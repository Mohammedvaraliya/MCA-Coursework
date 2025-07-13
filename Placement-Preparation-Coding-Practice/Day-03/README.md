# Leetcode Questions Solved on Day 2 Placement Training:

1. **Find Minimum and Maximum**
2. **Find Second Largest Element**
3. **Plus One**

## 01. Find Minimum and Maximum

### Explanation

---

---

## 02. Find Second Largest Element

You have been given an array/list 'ARR' of integers. Your task is to find the second largest element present in the 'ARR'.

Note:

1. Duplicate elements may be present.
2. If no such element is present return -1.

```bash
Example:
Input: Given a sequence of five numbers 2, 4, 5, 6, 8.
Output:  6
Explanation:
In the given sequence of numbers, number 8 is the largest element, followed by number 6 which is the second-largest element. Hence we return number 6 which is the second-largest element in the sequence.
```

### Explanation

#### Approach Explanation

1. Why This Approach Was Chosen

   The problem requires finding the second-largest element in an unsorted array **without using extra space or sorting**. A **single-pass traversal** is optimal because it avoids the overhead of sorting (`O(n log n)`) and is memory efficient.

2. Problem-Solving Pattern

   This approach is based on a **Greedy + Linear Scan** pattern:

   - **Greedy**: At each step, we keep track of the best (largest) and second-best (second largest) values seen so far.
   - **Linear Scan**: We only make one pass over the array, checking each number once.

#### Step-by-Step Walkthrough

1. Input:

   ```python
   nums = [7, 3, 9, 2, 8]
   ```

1. Initial State:

- `first = -inf` (tracks the largest value seen)
- `second = -inf` (tracks the second largest)

1. Iteration-wise Trace:

   | Iteration | `num` | Condition                             | `first` | `second` |
   | --------- | ----- | ------------------------------------- | ------- | -------- |
   | 1         | 7     | 7 > -inf → update `first`             | 7       | -inf     |
   | 2         | 3     | 3 < 7 → update `second` to 3          | 7       | 3        |
   | 3         | 9     | 9 > 7 → `second = first`, `first = 9` | 9       | 7        |
   | 4         | 2     | 2 < 7 → no update                     | 9       | 7        |
   | 5         | 8     | 8 < 9 and > 7 → update `second = 8`   | 9       | 8        |

1. Final Result:

   - `first = 9`
   - `second = 8`

1. Output:

   ```python
   return second  # → 8
   ```

#### Alternative (Brute Force Approach)

Sorting the array in descending order and picking the first distinct element that is less than the maximum.
**Drawback**: Requires extra time (`O(n log n)`) and potentially extra space.

#### Time and Space Complexity

| Metric           | Complexity | Explanation                                                          |
| ---------------- | ---------- | -------------------------------------------------------------------- |
| Time Complexity  | $O(n)$     | Single loop through the array to find the largest and second largest |
| Space Complexity | $O(1)$     | No additional data structures used                                   |

#### Summary

- Efficient single-pass solution.
- No extra space used.
- Covers edge cases like duplicates and very small arrays.
- A must-know pattern for problems involving **top-k** elements.

---

---

## 03. Plus One

[Leetcode Problem URL](https://leetcode.com/problems/plus-one/)

You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.

```bash
Example 1:

Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].
```

```bash
Example 2:

Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].
```

```bash
Example 3:

Input: digits = [9]
Output: [1,0]
Explanation: The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].
```

### Explanation

---

---
