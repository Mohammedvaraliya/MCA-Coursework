class Solution:
    def productExceptSelf_not_acceptable(self, nums: list[int]) -> list[int]:
        product = 1
        for n in nums:
            product *= n
        print(product)
        for i in range(len(nums)):
            nums[i] = product // nums[i]
        
        return nums
    
    def productExceptSelf(self, nums: list[int]) -> list[int]:
        res = [1] * len(nums)

        prefix = 1
        for i in range(len(nums)):
            res[i] = prefix
            prefix *= nums[i]
        
        postfix = 1
        for i in range(len(nums) -1, -1, -1):
            res[i] *= postfix
            postfix *= nums[i]
        
        return res

        



if __name__ == "__main__":
    
    obj = Solution()

    nums1 = [1,2,3,4]
    print(obj.productExceptSelf(nums1))