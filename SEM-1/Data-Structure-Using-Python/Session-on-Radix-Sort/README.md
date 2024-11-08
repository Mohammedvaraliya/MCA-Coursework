# Radix Sort Explanation

## Problem Statement

To implement Radix Sort in Python, a non-comparative integer sorting algorithm that processes elements digit by digit, from least significant to most significant.

Radix Sort is an integer sorting algorithm that groups numbers based on their digits, sorting them from the least significant digit (LSD) to the most significant digit (MSD). It leverages counting sort or a stable sorting algorithm to organize the numbers for each digit position. Radix Sort is especially useful for large lists of non-negative integers, providing efficient sorting with a time complexity of $O(d \cdot (n + k))$, where:

1. $n$ is the number of elements,
2. $d$ is the number of digits in the largest number, and
3. $k$ is the range of the digit values (0-9 for decimal numbers).

### Problem Recap

We are given an array of integers:

```python
arr = [170, 45, 75, 90, 802, 24, 2, 66]
```

The goal is to sort the array in ascending order using **radix sort**. Radix sort works by sorting the numbers based on each digit, starting from the least significant digit (LSD) to the most significant digit (MSD). We do this using a **stable sorting algorithm** at each digit position, in this case, **counting sort**.

### Step 1: Understanding `counting_sort` function

The `counting_sort` function sorts the array based on a specific digit, referred to by `exp` (which represents the place value of the digit being considered).

- **`arr[i] // exp`** extracts the digit at the position represented by `exp`.
- **`(arr[i] // exp) % 10`** gives the specific digit in the current position (ones, tens, hundreds, etc.) of `arr[i]`.

`counting_sort` then sorts based on this digit.

### Step 2: Walkthrough of the `radix_sort` function

Now, let's walk through how the main `radix_sort` function works.

#### Initial Setup:

```python
arr = [170, 45, 75, 90, 802, 24, 2, 66]
```

The first thing we do in `radix_sort` is find the **maximum value** in the array:

```python
max_val = max(arr)  # max_val = 802
```

So, the **maximum value** is `802`.

#### Step 3: Understanding the `exp` Loop

The idea behind the `exp` loop is to sort by each digit, starting from the least significant digit (LSD). In each iteration, we sort based on the current digit, and we keep increasing `exp` by a factor of 10 (i.e., move to the next place value: tens, hundreds, etc.).

##### First iteration (`exp = 1`):

In the first iteration, `exp = 1`, meaning we're looking at the **ones** place (least significant digit). The condition for continuing the loop is:

```python
while max_val // exp > 0:
```

- For `exp = 1`, we check if `max_val // 1 > 0`, i.e., `802 // 1 > 0` → **True**. So, we continue.

We call the `counting_sort` function to sort based on the ones place (exp = 1):

```python
counting_sort(arr, exp)  # exp = 1
```

### Initial Setup:

1. Find the maximum value (`max_val`) in the array:
   ```python
   max_val = max(arr)  # max_val = 802
   ```
2. We'll sort the array using the least significant digit (LSD) first and move towards the most significant digit (MSD).

---

### **Iteration 1: Sorting by the Ones Place (`exp = 1`)**

In this first iteration, we’ll sort based on the **ones** place, which corresponds to `exp = 1`.

#### Step 1: Setup for `counting_sort`

We initialize:

- `n = 8` (size of the array)
- `output = [0, 0, 0, 0, 0, 0, 0, 0]` (an array to store sorted values)
- `count = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]` (an array to count occurrences of digits 0-9)

#### Step 2: Count Occurrences for Ones Place

For each element in the array, we calculate the digit at the ones place (i.e., `arr[i] // exp % 10`):

- `170 // 1 = 170`, `170 % 10 = 0` → increment `count[0]`
- `45 // 1 = 45`, `45 % 10 = 5` → increment `count[5]`
- `75 // 1 = 75`, `75 % 10 = 5` → increment `count[5]`
- `90 // 1 = 90`, `90 % 10 = 0` → increment `count[0]`
- `802 // 1 = 802`, `802 % 10 = 2` → increment `count[2]`
- `24 // 1 = 24`, `24 % 10 = 4` → increment `count[4]`
- `2 // 1 = 2`, `2 % 10 = 2` → increment `count[2]`
- `66 // 1 = 66`, `66 % 10 = 6` → increment `count[6]`

After counting, the `count` array becomes:

```python
count = [2, 0, 2, 0, 1, 2, 1, 0, 0, 0]
```

#### Step 3: Cumulative Count for Stable Sorting

Next, we update `count` to reflect the cumulative sum of counts. This will help in determining the position of each digit in the sorted output.

- `count[1] += count[0]` → `count[1] = 2`
- `count[2] += count[1]` → `count[2] = 4`
- `count[3] += count[2]` → `count[3] = 4`
- `count[4] += count[3]` → `count[4] = 5`
- `count[5] += count[4]` → `count[5] = 7`
- `count[6] += count[5]` → `count[6] = 8`
- `count[7] += count[6]` → `count[7] = 8`
- `count[8] += count[7]` → `count[8] = 8`
- `count[9] += count[8]` → `count[9] = 8`

Now, the `count` array is:

```python
count = [2, 2, 4, 4, 5, 7, 8, 8, 8, 8]
```

#### Step 4: Build the Output Array

Now, we place the elements into the correct positions in the `output` array, starting from the last element in the original array to maintain stability.

- For `66`, the digit is `6` → place `66` at `count[6] - 1 = 7`. Decrement `count[6]`.
- For `2`, the digit is `2` → place `2` at `count[2] - 1 = 3`. Decrement `count[2]`.
- For `24`, the digit is `4` → place `24` at `count[4] - 1 = 4`. Decrement `count[4]`.
- For `802`, the digit is `2` → place `802` at `count[2] - 1 = 2`. Decrement `count[2]`.
- For `90`, the digit is `0` → place `90` at `count[0] - 1 = 1`. Decrement `count[0]`.
- For `75`, the digit is `5` → place `75` at `count[5] - 1 = 6`. Decrement `count[5]`.
- For `45`, the digit is `5` → place `45` at `count[5] - 1 = 5`. Decrement `count[5]`.
- For `170`, the digit is `0` → place `170` at `count[0] - 1 = 0`. Decrement `count[0]`.

The `output` array becomes:

```python
output = [170, 90, 802, 2, 24, 45, 75, 66]
```

#### Step 5: Copy the Output Array Back to `arr`

Finally, we copy the `output` array back into `arr`:

```python
arr = [170, 90, 802, 2, 24, 45, 75, 66]
```

---

### **Iteration 2: Sorting by the Tens Place (`exp = 10`)**

Now, we sort by the **tens** place, with `exp = 10`.

#### Step 1: Setup for `counting_sort`

We initialize the same arrays:

- `n = 8`
- `output = [0, 0, 0, 0, 0, 0, 0, 0]`
- `count = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]`

#### Step 2: Count Occurrences for Tens Place

For each element in `arr`, we calculate the digit at the tens place (`arr[i] // exp % 10`):

- `170 // 10 = 17`, `17 % 10 = 7` → increment `count[7]`
- `90 // 10 = 9`, `9 % 10 = 9` → increment `count[9]`
- `802 // 10 = 80`, `80 % 10 = 0` → increment `count[0]`
- `2 // 10 = 0`, `0 % 10 = 0` → increment `count[0]`
- `24 // 10 = 2`, `2 % 10 = 2` → increment `count[2]`
- `45 // 10 = 4`, `4 % 10 = 4` → increment `count[4]`
- `75 // 10 = 7`, `7 % 10 = 7` → increment `count[7]`
- `66 // 10 = 6`, `6 % 10 = 6` → increment `count[6]`

The `count` array becomes:

```python
count = [2, 0, 1, 0, 1, 0, 1, 2, 0, 1]
```

#### Step 3: Cumulative Count for Stable Sorting

We update `count` for cumulative counts:

- `count[1] += count[0]` → `count[1] = 2`
- `count[2] += count[1]` → `count[2] = 3`
- `count[3] += count[2]` → `count[3] = 3`
- `count[4] += count[3]` → `count[4] = 4`
- `count[5] += count[4]` → `count[5] = 4`
- `count[6] += count[5]` → `count[6] = 5`
- `count[7] += count[6]` → `count[7] = 7`
- `count[8] += count[7]` → `count[8] = 7`
- `count[9] += count[8]` → `count[9] = 8`

Now, the `count` array is:

```python
count = [2, 2, 3, 3, 4, 4, 5,

7, 7, 8]
```

#### Step 4: Build the Output Array

We now place the elements into their correct positions based on the tens digit.

- For `66`, the digit is `6` → place `66` at `count[6] - 1 = 5`. Decrement `count[6]`.
- For `75`, the digit is `7` → place `75` at `count[7] - 1 = 6`. Decrement `count[7]`.
- For `45`, the digit is `4` → place `45` at `count[4] - 1 = 3`. Decrement `count[4]`.
- For `24`, the digit is `2` → place `24` at `count[2] - 1 = 2`. Decrement `count[2]`.
- For `802`, the digit is `0` → place `802` at `count[0] - 1 = 1`. Decrement `count[0]`.
- For `2`, the digit is `0` → place `2` at `count[0] - 1 = 0`. Decrement `count[0]`.
- For `90`, the digit is `9` → place `90` at `count[9] - 1 = 7`. Decrement `count[9]`.
- For `170`, the digit is `7` → place `170` at `count[7] - 1 = 4`. Decrement `count[7]`.

The `output` array becomes:

```python
output = [2, 802, 24, 45, 170, 75, 66, 90]
```

#### Step 5: Copy the Output Array Back to `arr`

Finally, copy the `output` array back into `arr`:

```python
arr = [2, 802, 24, 45, 170, 75, 66, 90]
```

---

### **Iteration 3: Sorting by the Hundreds Place (`exp = 100`)**

We now sort by the **hundreds** place (`exp = 100`).

#### Step 1: Setup for `counting_sort`

Initialize the arrays again:

- `n = 8`
- `output = [0, 0, 0, 0, 0, 0, 0, 0]`
- `count = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]`

#### Step 2: Count Occurrences for Hundreds Place

Calculate the hundreds digit for each element:

- `2 // 100 = 0`, `0 % 10 = 0` → increment `count[0]`
- `802 // 100 = 8`, `8 % 10 = 8` → increment `count[8]`
- `24 // 100 = 0`, `0 % 10 = 0` → increment `count[0]`
- `45 // 100 = 0`, `0 % 10 = 0` → increment `count[0]`
- `170 // 100 = 1`, `1 % 10 = 1` → increment `count[1]`
- `75 // 100 = 0`, `0 % 10 = 0` → increment `count[0]`
- `66 // 100 = 0`, `0 % 10 = 0` → increment `count[0]`
- `90 // 100 = 0`, `0 % 10 = 0` → increment `count[0]`

The `count` array becomes:

```python
count = [6, 1, 0, 0, 0, 0, 0, 0, 1, 0]
```

#### Step 3: Cumulative Count for Stable Sorting

Now, compute the cumulative sum:

```python
count = [6, 7, 7, 7, 7, 7, 7, 7, 8, 8]
```

#### Step 4: Build the Output Array

We place the elements in the correct positions:

- For `90`, the hundreds digit is `0` → place `90` at `count[0] - 1 = 5`. Decrement `count[0]`.
- For `66`, the hundreds digit is `0` → place `66` at `count[0] - 1 = 4`. Decrement `count[0]`.
- For `75`, the hundreds digit is `0` → place `75` at `count[0] - 1 = 3`. Decrement `count[0]`.
- For `170`, the hundreds digit is `1` → place `170` at `count[1] - 1 = 6`. Decrement `count[1]`.
- For `802`, the hundreds digit is `8` → place `802` at `count[8] - 1 = 7`. Decrement `count[8]`.
- For `24`, the hundreds digit is `0` → place `24` at `count[0] - 1 = 2`. Decrement `count[0]`.
- For `2`, the hundreds digit is `0` → place `2` at `count[0] - 1 = 1`. Decrement `count[0]`.
- For `45`, the hundreds digit is `0` → place `45` at `count[0] - 1 = 0`. Decrement `count[0]`.

The `output` array becomes:

```python
output = [2, 24, 45, 66, 75, 90, 170, 802]
```

#### Step 5: Copy the Output Array Back to `arr`

Finally, copy the sorted `output` array back into `arr`:

```python
arr = [2, 24, 45, 66, 75, 90, 170, 802]
```

---

### **Final Sorted Array**

After sorting by all digit places, we get the final sorted array:

```python
arr = [2, 24, 45, 66, 75, 90, 170, 802]
```

This is the sorted array in ascending order.

---
