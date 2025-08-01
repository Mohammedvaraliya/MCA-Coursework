class MatrixDiagonalMaximizer:
    def __init__(self, matrix):
        self.matrix = matrix
        self.size = len(matrix)
        self.diagonal_indices = [(i, i) for i in range(self.size)]

    def maximize_diagonal(self):
        non_diag_positions = []
        
        # Gather all non-diagonal values with their positions
        for i in range(self.size):
            for j in range(self.size):
                if i != j:
                    non_diag_positions.append(((i, j), self.matrix[i][j]))

        # Sort non-diagonal elements in descending order by value
        non_diag_positions.sort(key=lambda x: x[1], reverse=True)
        # print(non_diag_positions)

        used = set()  # Track already used swap positions

        for idx, (i, j) in enumerate(self.diagonal_indices):
            for k in range(len(non_diag_positions)):
                (ni, nj), val = non_diag_positions[k]
                if (ni, nj) not in used:
                    # Swap diagonal with selected value
                    self.matrix[i][j], self.matrix[ni][nj] = self.matrix[ni][nj], self.matrix[i][j]
                    used.add((ni, nj))
                    break  # Move to next diagonal cell

    def calculate_diagonal_sum(self):
        return sum(self.matrix[i][i] for i in range(self.size))

    def display_result(self):
        self.maximize_diagonal()
        diagonal_sum = self.calculate_diagonal_sum()
        print(f"sum={diagonal_sum}")
        for row in self.matrix:
            print(" ".join(f"{val:3}" for val in row))


if __name__ == "__main__":
    print("Enter a 5x5 matrix (each row on a new line, space-separated):")
    matrix = []
    try:
        for _ in range(5):
            row = list(map(int, input().split()))
            if len(row) != 5:
                raise ValueError("Each row must contain exactly 5 integers.")
            matrix.append(row)

        maximizer = MatrixDiagonalMaximizer(matrix)
        maximizer.display_result()

    except ValueError as e:
        print(f"Invalid input: {e}")
