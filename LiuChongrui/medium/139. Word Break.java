/*
思路：首先将所有的word都放入set中，f[i]表示到i位置能不能分割，f[i] = f[j] && (substring(j+1,i+1) in set)
    这里可以用广搜记录所有f[i]为true的位置，那么那些f[j]为false地方就可以不判断了
 */
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        for (String ss: wordDict) dict.add(ss);

        int[] index = new int[s.length()+1]; // BFS队列
        index[0] = -1;
        int total = 1;
        for (int i = 0; i < s.length(); ++i){
            for (int j = total-1; j >= 0; --j){
                String x = s.substring(index[j]+1,i+1);
                if (dict.contains(x)) {
                    index[total++] = i;
                    break;
                }
            }
        }
        return index[total-1] == s.length()-1;
    }
}