class Solution(object):
    def updateMatrix(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[List[int]]
        """
        height = len(matrix)
        if not height:
            return matrix
        width = len(matrix[0])
        if not width:
            return matrix
            
        zeros = []
        for i in range(height):
            for j in range(width):
                if matrix[i][j] == 0:
                    zeros.append((i, j))
                else:
                    matrix[i][j] = width + height - 1
        
        def neighbour(i, j):
            result = []
            for ic, jc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                if 0 <= i + ic < height and 0 <= j + jc < width:
                    result.append((i+ic, j+jc))
            return result
        
        step = 0
        while zeros:
            temp = []
            step += 1
            for point in zeros:
                for c in neighbour(point[0], point[1]):
                    if step < matrix[c[0]][c[1]]:
                        matrix[c[0]][c[1]] = step
                        temp.append((c[0], c[1]))
            zeros = temp
        
        return matrix
        
