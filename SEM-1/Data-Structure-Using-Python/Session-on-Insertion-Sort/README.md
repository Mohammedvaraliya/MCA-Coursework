# Insertion Sort Explanation

## Problem Statement

To implement **Insertion Sort** in Python, which sorts a list of elements by building a sorted portion one item at a time, inserting each element into its correct position within the sorted portion.

Insertion Sort is a simple and intuitive sorting algorithm that builds the sorted list one element at a time. It starts with the second element and compares it with the elements before it, inserting it into its appropriate position. This process is repeated for all elements, resulting in a sorted list.

1. **Working**:
   1. Divide the list into a sorted and an unsorted part.
   2. Pick each element from the unsorted part and insert it into the correct position in the sorted part.
2. This algorithm is efficient for small or nearly sorted data but performs poorly on large datasets with a time complexity of $O(n^2)$ in the worst case.
