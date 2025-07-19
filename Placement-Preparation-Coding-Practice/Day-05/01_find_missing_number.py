class Solution:
    def missingNumber_method_1(self, nums: list[int]) -> int:
        res = len(nums)

        for i in range(len(nums)):
            res += (i - nums[i])
        
        return res
    
    def missingNumber_method_2(self, nums: list[int]) -> int:
        n = len(nums)
        xorr = n

        for i in range(n):
            xorr ^= i ^ nums[i]
            
        return xorr






if __name__ == "__main__":

    obj = Solution()

    nums1 = [3,0,1]
    print(obj.missingNumber_method_1(nums=nums1))
    print(obj.missingNumber_method_2(nums=nums1), "\n")

    nums2 = [0,1]
    print(obj.missingNumber_method_1(nums=nums2))
    print(obj.missingNumber_method_2(nums=nums2), "\n")

    nums3 = [9,6,4,2,3,5,7,0,1]
    print(obj.missingNumber_method_1(nums=nums3))
    print(obj.missingNumber_method_2(nums=nums3))