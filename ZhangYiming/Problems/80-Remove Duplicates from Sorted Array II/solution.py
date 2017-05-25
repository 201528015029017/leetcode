class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) < 2:
            return len(nums)
        i = 2
        for n in range(2, len(nums)):
            if nums[n] > nums[i-2]:
                nums[i] = nums[n]
                i += 1
        return i
        
