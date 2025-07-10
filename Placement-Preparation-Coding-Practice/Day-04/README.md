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
