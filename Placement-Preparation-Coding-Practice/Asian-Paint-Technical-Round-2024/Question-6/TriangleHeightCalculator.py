class TriangleHeightCalculator:
    def __init__(self, total_balls):
        self.total_balls = total_balls
        self.height_complete = 0
        self.height_incomplete = 0

    def calculate_heights(self):
        """
        Calculates both complete and incomplete triangle heights.
        """
        balls_used = 0

        for row in range(1, self.total_balls + 1):
            balls_used += row
            if balls_used <= self.total_balls:
                self.height_complete = row
            self.height_incomplete = row
            if balls_used > self.total_balls:
                break

    def display_results(self):
        """
        Prints the results.
        """
        print(f"Total Balls: {self.total_balls}")
        print(f"Height-Complete = {self.height_complete}")
        print(f"Height-Incomplete = {self.height_incomplete}")


if __name__ == "__main__":
    try:
        N = int(input("Enter the number of balls: "))
        if N < 0:
            raise ValueError("Number of balls must be non-negative.")
        calculator = TriangleHeightCalculator(N)
        calculator.calculate_heights()
        calculator.display_results()
    except ValueError as e:
        print(f"Invalid input: {e}")
