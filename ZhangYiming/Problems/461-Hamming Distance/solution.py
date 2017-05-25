class Solution(object):
    def hammingDistance(self, x, y):
        """
        :type x: int
        :type y: int
        :rtype: int
        """
        count = 0
        result = x ^ y
        while result > 0:
            if result % 2 == 1:
                count += 1
            result /= 2
        return count
