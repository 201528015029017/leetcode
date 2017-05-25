class Solution(object):
    def strStr(self, haystack, needle):
        """
        :type haystack: str
        :type needle: str
        :rtype: int
        """
        next = [0 for i in range(len(needle))]
        for i in range(1, len(needle)):
            j = next[i-1]
            while j != 0 and needle[i] != needle[j]:
                j = next[j - 1]
            if j == 0:
                next[i] = 1 if needle[i] == needle[0] else 0
            else:
                next[i] = j + 1
        
        i = 0
        j = 0
        while True:
            if i == len(needle):
                return j - i
            if j == len(haystack):
                return -1
            if needle[i] == haystack[j]:
                i += 1
                j += 1
            else:
                if i == 0:
                    j += 1
                else:
                    i = next[i-1]
            
        
        
        
        
