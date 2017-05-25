class Solution(object):
    def isPowerOfTwo(self, n):
        """
        :type n: int
        :rtype: bool
        """
        if n <= 0:
            return False
            
        while n > 1:
            if n & 1 == 0:
                n >>= 1
            else:
                return False
        
        return True
