/*
Problem: 求一个有重复元素的集合的所有子集
If nums = [1,2,2], a solution is:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */
/*
思路：为了处理重复元素，首先排序，将相同的数放在一起，每个元素有取或者不取两种策略，当A元素执行不取策略时，往后跳过所有的A元素然后递归。
    当A元素执行取策略时，直接下一个递归，如此可以保证子集不重复出现
 */
public class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ans = new ArrayList<>();
        Arrays.sort(nums);
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
        int j = i;
        while (++j < a.length && a[j] == a[i]);
        dfs(a,f,j,count);
        //取
        f[count++] = a[i];
        dfs(a,f,i+1,count);
        --count;
    }
}