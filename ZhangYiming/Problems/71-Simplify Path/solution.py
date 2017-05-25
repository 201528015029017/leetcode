class Solution(object):
    def simplifyPath(self, path):
        """
        :type path: str
        :rtype: str
        """
        result = path.split("/")
        
        stack = []
        for x in result:
            if x == "":
                continue
            if x == ".":
                continue
            if x == "..":
                if len(stack) > 0:
                    stack.pop()
                continue
            stack.append(x)
        
        return "/" + "/".join(stack)
