/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newproject2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author hieub
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static int n = 3;
    private static File input = new File("input.txt");
    private static File output = new File("output.txt");
    
    public static int[] readFile(File file) throws IOException {
        long timeRead = System.currentTimeMillis();
        List<Integer> listInteger = new ArrayList<>();
        FileInputStream fileInput = null;
        BufferedReader bufferedReader = null;
        try {
            fileInput = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInput));
            String line = bufferedReader.readLine();
            while(line!=null) {
                String[] arr = line.split(" ");
                for(String s:arr) {
                    int number = 0;
                    for(char c:s.toCharArray()){
                        number = number*10 + Integer.parseInt(String.valueOf(c));
                    }
                    listInteger.add(number);
                }
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            fileInput.close();
            bufferedReader.close();
        }
        int arr[] = listInteger.stream().mapToInt(i->i).toArray();

//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        List<Integer> listInteger = new ArrayList<>();
//        try {
//            String line = reader.readLine();
//            while (line != null) {
//                String[] arr = line.split(" ");
//                for(String s:arr) {
//                    int number = 0;
//                    for(char c:s.toCharArray()){
//                        number = number*10 + Integer.parseInt(String.valueOf(c));
//                    }
//                    listInteger.add(number);
//                }
//                line = reader.readLine();
//            }
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex);
//        } catch (IOException ex) {
//            System.out.println(ex);
//        } finally {
//            reader.close();
//        }
//        int arr[] = listInteger.stream().mapToInt(i->i).toArray();
        timeRead = System.currentTimeMillis() - timeRead;
        System.out.println("Total read: " + timeRead);
        return arr;
    }
    
    public static void writeFile(File file, int[] arr) throws IOException {
        long timeWrite = System.currentTimeMillis();
        Writer write = new FileWriter(file);
        int length = arr.length;
        int j = 1;
        for(int i=0; i<length; i++,j++) {
            write.write(arr[i] + " ");
            if(j%10==0) write.write("\n");
        }
        write.close();
        timeWrite = System.currentTimeMillis() - timeWrite;
        System.out.println("Total write: " + timeWrite);
    }
    
    public static void threadSort(int[] arr) throws InterruptedException {
        long timeSort = System.currentTimeMillis();
        int length = arr.length;
        List<Thread> listThread = new ArrayList<>();
        int maxlim = length%n==0 ? (length/n) : (length/(n-1));
        for(int i=0; i<length; i+=maxlim) {
            int begin = i;
            int end = (length < i+maxlim ? length-1 : i+maxlim-1);
            MergeSort mergeSort = new MergeSort(arr, begin, end);
            Thread thread = new Thread(mergeSort);
            thread.start();
            listThread.add(thread);
        }
        for(Thread th:listThread) {
            th.join();
        }
        for(int i=maxlim; i<length; i+=maxlim) {
            int begin = 0;
            int mid = i-1;
            int end = (length < i+maxlim ? length-1 : i+maxlim-1);
            MergeSort.merge(arr, begin, mid, end);
        }
        timeSort = System.currentTimeMillis() - timeSort;
        System.out.println("Total sort: " + timeSort);
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        long totalTime = System.currentTimeMillis();
        
        int[] arr = readFile(input);    // Đọc file input và lưu data vào mảng arr
        threadSort(arr);                // Sắp xếp mảng arr
        writeFile(output, arr);         // Ghi mảng arr vào file output
        
        totalTime = System.currentTimeMillis() - totalTime;
        System.out.println("Total time: " + totalTime);
              
    }
    
}
