/**
 * Created by Chongrui on 2017/5/24 0024.
 */
public class BinarySearch {
    //找到第一个大于等于key的位置
    //PS： 这里我们看到当a[mid] >= key 时right = mid - 1； 当a[mid] < key 时，left = mid + 1；
    //     这样的话是不是有可能正确值别排除在【left,right】之外了呢？
    //     确实会这样，但是我们看到，加入正确值被排除在外了，那它只可能在right+1的位置
    //     又因为二分结束时left == right + 1的，所以，当最后一次比较mid时(left == right)，
    //       若a[mid] < key left = mid + 1就是被排除的正确位置
    //       若a[mid] >= key right = mid - 1 此时left==mid没变，left还是满足条件位置
    public static  int firstGreatOrEqual(int[] a, int key){
        int left = 0;
        int right = a.length-1;
        int mid;
        while(left <= right) {
            mid = left + ((right-left) >> 1);
            if(a[mid] >= key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left < a.length ? left : -1;
    }
    //找到第一个比key大的位置
    //这里可以看到就是讲a[mid] >= key 改成了 a[mid] > key, 很好理解
    public static  int firstGreat(int[] a, int key){
        int left = 0;
        int right = a.length-1;
        int mid;
        while(left <= right) {
            mid = left + ((right-left) >> 1);
            if(a[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left < a.length ? left : -1;
    }

    //找到最后一个小于key的位置
    //其实套用的是firstGreatOrEqual模板，先找到第一个大于等于key的位置，它的前一个就是比key小的位置
    public static  int lastLower(int[] a, int key){
        int left = 0;
        int right = a.length-1;
        int mid;
        while(left <= right) {
            mid = left + ((right-left) >> 1);
            if(a[mid] >= key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left - 1 >= 0 ? left - 1 : -1;
    }

    //找到最后一个小于等于key的位置
    //套用的是firstGreat模板，先找到第一个大于key的位置，它的前一个就是小于等于key的位置
    public static  int lastLowerOrEqual(int[] a, int key){
        int left = 0;
        int right = a.length-1;
        int mid;
        while(left <= right) {
            mid = left + ((right-left) >> 1);
            if(a[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left - 1 >= 0 ? left - 1 : -1;
    }
    //找到第一个等于key的位置
    //套用firstGreatOrEqual模板，先找到 第一个大于等于key的位置，再判断是不是key
    public static  int firstEqual(int[] a, int key){
        int left = 0;
        int right = a.length-1;
        int mid;
        while(left <= right) {
            mid = left + ((right-left) >> 1);
            if(a[mid] >= key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (left < a.length && a[left] == key)? left : -1;
    }
    //找到最后一个等于key的位置
    //套用lastLowerOrEqual模板，先找到最后一个小于等于key的位置，再判断是不是key
    public static  int lastEqual(int[] a, int key){
        int left = 0;
        int right = a.length-1;
        int mid;
        while(left <= right) {
            mid = left + ((right-left) >> 1);
            if(a[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (left - 1 >= 0 && a[left - 1] == key)? left - 1 : -1;
    }

    public static void main(String[] args) {
        int[] a = {1,1,2,2,3,5,6,6,6,6,8};
        System.out.println(firstEqual(a,6));
    }
}
