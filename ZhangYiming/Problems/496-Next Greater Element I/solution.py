class Solution(object):
    def nextGreaterElement(self, findNums, nums):
        """
        :type findNums: List[int]
        :type nums: List[int]
        :rtype: List[int]
        """
        Dict = {}
        stack = []
        for x in nums:
            while len(stack) > 0 and stack[-1] < x:
                Dict[stack.pop()] = x
            stack.append(x)
        
        result = []
        for x in findNums:
            if Dict.has_key(x):
                result.append(Dict[x])
            else:
                result.append(-1)
        
        return result
