/*
思路：全排列生成，这里采用的是交换法，假设有{1，2，3，4，5}这5个数进行全排列，我们首先确定第一个位置{1} + p{2，3，4，5}，
    {2}+ p{1,3,4,5}, {3}+ p{1,2,4,5}...一次交换第一位置的值，使得所有数都在第一位置排一遍。
 */
public class Solution {
    List<List<Integer>> ans;
    public void swap(int[] a,int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public void dfs(int[] a,int index){
        if (index == a.length) {
            ans.add(Arrays.stream(a).boxed().collect(Collectors.toList()));
            return ;
        }
        for (int i = index; i < a.length; i++){
            swap(a,index,i);
            dfs(a,index+1);
            swap(a,index,i);
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        dfs(nums,0);
        return ans;
    }
}