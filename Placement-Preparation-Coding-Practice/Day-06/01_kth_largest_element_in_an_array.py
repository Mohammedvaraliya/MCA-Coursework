import heapq

class Solution:
    def findKthLargest_with_sorting(self, nums: list[int], k: int) -> int:
        nums = sorted(nums)

        return nums[-k]
    
    def findKthLargest(self, nums: list[int], k: int) -> int:
        heap = []

        for num in nums:
            heapq.heappush(heap, num)
            if len(heap) > k:
                heapq.heappop(heap)
        
        return heap[0]


if __name__ == "__main__":
    
    obj = Solution()

    nums1 = [3,2,1,6,5,4]
    k1 = 2
    print(obj.findKthLargest_with_sorting(nums1, k1))
    print(obj.findKthLargest(nums1, k1))