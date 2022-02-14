package model;

public class CSVWriterFactory extends CSVToolFactory{
    @Override
    public CSVTool getInstance() {
        System.out.println("Writing to Database");
        return new CSVWriter();
    }
}
