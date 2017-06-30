/*
思路：广搜扩展，看是否能扩展到最后一个点
 */
public class Solution {
    public boolean canJump(int[] a) {
        int maxBound = 0;
        int nowStep = 0;
        while (nowStep <= maxBound){
            maxBound = Math.max(nowStep+a[nowStep],maxBound);
            if (maxBound >= a.length-1) return true;
            nowStep++;
        }
        return false;
    }
}