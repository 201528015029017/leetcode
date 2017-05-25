class Solution(object):
    def gameOfLife(self, board):
        """
        :type board: List[List[int]]
        :rtype: void Do not return anything, modify board in-place instead.
        """
        # 3  1 to 0:  live to dead 2 0 to 1: dead to live
        height = len(board)
        if not height:
            return
            
        width = len(board[0])
        if not width:
            return       
        
        
        def neighbour(i, j):
            result = 0
            for ic in [-1, 0, 1]:
                for jc in [-1, 0, 1]:
                    if 0 <= i + ic < height and 0 <= j + jc < width and (ic != 0 or jc != 0):
                        result += board[i + ic][j + jc] % 2
            return result
        
        for i in range(height):
            for j in range(width):
                num = neighbour(i, j)
                if board[i][j] == 0:
                    if num == 3:
                        board[i][j] = 2
                else:
                    if num < 2 or num > 3:
                        board[i][j] = 3
        
        for i in range(height):
            for j in range(width):
                board[i][j] = 1 if board[i][j]  % 3 else 0
        
        return
                        
        
