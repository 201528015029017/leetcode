# class TreeNode(object):
#     def __init__(self, val):
#         self.val = val
#         self.count = 0
#         self.sons = {}
        
#     def add(self, s):
#         self.count += 1
#         if len(s) <= 0:
#             return
#         if s[0] not in self.sons:
#             self.sons[s[0]] = TreeNode(s[0])
#         self.sons[s[0]].add(s[1:])
#         return
    
#     def value(self, s):
#         if self.count <= 1:
#             return []
#         if len(self.sons) > 0:
#             result = []
#             for key in self.sons:
#                 result += self.sons[key].value(s + self.val)
#             return result
#         else:
#             return [s + self.val]
            
# class Solution(object):
#     def findRepeatedDnaSequences(self, s):
#         root = TreeNode("")
#         for i in range(len(s) - 9):
#             c = s[i:i+10]
#             root.add(c)
#         return root.value()
                    
        

class Solution(object):
    def findRepeatedDnaSequences(self, s):
        dict = {}
        for i in range(len(s) - 9):
            c = s[i:i+10]
            if c in dict:
                dict[c] += 1
            else:
                dict[c] = 1
        
        result = []
        for key in dict:
            if dict[key] > 1:
                result.append(key)
        
        return result
        
    
            
