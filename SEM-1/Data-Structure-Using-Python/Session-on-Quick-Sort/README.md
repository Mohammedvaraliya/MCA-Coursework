# Quick Sort Explanation

## Problem Statement

To implement the Quick Sort algorithm in Python, which sorts an array by partitioning it into subarrays and sorting them individually.

Quick Sort is a divide-and-conquer sorting algorithm that partitions an array into two parts based on a pivot element. Each part is sorted individually through recursive partitioning. There are two main partitioning schemes:

1. **Hoare Partition Scheme**

   1. **Description**: Hoare Partition uses Two-directional scanning technique that comes from the left until it finds an element that is bigger than the pivot, and from the right until it finds an element that is smaller than the pivot and then swaps the two. The process continues until the scan from the left meets the scan from the right.
   2. **Implementation**:
      1. Select the first element as the pivot.
      2. Move pointers inward, swapping elements until they cross.
   3. **Effectiveness**:
      1. Generally than Lomuto because it makes fewer swaps and can be more efficient in terms of cache performance.
      2. It has better worst-case performance compared to Lomuto (still $O(n^2)$ in worst-case scenarios but performs better in practice).

   ***

2. **Lomuto Partition Scheme**
   1. **Description**: This method uses a single pivot. Elements are rearranged such that all elements less than or equal to the pivot are on one side and those greater are on the other.
   2. **Implementation**:
      1. Select the last element as the pivot.
      2. Maintain a pointer that tracks the position of the smaller element.
      3. Swap elements to ensure that smaller elements are moved to the front.
   3. **Effectiveness**:
      1. Simplicity of implementation.
      2. Performs well on average $\text{O(n} \log \text{n)}$, but can degrade to $O(n^2)$ in the worst case (e.g., already sorted arrays).

We are going to solve the Quick Sort with Hoare Partition. Quick sort is a **divide-and-conquer** sorting algorithm that works by:

1. **Partitioning** the array around a pivot element: elements smaller than the pivot go to the left, and elements greater than the pivot go to the right.
2. **Recursively sorting** the sub-arrays: after partitioning, quick sort is called recursively on the left and right sub-arrays.

### The Functions in the Program

1. **`partition(arr, start, end)`**: This function selects a pivot element, rearranges the array such that elements less than the pivot are on the left and elements greater than the pivot are on the right, and returns the final position of the pivot.
2. **`quick_sort(arr, start, end)`**: This function implements the recursive quick sort algorithm. It calls the `partition` function and recursively sorts the left and right partitions.

Here is the complete walkthrough of the quick sort algorithm using the example `array1 = [10, 5, 8, 12, 15, 6, 3, 9, 16]`.

### Initial Setup:

```python
array1 = [10, 5, 8, 12, 15, 6, 3, 9, 16]
```

We call the main function `quick_sort`:

```python
quick_sort(arr=array1, start=0, end=len(array1)-1)
```

This translates to:

```python
quick_sort(arr=array1, start=0, end=8)
```

### Step 1: `quick_sort(arr, start=0, end=8)` - First Call

- The first call to `quick_sort` checks if `start < end`. Since `start = 0` and `end = 8`, the condition is `True`, so it proceeds to call the `partition` function:

```python
partition_index = partition(arr, start=0, end=8)
```

### Step 2: `partition(arr, start=0, end=8)` - First Partitioning

- **Pivot Selection**: In the partition function, we select the pivot element as the element at the `start` index (`arr[0] = 10`). So, `pivot = 10` and `pivot_index = 0`.

- Now, we start rearranging the elements around the pivot (`10`).

#### Iteration Details:

- **Start at `start = 0` and `end = 8`**:

  - **Move `start` right**: The loop `while start < len(arr) and arr[start] <= pivot:` moves `start` to the right until an element greater than the pivot is found.
  - `arr[start] = 10`, so `start` moves to index `1` (value `5`), which is less than `pivot`, so it moves to index `2` (value `8`), which is also less than `pivot`, so it moves further to index `3` (value `12`), which **is greater than the pivot**. Therefore, `start = 3`.

  - **Move `end` left**: The loop `while arr[end] > pivot:` moves `end` left until it finds an element smaller than or equal to the pivot. Starting from `end = 8`, we check `arr[end] = 16`, which is greater than `pivot = 10`, so we move `end` to index `7` (value `9`), which is **less than** the pivot.
  - Since `start < end`, we swap `arr[start]` with `arr[end]`, i.e., swap `arr[3]` (value `12`) with `arr[7]` (value `9`).
  - Array after swap: `[10, 5, 8, 9, 15, 6, 3, 12, 16]`
  - After the swap, the array looks like this: `[10, 5, 8, 9, 15, 6, 3, 12, 16]`.

- **Repeat the process**:

  - **Move `start` right**: `arr[start] = 15` (value `15`), which is greater than `pivot = 10`, so we stop moving `start`. It stays at index `4`.
  - **Move `end` left**: Now we move `end` to index `6` (value `3`), which is less than `pivot = 10`. Therefore, `end = 6`.

  - Swap `arr[start]` with `arr[end]`, i.e., swap `arr[4]` (value `15`) with `arr[6]` (value `3`).
  - Array after swap: `[10, 5, 8, 9, 3, 6, 15, 12, 16]`
  - After the swap, the array looks like this: `[10, 5, 8, 9, 3, 6, 15, 12, 16]`.

  - **Move `start` right**: `arr[start] = 15`, which is greater than the pivot, so it stops at index `6`.

  - **Move `end` left**: Now we move `end` to index `5` (value `6`), which is less than the pivot. So, we swap `arr[start]` (value `15`) with `arr[end]` (value `6`).
  - Array after swap: `[10, 5, 8, 9, 3, 6, 15, 12, 16]`

  - **End of iteration**: `start = 6`, `end = 5`, so the loop stops. We now swap `arr[pivot_index]` (pivot value `10`) with `arr[end]` (value `15`).
  - Array after swap: `[6, 5, 8, 9, 3, 10, 15, 12, 16]`
  - The pivot `10` is now at index `5`. This is the partition index.

### Step 3: After the First Partition

The array after the first partitioning is:

```python
[6, 5, 8, 9, 3, 10, 15, 12, 16]
```

The pivot `10` is at index `5`. Now, we need to recursively sort the left and right sub-arrays:

- Left sub-array: `[6, 5, 8, 9, 3]` (from index 0 to 4)
- Right sub-array: `[15, 12, 16]` (from index 6 to 8)

### Step 4: Sorting Left Sub-array (`arr[0..4]`)

We now recursively call `quick_sort(arr, start=0, end=4)` to sort the left sub-array.

#### Step 4a: `partition(arr, start=0, end=4)` - Second Partitioning

- **Pivot Selection**: The pivot element is `arr[0] = 6`.
- Now, we start rearranging elements around the pivot (`6`).

#### Iteration Details:

- **Start at `start = 0` and `end = 4`**:

  - **Move `start` right**: The loop moves `start` right. `arr[start] = 6` (pivot), so it stops at `start = 1` (value `5`), which is less than the pivot. Move further to index `2` (value `8`), which is greater than the pivot. We stop here at index `2`.

  - **Move `end` left**: `arr[end] = 3`, which is less than the pivot. So we swap `arr[start]` with `arr[end]`, i.e., swap `arr[2]` (value `8`) with `arr[4]` (value `3`).
  - Array after swap: `[6, 5, 3, 9, 8, 10, 15, 12, 16]`
  - After the swap, the array looks like this: `[6, 5, 3, 9, 8, 10, 15, 12, 16]`.

- **End of iteration**: Now we swap `arr[0]` (pivot `6`) with `arr[end]` (value `8`).
  - Array after swap: `[3, 5, 6, 9, 8, 10, 15, 12, 16]`

Now, the pivot `6` is at index `2`. The array is partitioned into two sub-arrays:

- Left sub-array: `[3, 5]` (from index 0 to 1)
- Right sub-array: `[9, 8]` (from index 3 to 4)

### Step 5: Sorting Left Sub-array (`arr[0..1]`)

Now, we recursively call `quick_sort(arr, start=0, end=1)` on the left sub-array:

```python
[3, 5]
```

Since the sub-array has only two elements, the partitioning and sorting steps are straightforward. The array is already sorted after partitioning, and it remains:

```python
[3, 5]
```

### Step 6: Sorting Right Sub-array (`arr[3..4]`)

Next, we call `quick_sort(arr, start=3, end=4)` to sort the right sub-array:

```python
[9, 8]
```

This results in swapping the elements so that the array becomes:

```python
[8, 9]
```

### Step 7: Sorting Right Sub-array (`arr[6..8]`)

Next, we recursively call `quick_sort(arr, start=6, end=8)` to sort the right sub-array.

#### Step 7a: `partition(arr, start=6, end=8)` - Third Partitioning

- **Pivot Selection**: The pivot is `arr[6] = 15`.
- Rearranging around the pivot:
  - The elements are already in a state where they need no more rearrangement.
  - The pivot `15` stays at its position. - The partitioning results in:
    `python
[12, 15, 16]
`

Now, the array is fully sorted:

```python
[3, 5, 6, 8, 9, 10, 12, 15, 16]
```

### Final Output:

After all the recursive calls and partitioning, the array is sorted as:

```python
[3, 5, 6, 8, 9, 10, 12, 15, 16]
```
