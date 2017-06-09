/*
思路：类似于高精度加法。
 */

public class Solution {
    public String addBinary(String a, String b) {
        int i = a.length();
        int j = b.length();
        int g = 0,d = 0;
        int x, y;
        int [] c = new int[Math.max(i,j)+1];
        while (i > 0 || j > 0){
            i--; j--;
            if (i >= 0) x = a.charAt(i)-'0'; else x = 0;
            if (j >= 0) y = b.charAt(j)-'0'; else y = 0;
            c[d] = x+y+g;
            g = c[d]/2;
            c[d] = c[d]%2;
            d++;
        }
        if (g > 0) c[d++] = 1;
        StringBuilder ans = new StringBuilder();
        for (i = d-1; i >= 0; i--){
            ans.append(c[i]);
        }
        return ans.toString();
    }
}