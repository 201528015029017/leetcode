class Solution(object):
    def findWords(self, words):
        """
        :type words: List[str]
        :rtype: List[str]
        """
        keyboard = []
        keyboard.append(set(list("qwertyuiopQWERTYUIOP")))
        keyboard.append(set(list("asdfghjklASDFGHJKL")))
        keyboard.append(set(list("zxcvbnmZXCVBNM")))
        
        result = []
        for s in words:
            
            count = -1
            if len(s) == 0:
                continue
            
            if s[0] in keyboard[0]:
                count = 0
            elif s[0] in keyboard[1]:
                count = 1
            elif s[0] in keyboard[2]:
                count = 2
                
            if count == -1:
                continue
            
            for c in s:
                if c not in keyboard[count]:
                    count = -1
                    break
            
            if count == -1:
                continue
            
            result.append(s)
            
        return result
        
