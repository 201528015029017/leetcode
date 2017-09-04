/*
思路：
    解法1：枚举回文串的中间点，然后往两边扩展，分奇数和偶数两种情况。
    解法2：manacher算法
 */

public class Solution {
    public String longestPalindrome(String s) {
        int count,anslen = 0,anssta = 0;
        for (int i = 0; i < s.length();i++){
            int right = i+1, left = i-1;
            while (right < s.length() && left >= 0){
                if (s.charAt(right) != s.charAt(left)) break;
                right++; left--;
            }
            count = (right - left + 1) -2;
            if (count > anslen){
                anslen = count; anssta = left + 1;
            }
            //
            right = i+1; left = i;
            while (right < s.length() && left >= 0){
                if (s.charAt(right) != s.charAt(left)) break;
                right++; left--;
            }
            count = (right - left + 1) -2;
            if (count > anslen){
                anslen = count; anssta = left + 1;
            }
        }
        return s.substring(anssta,anssta+anslen);
    }
}