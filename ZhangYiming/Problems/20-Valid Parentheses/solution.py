class Solution(object):
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        val = {"(":1, ")":-1, "[":2, "]":-2, "{":3, "}":-3}
        stack = []
        for c in s:
            p = val[c]
            if p > 0:
                stack.append(p)
            elif not stack:
                return False
            elif stack.pop() + p != 0:
                return False
        
        return True if not stack else False
