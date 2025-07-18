class Solution:
    def findIndexOfFirstOccurence(self, haystack: str, needle: str) -> int:
        n = len(haystack)
        m = len(needle)

        for i in range(n):
            j = 0
            for k in range(i, n):
                if haystack[k] == needle[j]:
                    j += 1
                else:
                    break
                if j == m:
                    return i
                
        return -1
    
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

    haystack2 = "leetcode"
    needle2 = "leeto"
    print(obj.strStr(haystack2, needle2))

