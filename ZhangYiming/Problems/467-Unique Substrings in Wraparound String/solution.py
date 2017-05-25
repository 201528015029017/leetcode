class Solution(object):
    def findSubstringInWraproundString(self, p):
        """
        :type p: str
        :rtype: int
        """
        if not p:
            return 0
            
        count = [ 0 for i in range(26) ]
        
        maxLength = 1
        pre = ord(p[0]) - 97
        count[pre] = 1
        for i in range(1, len(p)):
            now = ord(p[i]) - 97
            if (pre == now - 1) or (pre == 25 and now == 0):
                maxLength += 1
            else:
                maxLength = 1
            count[now] = max(count[now], maxLength)
            pre = now
        return sum(count)
        
        
