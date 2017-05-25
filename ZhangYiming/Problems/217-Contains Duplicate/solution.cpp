class Solution {
public:
    bool containsDuplicate(vector<int>& nums) {
        map<int, int> hash;
        for(auto n: nums){
            if (hash.find(n) != hash.end())
                return true;
            else
                hash[n] = 1;
        }
        return false;
    }
};
