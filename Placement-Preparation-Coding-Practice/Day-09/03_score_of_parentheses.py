class Solution:
    def scoreOfParentheses_1(self, s: str) -> int:
        stack = []

        for char in s:
            if char == "(":
                stack.append(char)
            else:  # char == ")"
                if stack[-1] == "(":
                    stack.pop()
                    stack.append(1)
                else:
                    val = 0
                    while stack[-1] != "(":
                        val += stack.pop()
                    stack.pop()
                    stack.append(2 * val)

        return sum(stack)
    
    def scoreOfParentheses(self, s: str) -> int:
        stack = [0]

        for char in s:
            if char == '(':
                stack.append(0)
            else:
                v = stack.pop()
                stack[-1] += max(2 * v, 1)

        return stack.pop()


if __name__ == "__main__":
    
    obj = Solution()

    s1 = "()"
    print(obj.scoreOfParentheses(s1))

    s2 = "()(())"
    print(obj.scoreOfParentheses(s2))