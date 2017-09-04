/*
思路：模拟题，我们将数字三个三个分组，不足三个的前面补0，然后每组分别处理百位十位和个位，
    注意一下空格的添加还有0的处理
 */
public class Solution {
    public String numberToWords(int num) {
        String[] dict1 = {"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
        String[] dict2 = {"Ten", "Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
        String[] dict3 = {"Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
        String[] dict4 = {"Hundred","","Thousand","Million","Billion"};
        int x,num3,count,ge=0,m=0,s=0;
        String ans="";
        String fans = "";
        if(num == 0) return "Zero";
        x = num;
        num3 = 0;
        count = 0;
        while (x > 0 || count != 0){
            m = x % 10;
            x = x / 10;
            count++;
            s = s * 10 + m;
            if (count == 3){
                if (m > 0) ans = dict1[m] + " " + dict4[0]+ " " + ans;
                if (s > 0) {
                    ans = ans.trim() + " "+dict4[num3];
                    fans = ans.trim() +" "+ fans;
                }
                ans = "";
                count = 0;
                s = 0;
            }
            else if (count == 2){
                if (m == 1) ans = dict2[ge]+ " " + ans;
                else if (m >= 2) ans = dict3[m-2] + " "+ dict1[ge] +" "+ ans;
                else if (m == 0) ans = dict1[ge] + ans;
            }
            else if (count == 1){
                num3 ++;
                ge = m;
            }
        }
        return fans.trim();
    }
}