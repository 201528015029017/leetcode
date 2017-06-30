/*
思路：贪心，当差值变符号时，就把长度+1
 */
public class Solution {
    public int wiggleMaxLength(int[] a) {
        if (a.length <= 1) return a.length;
        int pre = 0,len = 1;
        for (int i = 1; i < a.length; i++){
            int cha = a[i]-a[i-1];
            if (pre == 0)
                if (cha != 0){
                    pre = cha / Math.abs(cha);
                    ++len;
                }
                else continue;
            else if (pre * cha < 0) {
                ++len; pre = -pre;
            }
        }
        return len;
    }
}