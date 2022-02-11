package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVThreadFactory {

    private ArrayList<CSVThread> threadList = new ArrayList<>();
    private ArrayList<String[]> list = new ArrayList<>();
    private BufferedReader inFile;

    public ArrayList<String[]> getList() {
        return list;
    }

    public void createThreads(int numberOfThreads){
        try {
            inFile = new BufferedReader(new FileReader("EmployeeRecordsLarge.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < numberOfThreads; i++){
            this.threadList.add(new CSVThread("thread" + i, list, inFile));
        }
    }

    public void executeThreads(ArrayList<CSVThread> threadList){
        CSVThread[] threads = threadList.toArray(new CSVThread[0]);
        for(int i = 0; i < threads.length; i++){
            threads[i].start();
        }
        for(int i = 0; i < threads.length; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run(){
        executeThreads(threadList);
    }

    public static void main(String[] args) {
        CSVThreadFactory threadFactory = new CSVThreadFactory();
        threadFactory.createThreads(8);
        double start = System.currentTimeMillis();
        threadFactory.run();
        double end = System.currentTimeMillis();

        for(String[] record: threadFactory.getList()){
            System.out.println(Arrays.deepToString(record));
        }
        System.out.println(threadFactory.getList().size()-1);
        System.out.println("Process took: " + ((end - start)/1000) + " seconds.");
    }
}
