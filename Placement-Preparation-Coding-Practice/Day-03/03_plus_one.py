class Solution:    
    def plusOne(self, digits: list[int]) -> list[int]:
        n = len(digits)

        for i in range(n-1, -1, -1):
            if digits[i] < 9:
                digits[i] += 1
                return digits
            digits[i] = 0
        
        return [1] + digits


if __name__ == "__main__":
        
    obj = Solution()

    digits1 = [1,2,3]
    print(obj.plusOne(digits1))

    digits2 = [4,3,2,1]
    print(obj.plusOne(digits2))

    digits3 = [9, 9]
    print(obj.plusOne(digits3))