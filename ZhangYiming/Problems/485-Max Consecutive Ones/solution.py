class Solution(object):
    def findMaxConsecutiveOnes(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        ans = 0
        next = 0
        for x in nums:
            if x == 1:
                next += 1
                ans = max(ans, next)
            else:
                next = 0
        return ans
