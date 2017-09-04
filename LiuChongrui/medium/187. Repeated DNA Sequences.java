/*
思路：看着像是要计算子串在母串中的出现位置，其实不用，我们只有把所有子串都找出来，看是否有重复的就行。
      根据discuss的说法，map（key，value）中key如果用string会比Integer占用更多的内存，所以我们首先将10位的String转成int,
      因为只有可能是A,C,G,T四位，所以用00,01,10,11 2位二进制表示，子串为10位，整个二进制位20位，在int范围中，可以转化成int。

 */
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<String>();
        if (s.length()<10) return ans;
        //
        int [] c = new int [100];
        c['A'] = 0;
        c['C'] = 1;
        c['G'] = 2;
        c['T'] = 3;
        HashSet<Integer> firstDisplay = new HashSet<Integer>();
        HashSet<Integer> secondDisplay = new HashSet<Integer>();
        for (int i = 0; i < s.length()-9; i++){
            int y = 0;
            for (int j  = i; j < i + 10; j++){
                y = y << 2;
                y = y | c[s.charAt(j)];
            }
            if (!firstDisplay.add(y) && secondDisplay.add(y)){ // 非常巧的方法，若子串初次出现，则前面的!Hashset.add为false，则后面的Hashset不会做，
                                                                // 若子串超过两次出现，后面的Hashset.add为false
                                                                // 则只有当子串正好第二次出现时才执行if语句中内容。
                ans.add(s.substring(i,i+10));
            }
        }
        return ans;
    }
}