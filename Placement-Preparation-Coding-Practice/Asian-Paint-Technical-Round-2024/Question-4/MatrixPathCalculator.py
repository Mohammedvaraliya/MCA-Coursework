class MatrixPathCalculator:
    def __init__(self):
        self.matrix = [
            ['A', 'B', 'C', 'D', 'E', 'F'],
            ['G', 'H', 'I', 'J', 'K', 'L'],
            ['M', 'N', 'O', 'P', 'Q', 'R'],
            ['S', 'T', 'U', 'V', 'W', 'X'],
            ['Y', 'Z', '1', '2', '3', '4'],
            ['5', '6', '7', '8', '9', '0']
        ]
        self.char_positions = self._map_char_positions()

    def _map_char_positions(self):
        """
        Preprocess the matrix to map each character to its (row, col) position.
        """
        positions = {}
        for i in range(6):
            for j in range(6):
                positions[self.matrix[i][j]] = (i, j)
        return positions

    def find_position(self, char):
        """
        Return the position of the character in the matrix.
        """
        if char in self.char_positions:
            return self.char_positions[char]
        else:
            raise ValueError(f"Character '{char}' not found in matrix.")

    def steps_between(self, pos1, pos2):
        """
        Calculate Manhattan distance between two positions.
        """
        return abs(pos1[0] - pos2[0]) + abs(pos1[1] - pos2[1])

    def calculate_total_steps(self, input_string):
        """
        Calculate total steps required to traverse the input string from 'A'.
        """
        input_string = input_string.upper()
        total_steps = 0
        current_position = self.find_position('A')  # Start from 'A'

        for char in input_string:
            next_position = self.find_position(char)
            step_count = self.steps_between(current_position, next_position)
            print(f"Move from '{self.matrix[current_position[0]][current_position[1]]}' to '{char}' takes {step_count} steps.")
            total_steps += step_count
            current_position = next_position

        return total_steps


if __name__ == "__main__":
    input_string = input("Enter an alphanumeric string: ").strip()
    calculator = MatrixPathCalculator()
    try:
        total = calculator.calculate_total_steps(input_string)
        print(f"\nTotal steps to reach all characters in '{input_string.upper()}': {total}")
    except ValueError as e:
        print(e)
