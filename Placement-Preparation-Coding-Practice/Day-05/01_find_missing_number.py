class Solution:
    def findMissingNumber(self, nums: list[int]) -> int:
        res = len(nums)

        for i in range(len(nums)):
            res += (i - nums[i])
        
        return res


if __name__ == "__main__":
    
    obj = Solution()

    nums1 = [0, 1, 3]
    print(obj.findMissingNumber(nums1))