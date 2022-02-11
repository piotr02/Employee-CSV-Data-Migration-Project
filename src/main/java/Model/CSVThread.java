package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVThread extends Thread{
    private String name;
    private BufferedReader inFile;
    private ArrayList<String[]> list;
    private ArrayList<CSVThread> threadList = new ArrayList<>();

    public CSVThread(String name, BufferedReader inFile){
        this.name = name;
        this.inFile = inFile;
    }

    public void createThreads(int numberOfThreads){
        for(int i = 0; i < numberOfThreads; i++){
            this.threadList.add(new CSVThread("thread" + i, inFile));
        }
    }

    public void populateDatabase(){
        String line;
        try {
            while((line = inFile.readLine()) != null){
                String[] values = line.split(",");
                synchronized (list) {
                    list.add(values);
                }
            }
            System.out.println("Finished" + name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void executeThreads(ArrayList<CSVThread> threadList){

    }

    @Override
    public void run(){
        executeThreads();
    }
}
