class Solution:
    def findMajority(self, nums: list[int]) -> int:
        count = {}
        for n in nums:
            if n in count:
                count[n] += 1
            else:
                count[n] = 1
        
        max_count = 0
        res = -1
        for key, value in count.items():
            if value > max_count:
                max_count = value
                res = key

        return res

        



if __name__ == "__main__":
    
    obj = Solution()

    nums1 = [3, 3, 4, 2, 4, 4, 2, 4, 4]
    print(obj.findMajority(nums1))