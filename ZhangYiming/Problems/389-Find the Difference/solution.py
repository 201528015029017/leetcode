class Solution(object):
    def findTheDifference(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: str
        """
        result = 0
        for c in s:
            result ^= ord(c)
        for c in t:
            result ^= ord(c)
        return chr(result)
        
