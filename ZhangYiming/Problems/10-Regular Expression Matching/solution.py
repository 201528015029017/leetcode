class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        expression = []
        for ch in p:
            if ch == "*":
                expression[-1] = expression[-1] + "*"
            else:
                expression.append(ch)
        
        dic = {}
        
        def isMatchStr(i, j):
            if (i, j) in dic:
                return dic[(i, j)]
                
            if i == len(s):
                for k in range(j, len(expression)):
                    if len(expression[k]) != 2:
                        dic[(i, j)] = False
                        return False
                dic[(i, j)] = True
                return True
            if j == len(expression):
                dic[(i, j)] = False
                return False
            
            ex = expression[j]
            ch = ex[0]
            if len(ex) == 1:
                if ch == "." or ch == s[i]:
                    dic[(i, j)] = isMatchStr(i + 1, j + 1)
                    return dic[(i, j)]
                else:
                    dic[(i, j)] = False
                    return False
            else:
                if ch == "." or ch == s[i]:
                    dic[(i, j)] = isMatchStr(i + 1, j) or isMatchStr(i + 1, j + 1) or isMatchStr(i, j + 1)
                    return dic[(i, j)]
                else:
                    dic[(i, j)] = isMatchStr(i, j + 1)
                    return dic[(i, j)]
        
        return isMatchStr(0, 0)
            
            
            
            
            
            
        
        
