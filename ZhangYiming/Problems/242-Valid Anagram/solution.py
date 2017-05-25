class Solution(object):
    def isAnagram(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        a = [0 for i in range(26)]
        for c in s:
            a[ord(c) - 97] += 1
        for c in t:
            a[ord(c) - 97] -= 1
        for x in a:
            if x != 0:
                return False
        return True
        
