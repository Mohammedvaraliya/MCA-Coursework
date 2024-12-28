# Graphs Data Structure

### Example Walkthrough of the Program:

This program implements a graph using an adjacency list and provides the following functionalities:

1. **Breadth-First Search (BFS) Traversal**
2. **Depth-First Search (DFS) Traversal**
3. **Adjacency Matrix Construction**

### Step 1: **Graph Initialization**

The graph is initialized with the following edges:

```python
edges = [
    (1, 2, 3),
    (2, 4, 5),
    (3, 6, 7),
    (4,),
    (5,),
    (6,),
    (7,)
]
```

- **Explanation:** Each tuple in the `edges` list represents a node and its adjacent nodes. For example, `(1, 2, 3)` means node `1` has edges to nodes `2` and `3`.

#### Graph Dictionary Construction:

When the `Graph` class is instantiated with the edges, it builds a dictionary (`self.graph_dict`) to represent the graph as an adjacency list. The construction proceeds as follows:

- `(1, 2, 3)` → Add `2` and `3` as neighbors of `1`.
- `(2, 4, 5)` → Add `4` and `5` as neighbors of `2`.
- `(3, 6, 7)` → Add `6` and `7` as neighbors of `3`.
- `(4,)`, `(5,)`, `(6,)`, `(7,)` → Add no additional neighbors (each node has no children).

**Final Graph Dictionary:**

```python
{
    1: [2, 3],
    2: [4, 5],
    3: [6, 7],
    4: [],
    5: [],
    6: [],
    7: []
}
```

The `print("Graph Dictionary:", self.graph_dict)` outputs the above dictionary.

### Step 2: **Adjacency Matrix Construction**

Next, the program constructs the **Adjacency Matrix** for the graph.

#### Steps to Build the Adjacency Matrix:

1. **Identify all unique nodes:**
   - Extract nodes from all the edges: `{1, 2, 3, 4, 5, 6, 7}`.
2. **Sort nodes:**
   - Sorted nodes list: `[1, 2, 3, 4, 5, 6, 7]`.
3. **Node-Index Mapping:**

   - Map each node to an index:

   ```python
   {
       1: 0,
       2: 1,
       3: 2,
       4: 3,
       5: 4,
       6: 5,
       7: 6
   }
   ```

4. **Initialize the adjacency matrix:**

   - A `7x7` matrix is created with all values set to `0` initially.

5. **Fill the adjacency matrix based on the graph dictionary:**
   - For each node and its neighbors, set the corresponding entry in the matrix to `1`:
     - Node `1` is connected to `2` and `3`, so set `matrix[0][1] = 1` and `matrix[0][2] = 1`.
     - Node `2` is connected to `4` and `5`, so set `matrix[1][3] = 1` and `matrix[1][4] = 1`.
     - Node `3` is connected to `6` and `7`, so set `matrix[2][5] = 1` and `matrix[2][6] = 1`.
     - Nodes `4`, `5`, `6`, and `7` have no outgoing edges.

**Adjacency Matrix:**

```python
Adjacency Matrix:
    1 2 3 4 5 6 7
1  0 1 1 0 0 0 0
2  0 0 0 1 1 0 0
3  0 0 0 0 0 1 1
4  0 0 0 0 0 0 0
5  0 0 0 0 0 0 0
6  0 0 0 0 0 0 0
7  0 0 0 0 0 0 0
```

The program prints the adjacency matrix using the `print_adjacency_matrix()` method.

### Step 3: **BFS Traversal**

The program now performs **Breadth-First Search (BFS)** starting from node `1`. BFS explores the graph level by level.

#### BFS Steps:

1. Start at node `1`. Mark it as visited and add it to the queue.
   - Queue: `[1]`, Visited: `{1}`
2. Dequeue `1`, visit its neighbors `2` and `3`. Add them to the queue.

   - Queue: `[2, 3]`, Visited: `{1, 2, 3}`

3. Dequeue `2`, visit its neighbors `4` and `5`. Add them to the queue.

   - Queue: `[3, 4, 5]`, Visited: `{1, 2, 3, 4, 5}`

4. Dequeue `3`, visit its neighbors `6` and `7`. Add them to the queue.

   - Queue: `[4, 5, 6, 7]`, Visited: `{1, 2, 3, 4, 5, 6, 7}`

5. Dequeue nodes `4`, `5`, `6`, and `7`, but they have no unvisited neighbors.
   - Queue: `[]`, Visited: `{1, 2, 3, 4, 5, 6, 7}`

**BFS Traversal Result:**

```python
BFS Traversal: [1, 2, 3, 4, 5, 6, 7]
```

### Step 4: **DFS Traversal**

The program then performs **Depth-First Search (DFS)** starting from node `1`. DFS explores as far as possible along each branch before backtracking.

#### DFS Steps:

1. Start at node `1`. Visit its neighbors `2` and `3` recursively.

   - Traversal: `[1]`

2. Visit node `2`, then its neighbors `4` and `5` recursively.

   - Traversal: `[1, 2]`

3. Visit node `4` (no further neighbors).

   - Traversal: `[1, 2, 4]`

4. Backtrack to `2`, then visit node `5` (no further neighbors).

   - Traversal: `[1, 2, 4, 5]`

5. Backtrack to `1`, then visit node `3`, and recursively visit its neighbors `6` and `7`.

   - Traversal: `[1, 2, 4, 5, 3]`

6. Visit node `6` (no further neighbors).

   - Traversal: `[1, 2, 4, 5, 3, 6]`

7. Backtrack to `3`, then visit node `7` (no further neighbors).
   - Traversal: `[1, 2, 4, 5, 3, 6, 7]`

**DFS Traversal Result:**

```python
DFS Traversal: [1, 2, 4, 5, 3, 6, 7]
```

### Step 5: **Program Output**

The program outputs the following results:

- **BFS Traversal:**

```python
BFS Traversal: [1, 2, 3, 4, 5, 6, 7]
```

- **DFS Traversal:**

```python
DFS Traversal: [1, 2, 4, 5, 3, 6, 7]
```

- **Adjacency Matrix:**

```python
Adjacency Matrix:
    1 2 3 4 5 6 7
1  0 1 1 0 0 0 0
2  0 0 0 1 1 0 0
3  0 0 0 0 0 1 1
4  0 0 0 0 0 0 0
5  0 0 0 0 0 0 0
6  0 0 0 0 0 0 0
7  0 0 0 0 0 0 0
```
