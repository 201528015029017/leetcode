class Solution {
public:
    bool search(vector<vector<int>>& matrix, int ir, int ic, int jr,  int jc, int target) {
        if(ir > jr || ic > jc ) return false;
        int mr = ir + (jr - ir) / 2, mc = ic + (jc - ic) / 2;
        if(matrix[mr][mc] == target) return true;
        
        bool flag;
        if (matrix[mr][mc] > target)
            flag = search(matrix, ir, ic, mr-1, mc-1, target) || search(matrix, ir, mc, mr - 1, mc, target) ||  search(matrix, mr, ic, mr, mc - 1, target);
        else
            flag = search(matrix, mr + 1, mc + 1, jr, jc, target) || search(matrix, mr + 1, mc, jr, mc, target) ||  search(matrix, mr, mc + 1, mr, jc, target);
        
        return flag || search(matrix, ir, mc + 1, mr - 1, jc, target) || search(matrix, mr + 1, ic, jr, mc - 1, target);
    }
    
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int height = matrix.size();
        if (height <= 0) return false;
        int width = matrix[0].size();
        if (width <= 0) return false;
        return search(matrix, 0, 0, height - 1, width - 1, target);
    }
};
