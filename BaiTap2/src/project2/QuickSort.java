/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author hieub
 */
public class QuickSort implements Callable<List<Integer>>{
    private List<Integer> list;
    private int left;
    private int right;

    public QuickSort(List<Integer> list, int left, int right) {
        this.list = list;
        this.left = left;
        this.right = right;
    }

    @Override
    public List<Integer> call() throws Exception {
        List<Integer> ansList = new ArrayList<>();
        for(int i=left; i<=right; i++) 
            ansList.add(list.get(i));
        Collections.sort(ansList);
        return ansList;
    }
}
