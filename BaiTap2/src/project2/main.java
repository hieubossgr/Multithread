/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hieub
 */
public class main extends config{
      
    private static List<Integer> readFile() {
        
        List<Integer> list = new ArrayList<>();
        try {
            RandomAccessFile raf = new RandomAccessFile(in, "r");
            String line = raf.readLine();
            while(line!=null) {
                String[] arr = line.split(" ");
                for(String s:arr) {
                    int number = 0;
                    for(char c:s.toCharArray()){
                        number = number*10 + Integer.parseInt(String.valueOf(c));
                    }
                    list.add(number);
                }
                line = raf.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file");
        } catch (IOException e) {
            System.out.println(e);
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
        while(results.size() > 1) {
            try {
                List<Integer> list1 = results.get(0).get();
                results.remove(0);
                List<Integer> list2 = results.get(0).get();
                results.remove(0);
                results.add(exc.submit(new MergeSort(list1, list2)));
                    
           } catch (Exception e) {
                System.out.println(e);
           }
        }
        
        List<Integer> ans = new ArrayList<>();
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
            System.out.println(pool.getActiveCount());
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        final long startTimeRead = System.currentTimeMillis();
        List<Integer> list = readFile();
        final long endTimeRead = System.currentTimeMillis();
        System.out.println("Total time read " + (endTimeRead - startTimeRead));
        
        final long startTime = System.currentTimeMillis();
        sort(list);                     // Chia thành n mảng và sắp xếp mỗi mảng
        List<Integer> ans = Merge();    // Gộp các mảng lại
        final long endTime = System.currentTimeMillis();
        System.out.println("Total time sort: " + (endTime - startTime));
        
        final long startTimeWrite = System.currentTimeMillis();
        writeFile(out, ans);
        final long endTimeWrite = System.currentTimeMillis();
        System.out.println("Total time write: " + (endTimeWrite - startTimeWrite));
        exc.shutdown();
    }
    
}
