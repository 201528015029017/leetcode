/**
 * Created by Chongrui on 2017/7/7 0007.
 */
/*
jdk1.8排序部分的源码分析

主要class DualPivotQuicksort
 */

public class DualPivotQuicksort {
    /*
    这里对于不同的类型，涉及不同的函数重载，对int进行分析
     */
    private static final int MAX_RUN_COUNT = 67;

    private static final int MAX_RUN_LENGTH = 33;

    private static final int QUICKSORT_THRESHOLD = 286;

    private static final int INSERTION_SORT_THRESHOLD = 47;

    private static final int COUNTING_SORT_THRESHOLD_FOR_BYTE = 29;

    private static final int COUNTING_SORT_THRESHOLD_FOR_SHORT_OR_CHAR = 3200;

    static void sort(int[] a, int left, int right,
                     int[] work, int workBase, int workLen) {         //第一级入口，这里根据数据量和数组元素特点判断进行何种排序
        // 当数据量小于286时，直接进行快速排序
        if (right - left < QUICKSORT_THRESHOLD) {
            sort(a, left, right, true);
            return;
        }

        /*
         * 当数据量大于286时，判断数组元素的有序程度
         * 就是把数组分成一段一段的有序段，如果有序段越少，说明有序程度越高
         * run[]记录有序段的位置
         */
        int[] run = new int[MAX_RUN_COUNT + 1];
        int count = 0; run[0] = left;

        // Check if the array is nearly sorted
        for (int k = left; k < right; run[count] = k) {
            if (a[k] < a[k + 1]) { // 升序
                while (++k <= right && a[k - 1] <= a[k]);
            } else if (a[k] > a[k + 1]) { // 降序
                while (++k <= right && a[k - 1] >= a[k]);
                for (int lo = run[count] - 1, hi = k; ++lo < --hi; ) {  //将降序段翻转变成升序段
                    int t = a[lo]; a[lo] = a[hi]; a[hi] = t;
                }
            } else { // 相等
                for (int m = MAX_RUN_LENGTH; ++k <= right && a[k - 1] == a[k]; ) {
                    if (--m == 0) {           //相等段的长度大于了33，直接进行快速排序
                        sort(a, left, right, true);
                        return;
                    }
                }
            }

            /*
             * The array is not highly structured,
             * use Quicksort instead of merge sort.
             */
            if (++count == MAX_RUN_COUNT) {        //当分得的有序段数量太多（大于67），就直接进行快速排序
                sort(a, left, right, true);
                return;
            }
        }

        // Check special cases
        // Implementation note: variable "right" is increased by 1.
        if (run[count] == right++) { // The last run contains one element，注意这里的right++,
            run[++count] = right;
        } else if (count == 1) { //只有一个有序段，说明整个数组是有序的
            return;
        }

        //进行归并排序
        // Determine alternation base for merge
        byte odd = 0;
        for (int n = 1; (n <<= 1) < count; odd ^= 1);

        // Use or create temporary array b for merging
        int[] b;                 // temp array; alternates with a,其实就是用于滚动数组
        int ao, bo;              // array offsets from 'left' 偏移量
        int blen = right - left; // space needed for b  注意，这里的right已经在之前加+1了，所以right-left就是元素个数
        if (work == null || workLen < blen || workBase + blen > work.length) {
            work = new int[blen];
            workBase = 0;
        }
        if (odd == 0) {
            System.arraycopy(a, left, work, workBase, blen); //a数组从left开始复制blen个元素到work数组中
            b = a;
            bo = 0;
            a = work;
            ao = workBase - left;
        } else {
            b = work;
            ao = 0;
            bo = workBase - left;
        }

        // 归并排序，根据run[]，两两相邻的有序段合并，直到有序段为1
        for (int last; count > 1; count = last) {
            for (int k = (last = 0) + 2; k <= count; k += 2) {
                int hi = run[k], mi = run[k - 1];
                for (int i = run[k - 2], p = i, q = mi; i < hi; ++i) {
                    if (q >= hi || p < mi && a[p + ao] <= a[q + ao]) {
                        b[i + bo] = a[p++ + ao];
                    } else {
                        b[i + bo] = a[q++ + ao];
                    }
                }
                run[++last] = hi;
            }
            if ((count & 1) != 0) {       //当次迭代的元素为奇数，单一个有序段
                for (int i = right, lo = run[count - 1]; --i >= lo;
                     b[i + bo] = a[i + ao]
                        );
                run[++last] = right;
            }
            int[] t = a; a = b; b = t; //滚动数组
            int o = ao; ao = bo; bo = o;
        }
    }
    private static void sort(int[] a, int left, int right, boolean leftmost) { //排序第二级，这里进行递归的方式
        int length = right - left + 1;

        // Use insertion sort on tiny arrays
        if (length < INSERTION_SORT_THRESHOLD) {  //当元素个数小于47时，进行插入排序
            if (leftmost) {       //如果当前区间是整个数组的最左边（这里的整个数组的最左边指的是最开始用户传入的待排序区间的左界，不一定是内存分配的最左边）
                /*
                 * 采用传统的插入排序算法
                 */
                for (int i = left, j = i; i < right; j = ++i) {
                    int ai = a[i + 1];
                    while (ai < a[j]) {
                        a[j + 1] = a[j];
                        if (j-- == left) {
                            break;
                        }
                    }
                    a[j + 1] = ai;
                }
            } else { //如果不是最左边，那么首先可以证明，左边的数肯定是比当前区间的所有数都要小，可以把这些数当成是一个左边界来处理越界情况
                /*
                 * 跳过有序段
                 */
                do {
                    if (left >= right) {
                        return;
                    }
                } while (a[++left] >= a[left - 1]);

                /*
                 * 这里采用一个改良过的插入排序算法，双插入排序，传统的插入排序每次迭代插入一个数，这里每次迭代插入两个数，能提高效率。
                 */
                for (int k = left; ++left <= right; k = ++left) {
                    int a1 = a[k], a2 = a[left];

                    if (a1 < a2) {
                        a2 = a1; a1 = a[left];
                    }
                    //找较大数的位置
                    while (a1 < a[--k]) { //注意这里，如果当前区间是整个数组左边界，那么--k会数组越界，所以leftmost为true时，不能用双插入排序。
                                            //如果当前区间不是整个数组左边界，那么左边的数会比当前区间的数小，即肯定也比a1小，所以循环会结束，
                                            // 所以其实是把越界了的数当成的判断条件，很巧妙！
                        a[k + 2] = a[k];
                    }
                    a[++k + 1] = a1;
                    //找较小数的位置
                    while (a2 < a[--k]) {
                        a[k + 1] = a[k];
                    }
                    a[k + 1] = a2;
                }
                int last = a[right];

                while (last < a[--right]) {
                    a[right + 1] = a[right];
                }
                a[right + 1] = last;
            }
            return;
        }

        //如果元素个数大于47个，那么就进行双轴快速排序算法
        // Inexpensive approximation of length / 7
        int seventh = (length >> 3) + (length >> 6) + 1;

        /*
         * 在区间中等距离的取出5个点，将这五个点按照从小到大的顺序排列
         */
        int e3 = (left + right) >>> 1; // The midpoint
        int e2 = e3 - seventh;
        int e1 = e2 - seventh;
        int e4 = e3 + seventh;
        int e5 = e4 + seventh;

        // Sort these elements using insertion sort
        if (a[e2] < a[e1]) { int t = a[e2]; a[e2] = a[e1]; a[e1] = t; }

        if (a[e3] < a[e2]) { int t = a[e3]; a[e3] = a[e2]; a[e2] = t;
            if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
        }
        if (a[e4] < a[e3]) { int t = a[e4]; a[e4] = a[e3]; a[e3] = t;
            if (t < a[e2]) { a[e3] = a[e2]; a[e2] = t;
                if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
            }
        }
        if (a[e5] < a[e4]) { int t = a[e5]; a[e5] = a[e4]; a[e4] = t;
            if (t < a[e3]) { a[e4] = a[e3]; a[e3] = t;
                if (t < a[e2]) { a[e3] = a[e2]; a[e2] = t;
                    if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
                }
            }
        }

        // Pointers
        int less  = left;  // The index of the first element of center part
        int great = right; // The index before the first element of right part

        /*
         * 如果5个点均不等，那么进行双轴快速排序算法，
         * 我们把第二个和第四个点作为 pivot1 和 pivot2，并且放到首尾两端，双轴快速排序的思想就是把区间分成3部分，分别是
         * 小于 pivot1 , 大于pivot1小于pivot2， 大于pivot2。，然后在3个部分分别递归
         */
        if (a[e1] != a[e2] && a[e2] != a[e3] && a[e3] != a[e4] && a[e4] != a[e5]) {
            /*
             * Use the second and fourth of the five sorted elements as pivots.
             * These values are inexpensive approximations of the first and
             * second terciles of the array. Note that pivot1 <= pivot2.
             */
            int pivot1 = a[e2];
            int pivot2 = a[e4];

            /*
             * The first and the last elements to be sorted are moved to the
             * locations formerly occupied by the pivots. When partitioning
             * is complete, the pivots are swapped back into their final
             * positions, and excluded from subsequent sorting.
             */
            a[e2] = a[left];
            a[e4] = a[right];

            /*
             * Skip elements, which are less or greater than pivot values.
             */
            while (a[++less] < pivot1);
            while (a[--great] > pivot2);

            /* ？代表的是待排区间，由k指针控制，直到待排区间变成0后，当次排序完成。less，great代表3个区间的分隔线
             * Partitioning:
             *
             *   left part           center part                   right part
             * +--------------------------------------------------------------+
             * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
             * +--------------------------------------------------------------+
             *               ^                          ^       ^
             *               |                          |       |
             *              less                        k     great
             *
             * Invariants:
             *
             *              all in (left, less)   < pivot1
             *    pivot1 <= all in [less, k)     <= pivot2
             *              all in (great, right) > pivot2
             *
             * Pointer k is the first index of ?-part.
             */
            //这里是交换的策略
            outer:
            for (int k = less - 1; ++k <= great; ) {
                int ak = a[k];
                if (ak < pivot1) { // 如果当前的数a[k]比pivot1小，移动 a[k] 到第一区间，可以看到移动就是和less换交换位置
                    a[k] = a[less];
                    /*
                     * Here and below we use "a[i] = b; i++;" instead
                     * of "a[i++] = b;" due to performance issue.
                     */
                    a[less] = ak;
                    ++less;
                } else if (ak > pivot2) { // 如果当前的数a[k]比pivot2大，需要移动 a[k] 到第3区间，分情况讨论
                    while (a[great] > pivot2) {              //往前移动great指针，找到第一个不属于第3区间的数a[great]
                        if (great-- == k) {
                            break outer;
                        }
                    }
                    if (a[great] < pivot1) { // 如果a[great] <= pivot1，那么就a[k],a[less],a[great] 3数换位置
                        a[k] = a[less];
                        a[less] = a[great];
                        ++less;
                    } else { // 如果pivot1 <= a[great] <= pivot2, 那么就a[k],a[great]换位置
                        a[k] = a[great];
                    }
                    /*
                     * Here and below we use "a[i] = b; i--;" instead
                     * of "a[i--] = b;" due to performance issue.
                     */
                    a[great] = ak;
                    --great;
                }
            }

            // 最后把pivot1 和 pivot2 换到本身位置上
            a[left]  = a[less  - 1]; a[less  - 1] = pivot1;
            a[right] = a[great + 1]; a[great + 1] = pivot2;

            // 递归处理第一区间和第三区间
            sort(a, left, less - 2, leftmost);
            sort(a, great + 2, right, false);

            /*
             * 对于第二区间，可能这个区间很大，我们认为可能的因素是等于pivot1 和 pivot2的值很多，所以我们将这个区间再
             * 进行细分，分成3个部分，等于pivot1， 大于pivot1小于pivot2， 等于pivot2，然后只处理第二区间
             */
            if (less < e1 && e5 < great) {
                /*
                 * Skip elements, which are equal to pivot values.
                 */
                while (a[less] == pivot1) {
                    ++less;
                }

                while (a[great] == pivot2) {
                    --great;
                }

                /*
                 * Partitioning:
                 *
                 *   left part         center part                  right part
                 * +----------------------------------------------------------+
                 * | == pivot1 |  pivot1 < && < pivot2  |    ?    | == pivot2 |
                 * +----------------------------------------------------------+
                 *              ^                        ^       ^
                 *              |                        |       |
                 *             less                      k     great
                 *
                 * Invariants:
                 *
                 *              all in (*,  less) == pivot1
                 *     pivot1 < all in [less,  k)  < pivot2
                 *              all in (great, *) == pivot2
                 *
                 * Pointer k is the first index of ?-part.
                 */
                outer:
                for (int k = less - 1; ++k <= great; ) {
                    int ak = a[k];
                    if (ak == pivot1) { // Move a[k] to left part
                        a[k] = a[less];
                        a[less] = ak;
                        ++less;
                    } else if (ak == pivot2) { // Move a[k] to right part
                        while (a[great] == pivot2) {
                            if (great-- == k) {
                                break outer;
                            }
                        }
                        if (a[great] == pivot1) { // a[great] < pivot2
                            a[k] = a[less];
                            /*
                             * Even though a[great] equals to pivot1, the
                             * assignment a[less] = pivot1 may be incorrect,
                             * if a[great] and pivot1 are floating-point zeros
                             * of different signs. Therefore in float and
                             * double sorting methods we have to use more
                             * accurate assignment a[less] = a[great].
                             */
                            a[less] = pivot1;
                            ++less;
                        } else { // pivot1 < a[great] < pivot2
                            a[k] = a[great];
                        }
                        a[great] = ak;
                        --great;
                    }
                }
            }

            //递归处理第二区间
            sort(a, less, great, false);

        } else { // 如果5个数有相同的数，那么就进行单轴快速排序，将第三个数即中间数取为关键字，和普通快速排序不同的是，
            //这里同样是分成3个区间，小于pivot, 等于pivot, 大于pivot，第二区间无需递归
            /*
             * Use the third of the five sorted elements as pivot.
             * This value is inexpensive approximation of the median.
             */
            int pivot = a[e3];

            /*
             * Partitioning degenerates to the traditional 3-way
             * (or "Dutch National Flag") schema:
             *
             *   left part    center part              right part
             * +-------------------------------------------------+
             * |  < pivot  |   == pivot   |     ?    |  > pivot  |
             * +-------------------------------------------------+
             *              ^              ^        ^
             *              |              |        |
             *             less            k      great
             *
             * Invariants:
             *
             *   all in (left, less)   < pivot
             *   all in [less, k)     == pivot
             *   all in (great, right) > pivot
             *
             * Pointer k is the first index of ?-part.
             */
            for (int k = less; k <= great; ++k) {
                if (a[k] == pivot) {
                    continue;
                }
                int ak = a[k];
                if (ak < pivot) { // Move a[k] to left part
                    a[k] = a[less];
                    a[less] = ak;
                    ++less;
                } else { // a[k] > pivot - Move a[k] to right part
                    while (a[great] > pivot) {
                        --great;
                    }
                    if (a[great] < pivot) { // a[great] <= pivot
                        a[k] = a[less];
                        a[less] = a[great];
                        ++less;
                    } else { // a[great] == pivot
                        /*
                         * Even though a[great] equals to pivot, the
                         * assignment a[k] = pivot may be incorrect,
                         * if a[great] and pivot are floating-point
                         * zeros of different signs. Therefore in float
                         * and double sorting methods we have to use
                         * more accurate assignment a[k] = a[great].
                         */
                        a[k] = pivot;
                    }
                    a[great] = ak;
                    --great;
                }
            }

            /*
             * Sort left and right parts recursively.
             * All elements from center part are equal
             * and, therefore, already sorted.
             */
            sort(a, left, less - 1, leftmost);
            sort(a, great + 1, right, false);
        }
    }
}
