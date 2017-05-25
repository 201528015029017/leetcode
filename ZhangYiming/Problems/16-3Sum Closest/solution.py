class Solution(object):
    def threeSumClosest(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        if len(nums) < 3:
            return None
        
        nums.sort()
        result = nums[0] + nums[1] + nums[2]
        for i in range(len(nums) - 2):
            j = i + 1
            k = len(nums) - 1
            while j < k:
                current = nums[i] + nums[j] + nums[k]
                if current == target:
                    return target
                if abs(result - target) > abs(current - target):
                    result = current
                if current < target:
                    j += 1
                else:
                    k -= 1
        return result
            
