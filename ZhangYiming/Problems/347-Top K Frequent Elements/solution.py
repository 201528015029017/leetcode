class Data{
private:
    int key;
    int count;

public:
    Data(int x) : key(x), count(1) {}
    void inc(){
        count ++;
    }
    int getKey() const{
        return key;
    }
    int getCount() const{
        return count;
    }
};

class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        vector<Data> ans;
        sort(nums.begin(), nums.end());
        for(auto num: nums){
            if(ans.size() > 0 && ans.back().getKey() == num)
                ans.back().inc();
            else
                ans.push_back(Data(num));
        }
        sort(ans.begin(), ans.end(),
            [](const Data &x, const Data &y){
                return x.getCount() > y.getCount();
            });
        vector<int> result;
        for(int i = 0; i < k; ++ i) result.push_back(ans[i].getKey());
        return result;
    }
};
