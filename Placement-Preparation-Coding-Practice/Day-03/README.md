# Leetcode Questions Solved on Day 2 Placement Training:

1. **Find Minimum and Maximum**
2. **Find Second Largest Element**
3. **Plus One**

## 01. Find Minimum and Maximum

Given an array, the task is to find the maximum and the minimum element of the array using the minimum number of comparisons.

```bash
Examples:

Input: arr[] = {3, 5, 4, 1, 9}
Output:
Minimum element is: 1
Maximum element is: 9
```

```bash
Input: arr[] = {22, 14, 8, 17, 35, 3}
Output:
Minimum element is: 3
Maximum element is: 35
```

### Explanation

#### Approach Explanation

1. **Chosen Strategy**

   - The solution uses a **single linear traversal** to simultaneously track both the minimum and maximum values in the array.
   - At each iteration, we compare the current number with the current `min_val` and `max_val`, updating them as necessary.

2. **Why This Approach?**

   - The approach is straightforward and efficient for this problem.
   - Instead of sorting the array or doing multiple passes (which would be inefficient), we find both values in one traversal using only **2 comparisons per element**.

3. **Problem-Solving Pattern**

   - This approach follows the **greedy pattern** and also leverages the **iterative traversal** technique.
   - It greedily updates the current best-known minimum and maximum as it scans the array from left to right.

4. **Efficiency and Elegance**

   - **Efficiency**: This method performs exactly `2(n-1)` comparisons in the worst case (where `n` is the length of the array).
   - **Elegance**: It's simple, readable, and does not rely on auxiliary data structures.

#### Step-by-Step Walkthrough

1. Let’s walk through the code using the input:

   ```python
   nums = [5, 3, 9, 2, 8]
   ```

2. Initial Setup

   - `min_val = +∞`
   - `max_val = -∞`

3. Iteration Breakdown

   | Iteration | Current `n` | Comparison with `max_val` | Update `max_val`? | Comparison with `min_val` | Update `min_val`? |
   | --------- | ----------- | ------------------------- | ----------------- | ------------------------- | ----------------- |
   | 1         | 5           | 5 > -∞ → true             | max_val = 5       | 5 < ∞ → true              | min_val = 5       |
   | 2         | 3           | 3 > 5 → false             | no                | 3 < 5 → true              | min_val = 3       |
   | 3         | 9           | 9 > 5 → true              | max_val = 9       | 9 < 3 → false             | no                |
   | 4         | 2           | 2 > 9 → false             | no                | 2 < 3 → true              | min_val = 2       |
   | 5         | 8           | 8 > 9 → false             | no                | 8 < 2 → false             | no                |

4. Final Output

   - After all iterations:

   - `min_val = 2`
   - `max_val = 9`

   - Output: `"Maximum is 9, and minimum is 2"`

#### Time and Space Complexity Analysis

| Complexity Type  | Value    | Explanation                                                                  |
| ---------------- | -------- | ---------------------------------------------------------------------------- |
| Time Complexity  | **O(n)** | We traverse the array once, making 2 comparisons per element (except first). |
| Space Complexity | **O(1)** | Constant extra space is used regardless of input size.                       |

#### Summary

- This implementation finds both the minimum and maximum in a **single pass** through the array.
- It is both time-efficient and space-efficient, using a simple greedy strategy.
- No auxiliary space or complex logic is needed, making it ideal for real-world applications where performance and clarity are equally important.

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

1.  Why This Approach Was Chosen

    - Instead of converting the entire array to a number (which could lead to overflow in some languages), we use an **in-place digit-level carry propagation** approach. This method is:

    - Efficient.
    - Safe for arbitrarily large integers.
    - Avoids unnecessary conversions or libraries.

2.  Problem-Solving Pattern Used

    - **Reverse Iteration + Carry Handling** (similar to manual addition)
    - This approach is related to the **greedy pattern**, making the locally optimal choice (increase from the end) for a globally correct solution.

3.  How It’s Efficient Compared to Other Methods

    - **Avoids integer overflow** by not converting the entire number.
    - Works **in-place** with constant space.
    - Handles edge cases like multiple trailing 9s naturally.

#### Step-by-Step Walkthrough

1. Let’s consider the input:

   ```python
   digits = [1, 4, 9]
   ```

   We are adding 1 to the number 149.

2. Initial State:

   - Start from the last digit (i = 2)

3. Iteration Breakdown

   | Step | Index (i) | Value at digits\[i] | Action               | digits         |
   | ---- | --------- | ------------------- | -------------------- | -------------- |
   | 1    | 2         | 9                   | 9 → 0, carry over    | \[1, 4, **0**] |
   | 2    | 1         | 4                   | 4 + 1 = 5, stop here | \[1, **5**, 0] |

4. Final Output:

   ```python
   [1, 5, 0]  # Which represents 150
   ```

5. Edge Case: All Nines

   For `digits = [9, 9, 9]`, you would get:

   | Step | Index | Value     | Action          | digits     |
   | ---- | ----- | --------- | --------------- | ---------- |
   | 1    | 2     | 9         | → 0, carry over | \[9, 9, 0] |
   | 2    | 1     | 9         | → 0, carry over | \[9, 0, 0] |
   | 3    | 0     | 9         | → 0, carry over | \[0, 0, 0] |
   | 4    | -     | prepend 1 | \[1, 0, 0, 0]   |            |

#### Time and Space Complexity

| Metric               | Complexity | Explanation                                                                                                                         |
| -------------------- | ---------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| **Time Complexity**  | $O(n)$     | Traverse the list once from right to left (worst case: all 9s).                                                                     |
| **Space Complexity** | $O(1)$     | Modifies in-place, with the exception of one inserted digit in worst-case (`[1, 0, 0, 0]`). This does not count as auxiliary space. |

#### Summary

- This is a classic digit manipulation problem.
- Efficient single-pass, constant space solution.
- Mastering this problem improves your understanding of digit-by-digit algorithms (similar to handling carry in addition).

---

---
