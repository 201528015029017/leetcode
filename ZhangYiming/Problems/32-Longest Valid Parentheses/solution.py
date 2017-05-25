class Solution(object):
    def longestValidParentheses(self, s):
        """
        :type s: str
        :rtype: int
        """
        left = [0]
        length = [0]
        ans = 0
        for c in s:
            if c == '(':
                if length[-1] == 0:
                    left[-1] += 1
                else:
                    left.append(1)
                    length.append(0)
            else:
                if left[-1] > 0:
                    left[-1] -= 1
                    length[-1] += 2
                    if left[-1] == 0 and len(left) >= 2:
                        left.pop()
                        length[-2] += length[-1]
                        length.pop()
                else:
                    ans = max(length[-1], ans)
                    left = [0]
                    length = [0]
        return max(max(length), ans)
                
