/*
思路：求一个子集，使得子集中任意两个元素都有整除关系
    f[i]表示0..i的元素的子集的最大个数
    f[i] = max(f[j])+1  where a[i]%a[k]=0
    pre[i]表示转移位置
    最后通过pre求方案
 */
public class Solution {
    public List<Integer> largestDivisibleSubset(int[] a) {
        Arrays.sort(a);
        int ans = 0;
        int n = a.length;
        if (n == 0) return new ArrayList<Integer>();
        int[] pre = new int [n];
        int[] f = new int[n];
        for (int i = 0; i < n; ++i){
            pre[i] = -1; f[i] = 1;
            for (int j = 0; j < i; ++j){
                if (a[i] % a[j] == 0)
                    if (f[j] + 1 > f[i]) {
                        f[i] = f[j] + 1;
                        pre[i] = j;
                    }
            }
            if (f[ans] < f[i]) ans = i;
        }
        List<Integer> ansList = new ArrayList<>();
        while (ans >= 0){
            ansList.add(a[ans]);
            ans = pre[ans];
        }
        return ansList;
    }
}