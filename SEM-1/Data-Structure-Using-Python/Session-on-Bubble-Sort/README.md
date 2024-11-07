# Tower of Hanoi Explanation

## Problem Statement

The **Tower of Hanoi** is a mathematical puzzle where we have three towers (pegs) and a certain number of disks of different sizes. The goal is to move all disks from the source tower to the destination tower with the help of an auxiliary tower, following these rules:

1. Only one disk can be moved at a time.
2. A larger disk cannot be placed on top of a smaller disk.
3. Only the top disk of a tower can be moved at any time.

For $n$ disks, the solution requires moving the disks in a specific sequence. This puzzle is commonly solved using **recursion** due to its repetitive nature.

### Initial Setup:

We start with the array:

```python
arr = [64, 34, 25, 12, 22, 11, 90]
```

### Bubble Sort Algorithm Recap:

- **Outer loop**: Runs `n` times where `n` is the length of the array. On each pass, the largest unsorted element "bubbles up" to its correct position.
- **Inner loop**: Compares adjacent elements and swaps them if they're in the wrong order.
- **Swapped flag**: Keeps track of whether any swaps were made during the current pass. If no swaps happen during a pass, we know the array is already sorted, and we can **terminate early** using the `break` statement.

### Example Walkthrough:

**Step 1: First pass (`i = 0`):**

- Start with the array: `[64, 34, 25, 12, 22, 11, 90]`
- Initialize `swapped = False`

**Inner loop (Comparing adjacent elements):**

1. Compare `64` and `34`: `64 > 34`, so swap them → `[34, 64, 25, 12, 22, 11, 90]` → set `swapped = True`
2. Compare `64` and `25`: `64 > 25`, so swap them → `[34, 25, 64, 12, 22, 11, 90]` → `swapped = True`
3. Compare `64` and `12`: `64 > 12`, so swap them → `[34, 25, 12, 64, 22, 11, 90]` → `swapped = True`
4. Compare `64` and `22`: `64 > 22`, so swap them → `[34, 25, 12, 22, 64, 11, 90]` → `swapped = True`
5. Compare `64` and `11`: `64 > 11`, so swap them → `[34, 25, 12, 22, 11, 64, 90]` → `swapped = True`
6. Compare `64` and `90`: `64 < 90`, no swap → no change.

After the first pass, the largest element `90` has bubbled up to the correct position at the end. The array now looks like:

```python
[34, 25, 12, 22, 11, 64, 90]
```

Since we made swaps (`swapped = True`), we move on to the next pass.

---

**Step 2: Second pass (`i = 1`):**

- Start with the array: `[34, 25, 12, 22, 11, 64, 90]`
- Initialize `swapped = False`

**Inner loop (Comparing adjacent elements):**

1. Compare `34` and `25`: `34 > 25`, so swap them → `[25, 34, 12, 22, 11, 64, 90]` → `swapped = True`
2. Compare `34` and `12`: `34 > 12`, so swap them → `[25, 12, 34, 22, 11, 64, 90]` → `swapped = True`
3. Compare `34` and `22`: `34 > 22`, so swap them → `[25, 12, 22, 34, 11, 64, 90]` → `swapped = True`
4. Compare `34` and `11`: `34 > 11`, so swap them → `[25, 12, 22, 11, 34, 64, 90]` → `swapped = True`
5. Compare `34` and `64`: `34 < 64`, no swap → no change.

After the second pass, the second-largest element `64` is in its correct position, and the array looks like:

```python
[25, 12, 22, 11, 34, 64, 90]
```

Since swaps were made (`swapped = True`), we continue to the next pass.

---

**Step 3: Third pass (`i = 2`):**

- Start with the array: `[25, 12, 22, 11, 34, 64, 90]`
- Initialize `swapped = False`

**Inner loop (Comparing adjacent elements):**

1. Compare `25` and `12`: `25 > 12`, so swap them → `[12, 25, 22, 11, 34, 64, 90]` → `swapped = True`
2. Compare `25` and `22`: `25 > 22`, so swap them → `[12, 22, 25, 11, 34, 64, 90]` → `swapped = True`
3. Compare `25` and `11`: `25 > 11`, so swap them → `[12, 22, 11, 25, 34, 64, 90]` → `swapped = True`
4. Compare `25` and `34`: `25 < 34`, no swap → no change.

After the third pass, the third-largest element `34` is in its correct position, and the array looks like:

```python
[12, 22, 11, 25, 34, 64, 90]
```

Since swaps were made (`swapped = True`), we continue to the next pass.

---

**Step 4: Fourth pass (`i = 3`):**

- Start with the array: `[12, 22, 11, 25, 34, 64, 90]`
- Initialize `swapped = False`

**Inner loop (Comparing adjacent elements):**

1. Compare `12` and `22`: `12 < 22`, no swap → no change.
2. Compare `22` and `11`: `22 > 11`, so swap them → `[12, 11, 22, 25, 34, 64, 90]` → `swapped = True`
3. Compare `22` and `25`: `22 < 25`, no swap → no change.

After the fourth pass, the array looks like:

```python
[12, 11, 22, 25, 34, 64, 90]
```

Since swaps were made (`swapped = True`), we continue to the next pass.

---

**Step 5: Fifth pass (`i = 4`):**

- Start with the array: `[12, 11, 22, 25, 34, 64, 90]`
- Initialize `swapped = False`

**Inner loop (Comparing adjacent elements):**

1. Compare `12` and `11`: `12 > 11`, so swap them → `[11, 12, 22, 25, 34, 64, 90]` → `swapped = True`
2. Compare `12` and `22`: `12 < 22`, no swap → no change.

After the fifth pass, the array looks like:

```python
[11, 12, 22, 25, 34, 64, 90]
```

Since swaps were made (`swapped = True`), we continue to the next pass.

---

**Step 6: Sixth pass (`i = 5`):**

- Start with the array: `[11, 12, 22, 25, 34, 64, 90]`
- Initialize `swapped = False`

**Inner loop (Comparing adjacent elements):**

1. Compare `11` and `12`: `11 < 12`, no swap → no change.

At the end of this pass, **no swaps were made**, and since `swapped = False`, we can conclude that the array is already sorted. The algorithm exits early using the `break` statement.

---

### Final Sorted Array:

```python
[11, 12, 22, 25, 34, 64, 90]
```

### Explanation of `swapped` and `break`:

1. **Why `swapped` is used**:

   1. The `swapped` variable tracks whether any swaps were made in a particular pass. If no swaps are made, it means the array is already sorted, and further passes are unnecessary.

2. **Why the `break` is used**:

   1. If `swapped` is `False` at the end of a pass, the `break` statement terminates the outer loop early, avoiding redundant passes.
   2. This improves the efficiency of the algorithm, especially in the best case where the array is already sorted.

### In Essense:

- **With `swapped` and `break`**, Bubble Sort becomes more efficient, as it stops early if the array is sorted before completing all passes, reducing the number of unnecessary comparisons.
- **Without `swapped` and `break`**, Bubble Sort would always perform `n` passes, even if the array is already sorted, which could result in unnecessary time complexity in the best case scenario.
