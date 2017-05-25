class Solution {
public:
    vector<string> fizzBuzz(int n) {
        vector<string> ans;
        int fizz = 3;
        int buzz = 5;
        for(int i = 1; i <= n; ++i){
            fizz --;
            buzz --;
            if(buzz == 0){
                buzz = 5;
                if(fizz == 0){
                    fizz = 3;
                    ans.push_back("FizzBuzz");
                }
                else
                    ans.push_back("Buzz");
            }
            else{
                if(fizz == 0){
                    fizz = 3;
                    ans.push_back("Fizz");
                }
                else
                    ans.push_back(to_string(i));
            }    
        }
        return ans;
    }
};
