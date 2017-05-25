struct Data{
public:
    int height;
    int left;
    int right;
    Data(){};
};

class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {
        
        int height = matrix.size();
        if (height == 0) return 0;
        int width = matrix[0].size();
        if (width == 0) return 0;
        
        Data * current = new Data[width];
        int layer = 0;
        int result = 0;
        init(current, layer, width, matrix);
        judge(current, width, result);
        for(layer = 1; layer < height; ++layer){
            Data *next = new Data[width];
            init(next, layer, width, matrix);
            inc(current, next, width);
            judge(next, width, result);
            delete [] current;
            current = next;
        }
        
        delete [] current;
        return result;
    }
private:

    void judge(Data* &current, int &width, int &result){
        int temp;
        for(int col = 0; col < width; ++col){
            if(current[col].height > 0){
                temp = (current[col].right - current[col].left + 1) * current[col].height;
                if(result < temp) result = temp;
            }
        }
    }
    
    void inc(Data* &current, Data* &next, int &width){
        for(int col = 0; col < width; ++col){
            if(current[col].height > 0 &&  next[col].height > 0){
                next[col].height = current[col].height + 1;
                if(next[col].left < current[col].left) next[col].left = current[col].left; 
                if(next[col].right > current[col].right) next[col].right = current[col].right; 
            }
        }
    }

    void init(Data* &current, int &layer, int &width, vector<vector<char>>& matrix){
        int col, label = 0;
        for(col = 0; col < width; ++col){
            if(matrix[layer][col] == '0'){
                label = col + 1;
                current[col].left = 0;
                current[col].right = width - 1;
                current[col].height = 0;
            }
            else{
                current[col].left = label;
                current[col].height = 1;
            }
        }
        for(col = width - 1, label = width - 1; col >= 0; --col){
            if(matrix[layer][col] == '1')
                current[col].right = label;
            else
                label = col - 1;
        }
    }
};
