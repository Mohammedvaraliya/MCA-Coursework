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

## 02. Subarray Sum Equals K

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

We will use a trick called cumulative sum (think of it like adding up the numbers one by one) and a little memory helper (called a hashmap) to keep track of things we've already added up.

We use a **prefix sum with hashmap** approach to solve this problem in linear time. This is an optimized method that avoids brute-force checking of all subarrays.

#### **Why This Approach Was Chosen**

- A brute-force solution would require checking all subarrays and computing their sums, which leads to **O(n²)** time complexity. This is inefficient for large arrays.
- The prefix sum method allows us to calculate the sum of any subarray in **O(1)** time using previously stored cumulative sums.
- A hashmap helps us efficiently track how many times a specific cumulative sum has occurred, which allows us to count valid subarrays without iterating over them again.

#### **Underlying Pattern**

This solution is based on the **prefix sum** technique combined with **hashing** for fast lookups.

We maintain:

- Cumulative Sum: We keep adding the numbers one by one. Each time we do that, we check if the difference between the current sum (s) and k has appeared before.
- Map: The map remembers all the sums we've seen so far, and how many times we’ve seen them. This helps us check quickly if there's a subarray that sums to k.

The key idea is:

> If `s` is the current cumulative sum and `s - k` has appeared before, then there exists a subarray ending at the current index which sums to `k`.

#### Step-by-Step Walkthrough

1. Let’s walk through this with the input:

   ```python
   nums = [1, 1, 1], k = 2
   ```

2. Initialization:

   - `sumdict = {0: 1}`
     (There's one way to have a sum of 0 before starting) This means we’ve seen a sum of 0 once (before starting).
   - `count = 0`
   - `s = 0` (Running sum)

3. Iteration Details:

   | Iteration | num | s (cumulative sum) | s - k | sumdict lookup | count (subarrays) | sumdict updated        |
   | --------: | --- | ------------------ | ----- | -------------- | ----------------- | ---------------------- |
   |         1 | 1   | 1                  | -1    | not found      | 0                 | `{0:1, 1:1}`           |
   |         2 | 1   | 2                  | 0     | found → +1     | 1                 | `{0:1, 1:1, 2:1}`      |
   |         3 | 1   | 3                  | 1     | found → +1     | 2                 | `{0:1, 1:1, 2:1, 3:1}` |

4. Final Result:

   - `count = 2`

   This means there are **2 subarrays** whose sum is `k = 2`.

   - Subarray `[1,1]` at index 0 to 1
   - Subarray `[1,1]` at index 1 to 2

5. **Example: nums = `[1, 1, 1]`, k = 2**

6. **Start with an empty list** and a running sum of 0.

   - **Map** (`sumdict`): This will keep track of how many times a certain sum has appeared.
   - Start with `{0: 1}`. This means we’ve seen a sum of 0 **once** (before starting).

7. **Start walking through the list:**

8. Step 1: Take the first number, which is `1`.

   - **Running sum (s)**: Add `1` to our current sum, so `s = 1`.
   - **Check if we’ve seen `s - k`** before:

   - We want to know if `s - k = 1 - 2 = -1` has been seen. It hasn’t, so no new subarray found.
   - **Update the map**: We’ve now seen a sum of `1`, so we update the map: `{0: 1, 1: 1}`.
   - No subarray found yet, so `count = 0`.

9. Step 2: Take the second number, which is `1`.

   - **Running sum (s)**: Add `1` again, so `s = 2`.
   - **Check if we’ve seen `s - k`** before:

   - We want to know if `s - k = 2 - 2 = 0` has been seen. Yes! The sum `0` was seen before (at the start).
   - This means there’s a subarray that ends here and has a sum of `2`! The subarray is `[1, 1]`.
   - **Update the map**: Now we’ve seen a sum of `2`, so we update the map: `{0: 1, 1: 1, 2: 1}`.
   - We found **1 subarray** so far, so `count = 1`.

10. Step 3: Take the third number, which is `1`.

    - **Running sum (s)**: Add `1` again, so `s = 3`.
    - **Check if we’ve seen `s - k`** before:

    - We want to know if `s - k = 3 - 2 = 1` has been seen. Yes! The sum `1` was seen before (at step 1).
    - This means there’s another subarray that ends here and has a sum of `2`! The subarray is `[1, 1]`.
    - **Update the map**: Now we’ve seen a sum of `3`, so we update the map: `{0: 1, 1: 1, 2: 1, 3: 1}`.
    - We found **another subarray**. Now, `count = 2`.

11. Final Result:

    We have found **2 subarrays** with a sum of `2`:

    1. Subarray `[1, 1]` at indices 0 to 1
    2. Subarray `[1, 1]` at indices 1 to 2

    So, the output is `2`.

#### Time and Space Complexity Analysis

1. **Time Complexity: O(n)**

   - We iterate over the array exactly once.
   - All operations inside the loop (sum calculation, dictionary lookup and update) are O(1).

2. **Space Complexity: O(n)**

   - In the worst case, all prefix sums are unique, and we store `n` sums in the hashmap.

#### Strengths of This Solution:

- **Optimal Time:** Achieves linear time, a significant improvement over brute-force O(n²).
- **In-place Processing:** Only uses a hashmap for auxiliary storage.
- **Scalable:** Works efficiently even on large arrays (e.g., 10⁵+ elements).

#### When to Use This Pattern:

Look for these cues:

- You are asked to count subarrays or ranges that meet a specific sum or condition.
- Prefix sums or differences are relevant.
- You need an efficient way to search previous computations → use a hashmap.

---

---

## 03. Sort Array By Parity

[Leetcode Problem URL](https://leetcode.com/problems/sort-array-by-parity/)

Given an integer array `nums`, move all the even integers at the beginning of the array followed by all the odd integers.

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

The problem requires rearranging an array so that all **even numbers appear before odd numbers**, maintaining **no specific relative order**. To solve this, two methods are considered:

- **Brute Force Approach**: Use additional space to build a result array.
- **In-Place Two-Pointer Approach**: Modify the array directly using the two-pointer technique.

#### Why This Approach Was Chosen

The in-place **two-pointer approach** was chosen for the final solution because:

- It satisfies the in-place constraint (no extra array used).
- It reduces space complexity from O(n) to **O(1)**.
- It is more **efficient and elegant** than building a new array using two separate loops.
- It avoids unnecessary memory allocation and copying.

#### Problem-Solving Pattern

This approach follows the **Two-Pointer Pattern**, which is commonly used when modifying or partitioning arrays in-place based on a condition.

- One pointer (`left`) moves forward from the start.
- Another pointer (`right`) moves backward from the end.
- When necessary, values are **swapped** to ensure even numbers go to the front and odd numbers go to the back.

#### Step-by-Step Walkthrough

1. Input:

   ```python
   nums = [3, 1, 2, 4]
   ```

2. Initial State:

   - `left = 0`, pointing to `nums[0] = 3`
   - `right = 3`, pointing to `nums[3] = 4`

3. Iteration 1:

   - `nums[left] = 3` → odd
   - `nums[right] = 4` → even
   - Swap `nums[left]` and `nums[right]` → `nums = [4, 1, 2, 3]`
   - Increment `left → 1`, Decrement `right → 2`

4. Iteration 2:

   - `nums[left] = 1` → odd
   - `nums[right] = 2` → even
   - Swap `nums[left]` and `nums[right]` → `nums = [4, 2, 1, 3]`
   - Increment `left → 2`, Decrement `right → 1`

5. Exit Condition:

- `left >= right` (2 ≥ 1), loop ends.

1. Final Output:

   ```python
   [4, 2, 1, 3]
   ```

   Any order that places all even numbers before odd numbers is valid. Therefore, this output is accepted.

#### Time and Space Complexity Analysis

1. Time Complexity: **O(n)**

- Each element is visited at most once.
- Swapping is a constant time operation.

> **Total Time = O(n)**, where `n` is the length of the array.

---

2. Space Complexity: **O(1)**

- No additional space is used except for a few pointers.
- The input array is modified in-place.

> **Total Space = O(1)** constant space.

#### Strengths of the Two-Pointer In-Place Approach:

- Efficient: O(n) time, O(1) space.
- No auxiliary array is needed.
- Easy to implement and understand.
- Well-suited for partitioning problems.

#### When to Use This Pattern:

- When the problem involves rearranging elements based on a condition (e.g., even vs. odd).
- When in-place modification is required or optimal.
- When order of elements is not important, only grouping by condition.

---

---

## 04. Remove Duplicates from Sorted Array

[Leetcode Problem URL](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

Given an integer array `nums` sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in `nums`.

Consider the number of unique elements of `nums` to be `k`, to get accepted, you need to do the following things:

- Change the array `nums` such that the first `k` elements of nums contain the unique elements in the order they were present in `nums` initially. The remaining elements of `nums` are not important as well as the size of `nums`.
- Return `k`.

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

#### Approach Explanation

1. Why This Approach Was Chosen

   This problem is best tackled using a **two-pointer** approach that processes the array in a **single pass** while modifying it in-place. This pattern is optimal because:

   - It respects the in-place constraint.
   - It avoids unnecessary shifting or auxiliary data structures.
   - It allows direct overwriting of duplicates with the next distinct values.

2. Applied Pattern

   - **Two-Pointer Technique**:

   - `prev` pointer keeps track of the position of the **last unique element**.
   - `next` pointer scans through the array, identifying **new unique elements**.

This pattern is frequently used in array transformation problems where you need to "move" or "filter" values efficiently in-place.

3. How This Is More Efficient

   - **Brute force** solutions may require building new arrays or using sets.
   - This solution avoids any additional space while operating in linear time.
   - Each element is visited exactly once, making the approach both efficient and elegant.

### Step-by-Step Walkthrough

1. Input:

   ```python
   nums = [0,0,1,1,1,2,2,3,3,4]
   ```

2. Initial State:

   - `prev = 0`, `next = 1`

3. Iteration-wise Trace:

   | Iteration | `next` Index | `nums[next]` | `nums[prev]` | Action                        | Updated `nums`         | `prev` |
   | --------- | ------------ | ------------ | ------------ | ----------------------------- | ---------------------- | ------ |
   | 1         | 1            | 0            | 0            | Duplicate, skip               | \[0,0,1,1,1,2,2,3,3,4] | 0      |
   | 2         | 2            | 1            | 0            | New unique → move to `prev+1` | \[0,1,1,1,1,2,2,3,3,4] | 1      |
   | 3         | 3            | 1            | 1            | Duplicate, skip               | \[0,1,1,1,1,2,2,3,3,4] | 1      |
   | 4         | 4            | 1            | 1            | Duplicate, skip               | \[0,1,1,1,1,2,2,3,3,4] | 1      |
   | 5         | 5            | 2            | 1            | New unique → move to `prev+1` | \[0,1,2,1,1,2,2,3,3,4] | 2      |
   | 6         | 6            | 2            | 2            | Duplicate, skip               | \[0,1,2,1,1,2,2,3,3,4] | 2      |
   | 7         | 7            | 3            | 2            | New unique → move to `prev+1` | \[0,1,2,3,1,2,2,3,3,4] | 3      |
   | 8         | 8            | 3            | 3            | Duplicate, skip               | \[0,1,2,3,1,2,2,3,3,4] | 3      |
   | 9         | 9            | 4            | 3            | New unique → move to `prev+1` | \[0,1,2,3,4,2,2,3,3,4] | 4      |

4. Final State:

   - `prev = 4`, so total unique elements = `prev + 1 = 5`
   - `nums` becomes: `[0, 1, 2, 3, 4, _, _, _, _, _]`

5. Return Value:

   ```python
   return 5
   ```

### Time and Space Complexity

| Complexity | Value  | Explanation                                         |
| ---------- | ------ | --------------------------------------------------- |
| Time       | $O(n)$ | The array is traversed once.                        |
| Space      | $O(1)$ | No extra space is used; in-place modification only. |

#### Summary

- **Pattern**: Two-pointer (in-place overwrite).
- **Efficiency**: Optimal space and linear time.
- **Mutates input array** to store unique values at the start.

#### Use-Cases for This Pattern

- Removing duplicates in sorted arrays.
- In-place filtering of elements based on conditions.
- Partitioning or compressing data in fixed memory.

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

1. Why This Approach Was Chosen

   The requirement is to modify the array in-place without using extra space, while filtering out a specific value. A **two-pointer technique** provides the cleanest and most optimal way to achieve this by:

   - Keeping track of the position where the next non-`val` element should be placed.
   - Skipping the values that need to be removed.
   - Ensuring we never use extra memory.

2. Problem-Solving Pattern

   This solution follows the **Two-Pointer Technique**, specifically the **fast-slow pointer** or **overwrite pattern**:

   - One pointer (`next`) scans through the array.
   - The other pointer (`cur`) tracks the position to place the next valid value.

   This is a common pattern used in problems like:

   - Removing duplicates
   - Moving zeros
   - Filtering elements in place

3. Why This Is Efficient

   Compared to brute-force approaches that may shift elements or use new lists:

   - This method **minimizes writes**.
   - It maintains **O(1) space** and **linear time**.
   - It works in-place and is suitable even for large arrays.

#### Step-by-Step Walkthrough

1. Input:

   ```python
   nums = [0,1,2,2,3,0,4,2], val = 2
   ```

2. Initial State:

   - `cur = 0` (position to place next valid element)

3. Iteration-wise Trace:

   | Iteration | `next` | `nums[next]` | Action Taken           | Updated `nums`     | `cur` |
   | --------- | ------ | ------------ | ---------------------- | ------------------ | ----- |
   | 0         | 0      | 0            | Valid → place at `cur` | \[0,1,2,2,3,0,4,2] | 1     |
   | 1         | 1      | 1            | Valid → place at `cur` | \[0,1,2,2,3,0,4,2] | 2     |
   | 2         | 2      | 2            | Skip (matches `val`)   | \[0,1,2,2,3,0,4,2] | 2     |
   | 3         | 3      | 2            | Skip                   | \[0,1,2,2,3,0,4,2] | 2     |
   | 4         | 4      | 3            | Valid → place at `cur` | \[0,1,3,2,3,0,4,2] | 3     |
   | 5         | 5      | 0            | Valid → place at `cur` | \[0,1,3,0,3,0,4,2] | 4     |
   | 6         | 6      | 4            | Valid → place at `cur` | \[0,1,3,0,4,0,4,2] | 5     |
   | 7         | 7      | 2            | Skip                   | \[0,1,3,0,4,0,4,2] | 5     |

4. Final Result:

   - Modified `nums`: `[0, 1, 3, 0, 4, _, _, _]`
   - Return value: `5` (count of valid elements)

#### Time and Space Complexity

| Complexity | Value  | Explanation                                   |
| ---------- | ------ | --------------------------------------------- |
| Time       | $O(n)$ | Every element is visited exactly once.        |
| Space      | $O(1)$ | No extra data structures used; in-place only. |

#### Summary

- **Pattern**: Two-pointer overwrite.
- **Efficiency**: Best possible for in-place modification with O(1) space.
- **Simplicity**: Clear and easy to implement.

This method can be generalized for many in-place filtering or transformation problems, making it an essential technique in array manipulation.

---

---
