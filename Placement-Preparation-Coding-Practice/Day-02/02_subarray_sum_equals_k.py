class Solution:
    def subarraySum_brute_force(self, nums: list[int], k: int) -> int:
        res = 0

        for i in range(len(nums)):
            current_sum = 0
            for j in range(i, len(nums)):
                current_sum += nums[j]
                if current_sum == k:
                    res += 1

        return res
    
    def subarraySum(self, nums: list[int], k: int) -> int:
        from collections import defaultdict

        prefix_sum_counts = defaultdict(int)
        prefix_sum_counts[0] = 1

        count = 0
        current_sum = 0

        for num in nums:
            current_sum += num
            count += prefix_sum_counts[current_sum - k]
            prefix_sum_counts[current_sum] += 1

        return count


if __name__ == "__main__":
        
    obj = Solution()

    nums1 = [1,1,1]
    k1 = 2
    print(obj.subarraySum_brute_force(nums1, k1))

    nums2 = [1,2,3]
    k2 = 3
    print(obj.subarraySum_brute_force(nums2, k2))