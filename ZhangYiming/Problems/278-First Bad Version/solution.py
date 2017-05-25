# The isBadVersion API is already defined for you.
# @param version, an integer
# @return a bool
# def isBadVersion(version):

class Solution(object):
    def firstBadVersion(self, n):
        """
        :type n: int
        :rtype: int
        """
        good = 0
        bad = n
        while bad - good > 1:
            version = good + (bad - good) / 2
            result = isBadVersion(version)
            if result:
                bad = version
            else:
                good = version
        return bad
