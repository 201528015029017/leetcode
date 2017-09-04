/*
思路：一开始想到的就是裸的深搜，先预处理单词表，两两之间是否可以相互到达（即距离为1），但是超时。
    改成BFS，广搜时不仅记录路径长度，还记录下是由哪些单词达到它的（可能有多个，所以用list存），
    然后根据这些list来求得路径，时间为1000ms。
    优化1：我们在预处理时，如果采用n^2的方法，记录成boolean dist[i][j]，那么广搜也时n^2的复杂度。
        如果能事先知道某个单词所有能到达的点，那么可以用邻接表的方式存储，降低复杂度。
        考虑单词“abc”，它能转变的单词时一个位置变成另一个单词，所以用3×26种变法，我们再判断变成的单词是否在
        wordlist（hash下）中就行了。
    优化2：广搜时从起点开始往后扩展一次，然后从终点往前扩展一次，找是否有相同点。即首尾相继扩展的方法，这样能更快的找到。
        未实现。
 */
public class Solution {
    List<List<String>> ans = new ArrayList<List<String>>();
    List<String> wordlist;
    Map<String,Integer> wordHash = new HashMap<String,Integer>();
    Map<Integer,List<Integer>> neibormap = new HashMap<Integer, List<Integer>>();

    class qelement{
        int val;
        List<Integer> pre;
        int step;
        public qelement(int val){
            this.val = val;
            pre = new ArrayList<Integer>();
            step = Integer.MAX_VALUE;
        }
    }

    public void genNeibor(int id,String a){
        StringBuilder sb;
        String str;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < a.length(); i++){
            sb = new StringBuilder(a);
            for (char c = 'a'; c <= 'z'; c++){
                if (c == a.charAt(i)) continue;
                sb.setCharAt(i,c);
                str = sb.toString();
                if (wordHash.containsKey(str)){
                    int num = wordHash.get(str);
                    list.add(num);
                }
            }
        }
        neibormap.put(id,list);
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        wordlist = wordList;
        int n = wordList.size();
        int start = n-1;
        int end = -1;
        for (int i = 0; i < n; i++){
            if (endWord.equals(wordList.get(i))) end = i;
            wordHash.put(wordList.get(i),i);
        }
        for (int i = 0; i < n; i++){
            genNeibor(i,wordList.get(i));
        }

        if (end != -1)
            findstep(start,end,n);
        return ans;
    }

    public void findstep(int start,int end,int n){
        qelement[] f = new qelement[n];
        for (int i = 0; i < n; i++){
            f[i] = new qelement(i);
        }
        f[start].step = 0;
        int [] q = new int [n];
        q[0] = start;
        int t = -1,w = 0;
        Integer x;
        List<Integer> list;
        while (t < w){
            t++; x = q[t];
            list = neibormap.get(x);
            for (Integer i:list)
                if (f[i].step > f[x].step + 1) {
                    w++;
                    q[w] = i;
                    f[i].step = f[x].step + 1;
                    f[i].pre.add(x);
                }
                else if (f[i].step == f[x].step+1)
                    f[i].pre.add(x);
        }
        //
        List<Integer> res = new ArrayList<Integer>();
        res.add(end);
        findpath(f,end,res);
    }

    //
    public void findpath(qelement[] f, int x,List<Integer> res){
        if (f[x].step == 0){
            List<String> a = new ArrayList<String>();
            for (int k = res.size()-1; k >= 0; k--){
                a.add(wordlist.get(res.get(k)));
            }
            ans.add(a);
            return;
        }
        for(int i = 0; i < f[x].pre.size(); i++) {
            res.add(f[x].pre.get(i));
            findpath(f,f[x].pre.get(i),res);
            res.remove(res.size()-1);
        }
    }

}