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

    public  void splitData(){
        this.corruptedData = new ArrayList<>();
        this.validData = new ArrayList<>();
    }

    public ArrayList<String[]> getValid() {
        return validData;
    }

    public ArrayList<String[]> getCorrupted() {
        return corruptedData;
    }
}
