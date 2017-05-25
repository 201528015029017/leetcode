class Solution(object):
    def islandPerimeter(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        
        height = len(grid)
        if height == 0:
            return 0
        
        width = len(grid[0])
        if width == 0:
            return 0
            
        result = 0
        
        for i in range(height):
            for j in range(width):
                if grid[i][j] == 1:
                    result += 1 if i - 1 < 0 or i - 1 >= height or j < 0 or j >= width or grid[i-1][j] == 0 else 0
                    result += 1 if i + 1 < 0 or i + 1 >= height or j < 0 or j >= width or grid[i+1][j] == 0 else 0
                    result += 1 if i < 0 or i >= height or j - 1 < 0 or j - 1 >= width or grid[i][j-1] == 0 else 0
                    result += 1 if i < 0 or i >= height or j + 1 < 0 or j + 1 >= width or grid[i][j+1] == 0 else 0
        return result
        
