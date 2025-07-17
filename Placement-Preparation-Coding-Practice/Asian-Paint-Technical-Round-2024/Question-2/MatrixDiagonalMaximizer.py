class MatrixDiagonalMaximizer:
    def __init__(self, matrix):
        self.matrix = matrix
        self.size = len(matrix)
        self.diagonal_indices = [(i, i) for i in range(self.size)]

    def maximize_diagonal(self):
        # Flatten and sort all values in descending order
        flat_values = sorted([val for row in self.matrix for val in row], reverse=True)

        # Replace diagonal elements with top values
        for i in range(self.size):
            row, col = self.diagonal_indices[i]
            self.matrix[row][col] = flat_values[i]

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
