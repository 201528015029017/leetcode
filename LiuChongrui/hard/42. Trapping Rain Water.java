/*
思路：
    水注存在的地方肯定是两边高，中间低的形状。而且这些凹槽是独立存在的
    我们枚举左界，从左往右扫，找到这样的 %且左界比右界低或相等% 的凹槽形状。（可以保证这个凹槽不会是存在于某个大凹槽的一部分，而且水不会从右界溢出去）
    我们枚举右界，从右往左扫，找到这样的 %且右界比左界低或相等% 的凹槽形状。（可以保证这个凹槽不会是存在于某个大凹槽的一部分，而且水不会从左界溢出去）

    当存在一个最高的立柱时，两遍其实扫会在最高立柱时停止。
    当不存在一个最高立柱时，可能会出现重复计算凹槽的情况，所以我们可以用一个数组表示注满水后的水位情况，最后进行计算。
    代码是三遍循环，复杂度O（n）
改进：
    其实我们可以看到左界和右界做的事情很相像，而且若最高柱存在的情况，其实每次只扫了半边（最高柱左半边和最高柱右半边），所以可以整合在一起做。
    而且可以保证不出现重复的情况，所以注水后的数组可以去掉。
    附一段讨论区c++代码
    class Solution {
    public:
        int trap(int A[], int n) {
            int left=0; int right=n-1;
            int res=0;
            int maxleft=0, maxright=0;
            while(left<=right){
                if(A[left]<=A[right]){
                    if(A[left]>=maxleft) maxleft=A[left];
                    else res+=maxleft-A[left];
                    left++;
                }
                else{
                    if(A[right]>=maxright) maxright= A[right];
                    else res+=maxright-A[right];
                    right--;
                }
            }
            return res;
        }
    };

 */



public class Solution {
    public int trap(int[] height) {
        int pre_height = 0;
        int pre_id = -1;

        int[] result = Arrays.copyOf(height,height.length);
        for (int i = 0; i < height.length; i++){
            if (height[i] >= pre_height){
                for (int j = pre_id+1; j < i; j++){
                    result[j] = pre_height;
                }
                pre_height = height[i];
                pre_id = i;
            }
        }

        pre_id = height.length;
        pre_height = 0;
        for (int i = height.length - 1; i >= 0; i--){
            if (height[i] >= pre_height){
                for (int j = pre_id-1; j > i; j--){
                    result[j] = pre_height;
                }
                pre_height = height[i];
                pre_id = i;
            }
        }

        int sum = 0;
        for (int i = 0; i < result.length; i++){
            sum+= result[i] - height[i];
        }
        return sum;
    }
}