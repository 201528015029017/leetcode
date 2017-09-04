/*
思路：水题，稍微巧一点的是没有用辅助数组，用一个标记位记录当前处理到第几个元素，一个标记位记录当前改写哪一位，
    可以直接在原数组上进行了改写
 */


public class Solution {
    public int removeDuplicates(int[] nums) {
        int j = 0;
        int dup = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i-1] == nums[i]){
                if (dup == 2) continue;
                else {
                    dup++;
                    nums[++j] = nums[i];
                }
            }
            else{
                dup = 1;
                nums[++j] = nums[i];
            }
        }
        return j+1;
    }
}