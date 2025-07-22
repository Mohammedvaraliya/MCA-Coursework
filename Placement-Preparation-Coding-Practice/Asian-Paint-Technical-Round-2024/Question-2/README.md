# 🧮 Matrix Diagonal Maximizer

## 📌 Problem Statement

You are given a **5×5 matrix** filled with unique integers ranging between **−50 and 50**. Your task is to **maximize the sum of the principal diagonal** (i.e., the top-left to bottom-right diagonal).

🔄 You are allowed to:

- **Swap each diagonal element once** with **any non-diagonal element** in the matrix.

🏁 Goal:

- Rearrange the matrix using valid swaps to **maximize the diagonal sum**.
- Print the **modified matrix** and the **new diagonal sum**.

---

## 🧠 Understanding the Principal Diagonal

In a 5×5 matrix, the principal diagonal consists of:

- `(0,0)`, `(1,1)`, `(2,2)`, `(3,3)`, `(4,4)`

---

## 🛠️ Steps to Solve the Problem

### 1. **Identify Diagonal Elements**

- Store the positions of the main diagonal cells.

### 2. **Extract Non-Diagonal Elements**

- Collect all matrix elements that are **not on the main diagonal**, along with their positions.

### 3. **Sort Non-Diagonal Values**

- Sort non-diagonal elements in **descending order** to get the largest values.

### 4. **Swap Values**

- For each diagonal cell, **swap** it with one of the top unused non-diagonal values.

### 5. **Calculate the Diagonal Sum**

- After all swaps, compute the sum of the modified diagonal.

### 6. **Display Results**

- Show the new matrix and the final diagonal sum.

---

## ✅ Example Input

```
10  25   2   1   0
-15 23  48  31  13
  7   8 -19 -18 -25
-42 44  37  17   4
 29 -26 40 -17   3
```

---

## 🧪 Step-by-Step Example Walkthrough

### 🔍 Step 1: Original Diagonal

| Index | Value |
| ----- | ----- |
| (0,0) | 10    |
| (1,1) | 23    |
| (2,2) | -19   |
| (3,3) | 17    |
| (4,4) | 3     |

➡️ **Original diagonal sum =** 10 + 23 + (−19) + 17 + 3 = **34**

---

### 📊 Step 2: Sort Non-Diagonal Elements

All non-diagonal elements (with positions):

```
[(0,1)=25, (0,2)=2, (0,3)=1, (0,4)=0,
 (1,0)=−15, (1,2)=48, (1,3)=31, (1,4)=13,
 (2,0)=7, (2,1)=8, (2,3)=−18, (2,4)=−25,
 (3,0)=−42, (3,1)=44, (3,2)=37, (3,4)=4,
 (4,0)=29, (4,1)=−26, (4,2)=40, (4,3)=−17]
```

Top 5 values:

- **48**, **44**, **40**, **37**, **31**

---

### 🔄 Step 3: Perform Swaps

Swap each diagonal cell with one of these top values:

| Diagonal Position | Swapped With | Value |
| ----------------- | ------------ | ----- |
| (0,0)             | (1,2)        | 48    |
| (1,1)             | (3,1)        | 44    |
| (2,2)             | (4,2)        | 40    |
| (3,3)             | (3,2)        | 37    |
| (4,4)             | (1,3)        | 31    |

---

### 🧾 Final Matrix

```
 48  25   2   1   0
-15  44  10  23  13
  7   8  40 -18 -25
-42  17  37  37   4
 29 -26   3 -17  31
```

### ✅ Final Diagonal

| Index | Value |
| ----- | ----- |
| (0,0) | 48    |
| (1,1) | 44    |
| (2,2) | 40    |
| (3,3) | 37    |
| (4,4) | 31    |

➡️ **New diagonal sum =** 48 + 44 + 40 + 37 + 31 = **200**

---

## 💡 Pattern in This Problem

This is a classic **Greedy Optimization** problem with constraints. You're allowed limited actions (one swap per diagonal cell) and must maximize an outcome (the diagonal sum).

### Core Concepts:

- Matrix Manipulation
- Greedy Choice Strategy
- Swap Optimization under Constraint
- Position Tracking

---

## 🔁 Related Questions to Practice

1. **Minimize the diagonal sum** using valid swaps.
2. Maximize the **anti-diagonal** (top-right to bottom-left).
3. Swap entire **rows or columns** to maximize diagonal.
4. Given `k` allowed swaps, maximize the diagonal sum.
5. Use recursion or backtracking to explore multiple swap combinations.

---

## 🧠 Summary

| Task              | Description                            |
| ----------------- | -------------------------------------- |
| Input             | 5×5 matrix of integers                 |
| Allowed operation | One swap per diagonal element          |
| Goal              | Maximize the diagonal sum              |
| Approach          | Greedy swap with highest non-diagonals |
| Output            | Modified matrix + diagonal sum         |

---
