package com.benchmark.streaming.ff;

import java.util.Scanner;

public class Main {
    static int mod;
    static int[] a = new int[200001];
    static int[] label = new int[200001];
    static int index=0;
    static int len = 0;
    static int t = 0;
    public static void add(int x) {
        x = (x+t)%mod;
        ++index;
        while (len > 0 && a[len-1]<=x){
            len--;
        }
        a[len] = x;
        label[len] = index;
        len++;
    }

    public static int query(int x) {
        int left = 0;
        int right = len-1;
        x = index - x + 1;
        while (left < right){
            int mid = left + (right-left)/2;
            if (label[mid] < x) left = mid + 1;
            else right = mid;
        }
        return a[left];
    }

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        mod = in.nextInt();

        while(m-- > 0){
            String ch = in.next();
            int x = in.nextInt();

            if(ch.equals("I")){
                add(x);
            } else {
                t = query(x);
                System.out.println(t);
            }
            for (int i = 0; i < len; i++){
                System.out.print(a[i]+" ");
            }
            System.out.println();
            for (int i = 0; i < len; i++){
                System.out.print(label[i]+" ");
            }
            System.out.println();
        }
    }
}