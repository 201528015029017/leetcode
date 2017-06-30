/*
思路：求等差子串的个数，当差值和原来一样时，总数就+(count-2)，看一个例子
     当有 1,2,3,4,5时，这时来个6，那么加的等差数列长度应该为 6-2，所以我们维护count的值
 */
public class Solution {
    public int numberOfArithmeticSlices(int[] a) {
        int precz = 0,count = 0,sum = 0;
        for(int i = 1; i < a.length; i++){
            if (i == 1 || a[i]-a[i-1] != precz){
                precz = a[i]-a[i-1];
                count = 2;
            }
            else{
                count++;
                sum += count-2;
            }
        }
        return sum;
    }
}