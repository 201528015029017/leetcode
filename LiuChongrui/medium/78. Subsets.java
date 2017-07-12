/*
Problem：求一个集合的所有子集，不包含重复元素
 */
/*
思路：每个元素都有取或者不取两种方式
 */
public class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        dfs(nums,new int[nums.length],0,0);
        return ans;
    }
    public void dfs(int[] a, int[] f, int i, int count){
        if (i == a.length){
            List<Integer> res = new ArrayList<>();
            for (int k = 0; k < count; ++k)
                res.add(f[k]);
            ans.add(res);
            return;
        }
        //不取
        dfs(a,f,i+1,count);
        //取
        f[count++] = a[i];
        dfs(a,f,i+1,count);
        --count;
    }
}