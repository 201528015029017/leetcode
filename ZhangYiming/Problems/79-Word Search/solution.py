class Solution(object):
    def existStartWithXY(self, board, x, y, word, count):
        if count == len(word):
            return True
        c = word[count]
        if 0 <= x < len(board) and 0 <= y < len(board[0]) and board[x][y] == c:
            board[x][y] = False
            result = self.existStartWithXY(board, x + 1, y, word, count + 1) or \
                     self.existStartWithXY(board, x, y + 1, word, count + 1) or \
                     self.existStartWithXY(board, x - 1, y, word, count + 1) or \
                     self.existStartWithXY(board, x, y - 1, word, count + 1)
            board[x][y] = c
            return result
        else:
            return False
        
        
    def exist(self, board, word):
        """
        :type board: List[List[str]]
        :type word: str
        :rtype: bool
        """
        if not board:
            return False
        
        for i in range(len(board)):
            for j in range(len(board[0])):
                if self.existStartWithXY(board, i, j, word, 0):
                    return True
        
        return False
    
        
