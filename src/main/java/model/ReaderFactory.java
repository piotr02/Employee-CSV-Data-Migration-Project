package model;

public class ReaderFactory extends CSVToolFactory {
    @Override
    public CSVTool getInstance() {
        System.out.println("Reading CSV File");
        return new CSVReader();
    }
}
