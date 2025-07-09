class Solution:
    def findSecondLargest_brute_force(self, nums: list[int]) -> int:
        nums = sorted(nums)
        
        return nums[-2]
    
    def findSecondLargest(self, nums: list[int]) -> int:
        first = second = float('-inf')
        
        for num in nums:
            if num > first:
                second = first
                first = num
            elif first > num > second:
                second = num
        
        return second


if __name__ == "__main__":
        
    obj = Solution()

    nums1 = [7, 3, 9, 2, 8]
    print(obj.findSecondLargest(nums1))