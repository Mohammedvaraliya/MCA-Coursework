# Leetcode Questions Solved on Day 4 Placement Training:

1. **Valid Parenthesis**
2. **Find the Index of the First Occurrence in a String**
3. **Majority Element**
4. **Product of Array Except Self**

## 01. Valid Parenthesis

[LeetCode Problem URL](https://leetcode.com/problems/valid-parentheses/)

Given a string `s` containing just the characters '`(`', '`)`', '`{`', '`}`', '`[`' and '`]`', determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

```bash
Example 1:

Input: s = "()"
Output: true
```

```bash
Example 2:

Input: s = "()[]{}"
Output: true
```

```bash
Example 3:

Input: s = "(]"
Output: false
```

```bash
Example 4:

Input: s = "([])"
Output: true
```

### Explanation

#### Approach Explanation

1.  Why This Approach?

    This is a **classic stack-based problem** where we track opening brackets and match them with closing ones using **Last-In-First-Out (LIFO)** behavior. A stack is the most appropriate data structure for such pattern-based validations where **matching and ordering both matter**.

2.  Problem-Solving Pattern Used

    - **Stack** (LIFO)
    - Sometimes grouped under **greedy** or **simulation** patterns, because we simulate bracket closing in real time using stack operations.

3.  Efficiency of This Approach

    - **Optimal use of space**: We only store unmatched opening brackets.
    - **Early exit**: We return `False` as soon as a mismatch is found, avoiding unnecessary computation.
    - **Clean and readable**: The algorithm is concise and intuitive once the pattern is understood.

#### Step-by-Step Walkthrough

1. Let's walk through a complete example using the second approach (`is_paren_balanced_2nd_approach`) as it is more concise and efficient.

2. Input:

   ```python
   s = "({[]})"
   ```

3. Initial Setup:

   ```python
   stack = []
   matching = {')': '(', '}': '{', ']': '['}
   ```

4. Iteration-wise Breakdown:

   | Index | Character | Action                    | Stack             |
   | ----- | --------- | ------------------------- | ----------------- |
   | 0     | '('       | Open bracket → push       | \['(']            |
   | 1     | '{'       | Open bracket → push       | \['(', '{']       |
   | 2     | '\['      | Open bracket → push       | \['(', '{', '\['] |
   | 3     | ']'       | Closing → match with '\[' | \['(', '{']       |
   | 4     | '}'       | Closing → match with '{'  | \['(']            |
   | 5     | ')'       | Closing → match with '('  | \[]               |

5. Final Check:

   - Stack is empty → ✅ All brackets matched correctly.
   - Return `True`

6. Algorithm

   - If a character is a closing bracket:

   - Check if the last item in the stack matches.
   - If yes, pop the top item.
   - If not, return False.

   - If it’s an opening bracket, push it onto the stack.
   - At the end, if the stack is empty, return True.

#### Time and Space Complexity

| Metric               | Complexity | Explanation                                                                         |
| -------------------- | ---------- | ----------------------------------------------------------------------------------- |
| **Time Complexity**  | $O(n)$     | We traverse the input string once (`n` = number of characters in `s`).              |
| **Space Complexity** | $O(n)$     | In the worst case (e.g., all opening brackets), the stack holds all `n` characters. |

#### Summary

- The use of a **stack** makes this problem straightforward and efficient.
- Handles nesting and mixed types of brackets.
- The second approach is highly efficient in practice due to reduced object overhead compared to a custom class-based stack.

---

---

## 02. Find the Index of the First Occurrence in a String

[LeetCode Problem URL](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/)

Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

```bash
Example 1:

Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
```

```bash
Example 2:

Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.
```

### Explanation

#### Approach Explanation

1. Why This Approach?

   This problem is a **classic substring search**. Instead of relying on Python’s built-in string methods like `str.find()`, we manually implement the logic to understand how pattern matching works at a fundamental level.

   The approach follows the **brute-force pattern-matching** algorithm but is optimized with early exits and substring comparisons, making it intuitive and educational.

2. Problem-Solving Pattern Used

   - **Sliding Window**: A fixed-size window of the length of `needle` is moved over `haystack` to check for matches.
   - **Brute Force Matching**: We iterate through each starting index and check character-by-character if a match exists.
   - **Greedy Comparison**: We stop at the first successful match without further checking.

3. Why This Is Efficient for the Problem

   - The substring is always compared starting from the left to right.
   - Once a match fails, we immediately shift the window to the next index.
   - This is the most direct and readable approach before applying more advanced algorithms like **KMP (Knuth-Morris-Pratt)** for larger datasets or competitive scenarios.

### Step-by-Step Walkthrough

1. Let’s analyze the behavior of the second method: `strStr(haystack, needle)`

2. Input:

   ```python
   haystack = "sadbutsad"
   needle = "sad"
   ```

   - `len(needle) = 3`
   - We will check substrings of length 3 in `haystack` starting from each index.

3. Iteration Details:

   | i   | haystack\[i\:i+len(needle)] | Comparison     | Match?                                   |
   | --- | --------------------------- | -------------- | ---------------------------------------- |
   | 0   | "sad"                       | "sad" == "sad" | ✅ Yes — return 0                        |
   | 1   | "adb"                       | "adb" == "sad" | ❌ No                                    |
   | 2   | "dbu"                       | "dbu" == "sad" | ❌ No                                    |
   | 3   | "but"                       | "but" == "sad" | ❌ No                                    |
   | 4   | "uts"                       | "uts" == "sad" | ❌ No                                    |
   | 5   | "tsa"                       | "tsa" == "sad" | ❌ No                                    |
   | 6   | "sad"                       | "sad" == "sad" | ✅ Yes — but already returned at index 0 |

   > Since we are looking for the first occurrence, we return 0 immediately, So it won't check the other chars.

4. Final Output:

   ```python
   Return value: 0
   ```

---

#### Time and Space Complexity Analysis

| Metric               | Complexity | Explanation                                                                                                   |
| -------------------- | ---------- | ------------------------------------------------------------------------------------------------------------- |
| **Time Complexity**  | $O(n * m)$ | In the worst case, for each of the `n` characters in `haystack`, we compare up to `m` characters in `needle`. |
| **Space Complexity** | $O(1)$     | No extra space used except for a few variables. No additional data structures are used.                       |

> Note: The slicing operation `haystack[i:i+len(needle)]` takes O(m) time internally, so time complexity remains O(n \* m).

#### Summary

- The solution uses a **sliding window** strategy with a substring match to find the first occurrence.
- It is optimal and simple for most real-world input sizes.
- For large-scale or performance-critical applications, consider using more advanced algorithms like **KMP** or **Rabin-Karp**.

---

---

## 03. Majority Element

[LeetCode Problem URL](https://leetcode.com/problems/majority-element/)

Given an array nums of size `n`, return the majority element.

The majority element is the element that appears more than ⌊`n / 2`⌋ times. You may assume that the majority element always exists in the array.

```bash
Example 1:

Input: nums = [3,2,3]
Output: 3
```

```bash
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2
```

### Explanation

---

---

## 04. Product of Array Except Self

[LeetCode Problem URL](https://leetcode.com/problems/product-of-array-except-self/)

Given an integer array nums, return an array `answer` such that `answer[i]` is equal to the product of all the elements of `nums` except `nums[i]`.

The product of any prefix or suffix of `nums` is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in `O(n)` time and without using the division operation.

```bash
Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
```

```bash
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
```

### Explanation

---

---
