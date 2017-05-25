class Solution(object):
    def numberToWords(self, num):
        """
        :type num: int
        :rtype: str
        """
        tail = ["", " Thousand", " Million", " Billion"]
        number = ["", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"]
        number10 = ["", "Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"]
        
        def trans99(num):
            if num <= 20:
                return number[num]
            if num%10 == 0:
                return number10[num/10]
            else:
                return number10[num/10] + " " + number[num%10]
        
        def trans999(num):
            if num < 100:
                return trans99(num)
            
            if num%100 == 0:
                return number[num/100] + " Hundred"
            else:
                return number[num/100] + " Hundred " + trans99(num%100)
        
        
        if num == 0:
            return "Zero"
            
        nums = []
        while num > 0:
            nums.append(num%1000)
            num /= 1000
        
        
        result = []
        for i in range(len(nums) - 1, -1, -1):
            if nums[i] == 0:
                continue
            else:
                result.append(trans999(nums[i]) + tail[i])
            
        return " ".join(result)
