/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newproject2;

/**
 *
 * @author hieub
 */
public class MergeSort implements Runnable{
    private int[] arr;
    private int begin;
    private int end;

    public MergeSort(int[] arr, int begin, int end) {
        this.arr = arr;
        this.begin = begin;
        this.end = end;
    }
    
    static void merge(int[] arr, int begin, int mid, int end) {
        int i=begin;
        int j=mid+1;
        int k=0;
        int[] tmp = new int[(end-begin) + 1];
        
        while(i<=mid && j<=end) {
            if(arr[i] < arr[j]) tmp[k++] = arr[i++];
            else tmp[k++] = arr[j++];
        }
        while(i<=mid) tmp[k++] = arr[i++];
        while(j<=end) tmp[k++] = arr[j++];
        
        for(i=begin, k=0; i<=end; i++,k++) {
            arr[i] = tmp[k];
        }
    }
    
    private void mergeSort(int[] arr, int begin, int end) {
        if(begin < end) {
            int mid = (begin+end)/2;
            mergeSort(arr, begin, mid);
            mergeSort(arr, mid+1, end);
            merge(arr, begin, mid, end);
        }
    }

    @Override
    public void run() {
        mergeSort(arr, begin, end);
    }

   
    
}
