class Solution {
public:
    int minSubArrayLen(int s, vector<int>& nums) {
        vector<int> sums;
        sums.push_back(0);
        for(int i = 0; i < nums.size(); ++i) sums.push_back(sums[i] + nums[i]);
        int left = 0, right = 1;
        while(right < sums.size() && sums[right] - sums[left] < s) right ++;
        if(right == sums.size()) return 0;
        while(right < sums.size()){
            left ++;
            if(sums[right] - sums[left] < s) right ++;
        }
        return right - left;
    }
};
