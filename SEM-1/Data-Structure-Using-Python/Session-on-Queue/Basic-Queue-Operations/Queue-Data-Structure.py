from collections import deque

class Queue:
    def __init__(self):
        self.queue = deque()

    def enqueue(self, item):
        self.queue.append(item)
        print(f"Enqueued: {item}")

    def dequeue(self):
        if not self.is_empty():
            removed_item = self.queue.popleft()
            print(f"Dequeued: {removed_item}")
            return removed_item
        else:
            print("Queue is empty. Cannot dequeue.")
            return None

    def is_empty(self):
        return len(self.queue) == 0

    def display(self):
        print("Queue:", list(self.queue))




if __name__ == "__main__":

    Q = Queue()
    Q.enqueue(5)
    Q.enqueue(6)
    Q.enqueue(7)
    Q.enqueue(8)

    Q.display()

    Q.dequeue()

    Q.display()