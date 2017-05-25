class Solution(object):
    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        dic = {0:[""], 1:["()"]}
        for i in range(2, n + 1):
            result = []
            for k in range(i):
                for s1 in dic[k]:
                    for s2 in dic[i-k-1]:
                        result.append("(" + s1 + ")" + s2)
            dic[i] = result
        return dic[n]
        #     "()n-1" "(1)n-2" ... "(n-2)()" "(n-1)"
