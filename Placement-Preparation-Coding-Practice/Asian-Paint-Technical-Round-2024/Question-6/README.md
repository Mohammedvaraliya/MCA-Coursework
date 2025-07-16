Thanks for sharing the problem image! Let's walk through it clearly and simply.

---

## 🧩 Problem Explanation: Maximum Height of Triangle

You're given **N balls**, and you want to arrange them in rows to form a triangle:

- Row 1 → 1 ball
- Row 2 → 2 balls
- Row 3 → 3 balls
- … and so on.

### 🎯 Goal:

Write a program that calculates:

1. **Height of the complete triangle** — the tallest triangle where all rows are fully filled.
2. **Height of the incomplete triangle** — the tallest triangle including the last row even if it's not fully filled.

---

## 🧪 Example

If **N = 5**, the triangle looks like:

```
Row 1 → 1 ball
Row 2 → 2 balls
Row 3 → needs 3 balls, but only 2 left → incomplete
```

- ✅ Complete triangle height = 2 (first two rows are full)
- ✅ Incomplete triangle height = 3 (third row is partially filled)

---

## 🛠️ Step-by-Step Solution

### ✅ Step 1: Initialize Variables

```python
balls_used = 0
height_complete = 0
height_incomplete = 0
```

### ✅ Step 2: Loop Through Rows

Add balls row by row:

```python
for i in range(1, N + 1):
    balls_used += i
    if balls_used <= N:
        height_complete = i
    height_incomplete = i
    if balls_used > N:
        break
```

### ✅ Step 3: Return Results

```python
return height_complete, height_incomplete
```

---

## ✅ Final Code

```python
def calculate_triangle_height(N):
    balls_used = 0
    height_complete = 0
    height_incomplete = 0

    for i in range(1, N + 1):
        balls_used += i
        if balls_used <= N:
            height_complete = i
        height_incomplete = i
        if balls_used > N:
            break

    return height_complete, height_incomplete

# Sample input
N = 5
height_complete, height_incomplete = calculate_triangle_height(N)
print(f"Height-Complete = {height_complete}")
print(f"Height-Incomplete = {height_incomplete}")
```

---

## 🧠 Simple Explanation

- You keep adding balls row by row.
- If the total balls used so far is ≤ N, the row is complete.
- If the total balls used exceeds N, the last row is incomplete.
- You track both heights as you go.
