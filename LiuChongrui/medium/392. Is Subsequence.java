/*
思路：1.双指针扫描
    2.
 */
public class Solution {
    public boolean isSubsequence(String s, String t) {
        for (int i = 0,j = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            while (j < t.length() && t.charAt(j) != c) ++j;
            //System.out.println(j);
            if (j == t.length()) return false;
            j++;
        }
        return true;
    }
}

/*
看到字符串只有小写字母组合，那么可以预处理母串，将每种字母出现的位置从小到大存在一个数组dict[][]中
然后遍历子串，找当前位置的字母是否出现，这个可以在dict中二分实现
 */
public class Solution {
    public static int binarySearch(int[] a, int target){
        int mid,left = 0,right = a.length-1;
        while (left <= right){
            mid = left + (right - left)/2;
            if (a[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return left > a.length-1? -1:a[left];
    }
    public boolean isSubsequence(String s, String t) {
        int [] len = new int[26];
        int [] total = new int[26];
        int [][] dict = new int[26][];
        int c;
        for (int i = 0; i < t.length(); ++i){
            c = t.charAt(i) - 'a';
            ++len[c];
        }
        for (int i = 0; i < dict.length; ++i) dict[i] = new int[len[i]];
        for (int i = 0; i < t.length(); ++i){
            c = t.charAt(i) - 'a';
            dict[c][total[c]++] = i;
        }
        //
        int index = -1;
        for (int i = 0; i < s.length(); ++i){
            c = s.charAt(i) - 'a';
            index = binarySearch(dict[c],index);
            //System.out.println(index);
            if (index == -1) return false;
        }
        return true;
    }
}