class Solution(object):
    def findDisappearedNumbers(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        for i in range(len(nums)):
            if nums[i] != i + 1:
                x = nums[i]
                nums[i] = -1
                j = 0
                while x != -1 and nums[x-1] != x:
                    nums[x-1], x = x, nums[x-1]
        
        return [ i + 1  for i in range(len(nums)) if nums[i] == -1 ]
        
