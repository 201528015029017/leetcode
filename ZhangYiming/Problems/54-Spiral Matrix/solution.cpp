class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        
        int row_start = 0, col_start = 0;
        int row_end = matrix.size() - 1;
        vector<int> result;
        if (row_end < 0) return result;
        int col_end = matrix[0].size() - 1;
        if (col_end < 0) return result;
        
        int i, j;
        while (row_start < row_end and col_start < col_end){
            for(j = col_start; j < col_end; ++j) result.push_back(matrix[row_start][j]);
            for(i = row_start; i < row_end; ++i) result.push_back(matrix[i][col_end]);
            for(j = col_end; j > col_start; --j) result.push_back(matrix[row_end][j]);
            for(i = row_end; i > row_start; --i) result.push_back(matrix[i][col_start]);
            row_start ++;
            row_end --;
            col_start ++;
            col_end --;
        }
        
        if(row_start <= row_end and col_start <= col_end ){
            if(row_start == row_end)
                for(j = col_start; j <= col_end; ++j) result.push_back(matrix[row_start][j]);
            else
                for(i = row_start; i <= row_end; ++i) result.push_back(matrix[i][col_start]);
        }
        
        return result;
    }
};
