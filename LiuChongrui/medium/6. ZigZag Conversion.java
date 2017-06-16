/*
思路：首先要清楚排列方式是按S形走的，
e.g:
A   I   Q   Y
B H J P R X Z
C G K O S W
D F L N T V
E   M   U
所以最简单的办法就是按照规律填二维数组，然后输出。或者我们找找规律，直接填数。
 */
//二维数组方法
public class Solution {
    public String convert(String s, int k) {
        StringBuilder ans = new StringBuilder();
        int ind = 0;
        int j = -1;
        int n = s.length();
        if (n == 0) return "";
        if (k == 1) return s;
        char[][] a = new char[k][n/(k+k-2)*2+2];
        while (ind < n){
            j++;
            if (j % 2 == 0) {
                for (int i = 0; i < k; i++) {
                    a[i][j] = s.charAt(ind++);
                    if (ind >= n) break;
                }
            }
            else {
                for (int i = k - 2; i >= 1; i--) {
                    a[i][j] = s.charAt(ind++);
                    if (ind >= n) break;
                }
            }
        }
        for (int i = 0; i < k; i++)
            for (int jj = 0; jj <= j; jj++)
                if (((int)a[i][jj]) != 0) ans.append(a[i][jj]);
        return ans.toString();
    }
}

//找规律法
public class Solution {
    public String convert(String s, int k) {
        StringBuilder ans = new StringBuilder();
        if (k == 1) return s;
        for (int i = 0; i < s.length(); i = i+k+k-2){
            ans.append(s.charAt(i));
        }
        int [] gap = {k-2+k-2,2};
        for (int i = 1; i <= k-2; i++){
            int j = i;
            int step = 0;
            while (j < s.length()){
                ans.append(s.charAt(j));
                j = j + gap[step%2];
                step++;
            }
            gap[0] = gap[0]-2;
            gap[1] = gap[1]+2;
        }
        for (int i = k-1; i < s.length(); i = i+k+k-2){
            ans.append(s.charAt(i));
        }
        return ans.toString();
    }
}