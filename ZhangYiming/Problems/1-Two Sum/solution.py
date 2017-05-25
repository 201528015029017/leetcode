class Solution(object):

    def twoSum(self, nums, target):

        """

        :type nums: List[int]

        :type target: int

        :rtype: List[int]

        """

        Dict = {}

        for i in range(len(nums)):

            if Dict.has_key(target - nums[i]):

                return [Dict[target-nums[i]], i]

            else:

                Dict[nums[i]] = i

        

        return None

        
