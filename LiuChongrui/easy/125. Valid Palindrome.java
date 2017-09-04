/*
思路：去掉所有的非字母数字字符，全部转小写变成一个新串。然后reverse这个新串比较。
    PS：reverse函数是在原串上进行操作
 */
public class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder s1 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            if ((x >= 'A' && x <= 'Z')||(x >= 'a' && x <= 'z')){
                s1.append(Character.toUpperCase(x));
            }
            else if (x >= '0' && x <= '9')
                s1.append(x);
        }
        String s3 = new String(s1);
        String s2 = s1.reverse().toString();
        return s3.equals(s2);
    }
}