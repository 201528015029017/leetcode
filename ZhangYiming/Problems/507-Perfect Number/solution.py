class Solution(object):
    def checkPerfectNumber(self, num):
        """
        :type num: int
        :rtype: bool
        """
        if num < 5:
            return False
            
        sum = 1
        e = int(math.sqrt(num))
        if e*e == num:
            sum += e
        elif num % e == 0:
            sum += e + num/e
        for i in range(2, e):
            if num % i == 0:
                sum += i + num/i
                
        return sum == num
