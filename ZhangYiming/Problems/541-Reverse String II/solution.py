class Solution(object):
    def reverseStr(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: str
        """
        count = 0
        s = list(s)
        while True:
            i = count * 2 * k
            j = min(i + k - 1, len(s) - 1)
            if i >= j:
                break
            while i < j:
                s[i], s[j] = s[j], s[i]
                i += 1
                j -= 1
            count += 1
        
        return "".join(s)
        
