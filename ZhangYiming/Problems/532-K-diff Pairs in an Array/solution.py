class Solution(object):
    def findPairs(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        number = set([])
        result = set([])
        count = 0
        if k < 0:
            return 0
        for x in nums:
            if x - k in number and (x-k, x) not in result:
                count += 1
                result.add((x-k, x))
            if x + k in number and (x, x+k) not in result:
                count += 1
                result.add((x, x+k))
            if x not in number:
                number.add(x)
        return count
