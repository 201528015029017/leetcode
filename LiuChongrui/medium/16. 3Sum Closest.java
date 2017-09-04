/*
思路：找三个数的和最接近于target值
        一开始的思路是二分搜索，先枚举数组中两个数，然后二分搜索最靠近第三个值的数是多少。复杂度是O（n^2*logn）
      正解：类似于“找三个数的和等于target值”的那道题，我们用的是前后双指针分别靠近的方法，在这题也可以使用，
      但是需要注意的一点是我们需要将所有情况做完（前指针=后指针），这点没想到准确值和近似值都可以用双指针扫描实现，复杂度是O（n^2）
ps:二分搜索的各种情况可以总结下，卡了不少时间
 */



public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        //for(int i = 0; i < nums.length; i++) System.out.print(nums[i]+" ");
        int n = nums.length;
        int ans = nums[0]+nums[1]+nums[2];
        for (int i = 0; i < n - 2; i++){
            int sum = target - nums[i];
            int left = i+1;
            int right = n-1;
            while (left < right){
                int total = nums[left]+nums[right];
                if (Math.abs(total+nums[i] - target) < Math.abs(ans-target)) ans = total+nums[i];

                if (total > sum)
                    right--;
                else
                    left++;
            }
        }
         /* 二分超时
            for (int j = i + 1; j < n - 1; j++){
                int three = target - nums[i] - nums[j];
                int left = j + 1;
                int right = n - 1;
                int id;
                if (nums[left] >= three) id = left;
                else if (nums[right] <= three) id = right;
                else{
                    //System.out.println(left+" "+right);
                    while (left + 1 < right) {
                        int mid = (left + right) / 2;
                        if (nums[mid] <= three) left = mid;
                        else right = mid - 1;
                    }
                    if (Math.abs(nums[left] - three) > Math.abs(nums[left+1] - three)) id = left+1;
                    else id = left;
                }
                int sum = nums[i]+nums[j]+nums[id];
                if(Math.abs(sum - target) < Math.abs(ans - target)) ans = sum;
            }
            */

        return ans;
    }
}