/*
思路：将区间从小到大排序，然后合并判断相邻的区间能否合并。
 */
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        intervals.sort(new Comparator<Interval>() {         // 注意comparator的编写
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start < o2.start || (o1.start == o2.start && o1.end < o2.end))
                    return -1;
                else if (o1.start == o2.start && o1.end == o2.end)
                    return 0;
                else return 1;
            }
        });
        int start = Integer.MIN_VALUE;
        int end = Integer.MIN_VALUE;
        List<Interval> ans = new ArrayList<>();
        for (Interval item: intervals){
            if (end >= item.start) end = Math.max(end,item.end);
            else{
                if (start != Integer.MIN_VALUE)
                    ans.add(new Interval(start,end));
                start = item.start;
                end = item.end;
            }
        }
        if (start != Integer.MIN_VALUE)
            ans.add(new Interval(start,end));
        return ans;
    }

}