class Solution:
    def restoreString(self, s: str, indices: list[int]) -> str:
        shuffled_chars = [''] * len(s)

        for i in range(len(s)):
            shuffled_chars[indices[i]] = s[i]

        return "".join(shuffled_chars)


if __name__ == "__main__":
        
    obj = Solution()

    s1 = "codeleet"
    indices1 = [4,5,6,7,0,2,1,3]
    print(obj.restoreString(s1, indices1))

    s2 = "abc"
    indices2 = [0,1,2]
    print(obj.restoreString(s2, indices2))