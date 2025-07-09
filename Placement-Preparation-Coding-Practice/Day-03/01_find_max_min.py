class Solution:
    def findMaxMin(self, nums: list[int]) -> str:
        min_val = float('inf')
        max_val = float('-inf')

        for n in nums:
            if n > max_val:
                max_val = n
            if n < min_val:
                min_val = n
        
        return f"Maximum is {max_val}, and minimum is {min_val}"


if __name__ == "__main__":
        
    obj = Solution()

    nums1 = [5,3, 9, 2, 8]
    print(obj.findMaxMin(nums1))
    print(nums1)