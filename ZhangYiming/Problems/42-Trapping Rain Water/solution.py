class Solution(object):
    def trap(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        l, r = 0, len(height)-1
        lmax, rmax = 0, 0
        water = 0
        
        while l < r:
            if lmax <= rmax:
                while l < r and lmax >= height[l]:
                    water += lmax - height[l]
                    l += 1
                if l == r:
                    return water
                if lmax < height[l]:
                    lmax = height[l]
            else:
                while l < r and rmax >= height[r]:
                    water += rmax - height[r]
                    r -= 1
                if l == r:
                    return water
                if rmax < height[r]:
                    rmax = height[r]
        
        return water
