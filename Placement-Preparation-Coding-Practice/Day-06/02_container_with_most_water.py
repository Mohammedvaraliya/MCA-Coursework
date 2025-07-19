class Solution:
    # Brute force
    # Time complexity : O(n^2)
    def maxArea_method_1(self, height: list[int]) -> int:
        # Brute force
        res = 0

        for l in range(len(height)):
            for r in range(l + 1, len(height)):
                area = (r - l) * min(height[l], height[r])
                res = max(res, area)

        return res

    # Time complexity : O(n)
    def maxArea_method_2(self, height: list[int]) -> int:
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

    X = [1,8,6,2,5,4,8,3,7]
    print(obj.maxArea_method_1(X))
    print(obj.maxArea_method_2(X))

    Y = [1,1]
    print(obj.maxArea_method_1(Y))
    print(obj.maxArea_method_2(Y))