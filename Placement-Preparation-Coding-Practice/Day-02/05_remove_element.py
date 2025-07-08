class Solution:
    def removeElement(self, nums: list[int], val: int) -> int:
        
        cur = 0
        
        for next in range(len(nums)):
            if nums[next] != val:
                nums[cur] = nums[next]
                cur += 1
        
        return cur


if __name__ == "__main__":
        
    obj = Solution()

    nums1 = [3,2,2,3]
    val1 = 3
    print(obj.removeElement(nums1, val1))
    print(nums1)

    nums2 = [0,0,1,1,1,2,2,3,3,4]
    val2 = 2
    print(obj.removeElement(nums2, val2))