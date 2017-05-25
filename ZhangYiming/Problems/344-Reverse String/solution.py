class Solution(object):
    def reverseString(self, s):
        """
        :type s: str
        :rtype: str
        """
        l = list(s)
        i = 0
        j = len(l) - 1
        while i < j:
            l[i], l[j] = l[j], l[i]
            i += 1
            j -= 1
        return "".join(l)
        
