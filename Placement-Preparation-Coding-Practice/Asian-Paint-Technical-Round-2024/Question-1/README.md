## 🧩 Problem Explanation: Magic Money

You're given a magical scenario:

- A person enters a **magic cave** that increases their money in a special way.
- The first time they enter, their money increases by **Re 1**.
- From the second time onward, their money becomes the **sum of the last two amounts** they had when entering the cave.

### 🎯 Goal:

Write a program that:

- Takes two inputs:
  1. **Initial amount** of money.
  2. **Target amount** they want to reach.
- Outputs the **number of times** they must enter the cave to reach or exceed the target.

---

## 🧩 Problem Recap: Magic Money

- A person enters a **magic cave**.
- First time: their money increases by ₹1.
- From second time onward: their money becomes the **sum of the last two amounts**.
- You want to find how many times they must enter the cave to reach or exceed a **target amount**.

---

## 🧪 Example Input

Let’s use:

- Initial amount = ₹22
- Target amount = ₹100

---

## 🛠️ Step-by-Step Walkthrough of Your Code

### ✅ Step 1: Initialization

```python
previous = 22
current = 23  # initial + 1
count = 1     # first entry already done
```

So after the first entry:

- Money = ₹23
- Entries so far = 1

---

### ✅ Step 2: Loop Begins

#### 🔁 Entry 2:

```python
new_amount = previous + current = 22 + 23 = 45
previous = 23
current = 45
count = 2
```

#### 🔁 Entry 3:

```python
new_amount = 23 + 45 = 68
previous = 45
current = 68
count = 3
```

#### 🔁 Entry 4:

```python
new_amount = 45 + 68 = 113
previous = 68
current = 113
count = 4
```

Now `current = 113` which is **greater than target (100)**, so the loop stops.

---

### ✅ Final Output

```python
To reach ₹100 from ₹22
You must need to enter the cave 4 times.
```

---

## 🧠 Summary of Steps

| Entry | Previous | Current | New Amount |
| ----- | -------- | ------- | ---------- |
| 1     | 22       | 23      | 22 + 1     |
| 2     | 22       | 23      | 45         |
| 3     | 23       | 45      | 68         |
| 4     | 45       | 68      | 113 ✅     |

---
