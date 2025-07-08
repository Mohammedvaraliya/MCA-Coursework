class Solution:
    def reverseString(self, s: list[str]) -> None:
        """
        Do not return anything, modify s in-place instead.
        """
        p = 0
        q = len(s) - 1

        for i in range(len(s)):
            if p <= q:
                temp = s[p]
                s[p] = s[q]
                s[q] = temp
                p += 1
                q -= 1


if __name__ == "__main__":
        
        obj = Solution()

        s1 = ["h","e","l","l","o"]
        obj.reverseString(s1)

        print(s1)