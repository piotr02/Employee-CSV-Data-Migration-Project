package model;

import java.util.ArrayList;

public abstract class AbstractDataValidator {
    protected ArrayList<String[]> validData;
    protected ArrayList<String[]> corruptedData;
    private String[][] data;


    public AbstractDataValidator(String[][] data){
        this.data = data;
    }

    public AbstractDataValidator(){
        this.data = null;
    }
    public void setData(String[][] data) {
        this.data = data;
    }


    public String[][] getData(){
        return this.data;
    }

    /**
     * Everytime there is an intension to seperate out the data, a new arraylist is created*/
    public  void splitData(){
        this.corruptedData = new ArrayList<>();
        this.validData = new ArrayList<>();
    }

    /**
     * After splitting the Array this function can be used to get the valid data*/
    public ArrayList<String[]> getValid() {
        return validData;
    }

    /**
     * After splitting the Array this function can be used to get the corrupted data*/
    public ArrayList<String[]> getCorrupted() {
        return corruptedData;
    }
}
