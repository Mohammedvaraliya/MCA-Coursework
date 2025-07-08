class Solution:
    def removeDuplicates(self, nums: list[int]) -> int:
        
        if not nums:
            return 0
        
        prev = 0
        next = 1
        
        while next < len(nums):
            if nums[prev] != nums[next]:
                prev += 1
                nums[prev] = nums[next]
            next += 1
        
        return prev + 1


if __name__ == "__main__":
        
    obj = Solution()

    nums1 = [1,1,2]
    print(obj.removeDuplicates(nums1))

    nums2 = [0,0,1,1,1,2,2,3,3,4]
    print(obj.removeDuplicates(nums2))