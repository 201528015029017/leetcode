class Solution(object):
    def findRelativeRanks(self, nums):
        """
        :type nums: List[int]
        :rtype: List[str]
        """
        val = [i for i in range(len(nums))]
        val.sort(key = lambda x: -nums[x])
        result = [0 for i in range(len(val))]
        for i in range(len(val)):
            if i == 0:
                result[val[i]] = "Gold Medal"
            elif i == 1:
                result[val[i]] = "Silver Medal"
            elif i == 2:
                result[val[i]] = "Bronze Medal"
            else:
                result[val[i]] = str(i+1)
        return result
