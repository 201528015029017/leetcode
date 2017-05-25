class Solution(object):
    def getMoneyAmount(self, n):
        """
        :type n: int
        :rtype: int
        """
        # dic = {1:(0,0), 2:(1,0)}
        # for n in range(3, 100):
        #     result = []
        #     m = (100000, 100000, 1000000)
        #     for k in range(1, n/2 + 1):
        #         m1 = dic[k][0] + dic[n-k-1][0] + n - 1
        #         m2 = k*(n-1) + dic[k][1] + dic[n-k-1][1] + dic[n-k-1][0]*(k + 1)
        #         if m1 < m[0]:
        #             if m1 + m2 > m[0] + m[1]:
        #                 result.append(m)
        #             m = (m1, m2, k+1)
        #     dic[n] = m
        #     print n, m, [(a, b, c) for a, b, c in result if a + b < m[0] + m[1]]
        
        # dic = {}
        # def get(i, j):
        #     if i == j:
        #         return 0
        #     if i == j - 1:
        #         return i
        #     if (i, j) in dic:
        #         return dic[(i, j)]
        #     m = get(i + 2, j) + (j - i) * (i + 1)
        #     for k in range(i+2, j):
        #         if get(i, k - 1) + get(k + 1, j) + (j - i) * k < m:
        #             m = get(i, k - 1) + get(k + 1, j) + (j - i) * k
        #     dic[(i, j)] = m
        #     return m
        
        # return get(1, n)
        
        f = [[0 for i in range(n + 1)] for j in range(n + 1)]
        for i in range(1, n):
            f[i][i+1] = i
        for len in range(2, n):
            for start in range(1, n - len + 1):
                i = start
                j = start + len
                f[i][j] = min([k + max(f[i][k - 1], f[k+1][j]) for k in range(i + 1, j)])
        return f[1][n]
        
            
            
