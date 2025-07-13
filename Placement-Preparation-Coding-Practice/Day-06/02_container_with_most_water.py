class Solution:
    def maxArea(self, height: list[int]) -> int:
        res = 0
        left, right = 0, len(height) - 1


        while left < right:
            area = (right - left) * min(height[left], height[right])
            res = max(res, area)

            if height[left] < height[right]:
                left += 1
            else:
                right -= 1
            
        return res



if __name__ == "__main__":
    
    obj = Solution()

    height1 = [1,8,6,2,5,4,8,3,7]
    print(obj.maxArea(height1))