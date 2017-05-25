class Solution(object):
    def myAtoi(self, str):
        """
        :type str: str
        :rtype: int
        """
        l = []
        for c in str:
            if "0" <= c <= "9":
                l.append(c)
            elif c == "-" and len(l) == 0:
                l.append(c)
            elif c == "+" and len(l) == 0:
                l.append(c)
            elif c == " " and len(l) == 0:
                continue
            else:
                break
        
        if len(l) == 0:
            return 0
        
        i = 0
        flag = 1
        if l[i] == "-":
            flag = -1
            i += 1
        elif l[i] == "+":
            i += 1
            
        while i < len(l) and l[i] == "0":
            i += 1
        num = 0
        
        while i < len(l):
            num = num * 10 + ord(l[i]) - ord("0")
            i += 1
        
        if num * flag > 2147483647:
            return 2147483647
        if num * flag < -2147483648:
            return -2147483648
            
        return num * flag
            
            
