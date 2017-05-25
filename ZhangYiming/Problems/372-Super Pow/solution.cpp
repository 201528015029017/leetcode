class Solution {
public:
    int superPow(int a, vector<int>& b) {
        
        if(a == 0) return 0;
        if(a == 1) return 1;
        
        if(head(b) == b.size()) return 1;
        
        const int n = 1337;
        
        int rmd = a % n;
        if(rmd == 0) return 0;
        
        int circle = 1;
        
        int *remainders = new int[n];
        memset(remainders, 0, n*sizeof(int));
        int *steps = new int[n];
        memset(steps, 0, n*sizeof(int));
        
        while(steps[rmd] == 0){
            steps[rmd] = circle;
            remainders[circle] = rmd;
            rmd = rmd * (a % n) % n;
            circle ++;
        }
        
        int start = steps[rmd];
        circle -= start;
        
        int ans = 0, result;
        print(remainders, n);
        print(steps, n);
        cout << "circle:" << circle << endl;
        cout << "start:" << start << endl;
        
        if(b.size() - head(b) < 6){
            for(int j = head(b); j < b.size(); j ++) ans = ans * 10 + b[j];
            if(ans < start) result = remainders[ans];
            else result = remainders[start + (ans - start) % circle];
        }
        else{
            minus(b, start);
            cout << "(b-start)%%circle:" << rmdr(b, circle) << endl;
            result = remainders[start + rmdr(b, circle)];
        }
        return result;
        
        delete [] remainders;
        delete [] steps;
    }
    
private:
    int head(vector<int>& b){
        int i;
        for(i = 0; i < b.size(); ++i){
            if(b[i] != 0) break; 
        }
        return i;
    }
    void minus(vector<int>& b, int num){
        int i = b.size() - 1;
        while(num > 0){
            if(b[i] >= num % 10){
                b[i] -= num % 10;
                num /= 10;
                i --;
            }
            else{
                b[i] += 10 - num % 10;
                num = num / 10 + 1;
                i --;
            }
        }
    }
    int rmdr(vector<int>& b, int num){
        int ans = 0;
        for(int i = head(b); i < b.size(); ++i){
            ans = ans*10 + b[i];
            if(ans > num) ans %= num;
        }
        return ans;
    }
    
    void print(int* array, int size){
        cout << "[";
        for(int i = 0; i < size - 1; ++i) cout << array[i] << ",";
        cout << array[size - 1] << "]" << endl;
    }
};
