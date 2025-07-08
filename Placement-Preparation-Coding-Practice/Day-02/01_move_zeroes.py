class Solution:
    def moveZeroes(self, nums: list[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        non_zero_pos = 0
        
        for i in range(len(nums)):
            if nums[i] != 0:
                nums[non_zero_pos] = nums[i]
                non_zero_pos += 1
        
        for i in range(non_zero_pos, len(nums)):
            nums[i] = 0


if __name__ == "__main__":
        
    obj = Solution()

    nums1 = [0,1,0,3,12]
    obj.moveZeroes(nums1)
    print(nums1)

    nums2 = [0]
    obj.moveZeroes(nums2)
    print(nums2)

    nums3 = [0,0,1]
    obj.moveZeroes(nums3)
    print(nums3)