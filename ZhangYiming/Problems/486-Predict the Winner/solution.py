class data(object):
    def __init__(self, s1, s2):
        self.s1 = s1
        self.s2 = s2

class Solution(object):
    def PredictTheWinner(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        if len(nums) == 0:
            return True
            
        score = [[data(0, 0) if c != r else data(nums[r], 0) for c in range(len(nums))  ] for r in range(len(nums))]
        for length in range(1, len(nums)):
            for start in range(0, len(nums) - length):
                i = start
                j = start + length

                if score[i+1][j].s1 > score[i][j-1].s1:
                    score[i][j].s1 = score[i][j-1].s2 + nums[j]
                    score[i][j].s2 = score[i][j-1].s1
                else:
                    score[i][j].s1 = score[i+1][j].s2 + nums[i]
                    score[i][j].s2 = score[i+1][j].s1
            
        return score[0][len(nums) - 1].s1 >= score[0][len(nums) - 1].s2
                    
                    
        
        
