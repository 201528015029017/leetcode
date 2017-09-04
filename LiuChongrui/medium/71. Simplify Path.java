/*
思路：难点在于理解题意, /..表示回退到上层目录，所以可以用栈的方式来做
     PS：可以一开始用'/'来split字符串
*/
public class Solution {
    public String simplifyPath(String path) {
        List<String> que = new ArrayList<String>();
        StringBuilder pre = new StringBuilder();
        char x;
        for (int i = 0; i <= path.length(); i++){
            if (i == path.length()) x = '/';
            else x = path.charAt(i);
            if (x == '/'){
                String prex = pre.toString();
                if (prex.equals("") || prex.equals(".")){}
                else if (prex.equals("..")){
                    if (que.size()>0)que.remove(que.size()-1);
                }
                else que.add(prex);
                pre = new StringBuilder();
            }
            else pre.append(x);
        }
        StringBuilder ans = new StringBuilder("/");
        for (int i = 0; i < que.size(); i++){
            //System.out.println(que.get(i));
            ans.append(que.get(i));
            if (i < que.size()-1) ans.append('/');
        }
        return ans.toString();
    }
}