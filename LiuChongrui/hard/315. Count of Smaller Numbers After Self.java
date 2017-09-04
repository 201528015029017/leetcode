/*
Problem:
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].

思路：
   题意是求第i个数后面有多少个比它小的。
   1.类似于求逆序对，但是稍微有点不同的事，这个要求每个数的逆序对，而不是总的，所以要用结构体或类包装下，同时记录原来的位置。
    可以用归并排序的方法做，在merge之前先判断逆序的数目，然后再排，这样好处理。
   2.从后往前看，其实就是维护当前比a[i]小的有多少个，我们可以用平衡树/二叉排序树做，由于需要知道个数，所以jdk中的treemap不能满足要求
    得自己定义节点，记录左子树和右子树的个数。

 */
class Solution {

    class Ele{
        public int val,ans,no;
        public Ele(int val,int ans,int no){
            this.val = val;
            this.ans = ans;
            this.no = no;
        }
        public Ele(Ele a){
            this.val = a.val;
            this.ans = a.ans;
            this.no = a.no;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        Ele[] a = new Ele[nums.length];
        for (int i = 0; i < nums.length; i++)
            a[i] = new Ele(nums[i],0,i);
        mergeSort(a,0,a.length-1);
        Arrays.sort(a,new Comparator<Ele>(){
            public int compare(Ele o1,Ele o2){
                return o1.no - o2.no;
            }
        });
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < a.length; i++) ans.add(a[i].ans);
        return ans;
    }
    public void mergeSort(Ele[] a, int l, int r){
        if (l>=r) return;
        int mid = l + (r-l)/2;
        mergeSort(a,l,mid);
        mergeSort(a,mid+1,r);
        //calc
        int j = mid+1;
        for (int i = l; i <= mid; i++){
            while (j <= r && a[j].val < a[i].val) j++;
            a[i].ans += j-mid-1;
        }
        // merge;
        int i = l, k = 0;
        j = mid+1;
        Ele[] temp = new Ele[r-l+1];
        while (i <= mid && j <= r){
            if (a[i].val < a[j].val) {
                temp[k++] = new Ele(a[i]);
                i++;
            }
            else {
                temp[k++] = new Ele(a[j]);
                j++;
            }
        }
        while (i <= mid) {
            temp[k++] = new Ele(a[i]);
            i++;
        }
        while (j <= r) {
            temp[k++] = new Ele(a[j]);
            j++;
        }
        for (i = l; i <= r; i++) a[i] = temp[i-l];
        return;
    }
}