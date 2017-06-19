/*
思路：和word break1 思路一样，加一个结构记录当前位置i可以跳到之前的哪些位置，即先dp求路径，然后dfs找方案
    也可以用记忆化搜索直接做
 */

public class Solution {
    static List<String> ans;
    static List<Integer> path;
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        for (String ss: wordDict) dict.add(ss);
        //
        int[] que = new int[s.length()+1]; // BFS队列
        List<List<Integer>> index = new ArrayList<>();
        que[0] = -1;
        int total = 1;
        for (int i = 0; i < s.length(); ++i){
            List<Integer> list = new ArrayList<>();
            for (int j = total-1; j >= 0; --j){
                String x = s.substring(que[j]+1,i+1);
                if (dict.contains(x)) {
                    list.add(que[j]);
                }
            }
            if (list.size() > 0) que[total++] = i;
            index.add(list);
        }
        ans = new ArrayList<>();
        path = new ArrayList<>();
        findans(s.length()-1,s,index);
        return ans;
    }
    static void findans(int x,String s,List<List<Integer>> index){
        if (x == -1){
            int j = path.size()-2;
            StringBuilder ans1 = new StringBuilder();
            for (int k = 0; k < s.length(); ++k){
                ans1.append(s.charAt(k));
                if (j >= 0 && k == path.get(j)) {
                    ans1.append(' ');
                    --j;
                }
            }
            ans.add(ans1.toString());
            return;
        }
        for (Integer i: index.get(x)){
            path.add(i);
            findans(i,s,index);
            path.remove(path.size()-1);
        }
    }
}
/*
记忆化搜索
 */
public List<String> wordBreak(String s, Set<String> wordDict) {
    return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
}

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String>res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }