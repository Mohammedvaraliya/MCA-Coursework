# A = [1, 4, 9]
# B = [9, 9, 9]
# ex1 = ''.join(map(str, A))
# print(ex1)
# print(int(ex1) + 1)
# print("\n")
# ex2 = ''.join(map(str, B))
# print(ex2)
# print(int(ex2) + 1)
# print("\n")

class Solution:
    def plusOne_method1(array):
        array[-1] += 1
        for i in reversed(range(1, len(array))):
            if array[i] != 10:
                break
            array[i] = 0
            array[i - 1] += 1
        if array[0] == 10:
            array[0] = 1
            array.append(0)
        return array
    
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

    digits3 = [9, 9, 9]
    print(obj.plusOne(digits3))

    digits4 = [1, 4, 9]
    print(obj.plusOne(digits4))


