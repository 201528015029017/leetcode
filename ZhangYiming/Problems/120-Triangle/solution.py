class Solution(object):
    def minimumTotal(self, triangle):
        """
        :type triangle: List[List[int]]
        :rtype: int
        """
        depth = len(triangle)
        result = [0 for i in range(depth)]
        i = 0
        while i < depth:
            num1 = result[0]
            result[0] += triangle[i][0]
            for k in range(1, i):
                num2 = result[k]
                result[k] = min(num1, num2) + triangle[i][k]
                num1 = num2
            result[i] = num1 + triangle[i][i]
            i += 1
            print result
        
        return min(result)
                
