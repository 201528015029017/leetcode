class Solution {
public:
    bool canMeasureWater(int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0) return false;
        if (z == 0) return true;
        if (x == 0 && y == 0) return false;
        if (z > x + y) return false;
        return z % gcd(x, y) == 0;
    }
private:
    void swap(int &x, int &y){
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
    }
    int gcd(int x, int y){
        if(x > y) swap(x, y);
        while(x > 0){
            y = y % x;
            swap(x, y);
        }
        return y;
    }
};
