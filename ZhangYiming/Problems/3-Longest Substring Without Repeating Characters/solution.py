class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        dict = {}
        last, ans = 0, 0
        for i in range(len(s)):
            c = s[i]
            last = min(i - (dict[c] if c in dict else -1), last + 1)
            ans = max(last, ans)
            dict[c] = i
        return ans
