/*
思路：首先理解题意，'.'表示可以匹配任何非空字符，'a*'表示可以匹配0个a或多个a，特殊的'.*'表示可以匹配0个或多个任意字符
     （这里不要把'*'理解成了通配符）
    第一想到的就是搜索，遇到'*'号就分成两种，当前是匹配或不匹配，即find（s1,s2,i+2,j）|| find(s1,s2,i,j+1),
    这里为了更好的判断边界条件，把两串的结尾都添加了一个'#'.
    PS:可以用记忆搜索，f[i][j]表示当前在i位置和j位置，再往后搜索能不能得到出解。

    第二：这题还可以用dp求得  boolean f[i][j]表示s1[0..i-1] 和 s2[0..j-1]能不能匹配成功
    1.如果i位置不为'*' f[i][j] = f[i-1][j-1] && (s1[i-1] == '.' || s1[i-1] == s2[j-1])
    2.如果i位置为'*' f[i][j] = f[i-2][j] ||   //*号不扩展
                               f[i][j-1] && (s1[i-2] == '.' || s1[i-2] == s2[j-1]) //*号扩展
    PS：这里f[i][0]也是可能为true的，（比如"a*"和""可以匹配成功）,所以j==0时特殊处理一下
 */
public class Solution {
    boolean[][] f ;
    public boolean find(String s1,String s2, int i,int j){
        //System.out.println(i+" "+j);
        if (i == s1.length() && j == s2.length()){
            return true;
        }
        if (i >= s1.length() || j >= s2.length()){
            return false;
        }
        if (f[i][j] == false) return false;
        //
        if (i < s1.length() - 1 && s1.charAt(i+1) == '*'){
            if (s1.charAt(i) == '.') {                      //".*"
                return f[i][j] = find(s1,s2,i,j+1) || find(s1,s2,i+2,j);
            }
            if (s1.charAt(i) != s2.charAt(j))
                return f[i][j] = find(s1,s2,i+2,j);
            else return f[i][j] = find(s1,s2,i,j+1) || find(s1,s2,i+2,j);
        }
        else{
            if (s1.charAt(i) == '.') return f[i][j] = find(s1,s2,i+1,j+1);
            if (s1.charAt(i) != s2.charAt(j)) return f[i][j] = false;
            else return f[i][j] = find(s1,s2,i+1,j+1);
        }

    }
    public boolean isMatch(String s, String p) {
        f = new boolean[p.length()+1][s.length()+1];
        for (int i = 0; i < f.length; i++) Arrays.fill(f[i],true);
        return find(p+"#",s+"#",0,0);
    }
}

//dp
public class Solution {
    public boolean mydp(String s1,String s2){
        int n = s1.length();
        int m = s2.length();
        boolean [][] f = new boolean [n+1][m+1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i],false);
        f[0][0] = true;
        for (int i = 1; i <= n; i++){
            for (int j = 0; j <= m; j++){
                if (j == 0){
                    if (s1.charAt(i-1) == '*')
                        f[i][j] = (i >= 2) && f[i-2][j];
                    else f[i][j] = false;
                }else {
                    if (s1.charAt(i - 1) != '*')
                        f[i][j] = f[i - 1][j - 1] && ((s1.charAt(i - 1) == '.') || s1.charAt(i - 1) == s2.charAt(j - 1));
                    else f[i][j] = f[i - 2][j] || (f[i][j - 1] && (s1.charAt(i-2) == '.' ||s1.charAt(i - 2) == s2.charAt(j - 1)));
                }
            }
        }
        return f[n][m];
    }
    public boolean isMatch(String s, String p) {
        return mydp(p,s);
    }
}