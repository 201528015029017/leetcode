/*
思路：有重复的全排列，我们还是按照交换法，假设有{1，2，2，3}4个数全排列，我们首先排第一个位置{1}+p{2，2，3}，{2}+p{1，2，3}，
    {2}+p{1，2，3}，{3}+{1，2，2},会发现{2}+p{1，2，3}出现了重复，这里我们当然只能排一次，也就是说对于每个位置i，每种数只能出现
    一次，所以我们可以在每个位置用一个set记录哪些数出现过了。
 */
public class Solution {
    List<List<Integer>> ans;
    public void swap(int[] a,int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public void dfs(int[] a,int i){
        if (i == a.length-1) {
            ans.add(Arrays.stream(a).boxed().collect(Collectors.toList()));
            return ;
        }
        Set<Integer> set = new HashSet<>();
        for (int k = i; k < a.length; k++){
            if (set.add(a[k])){
                swap(a,i,k);
                dfs(a,i+1);
                swap(a,i,k);
            }
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        ans = new ArrayList<>();
        dfs(nums,0);
        return ans;
    }
}