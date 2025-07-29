class Stack():
    def __init__(self):
        self.items = []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        if not self.is_empty():
            return self.items.pop()

    def is_empty(self):
        return self.items == []

    def peek(self):
        if not self.is_empty():
            return self.items[-1]   

    def get_Stack(self):
        return self.items


class Solution:
    def convertInfixToPostfix(self, expr: str) -> str:
        precedence = {'+': 1, '-': 1, '*': 2, '/': 2}
        stack = Stack()
        postfix = ""

        for ch in expr:
            if ch.isalnum():
                postfix += ch
            elif ch == '(':
                stack.push(ch)
            elif ch == ')':
                while not stack.is_empty() and stack.peek() != '(':
                    postfix += stack.pop()
                stack.pop()
            elif ch in precedence:
                while (not stack.is_empty() and stack.peek() != '(' and
                       precedence[ch] <= precedence.get(stack.peek(), 0)):
                    postfix += stack.pop()
                stack.push(ch)

        while not stack.is_empty():
            postfix += stack.pop()

        return postfix
    
    def convertInfixToPrefix(self, expr: str) -> str:
        # Step 1: Reverse the expression and swap parentheses
        expr = expr[::-1]
        expr = list(expr)
        for i in range(len(expr)):
            if expr[i] == '(':
                expr[i] = ')'
            elif expr[i] == ')':
                expr[i] = '('
        expr = ''.join(expr)

        # Step 2: Get postfix of the modified expression
        postfix = self.convertInfixToPostfix(expr)

        # Step 3: Reverse postfix to get prefix
        prefix = postfix[::-1]

        return prefix


if __name__ == "__main__":
    obj = Solution()

    expr = "(a+b)*(c+e)"
    print("Infix Expression:", expr)
    print("Postfix Expression:", obj.convertInfixToPostfix(expr))
    print("Prefix Expression:", obj.convertInfixToPrefix(expr))
