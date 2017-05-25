class Solution(object):
    def findRadius(self, houses, heaters):
        """
        :type houses: List[int]
        :type heaters: List[int]
        :rtype: int
        """
        def partition(a, i, j):
            m = i + (j - i)/3
            a[i], a[m] = a[m], a[i]
            key = a[i]
            while i < j:
                while i < j and a[j] >= key:
                    j -= 1
                a[i] = a[j]
                while i < j and a[i] <= key:
                    i += 1
                a[j] = a[i]
            a[i] = key
            return i
        
        def quickSort(a, start, end):
            if start >= end:
                return
            p = partition(a, start, end)
            quickSort(a, start, p - 1)
            quickSort(a, p + 1, end)
            
        quickSort(houses, 0, len(houses) - 1)
        quickSort(heaters, 0, len(heaters) - 1)
        
        
        if not houses:
            return 0
        if not heaters:
            return None
            
        i = 0
        j = 0
        
        radius = 0
        max_radius = max(abs(houses[-1] - heaters[0]), abs(houses[0] - heaters[0]))
        
        while j < len(houses):
            while i < len(heaters) and heaters[i] < houses[j]:
                i += 1
            temp = max_radius
            if i < len(heaters) and temp > abs(heaters[i] - houses[j]): temp = abs(heaters[i] - houses[j])
            if i > 0 and temp > abs(heaters[i-1] - houses[j]): temp = abs(heaters[i-1] - houses[j])
            if radius < temp: radius = temp
            j += 1
        
        return radius
            
        
        
        
            
            
