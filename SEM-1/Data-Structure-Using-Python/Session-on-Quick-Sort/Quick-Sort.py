def partition(arr, start, end):
    pivot_index = start
    pivot = arr[pivot_index]

    while start < end:

        while start < len(arr) and arr[start] <= pivot:
            start += 1

        while arr[end] > pivot:
            end -= 1

        if start < end:
            arr[start], arr[end] = arr[end], arr[start]

    arr[end], arr[pivot_index] = arr[pivot_index], arr[end]

    return end

def quick_sort(arr, start, end):
    if len(arr)== 1:
        return

    if start < end:
        partition_index = partition(arr, start, end)
        quick_sort(arr, start, partition_index-1)
        quick_sort(arr, partition_index+1, end)


if __name__ == "__main__":

    array1 = [10, 5, 8, 12, 15, 6, 3, 9, 16]
    quick_sort(arr = array1, start = 0, end = len(array1) - 1)
    print(array1)

    array2 = [56, 61, 54, 64, 59, 53, 58, 56, 52, 60, 51]
    quick_sort(arr = array2, start = 0, end = len(array2) - 1)
    print(array2)
