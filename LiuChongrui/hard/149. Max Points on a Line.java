/*
思路：题意是平面上有N个点，求一条直线能最多穿过多少个点？即最多有多少个点在一条直线上。
      我最先想到的方法求斜率，两两之间的斜率我先存起来，然后枚举一个点X（即直线穿过X），其他点i到X的斜率为K[i]，
      则找这个K数组的众数就是过X的直线最多经过多少个点。
      这样做有几个问题：1.可能有重合点，这样斜率不存在，但注意斜率不存在不一定就是重合点，可能是垂直点，所以重合点需要特判。
                        2.斜率用非整数，所以判断斜率相等时浮点数相等，不能直接用==，需要abs（x-y）< e的方式判等
                        3.极端数据下，由于精度问题，会把斜率不同的点算成斜率相同（确实发生了），产生了错误。
      所以用算斜率的方法不能实现。我们只能用分数的形式存储斜率，用gcd求得最简分数形式，正解如下：
      枚举一个点X，计算其他点到他的斜率y/x,我们把斜率存储的（x,(y,t)）分数对存入到map中,其中t为分数对出现个数，
      可以避免最后的众数计算用到排序。注意重合点和特殊斜率的处理方式。
 */



/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    public int maxPoints(Point[] points) {

        if (points.length < 3) return points.length;
        int ans = 2;
        Map<Integer,Map<Integer,Integer>> pairs = new HashMap<Integer,Map<Integer,Integer>>();
        for (int i = 0; i < points.length; i++){
            int maxi = 0,samep = 0;
            pairs.clear();
            for (int j = i+1; j < points.length; j++){
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                if (x == 0 && y == 0){
                    samep++;
                    continue;
                }
                if (x == 0) y = 1;
                else if (y == 0) x = 1;
                else{
                    int g = gcd(x,y);
                    x = x / g; y = y/g;
                }
                if (pairs.containsKey(x)){
                    if (pairs.get(x).containsKey(y)){
                        pairs.get(x).put(y,pairs.get(x).get(y)+1);
                    }
                    else{
                        pairs.get(x).put(y,1);
                    }
                }
                else{
                    Map<Integer,Integer> mapy = new HashMap<Integer,Integer>();
                    mapy.put(y,1);
                    pairs.put(x,mapy);
                }
                int m = pairs.get(x).get(y);
                if (m > maxi) maxi = m;
            }
            if (maxi+samep+1 > ans) ans = maxi+samep+1;
        }

        return ans;
    }
    private int gcd(int a,int b){
        if (b == 0) return a;
        else return gcd(b,a%b);
    }
}