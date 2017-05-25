class Solution(object):
    def complexNumberMultiply(self, a, b):
        """
        :type a: str
        :type b: str
        :rtype: str
        """
        def convertToNum(num):
            nums = num.split("+")
            return (int(nums[0]), int(nums[1][:-1]))
        
        def convertToString(num0, num1):
            return str(num0) + "+" + str(num1) + "i"
        
        ac, ai = convertToNum(a)
        bc, bi = convertToNum(b)
        sc, si = ac * bc - ai * bi, ac * bi + ai * bc
        
        return convertToString(sc, si)
        
        
