/*
思路：题意是求等差子序列的个数，一开始我的想法和413第一问类似，也是维护一个count的值，用map[i]<long,integer>表示第i个数结尾的子序列（差值，长度），
    但是发现有问题，因为可以可能有多个差值都等于这个长度，比如 2,2,3,4 ，当计算3时，前面有两个2和3的差值为1，单纯的一个长度无法反映这种情况

    正解：map[i]<long,integer>表示第i个数结尾的子序列（差值，等差子序列个数），那么第i个数可以由前面的第j个数map中差值为diff的转移，其中diff = a[i]-a[j]；
    count1 = map[j].get(diff)，第i个数可以接在这些count1后，组成新的等差子序列，所以总数+count1，但是难点来了，如何更新i自己的map呢？
    我们新增了count1+1个子序列，count1好理解，1是为什么？ 这个1指的是 a[j],a[i]这个序列！，因为等差数列最少要3个，所以a[j],a[i]只能算半个，在计算i结尾时
    不能算入内，但是在存储时，我们就必须存进去了，好让之后的计算能够正确，所以这个map的含义有所变化,
    map[i]<long,integer>表示第i个数结尾的子序列（差值，等差子序列个数（包括半个的情况））
 */
public class Solution {
    public int numberOfArithmeticSlices(int[] a) {
        List<Map<Long,Integer>> list = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < a.length; ++i){
            Map<Long,Integer> map = new HashMap<>();
            for (int j = 0; j < i; ++j){
                Map<Long,Integer> preMap = list.get(j);
                long cha = (long)a[i]-a[j];
                int count1 = preMap.getOrDefault(cha,0);
                int count2 = map.getOrDefault(cha,0);
                map.put(cha,count1 + 1 + count2);    //注意1的含义！
                sum = sum + count1;
            }
            //System.out.println(map);
            list.add(map);
        }
        return sum;
    }
}