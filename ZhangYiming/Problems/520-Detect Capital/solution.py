class Solution(object):
    def detectCapitalUse(self, word):
        """
        :type word: str
        :rtype: bool
        """
        return True if re.match("^[A-Z]?([A-Z]*|[a-z]*)$", word) else False
