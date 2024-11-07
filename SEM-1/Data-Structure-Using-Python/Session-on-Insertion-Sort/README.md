# Insertion Sort Explanation

## Problem Statement

To implement **Insertion Sort** in Python, which sorts a list of elements by building a sorted portion one item at a time, inserting each element into its correct position within the sorted portion.

Insertion Sort is a simple and intuitive sorting algorithm that builds the sorted list one element at a time. It starts with the second element and compares it with the elements before it, inserting it into its appropriate position. This process is repeated for all elements, resulting in a sorted list.

1. **Working**:
   1. Divide the list into a sorted and an unsorted part.
   2. Pick each element from the unsorted part and insert it into the correct position in the sorted part.
2. This algorithm is efficient for small or nearly sorted data but performs poorly on large datasets with a time complexity of $O(n^2)$ in the worst case.

Insertion sort is an **in-place**, **comparison-based** sorting algorithm. The general idea behind it is to build the sorted array one element at a time by repeatedly picking the next element and inserting it into its correct position among the previously sorted elements.

---

### Input List:

```
arr = [12, 11, 13, 5, 6]
```

### Step 1: Initialize and start the first iteration.

- **Start with the second element** in the list (index 1, value `11`), since the first element (`12`) is trivially sorted by itself.

### First Iteration (`i = 1`):

- **Key = 11** (the element we're trying to insert into the sorted part of the array).
- **Compare key (11) with the element before it (arr[0] = 12)**.
- Since `11 < 12`, we **shift** the element `12` to the right.

Array after shifting:

```
[12, 12, 13, 5, 6]
```

- Now, we place `11` at the position where `12` was:

```
[11, 12, 13, 5, 6]
```

The array is now sorted up to index 1.

---

### Step 2: Move to the next element (index 2).

### Second Iteration (`i = 2`):

- **Key = 13** (the element we're trying to insert).
- **Compare key (13) with the element before it (arr[1] = 12)**.
- Since `13 > 12`, no shift is needed. We leave the key in place.

Array remains unchanged:

```
[11, 12, 13, 5, 6]
```

The array is now sorted up to index 2.

---

### Step 3: Move to the next element (index 3).

### Third Iteration (`i = 3`):

- **Key = 5** (the element we're trying to insert).
- **Compare key (5) with the element before it (arr[2] = 13)**.
- Since `5 < 13`, we **shift** `13` to the right.

Array after shifting:

```
[11, 12, 13, 13, 6]
```

- **Compare key (5) with the next element (arr[1] = 12)**.
- Since `5 < 12`, we **shift** `12` to the right.

Array after shifting:

```
[11, 12, 12, 13, 6]
```

- **Compare key (5) with the next element (arr[0] = 11)**.
- Since `5 < 11`, we **shift** `11` to the right.

Array after shifting:

```
[11, 11, 12, 13, 6]
```

- Now, we place `5` at the position where `11` was:

```
[5, 11, 12, 13, 6]
```

The array is now sorted up to index 3.

---

### Step 4: Move to the last element (index 4).

### Fourth Iteration (`i = 4`):

- **Key = 6** (the element we're trying to insert).
- **Compare key (6) with the element before it (arr[3] = 13)**.
- Since `6 < 13`, we **shift** `13` to the right.

Array after shifting:

```
[5, 11, 12, 13, 13]
```

- **Compare key (6) with the next element (arr[2] = 12)**.
- Since `6 < 12`, we **shift** `12` to the right.

Array after shifting:

```
[5, 11, 12, 12, 13]
```

- **Compare key (6) with the next element (arr[1] = 11)**.
- Since `6 < 11`, we **shift** `11` to the right.

Array after shifting:

```
[5, 11, 11, 12, 13]
```

- Now, we place `6` at the position where `11` was:

```
[5, 6, 11, 12, 13]
```

---

### Final Sorted Array:

After all iterations, the list is sorted:

```
[5, 6, 11, 12, 13]
```

### Time Complexity:

- The worst-case time complexity of insertion sort is **$O(n^2)$**, which happens when the list is sorted in reverse order. However, when the list is nearly sorted, the time complexity approaches **$O(n)$**.
