class Solution(object):
    def choose(self, a, b):
        i = a
        j = b
        while (i % 2 == 0 and j % 2 == 0):
            i /= 2
            j /= 2
        if i % 2 == 0:
            return -1
        else:
            return 1
            
    def integerReplacement(self, n):
        """
        :type n: int
        :rtype: int
        """
        count = 0
        
        while n != 1:
            if n == 3:
                return count + 2
            if n % 2 == 0:
                n /= 2
                count += 1
            else:
                n += self.choose(n-1, n+1)
                count += 1
        
        return count
