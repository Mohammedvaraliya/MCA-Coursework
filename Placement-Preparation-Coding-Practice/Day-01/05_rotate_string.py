class Solution:
    def rotateString(self, s: str, goal: str) -> bool:
        if len(s) != len(goal):
            return False
        
        for i in range(len(s)):
            checkStr = s[i:len(s)+1] + s[0:i]
            if checkStr == goal:
                return True
        
        return False


if __name__ == "__main__":

    obj = Solution()

    s1 = "abcde"
    goal1 = "cdeab"
    print(obj.rotateString(s1, goal1))

    s2 = "abcde"
    goal2 = "abced"
    print(obj.rotateString(s2, goal2))