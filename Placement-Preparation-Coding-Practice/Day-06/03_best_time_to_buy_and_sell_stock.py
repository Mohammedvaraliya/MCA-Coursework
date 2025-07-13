class Solution:
    def maxProfit_brute_force(self, prices: list[int]) -> int:
        max_profit = 0

        for i in range(len(prices) - 1):
            for j in range(i + 1, len(prices)):
                if prices[j] - prices[i] > max_profit:
                    max_profit = prices[j] - prices[i]

        return max_profit
    
    def maxProfit(self, prices: list[int]) -> int:
        max_profit = 0
        min = prices[0]

        for price in prices:
            if price < min:
                min = price

            profit = price - min

            if profit > max_profit:
                max_profit = profit
                
        return max_profit
    
    def maxProfit(self, prices: list[int]) -> int:
        max_profit = 0
        min = prices[0]

        for price in prices:
            if price < min:
                min = price
            else:
                max_profit = max(max_profit, (price - min))
                
        return max_profit



if __name__ == "__main__":
    
    obj = Solution()

    prices1 = [7,1,5,3,6,4]
    print(obj.maxProfit(prices1))