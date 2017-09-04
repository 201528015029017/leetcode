/*
思路：解法1：
    题意是求出现频率的topK,我们可以直接用堆来实现。
    首先把数组元素放到hashmap中，求出每种元素的出现个数。然后遍历hash表将这些元素建立一个大根堆，
    最后取前K个元素即可。
    优化：我们先分析下以上解法的复杂度为O(mlogm) ,m 为不同的元素个数，如果此时m==n则复杂度就退化成了O（nlogn）
        再次分析我们可以发现，我们只需要前k个数据就行了，如果能只建立一个数量为k的堆，就能减少复杂度。
        也就是说建堆时，我们只存当前的前k大的数，小的数我们就出堆了，怎么找需要出堆的数呢？小根堆！
        所以我们维护一个数量为k的小根堆，每来一个元素，
        若堆中元素不超过k，则不管；
        若堆中元素为k+1，我们就将这k+1中最小的元素出堆(可能正好是要添加的，也可能是堆的根节点)；
        这样最后留在堆中的元素的就是topK了
        复杂度为O（mlogk）（将大根堆转化成了小根堆，很神奇！）
     解法2：
     还是首先将数组元素hashmap，然后我们得到每种元素的出现个数freq[i]，我们直到freq[i]的值范围肯定是1-n的，
     所以我们将这个freq数组进行桶排序，bucket[i]记录出现i次的元素是多少，那么可能有多个元素都出现了i次，所以bucket[i]其实
     可以存成一个list或链表的形式，最后我们从大到小扫一遍bucket，求前k个就行了。
     复杂度为O（n）
    以下代码是堆实现，其实java也提供了很对封装好了数据结构，比如优先队列（其实就是堆的实现），还有treemap也可以做这道题
 */
public class Solution {
    private void swap(int[] a,int i,int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public void addheap(int[] heap,int[] index,int key,int value){
        heap[0]++;
        heap[heap[0]] = value;
        index[heap[0]] = key;
        int i = heap[0];
        while (i > 1){
            if (heap[i/2] > heap[i]){
                swap(heap,i,i/2);
                swap(index,i,i/2);
                i = i / 2;
            }
            else break;
        }
    }

    public void deleteheap(int[] heap,int[] index,int pos){
        heap[pos] = heap[heap[0]];
        index[pos] = index[heap[0]];
        heap[0]--;
        int n = heap[0];
        int i = pos;
        while (2*i <= n){
            int j = 2*i;
            if (j + 1 <= n && heap[j+1] < heap[j]) j = j + 1;
            if (heap[i] > heap[j]) {
                swap(heap,i,j);
                swap(index,i,j);
                i = j;
            }
            else break;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> freq = new HashMap<>();
        int heap[] = new int[nums.length+1];
        int index[] = new int[nums.length+1];
        heap[0] = 0;
        for (int i = 0; i < nums.length; i++){
            int c = freq.getOrDefault(nums[i],0);
            freq.put(nums[i],c+1);
        }
        for (Map.Entry<Integer,Integer> entry: freq.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            addheap(heap,index,key,value);
            if (heap[0] == k+1){
                deleteheap(heap,index,1);
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 1; i <= k; i++){
            ans.add(index[i]);
        }
        return ans;
    }
}