/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hieub
 */
public class main extends config{
      
    private static List<Integer> readFile() throws IOException {
        List<Integer> list = new ArrayList<>();
        FileInputStream fileInput = null;
        BufferedReader bufferedReader = null;
        try {
            fileInput = new FileInputStream("input.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInput));
            String line = bufferedReader.readLine();
            //doc so hang
            while(line!=null) {
                String[] arr = line.split(" ");
                for(String s:arr) {
                    int number = 0;
                    for(char c:s.toCharArray()){
                        number = number*10 + Integer.parseInt(String.valueOf(c));
                    }
                    list.add(number);
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
//        }  catch (CheckFile e) {
//            e.ErrorLimitCharacter();
        }  finally {
            fileInput.close();
            bufferedReader.close();
        }
        return list;
    }
    
    private static void writeFile(File file, List<Integer> data) throws IOException {
        Writer out = new FileWriter(file);
        for(int i=0; i<data.size(); i++) {
            out.write(String.valueOf(data.get(i)));
            out.write(" ");
        }
        out.close();
    }
    
    private static List<Integer> Merge() {
        while(results.size() > 0) {
            while(results.size() > 1) {
                try {
                    List<Integer> list1 = results.get(0).get();
                    results.remove(0);
                    List<Integer> list2 = results.get(0).get();
                    results.remove(0);
                    results.add(exc.submit(new MergeSort(list1, list2)));
//                    System.out.println(pool.getActiveCount() + " - " + results.size());
                }catch (Exception e) {
                    System.out.println(e);
                }
            }
            if(results.size()==1 && pool.getActiveCount()==0) break;
        }
        
//        List<List<Integer>> listAll = new ArrayList<>();
//        int[] lengthArray = new int[n];
//        for(int i=0; i<n; i++) {
//            try {
//                listAll.add(results.get(i).get());
//                lengthArray[i] = results.get(i).get().size();
//            } catch (InterruptedException ex) {
//                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ExecutionException ex) {
//                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        List<Integer> ans = new ArrayList<>();
//        while(listAll.size()>0) {
//            int minInt = Integer.MAX_VALUE;
//            int indexRemove=0;
//            for(int i=0; i<listAll.size(); i++) {
//                int value = listAll.get(i).get(0);
//                if(value < minInt) {
//                    minInt = value;
//                    indexRemove = i;
//                    System.out.println(value + " ");
//                }
//            }
//            listAll.get(indexRemove).remove(0);
//            if(listAll.get(indexRemove).isEmpty()) listAll.remove(indexRemove);
//            ans.add(minInt);
//        }

        
        try {
            ans = results.get(0).get();
        } catch (Exception e) {
            System.out.println(e);
        }
        return ans;
    }
    
    private static void sort(List<Integer> list) {
        int lengthOfChildArray = list.size()/n;
        for(int i=1; i<=n; i++) {
            int right, left = lengthOfChildArray*(i-1);
            if(i==n) right = list.size() - 1;
            else right = left + lengthOfChildArray - 1;
            results.add(exc.submit(new QuickSort(list, left, right)));
            //System.out.println(pool.getActiveCount());
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        final long startTime = System.currentTimeMillis();
        final long startTimeRead = System.currentTimeMillis();
        List<Integer> list = readFile();
        final long endTimeRead = System.currentTimeMillis();
        System.out.println("Total time read " + (endTimeRead - startTimeRead));
        
        final long startTimeSort = System.currentTimeMillis();
        sort(list);                     // Chia thành n mảng và sắp xếp mỗi mảng
        while(pool.getActiveCount()>0) {
        }
        final long endTimeSort = System.currentTimeMillis();
        System.out.println("Total time sort: " + (endTimeSort - startTimeSort));
        
        final long startTimeMerge = System.currentTimeMillis();
        List<Integer> ans = Merge();    // Gộp các mảng lại
        while(pool.getActiveCount()>0) {
        }
        final long endTimeMerge = System.currentTimeMillis();
        System.out.println("Total time merge: " + (endTimeMerge - startTimeMerge));
        
        final long startTimeWrite = System.currentTimeMillis();
        writeFile(out, ans);
        final long endTimeWrite = System.currentTimeMillis();
        System.out.println("Total time write: " + (endTimeWrite - startTimeWrite));
        exc.shutdown();
        final long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime));
    }
    
}
