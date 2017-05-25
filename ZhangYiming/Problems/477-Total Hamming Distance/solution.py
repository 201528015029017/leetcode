class Solution(object):
    def totalHammingDistance(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        count = [0 for i in range(32)]
        
        for x in nums:
            for i in range(32):
                count[i] += x & 1
                x >>= 1
        
        return sum([ (len(nums) - count[i]) * count[i] for i in range(32)])
