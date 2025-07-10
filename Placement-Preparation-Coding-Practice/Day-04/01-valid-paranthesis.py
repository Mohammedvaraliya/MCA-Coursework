class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        dict = {
            ")":"(",
            "}":"{",
            "]":"["
        }

        for char in s:
            if char in dict.values():
                stack.append(char)
            elif char in dict.keys():
                if not stack or stack.pop() != dict[char]:
                    return False
        
        return not stack


if __name__ == "__main__":
    
    obj = Solution()

    s1 = "()"
    print(obj.isValid(s1))

    s2 = "()[]{}"
    print(obj.isValid(s2))