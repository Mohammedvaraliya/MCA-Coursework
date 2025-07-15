class Stack:
    def __init__(self):
        self.items = []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        return self.items.pop() if not self.is_empty() else None

    def is_empty(self):
        return len(self.items) == 0

    def peek(self):
        return self.items[-1] if not self.is_empty() else None

    def get_stack(self):
        return self.items


class Solution:
    def __init__(self):
        self.stack = Stack()

    def is_match(self, p1, p2):
        return (p1 == '(' and p2 == ')') or \
               (p1 == '{' and p2 == '}') or \
               (p1 == '[' and p2 == ']')

    def is_paren_balanced(self, paren_string):
        self.stack = Stack()  # Reset stack for fresh check
        is_balanced = True
        index = 0

        while index < len(paren_string) and is_balanced:
            paren = paren_string[index]
            if paren in "({[":
                self.stack.push(paren)
            else:
                if self.stack.is_empty():
                    is_balanced = False
                else:
                    top = self.stack.pop()
                    if not self.is_match(top, paren):
                        is_balanced = False
            index += 1

        result = is_balanced and self.stack.is_empty()
        print("String is Balanced" if result else "String is not Balanced")
        return result

    def is_paren_balanced_2nd_approach(self, s: str) -> bool:
        matching = {
            ")":"(",
            "}":"{",
            "]":"["
        }
        stack = []

        for char in s:
            if char in matching:
                if stack and stack[-1] == matching[char]:
                    stack.pop()
                else:
                    return False
            else:
                stack.append(char)

        return not stack


if __name__ == "__main__":
    solution = Solution()

    print(solution.is_paren_balanced("()"))  # Balanced
    print(solution.is_paren_balanced_2nd_approach("()"))
    print()

    print(solution.is_paren_balanced("(([{{{([])}}}]))"))  # Balanced
    print(solution.is_paren_balanced_2nd_approach("(([{{{([])}}}]))"))
    print()

    print(solution.is_paren_balanced("[[}"))  # Not Balanced
    print(solution.is_paren_balanced_2nd_approach("[[}"))
    print()

    print(solution.is_paren_balanced("(([[])}[}]))"))  # Not Balanced
    print(solution.is_paren_balanced_2nd_approach("(([[])}[}]))"))
    print()

    print(solution.is_paren_balanced("()[]{}"))  # Balanced
    print(solution.is_paren_balanced_2nd_approach("()[]{}"))
    print()

    print(solution.is_paren_balanced("(]"))  # Not Balanced
    print(solution.is_paren_balanced_2nd_approach("(]"))
