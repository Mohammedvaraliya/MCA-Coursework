# Leetcode Questions Solved on Day 1 Placement Training:

1. **Roman to Interger**
2. **Shuffle String**
3. **Reverse String**
4. **Max Number of Words Found**
5. **Rotate String**

## 01. Roman to Integer

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
```

```bash
Example 2:
Input: s = "LVIII"
Output: 58
Explanation: L = 50, V = 5, III = 3 → 50 + 5 + 3 = 58
```

```bash
Example 3:
Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90, IV = 4 → 1000 + 900 + 90 + 4 = 1994
```

### Explanation

**Approach: Subtract Smaller Before Larger**

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

## 02. Shuffle String

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

**Approach: Index Mapping with Extra List**

- We initialize an array of empty strings of the same length as `s`.
- For each character in the string `s`, place it at the position given by `indices[i]` in the new array.
- Finally, join the characters of this new array to get the result.

This approach works in **linear time** and uses an auxiliary array to store characters in their new positions.

#### Step-by-Step Walkthrough (Example: `s = "codeleet", indices = [4,5,6,7,0,2,1,3]`)

1. We create an empty list:

   ```
   shuffled_chars = ["", "", "", "", "", "", "", ""]
   ```

2. Now we iterate:

   | Iteration (i) | s\[i] | indices\[i] | Operation              | shuffled_chars                             |
   | ------------- | ----- | ----------- | ---------------------- | ------------------------------------------ |
   | 0             | c     | 4           | Place `'c'` at index 4 | `["", "", "", "", "c", "", "", ""]`        |
   | 1             | o     | 5           | Place `'o'` at index 5 | `["", "", "", "", "c", "o", "", ""]`       |
   | 2             | d     | 6           | Place `'d'` at index 6 | `["", "", "", "", "c", "o", "d", ""]`      |
   | 3             | e     | 7           | Place `'e'` at index 7 | `["", "", "", "", "c", "o", "d", "e"]`     |
   | 4             | l     | 0           | Place `'l'` at index 0 | `["l", "", "", "", "c", "o", "d", "e"]`    |
   | 5             | e     | 2           | Place `'e'` at index 2 | `["l", "", "e", "", "c", "o", "d", "e"]`   |
   | 6             | e     | 1           | Place `'e'` at index 1 | `["l", "e", "e", "", "c", "o", "d", "e"]`  |
   | 7             | t     | 3           | Place `'t'` at index 3 | `["l", "e", "e", "t", "c", "o", "d", "e"]` |

3. Final Result:

   ```python
   "".join(shuffled_chars) → "leetcode"
   ```

#### Time and Space Complexity

| Metric           | Value    | Explanation                                |
| ---------------- | -------- | ------------------------------------------ |
| Time Complexity  | **O(n)** | One pass over the input string and indices |
| Space Complexity | **O(n)** | Extra array `shuffled_chars` of size n     |

#### Why This Approach?

- The problem requires placing characters into known positions — this is a direct use case of **index mapping**.
- Using an extra list allows us to directly assign characters with **O(1)** operations.
- Much more **efficient and cleaner** than building the string character-by-character or doing multiple passes.

#### Pattern Recognition

This problem follows a common **Index Mapping + Output Array** pattern:

- Given elements and their final positions, use a temporary structure to arrange them.
- Appears in problems like:

  - Restoring permutations
  - Reordering arrays based on rules
  - Position-based encoding/decoding

When to apply this:

- Whenever a list of elements and their target indices are given.
- Ideal when constraints allow extra space (`O(n)`).

---

---

## 03. Reverse String

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

**Approach: Two-Pointer Technique**

To reverse an array **in-place**, we use the **two-pointer technique**:

- Start one pointer (`p`) at the beginning of the array.
- Start another pointer (`q`) at the end of the array.
- Swap the characters at `p` and `q`.
- Move `p` forward and `q` backward.
- Repeat until both pointers meet or cross.

This guarantees that every element is moved to its correct reversed position **without using any extra space**.

#### Step-by-Step Walkthrough

1. Let’s walkthrough this using:

   ```python
   s = ["H", "a", "n", "n", "a", "h"]
   ```

2. Initial state:

   ```
   p = 0, q = 5
   s = ["H", "a", "n", "n", "a", "h"]
   ```

   | Iteration | p   | q   | s\[p] | s\[q] | Action                           | Array After Swap                       |
   | --------- | --- | --- | ----- | ----- | -------------------------------- | -------------------------------------- |
   | 1         | 0   | 5   | "H"   | "h"   | Swap s\[0] and s\[5]             | \["h", "a", "n", "n", "a", "H"]        |
   | 2         | 1   | 4   | "a"   | "a"   | Swap s\[1] and s\[4] (no change) | \["h", "a", "n", "n", "a", "H"]        |
   | 3         | 2   | 3   | "n"   | "n"   | Swap s\[2] and s\[3] (no change) | \["h", "a", "n", "n", "a", "H"]        |
   | 4         | 3   | 2   | —     | —     | p > q → Loop ends                | Final: \["h", "a", "n", "n", "a", "H"] |

#### Time and Space Complexity

| Complexity       | Value  | Explanation                                              |
| ---------------- | ------ | -------------------------------------------------------- |
| Time Complexity  | $O(n)$ | Each character is swapped at most once                   |
| Space Complexity | $O(1)$ | No extra memory used beyond a few pointers and temp vars |

#### Why This Approach?

- The **two-pointer technique** is optimal when we need to **swap elements symmetrically** from the ends.
- It’s ideal when we’re constrained by **in-place** and **O(1) space**.
- Avoids creation of new arrays or use of built-in reversing functions.

#### What Pattern Should I Look For?

This problem is part of the **Two-Pointer In-Place Swap Pattern**.
Look for this pattern when:

- You're reversing elements
- Rotating arrays
- Partitioning elements (like in QuickSort, Dutch National Flag, etc.)
- In-place transformation is required

---

---

## 04. Maximum Number of Words Found in Sentences

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

**Approach Used: Pythonic Split and Count**

- Each sentence is a string of words separated by spaces.
- To count the words in each sentence, we can use Python’s `str.split()` method which splits the sentence at each space and returns a list of words.
- The length of this list gives us the number of words.
- Track the maximum word count encountered while iterating through all sentences.

#### Step-by-Step Walkthrough

1. Let’s walk through the example:

   ```python
   sentences = [
      "alice and bob love leetcode",
      "i think so too",
      "this is great thanks very much"
   ]
   ```

1. Initial State:

   ```
   res = 0
   ```

   | Iteration | sentence                         | sentence.split()                                   | Word Count | Updated res |
   | --------- | -------------------------------- | -------------------------------------------------- | ---------- | ----------- |
   | 1         | "alice and bob love leetcode"    | \['alice', 'and', 'bob', 'love', 'leetcode']       | 5          | 5           |
   | 2         | "i think so too"                 | \['i', 'think', 'so', 'too']                       | 4          | 5           |
   | 3         | "this is great thanks very much" | \['this', 'is', 'great', 'thanks', 'very', 'much'] | 6          | **6**       |

1. Final Result:

   ```python
   return res  → 6
   ```

#### Why This Approach?

- `str.split()` efficiently handles word extraction in Python.
- Simple, clean, and readable one-pass loop.
- We avoid manual space counting (compared to brute force character-by-character iteration).

#### Time and Space Complexity

| Complexity       | Value         | Justification                                            |
| ---------------- | ------------- | -------------------------------------------------------- |
| Time Complexity  | $O(n $\*$ m)$ | Where `n` is number of sentences and `m` is avg length   |
| Space Complexity | $O(1)$        | We use only constant space (`res`), split allocates temp |

> Note: If you consider `split()` space internally, it uses O(m) temporarily, but result is not stored globally.

#### Pattern Recognition

This problem falls under:

- **String Manipulation**
- **Counting**
- **Maximum Pattern**
- **In-place Linear Scan**

Look for problems that involve measuring something across multiple strings (e.g., longest/shortest word, most vowels, longest sentence) — this maximum accumulation pattern is frequently useful.

---

---

## 05. Rotate String

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

**Approach Used: Brute-Force Rotation Check (Custom Rotation)**

- First, check if the lengths of `s` and `goal` are not equal. If not, return `False` immediately.
- Loop through each index from `0` to `len(s) - 1`, and at each index:

  - Rotate the string `s` by slicing: from that index to the end, then concatenate the start to that index.
  - Check if the rotated string matches `goal`.

- If any such rotated string matches, return `True`.
- If no match is found in all possible rotations, return `False`.

#### Step-by-Step Walkthrough

1. Let’s walk through the following example step by step:

   ```python
   s = "abcde"
   goal = "cdeab"
   ```

2. Initial Check:

   `len(s) == len(goal)` → both are 5 characters long.

   | Iteration | i   | Rotation (s\[i:] + s\[:i]) | Is Equal to `goal`? |
   | --------- | --- | -------------------------- | ------------------- |
   | 0         | 0   | "abcde"                    | No                  |
   | 1         | 1   | "bcdea"                    | No                  |
   | 2         | 2   | "cdeab"                    | Yes → Return True   |
   | -         | -   | loop stops                 | -                   |

   → Output: `True`

#### Time and Space Complexity

| Complexity       | Value | Explanation                                                                  |
| ---------------- | ----- | ---------------------------------------------------------------------------- |
| Time Complexity  | O(n²) | There are `n` rotations, each takes up to O(n) time due to slicing + compare |
| Space Complexity | O(n)  | Each rotation creates a new string of size `n`                               |

#### Optimized Observation (Second Method in the code)

Instead of checking all possible rotations, we can observe:
If `goal` is a **rotation** of `s`, then it must be a **substring** of `s + s`.

So an alternate approach would be:

```python
def rotateString(self, s: str, goal: str) -> bool:
    if len(s) != len(goal):
        return False

    doubled = s + s

    return goal in doubled
```

| Complexity           | Value    | Explanation                                                                                                                                                                                                      |
| -------------------- | -------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Time Complexity**  | **O(n)** | Concatenating `s + s` takes O(n), and checking if `goal` is a substring of it (`goal in doubled`) takes O(n) using efficient substring search algorithms (like KMP or Boyer-Moore in Python's C implementation). |
| **Space Complexity** | **O(n)** | `s + s` creates a new string of length `2n`, which takes O(n) space. No other data structures are used.                                                                                                          |

#### Pattern Recognition

This is a classic **rotation pattern** where:

- You simulate all possible **shifts** or **rotations**.
- The logic often involves **string slicing** or **concatenation**.
- Can be solved more efficiently using **string doubling** technique (`s + s`).
- Cyclic permutations or circular behavior.

---

---
