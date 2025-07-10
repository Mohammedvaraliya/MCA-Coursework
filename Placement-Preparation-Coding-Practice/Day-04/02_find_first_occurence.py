class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        res = -1

        for i in range(len(haystack)):
            if haystack[i] == needle[0] and haystack[i:len(needle) + i] == needle:
                res = i
                return res
            else:
                res = -1
        
        return res



if __name__ == "__main__":
    
    obj = Solution()

    haystack1 = "sadbutsad"
    needle1 = "sad"
    print(obj.strStr(haystack1, needle1))

    haystack2 = "hello"
    needle2 = "ll"
    print(obj.strStr(haystack2, needle2))