/*
思路：f[i]表示0..i-1有多少种解码方式，所以f[i] = f[i-1]+f[i-2]，当前位置的数字单独解码，或者和上一个组合一起解码
 */
public class Solution {
    public int numDecodings(String s) {
        if (s.length() == 0) return 0;
        int[] f = new int[s.length()+1];
        f[0] = 1;
        for (int i = 1; i <= s.length(); ++i){
            f[i] = 0;
            if (s.charAt(i-1) != '0') f[i] += f[i-1];
            if (i > 1){
                int c1 = s.charAt(i-2)-'0';
                int c2 = s.charAt(i-1)-'0';
                if (c1 != 0 && c1 * 10 + c2 <= 26) f[i] += f[i-2];
            }
        }
        return f[s.length()];
    }
}