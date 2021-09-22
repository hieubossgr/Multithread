/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author hieub
 */
public class config {
    protected static int n = 5;
    protected static  ExecutorService exc = Executors.newFixedThreadPool(n);
    protected static  ThreadPoolExecutor pool = (ThreadPoolExecutor)exc;
    protected static  ArrayList<Future<List<Integer>>> results = new ArrayList<>();
    protected static File in = new File("input.txt");
    protected static File out = new File("output.txt");
}
