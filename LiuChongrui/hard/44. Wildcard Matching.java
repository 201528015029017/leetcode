/*
思路：解法1：
    dp求解
    f[i][j] 表示p串（带通配符的）的前i个和s串的前j个字符是否可以匹配
    当p[i-1] 为普通字符时，匹配p[i-1] 和 s[j-1]是否相等
    当p[i-1] 为？时，任意匹配s[j-1]
    当p[i-1] 为*时，分三种情况:
                                1) * 匹配0个字符 那么就是f[i-1][j]
                                2）* 匹配从当前字符开始匹配 那么就是f[i-1][j-1]
                                3）* 之前匹配过了字符 那么就是f[i][j-1]
    最后注意一下j=0的情况特判
    解法2：
    leetcode上看到的一个解法，宣称是O（n）的 但是最坏情况还是O（nm），不过写得很有意思，思想有点像非递归的回溯
    https://discuss.leetcode.com/topic/3040/linear-runtime-and-constant-space-solution
 */
//solution1
class Solution {
    public boolean isMatch(String s, String p) {
        int n = p.length();
        int m = s.length();
        boolean[][] f = new boolean[n+1][m+1];
        f[0][0] = true;
        for (int i = 1; i <= n; i++){
            for (int j = 0; j <= m; j++){
                if (p.charAt(i-1) == '?'){
                    if (j == 0) f[i][j] = false;
                    else f[i][j] = f[i-1][j-1];
                }
                else if (p.charAt(i-1) == '*'){
                    if (j == 0) f[i][j] = f[i-1][j];
                    else f[i][j] = f[i-1][j-1] || f[i][j-1] || f[i-1][j];
                }
                else{
                    if (j == 0) f[i][j] = false;
                    else f[i][j] = f[i-1][j-1] && (p.charAt(i-1) == s.charAt(j-1));
                }
            }
        }
        return f[n][m];
    }
}

//解法2
    boolean comparison(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){ //这一步其实就是*匹配0次
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){           //这里就是说匹配次数+1，match记录匹配的位置，p,s都会回退，其实就是类似于回溯了，所以复杂度不是线性的
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }