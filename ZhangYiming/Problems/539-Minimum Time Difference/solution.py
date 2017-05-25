class Solution(object):
    def findMinDifference(self, timePoints):
        """
        :type timePoints: List[str]
        :rtype: int
        """
        def time(a):
            return ((int)(a.split(":")[0]) * 60 + (int)(a.split(":")[1])) % 1440
        
        dic = set([])
        for a in timePoints:
            tmp = time(a)
            if tmp not in dic:
                dic.add(tmp)
            else:
                return 0
        
        for i in range(1440):
            if i in dic:
                break
        
        diff = 1440
        start = i
        before = i
        for i in range(before + 1, 1440):
            if i in dic:
                if i - before < diff:
                    diff = i - before
                before = i
        
        if start + 1440 - before < diff:
            diff = start + 1440 - before
        
        return diff
                
            
            
        
        
        
