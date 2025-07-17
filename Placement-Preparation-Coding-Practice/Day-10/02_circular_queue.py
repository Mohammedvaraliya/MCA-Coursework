class CircularQueue:
    def __init__(self, size):
        self.size = size
        self.queue = [None] * size
        self.front = -1
        self.rear = -1

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
    
    def is_empty(self):
        return self.front == self.rear

    def size_of_queue(self):
        return (self.rear - self.front + self.size) % self.size

    def peek(self):
        if self.is_empty():
            print("Queue is empty")
            return None
        return self.queue[self.front]

    def display(self):
        print("Circular Queue:", self.queue)



if __name__ == "__main__":
    
    Q = CircularQueue(5)
    Q.enqueue(5)
    Q.enqueue(6)
    Q.enqueue(7)
    Q.enqueue(8)

    Q.display()

    Q.dequeue()

    Q.display()

    Q.size_of_queue()

    Q.peek()