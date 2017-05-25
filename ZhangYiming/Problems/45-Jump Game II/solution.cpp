class Solution {
public:
    int jump(vector<int>& nums) {
        int n = nums.size() - 1;
        int step = 0, rear = 0, start = 0, end = 0;
        while(rear < n){
            for(int i = start; i <= end; ++i)
                if(nums[i] + i > rear) rear = nums[i] + i;
            start = end + 1;
            end = rear;
            step ++;
        }
        return step;
    }
};
