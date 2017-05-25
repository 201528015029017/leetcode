class Solution(object):
    def isPalindrome(self, s):
        """
        :type s: str
        :rtype: bool
        """
        i = 0
        j = len(s) - 1
        while i < j:
            if not ("a" <= s[j] <= "z" or "A" <= s[j] <= "Z" or "0" <= s[j] <= "9"):
                j -= 1
                continue
            if not ("a" <= s[i] <= "z" or "A" <= s[i] <= "Z" or "0" <= s[i] <= "9"):
                i += 1
                continue
            if s[i].lower() != s[j].lower():
                return False
            i += 1
            j -= 1
                
        return True
