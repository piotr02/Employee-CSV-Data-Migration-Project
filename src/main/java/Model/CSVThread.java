package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVThread extends Thread{
    private String name;
    private ArrayList<String[]> list;
    private BufferedReader inFile;

    public CSVThread(String name, ArrayList<String[]> list, BufferedReader inFile){
        this.name = name;
        this.list = list;
        this.inFile = inFile;
    }

    /**
     * Populates the database with values from the file.
     */
    //!!!!!!!!!!
    // RIGHT NOW IT POPULATES AN ARRAY, DATABASE METHODS NEEDED
    //!!!!!!!!!!
    public void populateDatabase(){
        String line;
        try {
            while((line = inFile.readLine()) != null){
                String[] values = line.split(",");
                synchronized (list) {
                    list.add(values);
                }
            }
            System.out.println("Finished " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overrides the default run() method from class Thread to populate the database.
     */
    @Override
    public void run(){
        populateDatabase();
    }
}
