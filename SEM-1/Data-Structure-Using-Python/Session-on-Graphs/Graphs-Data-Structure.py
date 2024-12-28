from collections import deque

class Graph:
    def __init__(self, edges):
        self.edges = edges
        self.graph_dict = {}

        for start, *ends in self.edges:
            if start in self.graph_dict:
                self.graph_dict[start].extend(ends)
            else:
                self.graph_dict[start] = list(ends)

        print("Graph Dictionary: ", self.graph_dict)

    def adjacency_matrix(self):
        nodes = set()
        for start, *ends in self.edges:
            nodes.add(start)
            nodes.update(ends)

        nodes = sorted(nodes)  # Sort the nodes for consistency
        node_index = {node: idx for idx, node in enumerate(nodes)}

        size = len(nodes)
        matrix = [[0] * size for _ in range(size)]

        # Fill the adjacency matrix
        for start, ends in self.graph_dict.items():
            start_idx = node_index[start]
            for end in ends:
                end_idx = node_index[end]
                matrix[start_idx][end_idx] = 1

        return matrix, node_index

    def print_adjacency_matrix(self):
        matrix, node_index = self.adjacency_matrix()

        # Print the header row
        print("Adjacency Matrix:")
        header = "   " + " ".join(str(node) for node in node_index)
        print(header)

        # Print each row
        for i, row in enumerate(matrix):
            print(f"{list(node_index.keys())[i]}  " + " ".join(map(str, row)))

    def bfs(self, start):
        visited = set()
        queue = deque([start])
        traversal = []

        while queue:
            node = queue.popleft()
            if node not in visited:
                visited.add(node)
                traversal.append(node)
                queue.extend(neighbor for neighbor in self.graph_dict.get(node, []) if neighbor not in visited)

        return traversal

    def dfs(self, start, visited=None):
        if visited is None:
            visited = set()
        visited.add(start)
        traversal = [start]
        for neighbor in self.graph_dict.get(start, []):
            if neighbor not in visited:
                traversal.extend(self.dfs(neighbor, visited))
        return traversal


if __name__ == "__main__":
    
    edges = [
        (1, 2, 3),
        (2, 4, 5),
        (3, 6, 7),
        (4,),
        (5,),
        (6,),
        (7,)
    ]

    graph = Graph(edges)
    
    print("BFS Traversal:", graph.bfs(1))
    print("DFS Traversal:", graph.dfs(1))

    graph.print_adjacency_matrix()