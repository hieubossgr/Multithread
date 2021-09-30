/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author hieub
 */
public class MergeSort implements Callable<List<Integer>>{
    private static List<Integer> list1;
    private static List<Integer> list2;

    public MergeSort(List<Integer> list1, List<Integer> list2) {
        this.list1 = list1;
        this.list2 = list2;
    }
    
    private static List<Integer> merge(){
        List<Integer> ansList = new ArrayList<>();
        
        int i=0, j=0;
        int length1 = list1.size(), length2 = list2.size();
        while(i<length1 && j<length2) {
            if(list1.get(i) < list2.get(j)) ansList.add(list1.get(i++));
            else ansList.add(list2.get(j++));
        }
        while(i<length1) ansList.add(list1.get(i++));
        while(j<length2) ansList.add(list2.get(j++));
        return ansList;
    }

    @Override
    public List<Integer> call() throws Exception {
        return merge();
    }
    
}
