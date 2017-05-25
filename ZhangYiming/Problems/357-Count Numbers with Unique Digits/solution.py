class Solution(object):
    def countNumbersWithUniqueDigits(self, n):
        """
        :type n: int
        :rtype: int
        """
        sum = 1
        result = 1
        for i in range(1, min(10, n)):
            sum *= 10 - i
            result += sum
        return 1 if n == 0 else result * 9 + 1
        
