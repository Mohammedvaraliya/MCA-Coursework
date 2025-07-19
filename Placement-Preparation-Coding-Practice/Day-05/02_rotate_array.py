class Solution:
    def rotate(self, nums: list[int], k: int) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        print(f"Original array: {nums}")
        
        n = len(nums)
        k %= n

        left, right = 0, n - 1
        while left < right:
            nums[left], nums[right] = nums[right], nums[left]
            left += 1
            right -= 1
        
        print(f"Reversed entire array: {nums}")

        left, right = 0, k - 1
        while left < right:
            nums[left], nums[right] = nums[right], nums[left]
            left += 1
            right -= 1
        
        print(f"Reversed first {k} elements: {nums}")

        left, right = k, n - 1
        while left < right:
            nums[left], nums[right] = nums[right], nums[left]
            left += 1
            right -= 1
        
    # Simplified Version of above code
    def rotateSimplified(self, nums: list[int], k: int) -> None:
        n = len(nums)
        k %= n
        nums.reverse()
        nums[:k] = reversed(nums[:k])
        nums[k:] = reversed(nums[k:])



if __name__ == "__main__":
    obj = Solution()
    nums1 = [1, 2, 3, 4, 5, 6, 7]
    k1 = 3
    obj.rotate(nums1, k1)
    print(nums1)