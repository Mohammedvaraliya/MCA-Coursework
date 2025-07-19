# Leetcode Questions Solved on Day 4 Placement Training:

1. **Missing Number**
2. **Rotate Array**

## 01. Missing Number

[LeetCode Problem URL](https://leetcode.com/problems/missing-number/)

Given an array `nums` containing `n` distinct numbers in the range `[0, n]`, return the only number in the range that is missing from the array.

```bash
Example 1:

Input: nums = [3,0,1]
Output: 2
Explanation:
n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
```

```bash
Example 2:

Input: nums = [0,1]
Output: 2
Explanation:
n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
```

```bash
Example 3:

Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8
Explanation:
n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
```

### Explanation

I've implemented **two efficient approaches** to solve this problem:

1. **Approach 1: Mathematical Sum Formula**

   - The sum of first `n` natural numbers:

     $$
     \text{expected\_sum} = \frac{n \cdot (n + 1)}{2}
     $$

   - Subtract the actual sum of elements in the array from the expected sum:

     $$
     \text{missing\_number} = \text{expected\_sum} - \text{actual\_sum}
     $$

   - But I've used an optimized way:

     $$
     res = n + (0 - nums[0]) + (1 - nums[1]) + \dots + (n-1 - nums[n-1])
     $$

2. **Approach 2: Bit Manipulation (Using XOR)**

   - XOR of a number with itself is 0: `x ^ x = 0`
   - XOR of a number with 0 is the number itself: `x ^ 0 = x`
   - XOR all numbers from 0 to n with all elements in the array:

     $$
     \text{missing} = (0 \oplus 1 \oplus 2 \dots \oplus n) \oplus (\text{nums[0]} \oplus \text{nums[1]} \oplus \dots)
     $$

   - All matching numbers cancel out, leaving only the missing number.

#### Step-by-Step Example Walkthrough

1. We’ll use **Example 3**:

   ```python
   nums = [9,6,4,2,3,5,7,0,1]
   Expected Output: 8
   ```

2. **Walkthrough for Method 1 (Mathematical)**

   - Initial:

     ```
     res = len(nums) = 9
     ```

     | Iteration (i) | nums\[i] | i - nums\[i] | res updated    |
     | ------------- | -------- | ------------ | -------------- |
     | 0             | 9        | 0 - 9 = -9   | 9 + (-9) = 0   |
     | 1             | 6        | 1 - 6 = -5   | 0 + (-5) = -5  |
     | 2             | 4        | 2 - 4 = -2   | -5 + (-2) = -7 |
     | 3             | 2        | 3 - 2 = 1    | -7 + 1 = -6    |
     | 4             | 3        | 4 - 3 = 1    | -6 + 1 = -5    |
     | 5             | 5        | 5 - 5 = 0    | -5 + 0 = -5    |
     | 6             | 7        | 6 - 7 = -1   | -5 + (-1) = -6 |
     | 7             | 0        | 7 - 0 = 7    | -6 + 7 = 1     |
     | 8             | 1        | 8 - 1 = 7    | 1 + 7 = 8      |

   - **Final Answer: 8**

3. **Walkthrough for Method 2 (Bit Manipulation using XOR)**

   - Initial:

     ```
     n = 9
     xorr = 9
     ```

     | Iteration (i) | nums\[i] | i ^ nums\[i] | xorr (updated) |
     | ------------- | -------- | ------------ | -------------- |
     | 0             | 9        | 0 ^ 9 = 9    | 9 ^ 9 = 0      |
     | 1             | 6        | 1 ^ 6 = 7    | 0 ^ 7 = 7      |
     | 2             | 4        | 2 ^ 4 = 6    | 7 ^ 6 = 1      |
     | 3             | 2        | 3 ^ 2 = 1    | 1 ^ 1 = 0      |
     | 4             | 3        | 4 ^ 3 = 7    | 0 ^ 7 = 7      |
     | 5             | 5        | 5 ^ 5 = 0    | 7 ^ 0 = 7      |
     | 6             | 7        | 6 ^ 7 = 1    | 7 ^ 1 = 6      |
     | 7             | 0        | 7 ^ 0 = 7    | 6 ^ 7 = 1      |
     | 8             | 1        | 8 ^ 1 = 9    | 1 ^ 9 = 8      |

   - **Final Answer: 8**

#### Time & Space Complexity Analysis

| Approach       | Time Complexity | Space Complexity | Explanation                             |
| -------------- | --------------- | ---------------- | --------------------------------------- |
| Method 1 (Sum) | $O(n)$          | $O(1)$           | Single loop of size `n`, constant space |
| Method 2 (XOR) | $O(n)$          | $O(1)$           | Single loop with XORs, constant space   |

#### Why These Approaches Are Efficient

1. **Method 1**:

   - Avoids sorting or extra memory
   - Constant-time arithmetic inside loop
   - No need for a full sum formula if prone to overflow

2. **Method 2**:

   - XOR operation is extremely fast and lightweight
   - No risk of integer overflow (which can happen with sum formula)
   - Cancels out duplicates naturally, finding the missing number efficiently

---

---

## 02. Rotate Array

[LeetCode Problem URL](https://leetcode.com/problems/rotate-array/)

Given an integer array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.

```bash
Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
```

```bash
Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
```

### Explanation

---

---
