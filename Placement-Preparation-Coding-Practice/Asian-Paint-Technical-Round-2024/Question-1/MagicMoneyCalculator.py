class MagicMoneyCalculator:
    def __init__(self, initial_amount, target_amount):
        self.initial = initial_amount
        self.target = target_amount
        self.count = 0
        self.trace = []  # To store step-by-step trace

    def calculate_entries(self):
        previous = self.initial
        current = self.initial + 1
        self.count = 1

        self.trace.append(f"Entry 1: ₹{previous} → ₹{current} (added ₹1)")

        while current < self.target:
            new_amount = previous + current
            self.count += 1
            self.trace.append(
                f"Entry {self.count}: ₹{previous} + ₹{current} = ₹{new_amount}"
            )
            previous = current
            current = new_amount

        return self.count

    def display_result(self):
        entries = self.calculate_entries()
        print(f"\nTo reach ₹{self.target} from ₹{self.initial},")
        print(f"You must enter the cave {entries} time(s).\n")
        print("📜 Step-by-step trace:")
        for step in self.trace:
            print(step)


if __name__ == "__main__":
    try:
        initial = int(input("Enter initial amount: "))
        target = int(input("Enter target amount: "))
        if initial < 0 or target < 0:
            raise ValueError("Amounts must be non-negative.")
        calculator = MagicMoneyCalculator(initial, target)
        calculator.display_result()
    except ValueError as e:
        print(f"Invalid input: {e}")
