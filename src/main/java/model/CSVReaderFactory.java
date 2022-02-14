package model;

public class CSVReaderFactory extends CSVToolFactory {

    @Override
    public CSVTool getInstance() {
        System.out.println("Reading CSV File");
        return new CSVReader();
    }

}
