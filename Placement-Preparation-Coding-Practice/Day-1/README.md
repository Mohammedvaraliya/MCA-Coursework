# Leetcode Questions Solved on Day 1 Placement Training:

1. **Roman to Interger**
2. **Shuffle String**
3. **Reverse String**
4. **Max Number of Words Found**
5. **Rotate String**

## 1. Roman to Integer

[LeetCode Problem URL](https://leetcode.com/problems/roman-to-integer/)

Roman numerals are represented by seven symbols:

| Symbol | Value |
| ------ | ----- |
| I      | 1     |
| V      | 5     |
| X      | 10    |
| L      | 50    |
| C      | 100   |
| D      | 500   |
| M      | 1000  |

Roman numerals are written from **largest to smallest** from **left to right**. However, when a **smaller value precedes a larger one**, it is **subtracted**. For example:

- `IV = 4` → `5 - 1`
- `IX = 9` → `10 - 1`
- `XL = 40`, `XC = 90`
- `CD = 400`, `CM = 900`

Given a string `s` representing a Roman numeral, return its corresponding **integer value**.

```bash
Example 1:
Input: s = "III"
Output: 3
Explanation: III = 1 + 1 + 1 = 3

Example 2:
Input: s = "LVIII"
Output: 58
Explanation: L = 50, V = 5, III = 3 → 50 + 5 + 3 = 58

Example 3:
Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90, IV = 4 → 1000 + 900 + 90 + 4 = 1994
```

### Explanation

#### Approach: Subtract Smaller Before Larger

We iterate over the Roman numeral string and compare **current value with previous value**:

- If **current > previous**, it means a subtractive pattern (`IV`, `IX`, etc.), so we **subtract twice the previous** because it was already added once.
- Otherwise, we just **add current** to total.

#### Step-by-Step Walkthrough (Example: `"MCMXCIV"`)

Let's walk through the input `s = "MCMXCIV"`:

| Iteration | Char | curr | prev | Condition          | Action                    | total |
| --------- | ---- | ---- | ---- | ------------------ | ------------------------- | ----- |
| 0         | M    | 1000 | 0    | 1000 > 0 (True)    | total += 1000 - 0         | 1000  |
| 1         | C    | 100  | 1000 | 100 < 1000 (False) | total += 100              | 1100  |
| 2         | M    | 1000 | 100  | 1000 > 100 (True)  | total += 1000 - 2×100=800 | 1900  |
| 3         | X    | 10   | 1000 | 10 < 1000 (False)  | total += 10               | 1910  |
| 4         | C    | 100  | 10   | 100 > 10 (True)    | total += 100 - 2×10=80    | 1990  |
| 5         | I    | 1    | 100  | 1 < 100 (False)    | total += 1                | 1991  |
| 6         | V    | 5    | 1    | 5 > 1 (True)       | total += 5 - 2×1 = 3      | 1994  |

**Final Output:** `1994`

#### Time and Space Complexity

| Metric           | Value    | Explanation                                            |
| ---------------- | -------- | ------------------------------------------------------ |
| Time Complexity  | **O(n)** | Single pass over string `s`                            |
| Space Complexity | **O(1)** | Only constant extra space for dictionary and variables |

#### Why This Approach Works

- We avoid using multiple conditionals for specific substrings (`"IV"`, `"CM"`, etc.).
- Instead, we generalize the rule: _If a smaller numeral comes before a larger one, subtract it twice_ (since we already added it once).
- This leads to a **clean, readable**, and **efficient** $O(n)$ solution.

#### Pattern Recognition for Similar Problems

This pattern can be used in problems involving:

- **Symbol-to-value mappings**
- **Comparing adjacent elements** (current vs. previous/next)
- Situations where **implicit rules** (like subtraction when a smaller comes before a larger) affect logic

Look for:

- Mapping structures (`dict`)
- Iteration with state tracking (`prev`)
- Conditional logic involving previous and current values

---

---

## 2. Shuffle String

[Leetcode Problem URL](https://leetcode.com/problems/shuffle-string/)

You are given a string s and an integer array indices of the same length. The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.

Return the shuffled string.

![Example1](https://assets.leetcode.com/uploads/2020/07/09/q1.jpg)

```bash
Example 1:

Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
Output: "leetcode"
Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
```

```bash
Example 2:

Input: s = "abc", indices = [0,1,2]
Output: "abc"
Explanation: After shuffling, each character remains in its position.
```

### Explanation

---

---

## 3. Reverse String

[Leetcode Problem URL](https://leetcode.com/problems/reverse-string/)

Write a function that reverses a string. The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.

```bash
Example 1:

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
```

```bash
Example 2:

Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
```

### Explanation

---

---

## 4. Maximum Number of Words Found in Sentences

[Leetcode Problem URL](https://leetcode.com/problems/maximum-number-of-words-found-in-sentences/)

A sentence is a list of words that are separated by a single space with no leading or trailing spaces.

You are given an array of strings sentences, where each sentences[i] represents a single sentence.

Return the maximum number of words that appear in a single sentence.

```bash
Example 1:

Input: sentences = ["alice and bob love leetcode", "i think so too", "this is great thanks very much"]
Output: 6
Explanation:
- The first sentence, "alice and bob love leetcode", has 5 words in total.
- The second sentence, "i think so too", has 4 words in total.
- The third sentence, "this is great thanks very much", has 6 words in total.
Thus, the maximum number of words in a single sentence comes from the third sentence, which has 6 words.
```

```bash
Example 2:

Input: sentences = ["please wait", "continue to fight", "continue to win"]
Output: 3
Explanation: It is possible that multiple sentences contain the same number of words.
In this example, the second and third sentences (underlined) have the same number of words.
```

### Explanation

---

---

## 5. Rotate String

[Leetcode Problem URL](https://leetcode.com/problems/rotate-string/)

Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.

A shift on s consists of moving the leftmost character of s to the rightmost position.

For example, if s = "abcde", then it will be "bcdea" after one shift.

```bash
Example 1:

Input: s = "abcde", goal = "cdeab"
Output: true
```

```bash
Example 2:

Input: s = "abcde", goal = "abced"
Output: false
```

### Explanation

---

---
