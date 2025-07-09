# Leetcode Questions Solved on Day 2 Placement Training:

1. **Move Zeroes**
2. **Subarray Sum Equals K**
3. **Sort Array By Parity**
4. **Remove Duplicates from Sorted Array**
5. **Remove Element**

## 01. Move Zeroes

[LeetCode Problem URL](https://leetcode.com/problems/move-zeroes/)

Given an integer array `nums`, move all `0`'s to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

```bash
Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
```

```bash
Example 2:

Input: nums = [0]
Output: [0]
```

### Explanation

#### Approach Explanation

1. **Overview**

   This solution uses a **two-pointer** or **in-place overwrite** strategy to maintain the relative ordering of non-zero elements and move all zeroes to the end.

2. **Why This Approach Was Chosen**

   - **In-place requirement:** The problem specifically asks not to use extra space for another array. Hence, an in-place technique is needed.
   - **Order preservation:** Unlike some greedy or swap-based techniques that may reorder elements, this approach **preserves the relative order** of all non-zero elements.
   - **Efficiency:** The approach ensures only a single pass to shift non-zeroes, and a second short pass to fill trailing zeros. This ensures **O(n)** time complexity with minimal operations.

3. **Underlying Pattern**

   This is a classic example of the **Two-Pointer Pattern**:

   - One pointer (`i`) iterates through the array to explore elements.
   - Another pointer (`non_zero_pos`) tracks the position to place the next non-zero value.

#### Step-by-Step Walkthrough

1. Let us walk through the example:

   ```python
   Input: nums = [0, 1, 0, 3, 12]
   ```

2. Initialization:

   - `non_zero_pos = 0`
     This will point to the index where the next non-zero value should be placed.

3. First Loop: Shift Non-Zero Elements to the Front

   | Iteration | i   | nums\[i] | Action                            | nums               | non_zero_pos |
   | --------- | --- | -------- | --------------------------------- | ------------------ | ------------ |
   | 0         | 0   | 0        | Skip (since nums\[0] is 0)        | \[0, 1, 0, 3, 12]  | 0            |
   | 1         | 1   | 1        | nums\[0] = 1; `non_zero_pos` → 1  | \[1, 1, 0, 3, 12]  | 1            |
   | 2         | 2   | 0        | Skip                              | \[1, 1, 0, 3, 12]  | 1            |
   | 3         | 3   | 3        | nums\[1] = 3; `non_zero_pos` → 2  | \[1, 3, 0, 3, 12]  | 2            |
   | 4         | 4   | 12       | nums\[2] = 12; `non_zero_pos` → 3 | \[1, 3, 12, 3, 12] | 3            |

   Now, non-zero values have been pushed forward:
   Intermediate array: `[1, 3, 12, 3, 12]`

4. Second Loop: Fill Remaining Positions with Zeroes

   Start filling from index `non_zero_pos = 3` till the end.

   | Iteration | i   | Action       | nums               |
   | --------- | --- | ------------ | ------------------ |
   | 0         | 3   | nums\[3] = 0 | \[1, 3, 12, 0, 12] |
   | 1         | 4   | nums\[4] = 0 | \[1, 3, 12, 0, 0]  |

   Final Output: `[1, 3, 12, 0, 0]`

#### Time and Space Complexity

1. **Time Complexity: O(n)**

   - First pass (moving non-zeroes): O(n)
   - Second pass (filling zeroes): O(n)
   - Overall: **O(n)** where `n` is the number of elements in the input array.

2. **Space Complexity: O(1)**

   - The entire operation is performed **in-place**, using only constant extra variables (`non_zero_pos` and `i`).
   - No additional space is allocated based on input size.

### Strengths of This Solution:

- Fully in-place and maintains relative order.
- Efficient in both time and space.
- Simple to understand and implement.

#### When to Use This Pattern:

- Problems that require modifying an array **in-place**.
- When order of certain elements needs to be preserved while filtering or shifting.
- When we want to separate or push specific elements (like `0`s here) to the start or end of an array.

---

---

## 02. Shuffle String

[Leetcode Problem URL](https://leetcode.com/problems/subarray-sum-equals-k/)

Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.

```bash
Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
```

```bash
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
```

### Explanation

---

---

## 03. Sort Array By Parity

[Leetcode Problem URL](https://leetcode.com/problems/sort-array-by-parity/)

Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.

Return any array that satisfies this condition.

```bash
Example 1:

Input: nums = [3,1,2,4]
Output: [2,4,3,1]
Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
```

```bash
Example 2:

Input: nums = [0]
Output: [0]
```

### Explanation

---

---

## 04. Remove Duplicates from Sorted Array

[Leetcode Problem URL](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

- Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
- Return k.

**Custom Judge:**

The judge will test your solution with the following code:

```bash
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
```

If all assertions pass, then your solution will be accepted.

```bash
Example 1:

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
```

```bash
Example 2:

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
```

### Explanation

---

---

## 05. Remove Element

[Leetcode Problem URL](https://leetcode.com/problems/remove-element/)

Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
Return k.

**Custom Judge:**

The judge will test your solution with the following code:

```bash
int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
```

If all assertions pass, then your solution will be accepted.

```bash
Example 1:

Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).
```

```bash
Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).
```

### Explanation

---

---
