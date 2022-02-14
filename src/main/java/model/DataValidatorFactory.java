package model;


public class DataValidatorFactory extends CSVToolFactory{

    @Override
    public CSVTool getInstance() {

        System.out.println("Validating Employee Data");
        String[][] csvData = CSVReader.readCsvFile("EmployeeRecords.csv");

        EmployeeCsvDataValidatorNew dataValidator = new EmployeeCsvDataValidatorNew(csvData);
        return dataValidator;

    }
}
