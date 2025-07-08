class Solution:
    def sortArrayByParity1(self, nums: list[int]) -> list[int]:
        res = []

        for i in range(len(nums)):
            if nums[i] % 2 == 0:
                res.append(nums[i])
        
        for i in range(len(nums)):
            if nums[i] % 2 != 0:
                res.append(nums[i])
        
        return res
    
    def sortArrayByParity(self, nums: list[int]) -> list[int]:
        left, right = 0, len(nums) - 1
        
        while left < right:
            if nums[left] % 2 == 0:
                left += 1
            elif nums[right] % 2 != 0:
                right -= 1
            else:
                nums[left], nums[right] = nums[right], nums[left]
                left += 1
                right -= 1
        
        return nums


if __name__ == "__main__":
        
    obj = Solution()

    nums1 = [3,1,2,4]
    print(obj.sortArrayByParity(nums1))

    nums2 = [0]
    print(obj.sortArrayByParity(nums2))