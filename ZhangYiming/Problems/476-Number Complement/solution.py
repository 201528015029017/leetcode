class Solution(object):
    def findComplement(self, num):
        """
        :type num: int
        :rtype: int
        """
        result = 0
        count = 1
        while num > 0:
            result += (1 - num % 2) * count
            count *= 2
            num /= 2
        return result
