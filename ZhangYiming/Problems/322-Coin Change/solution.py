class Solution(object):
    def coinChange(self, coins, amount):
        """
        :type coins: List[int]
        :type amount: int
        :rtype: int
        """
        dic = {0: 0}
        for i in range(1, amount + 1):
            count = amount + 1
            for x in coins:
                if i - x in dic and dic[i-x] + 1 < count:
                    count = dic[i-x] + 1
            if count != amount + 1:
                dic[i] = count
        
        return dic[amount] if amount in dic else -1
