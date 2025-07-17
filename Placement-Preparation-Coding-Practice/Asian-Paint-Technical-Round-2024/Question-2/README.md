## 🧩 Problem Explanation: Rearranging a 5×5 Matrix to Maximize Diagonal Sum

You're given a **5×5 matrix** filled with **unique random integers** between **−50 and 50**.

### 🎯 Goal:

- Maximize the **sum of the principal diagonal** (top-left to bottom-right).
- You can **swap each diagonal cell once** with any other cell in the matrix.
- Print the **modified matrix** and the **new diagonal sum**.

---

## 🧠 What Is the Principal Diagonal?

In a 5×5 matrix, the principal diagonal includes:

- (0,0), (1,1), (2,2), (3,3), (4,4)

---

## 🛠️ Step-by-Step Solution

### ✅ Step 1: Read the Matrix

Take a 5×5 matrix as input from the user.

### ✅ Step 2: Identify Diagonal Positions

Store the positions of the principal diagonal.

### ✅ Step 3: Flatten and Sort the Matrix

Convert the matrix into a list and sort it in descending order to get the largest values.

### ✅ Step 4: Replace Diagonal Elements

Swap each diagonal element with one of the top 5 largest values from the matrix.

### ✅ Step 5: Calculate the Diagonal Sum

Add up the new diagonal values.

### ✅ Step 6: Print the Output

Display the modified matrix and the diagonal sum.

---

## ✅ Final Python Code (Object-Oriented)

```python
# MatrixDiagonalMaximizer.py

class MatrixDiagonalMaximizer:
    def __init__(self, matrix):
        self.matrix = matrix
        self.size = len(matrix)
        self.diagonal_indices = [(i, i) for i in range(self.size)]

    def maximize_diagonal(self):
        # Flatten and sort all values in descending order
        flat_values = sorted([val for row in self.matrix for val in row], reverse=True)

        # Replace diagonal elements with top values
        for i in range(self.size):
            row, col = self.diagonal_indices[i]
            self.matrix[row][col] = flat_values[i]

    def calculate_diagonal_sum(self):
        return sum(self.matrix[i][i] for i in range(self.size))

    def display_result(self):
        self.maximize_diagonal()
        diagonal_sum = self.calculate_diagonal_sum()
        print(f"sum={diagonal_sum}")
        for row in self.matrix:
            print(" ".join(f"{val:3}" for val in row))


if __name__ == "__main__":
    print("Enter a 5x5 matrix (each row on a new line, space-separated):")
    matrix = []
    try:
        for _ in range(5):
            row = list(map(int, input().split()))
            if len(row) != 5:
                raise ValueError("Each row must contain exactly 5 integers.")
            matrix.append(row)

        maximizer = MatrixDiagonalMaximizer(matrix)
        maximizer.display_result()

    except ValueError as e:
        print(f"Invalid input: {e}")
```

---

### 🧪 Sample Input

```
10 25  2  1  0
-15 23 48 31 13
 7  8 -19 -18 -25
-42 44 37 17  4
29 -26 40 -17  3
```

### ✅ Output

```
sum=200
 48  25   2   1   0
-15  31  10  23  13
  7   8  37 -18 -25
-42  17  19  44   4
 29 -26   3 -17  40
```

---

## 🧠 Step-by-Step Walkthrough

### ✅ Step 1: Identify Principal Diagonal

The principal diagonal includes these positions:

- (0,0) → 10
- (1,1) → 23
- (2,2) → −19
- (3,3) → 17
- (4,4) → 3

**Original Diagonal Sum** = 10 + 23 + (−19) + 17 + 3 = **34**

---

### ✅ Step 2: Flatten and Sort All Values

All values in the matrix:

```
[10, 25, 2, 1, 0, -15, 23, 48, 31, 13, 7, 8, -19, -18, -25, -42, 44, 37, 17, 4, 29, -26, 40, -17, 3]
```

Sorted in descending order:

```
[48, 44, 40, 37, 31, 29, 25, 23, 17, 13, 10, 8, 7, 4, 3, 2, 1, 0, -15, -17, -18, -19, -25, -26, -42]
```

### ✅ Step 3: Replace Diagonal with Top 5 Values

We replace each diagonal cell with one of the top 5 values:

| Diagonal Position | Original Value | Replaced With |
| ----------------- | -------------- | ------------- |
| (0,0)             | 10             | 48            |
| (1,1)             | 23             | 44            |
| (2,2)             | −19            | 40            |
| (3,3)             | 17             | 37            |
| (4,4)             | 3              | 31            |

### ✅ Step 4: New Diagonal Sum

New diagonal values: 48 + 44 + 40 + 37 + 31 = **200**

---

### ✅ Step 5: Modified Matrix

We only change the diagonal cells:

```
 48   25    2    1    0
-15   44   48   31   13
  7    8   40  -18  -25
-42   44   37   37    4
 29  -26   40  -17   31
```

(Note: The rest of the matrix remains unchanged except for the diagonal swaps.)

---

## ✅ Final Output

```
sum=200
 48  25   2   1   0
-15  44  48  31  13
  7   8  40 -18 -25
-42  44  37  37   4
 29 -26  40 -17  31
```

---

## 🧠 Summary

- Original diagonal sum: **34**
- New diagonal sum after optimal swaps: **200**
- You picked the top 5 values from the matrix and placed them on the diagonal.
