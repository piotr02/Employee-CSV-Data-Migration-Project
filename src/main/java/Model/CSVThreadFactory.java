package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVThreadFactory {
    private ArrayList<CSVThread> threadList = new ArrayList<>();
    private ArrayList<String[]> list = new ArrayList<>();

    /**
     * Returns the list populated by threads.
     * @return list populated by threads
     */
    //!!!!!!!!!!
    // ONLY BECAUSE DATABASE METHODS DON'T WORK NOW
    //!!!!!!!!!!
    public ArrayList<String[]> getList() {
        return list;
    }

    /**
     * Creates a list of threads based on desired number of threads.
     * @param numberOfThreads desired number of threads
     * @param inFile file to read data from
     */
    public void createThreads(int numberOfThreads, BufferedReader inFile){
        for(int i = 0; i < numberOfThreads; i++){
            this.threadList.add(new CSVThread("thread " + i, list, inFile));
        }
    }

    /**
     * Starts all threads and then joins them
     * @param threadList list of threads
     */
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

    /**
     * Runs the threads
     */
    public void run(){
        executeThreads(threadList);
    }

    //!!!!!!!!!!
    // TEMPORARY, TO DELETE LATER
    //!!!!!!!!!!
    public static void main(String[] args) {
        CSVThreadFactory threadFactory = new CSVThreadFactory();
        BufferedReader inFile = null;
        try {
            inFile = new BufferedReader(new FileReader("EmployeeRecordsLarge.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        threadFactory.createThreads(8, inFile);
        double start = System.currentTimeMillis();
        threadFactory.run();
        double end = System.currentTimeMillis();

//        for(String[] record: threadFactory.getList()){
//            System.out.println(Arrays.deepToString(record));
//        }
        System.out.println((threadFactory.getList().size()-1) + " records");
        System.out.println("Process took: " + ((end - start)/1000) + " seconds.");
    }
}
