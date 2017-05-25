# Definition for a point.
# class Point(object):
#     def __init__(self, a=0, b=0):
#         self.x = a
#         self.y = b

class Solution(object):
    def maxPoints(self, points):
        """
        :type points: List[Point]
        :rtype: int
        """
            

        
        point_count = {}
        point_dist = []
        for point in points:
            if (point.x, point.y) in point_count:
                point_count[(point.x, point.y)] += 1
            else:
                point_dist.append(point)
                point_count[(point.x, point.y)] = 1
        
        if len(point_dist) <= 1:
            return len(points)
            
        def gcd(a, b):
            if a == 0 or b == 0:
                return a ^ b
            if a < b:
                a, b = b, a
            while(b != 0):
                a, b = b, a % b
            return a
            
        def line(i, j):
            a = point_dist[i].y - point_dist[j].y
            b = point_dist[j].x - point_dist[i].x
            c = - point_dist[i].x * a - point_dist[i].y * b
            d = gcd(abs(a), abs(b))
            if d == 0:
                return (a, b, c)
            if a < 0 or (a == 0 and b < 0):
                a, b, c = -a, -b, -c
            return (a/d, b/d, c/d)     
            
        line_point = {}
        line_count = {}
        for i in range(len(point_dist) - 1):
            for j in range(i + 1, len(point_dist)):
                x = line(i, j)
                if x in line_count:
                    if i not in line_point[x]:
                        line_point[x].add(i)
                        line_count[x] += point_count[(point_dist[i].x, point_dist[i].y)]
                    if j not in line_point[x]:
                        line_point[x].add(j)
                        line_count[x] += point_count[(point_dist[j].x, point_dist[j].y)]
                else:
                    line_point[x] = {i, j}
                    line_count[x] = point_count[(point_dist[i].x, point_dist[i].y)] + point_count[(point_dist[j].x, point_dist[j].y)]
        
        count = 2    
        for key in line_count:
            if count < line_count[key]:
                count = line_count[key]
        
        return count
                
                
        
        
