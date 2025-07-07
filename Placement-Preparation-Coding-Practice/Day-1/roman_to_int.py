class Solution:
    def romanToInt(self, s: str) -> int:
        dict = {
            'I': 1,
            'V': 5,
            'X': 10,
            'L': 50,
            'C': 100,
            'D': 500,
            'M': 1000
        }

        prev = 0
        curr = 0
        total = 0

        for i in range(len(s)):
            curr = dict[s[i]]

            if curr > prev:
                total += curr - 2 * prev

            else:
                total += curr
            prev = curr

        return total


if __name__ == "__main__":

    obj = Solution()

    s1 = "III"
    print(obj.romanToInt(s1))

    s2 = "LVIII"
    print(obj.romanToInt(s2))

    s3 = "MCMXCIV"
    print(obj.romanToInt(s3))