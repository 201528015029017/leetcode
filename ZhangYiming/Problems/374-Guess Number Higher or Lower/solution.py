# The guess API is already defined for you.
# @param num, your guess
# @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
# def guess(num):

class Solution(object):
    def guessNumber(self, n):
        """
        :type n: int
        :rtype: int
        """
        start = 1
        end = n
        while True:
            num = start + (end - start) / 2
            result = guess(num)
            if result == 0:
                return num
            elif result == 1:
                start = num + 1
            elif result == -1:
                end = num - 1
            else:
                return None
                
            
        
