/*
思路：用二分求幂，注意几个情况，n为负数，n为int最小值
 */
public class Solution {
    public double myPow(double x, int n) {
        if (n >= 0) return myPositivePow(x,(long)n);
        else return 1.0/myPositivePow(x,(long)-n);
    }
    public double myPositivePow(double x, long n) {
        if (n == 0) return 1.0;
        else if (n == 1) return x;
        else{
            double temp = myPositivePow(x,n/2);
            if (n % 2 == 0) return temp*temp;
            else return temp*temp*x;
        }
    }
}