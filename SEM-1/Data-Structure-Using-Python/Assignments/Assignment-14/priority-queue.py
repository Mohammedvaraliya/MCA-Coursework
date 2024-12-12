import heapq

class PriorityQueue:
    def __init__(self):
        self.heap = []
    
    def insert(self, data, priority):
        heapq.heappush(self.heap, (priority, data))
    
    def delete(self):
        if not self.is_empty():
            return heapq.heappop(self.heap)[1]
        else:
            return "Queue is empty"
    
    def peek(self):
        if not self.is_empty():
            return self.heap[0][1]
        else:
            return "Queue is empty"
    
    def is_empty(self):
        return len(self.heap) == 0
    
    def display(self):
        if self.is_empty():
            print("Priority Queue is empty")
        else:
            for priority, data in self.heap:
                print(f"Priority: {priority}, Data: {data}")

if __name__ == "__main__":

    pq = PriorityQueue()
    pq.insert("Task1", 2)
    pq.insert("Task2", 1)
    pq.insert("Task3", 3)
    pq.display()

    print(pq.delete())
    pq.display()
