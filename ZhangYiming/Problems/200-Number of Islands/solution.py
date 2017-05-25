class Solution(object):
    def numIslands(self, grid):
        """
        :type grid: List[List[str]]
        :rtype: int
        """
        height = len(grid)
        if height == 0:
            return 0
            
        width = len(grid[0])
        if width == 0:
            return 0
            
        def neighbour(i, j):
            return [(i + ic, j + jc) for ic, jc in [(-1, 0), (0, -1), (1, 0), (0, 1)] \
                if 0 <= i + ic < height and 0 <= j + jc < width and grid[i+ic][j+jc] == "1"]
        
        def change(i, j):
            grid[i][j] = "0"
            for (r, c) in neighbour(i, j):
                change(r, c)
        
        count = 0
        for i in range(0, height):
            for j in range(0, width):
                if grid[i][j] == "1":
                    count += 1
                    change(i, j)
        
        return count
                    
        
