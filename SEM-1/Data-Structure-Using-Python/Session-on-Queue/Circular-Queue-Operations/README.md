### **Circular Queue Data Structure**

A **Circular Queue** is a variant of the normal queue, where the queue operations are done in a circular manner. The basic idea is to use the available spaces efficiently, especially when elements are dequeued, and to avoid wasting the space at the front of the queue after dequeue operations. This is particularly useful in situations where the queue is implemented in a fixed-size array.

In a **Circular Queue**, when an element is removed from the front, the space is freed up and can be reused by adding new elements at the rear, even if the front of the queue is ahead of the rear in the array.

### **Key Concepts in Circular Queue:**

1. **Enqueue:** Insert an element at the rear of the queue.
2. **Dequeue:** Remove an element from the front of the queue.
3. **Full Condition:** A circular queue is full when the next position of the rear is the front.
4. **Empty Condition:** A circular queue is empty when the front is equal to the rear.
5. **Size Calculation:** The size of the queue is determined by the difference between the rear and front, adjusted by the size of the array.
6. **Peek:** A function that allows you to view the front element without dequeuing it.

### **Code Explanation:**

Here's a breakdown of the `CircularQueue` class:

#### 1. **Initialization (`__init__`):**

```python
def __init__(self, size):
    self.size = size
    self.queue = [None] * size
    self.front = -1
    self.rear = -1
```

- The queue is initialized with a given size (`size`).
- The `queue` list stores the elements.
- `front` and `rear` are initialized to `-1`, indicating an empty queue.

#### 2. **Enqueue Operation (`enqueue`):**

```python
def enqueue(self, item):
    if (self.rear + 1) % self.size == self.front:
        print("Queue is full. Cannot enqueue.")
        return
    elif self.front == -1:
        self.front = self.rear = 0
    else:
        self.rear = (self.rear + 1) % self.size
    self.queue[self.rear] = item
    print(f"Enqueued: {item}")
```

- **Full Check:** If the queue is full (i.e., the next position of the `rear` is equal to `front`), it prints "Queue is full. Cannot enqueue."
- **Empty Queue Check:** If the queue is empty (`front == -1`), both `front` and `rear` are set to `0`.
- **Rear Pointer Update:** If the queue is not empty, the `rear` pointer is updated using modulo operation: `(self.rear + 1) % self.size`. This ensures the `rear` wraps around to the beginning when it reaches the end of the array.
- The item is then added at the updated `rear` position.

#### 3. **Dequeue Operation (`dequeue`):**

```python
def dequeue(self):
    if self.front == -1:
        print("Queue is empty. Cannot dequeue.")
        return None
    removed_item = self.queue[self.front]
    self.queue[self.front] = None
    if self.front == self.rear:
        self.front = self.rear = -1
    else:
        self.front = (self.front + 1) % self.size
    print(f"Dequeued: {removed_item}")
    return removed_item
```

- **Empty Queue Check:** If the queue is empty (`front == -1`), it prints "Queue is empty. Cannot dequeue."
- The item at the `front` position is removed, and that position is set to `None`.
- If the `front` equals `rear`, this means the queue becomes empty, so both `front` and `rear` are reset to `-1`.
- If there are still items in the queue, the `front` pointer is updated using `(self.front + 1) % self.size`.

#### 4. **Empty Check (`is_empty`):**

```python
def is_empty(self):
    return self.front == self.rear
```

- The queue is empty when `front` is equal to `rear`.

#### 5. **Size of Queue (`size_of_queue`):**

```python
def size_of_queue(self):
    return (self.rear - self.front + self.size) % self.size
```

- The size of the queue is calculated by considering the circular nature of the queue. The formula `(self.rear - self.front + self.size) % self.size` gives the correct number of elements in the queue.

#### 6. **Peek Operation (`peek`):**

```python
def peek(self):
    if self.is_empty():
        print("Queue is empty")
        return None
    return self.queue[self.front]
```

- The `peek` function returns the item at the front of the queue without removing it.
- If the queue is empty, it prints "Queue is empty" and returns `None`.

#### 7. **Display Operation (`display`):**

```python
def display(self):
    print("Circular Queue:", self.queue)
```

- The `display` function prints the current state of the circular queue.

---

### **Example Walkthrough:**

Let's walk through an example where we perform a sequence of operations on the circular queue:

```python
if __name__ == "__main__":

    Q = CircularQueue(5)  # Step 1: Create a queue with size 5.
    Q.enqueue(5)          # Step 2: Enqueue 5
    Q.enqueue(6)          # Step 3: Enqueue 6
    Q.enqueue(7)          # Step 4: Enqueue 7
    Q.enqueue(8)          # Step 5: Enqueue 8
    Q.display()           # Step 6: Display the queue

    Q.dequeue()           # Step 7: Dequeue one element
    Q.display()           # Step 8: Display the queue again

    Q.size_of_queue()     # Step 9: Get the size of the queue

    Q.peek()              # Step 10: Peek the front element
```

#### Step 1: Create the Circular Queue

```python
Q = CircularQueue(5)
```

- A queue of size `5` is initialized. The internal list is `[None, None, None, None, None]`. The `front` and `rear` are both `-1`.

#### Step 2: Enqueue 5

```python
Q.enqueue(5)
```

- Since the queue is empty, both `front` and `rear` are set to `0`.
- The queue now becomes: `[5, None, None, None, None]`.

#### Step 3: Enqueue 6

```python
Q.enqueue(6)
```

- The `rear` pointer moves to the next position, which is `1`.
- The queue now becomes: `[5, 6, None, None, None]`.

#### Step 4: Enqueue 7

```python
Q.enqueue(7)
```

- The `rear` pointer moves to `2`.
- The queue now becomes: `[5, 6, 7, None, None]`.

#### Step 5: Enqueue 8

```python
Q.enqueue(8)
```

- The `rear` pointer moves to `3`.
- The queue now becomes: `[5, 6, 7, 8, None]`.

#### Step 6: Display the Queue

```python
Q.display()
```

- The queue is displayed as:
  ```
  Circular Queue: [5, 6, 7, 8, None]
  ```

#### Step 7: Dequeue an Element

```python
Q.dequeue()
```

- The element at the `front` (which is `5`) is dequeued.
- The `front` pointer is moved to `1`, and the queue becomes: `[None, 6, 7, 8, None]`.

#### Step 8: Display the Queue After Dequeue

```python
Q.display()
```

- The queue is displayed as:
  ```
  Circular Queue: [None, 6, 7, 8, None]
  ```

#### Step 9: Get the Size of the Queue

```python
Q.size_of_queue()
```

- The size of the queue is calculated as `(self.rear - self.front + self.size) % self.size = (3 - 1 + 5) % 5 = 3`.
- Output: `3`.

#### Step 10: Peek the Front Element

```python
Q.peek()
```

- The element at the front of the queue is `6`, which is returned by the `peek` function.
- Output: `6`.
