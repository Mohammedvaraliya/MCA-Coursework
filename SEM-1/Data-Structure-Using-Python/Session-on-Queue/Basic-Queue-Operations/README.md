### **Queue Data Structure Explanation**

A **Queue** is a linear data structure that follows the **First In, First Out (FIFO)** principle. This means that the element that is added first to the queue will be the first one to be removed, like a line at a ticket counter or a queue in a print spooler.

In simpler terms:

- **Enqueue** operation: Adds an item to the end (rear) of the queue.
- **Dequeue** operation: Removes an item from the front of the queue.
- **Peek/Front** (not implemented in this code): Returns the item at the front of the queue without removing it.
- **is_empty**: Checks whether the queue is empty or not.
- **Display**: Displays all items in the queue.

The queue operates with two main operations:

1. **Enqueue** - Add an element to the queue.
2. **Dequeue** - Remove an element from the queue.

---

### **Code Walkthrough:**

Here is the code that implements the queue using the `deque` class from the `collections` module. The `deque` (double-ended queue) allows fast appends and pops from both ends, making it ideal for implementing a queue.

#### 1. **Queue Class Initialization:**

```python
class Queue:
    def __init__(self):
        self.queue = deque()
```

- The `Queue` class is initialized with an empty deque object. The deque will store the elements of the queue.
- The `self.queue` holds the actual queue elements and will be used for both enqueuing and dequeuing items.

#### 2. **Enqueue Operation:**

```python
def enqueue(self, item):
    self.queue.append(item)
    print(f"Enqueued: {item}")
```

- The `enqueue` method adds an item to the end of the queue using `deque.append(item)`.
- It also prints the item that has been enqueued.

#### 3. **Dequeue Operation:**

```python
def dequeue(self):
    if not self.is_empty():
        removed_item = self.queue.popleft()
        print(f"Dequeued: {removed_item}")
        return removed_item
    else:
        print("Queue is empty. Cannot dequeue.")
        return None
```

- The `dequeue` method removes and returns an item from the front of the queue using `deque.popleft()`.
- If the queue is empty, it prints a message indicating that the dequeue operation cannot proceed.
- The method checks if the queue is empty using the `is_empty()` function.

#### 4. **Check if Queue is Empty:**

```python
def is_empty(self):
    return len(self.queue) == 0
```

- This method returns `True` if the queue is empty (i.e., its length is 0), and `False` otherwise.

#### 5. **Display the Queue:**

```python
def display(self):
    print("Queue:", list(self.queue))
```

- The `display` method prints the current state of the queue. It converts the deque to a list to make it easier to read.

---

### **Example Walkthrough Step by Step**

Let's walk through an example where we enqueue and dequeue some elements from the queue.

```python
if __name__ == "__main__":
    Q = Queue()  # Step 1: Create an instance of the Queue

    Q.enqueue(5)  # Step 2: Enqueue 5
    Q.enqueue(6)  # Step 3: Enqueue 6
    Q.enqueue(7)  # Step 4: Enqueue 7
    Q.enqueue(8)  # Step 5: Enqueue 8

    Q.display()  # Step 6: Display the queue after enqueuing

    Q.dequeue()  # Step 7: Dequeue an item from the queue

    Q.display()  # Step 8: Display the queue after dequeuing
```

#### Step-by-Step Execution:

1. **Create a Queue Instance:**

   ```python
   Q = Queue()
   ```

   - The queue `Q` is initialized as an empty deque: `deque([])`.

2. **Enqueue 5:**

   ```python
   Q.enqueue(5)
   ```

   - The `enqueue(5)` method adds the item `5` to the queue.
   - The queue state becomes: `deque([5])`.

3. **Enqueue 6:**

   ```python
   Q.enqueue(6)
   ```

   - The `enqueue(6)` method adds the item `6` to the queue.
   - The queue state becomes: `deque([5, 6])`.

4. **Enqueue 7:**

   ```python
   Q.enqueue(7)
   ```

   - The `enqueue(7)` method adds the item `7` to the queue.
   - The queue state becomes: `deque([5, 6, 7])`.

5. **Enqueue 8:**

   ```python
   Q.enqueue(8)
   ```

   - The `enqueue(8)` method adds the item `8` to the queue.
   - The queue state becomes: `deque([5, 6, 7, 8])`.

6. **Display the Queue:**

   ```python
   Q.display()
   ```

   - The `display()` method prints the current state of the queue.
   - Output:
     ```
     Queue: [5, 6, 7, 8]
     ```

7. **Dequeue an Item (Remove from Front):**

   ```python
   Q.dequeue()
   ```

   - The `dequeue()` method removes the front item from the queue (which is `5`).
   - The queue state becomes: `deque([6, 7, 8])`.
   - Output:
     ```
     Dequeued: 5
     ```

8. **Display the Queue After Dequeue:**
   ```python
   Q.display()
   ```
   - The `display()` method prints the current state of the queue after the dequeue operation.
   - Output:
     ```
     Queue: [6, 7, 8]
     ```
