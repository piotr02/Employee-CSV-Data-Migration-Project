package model;

public class DataValidatorFactory extends CSVToolFactory{

    @Override
    public CSVTool getInstance() {

        System.out.println("Validating Employee Data");
        return new EmployeeCsvDataValidatorNew();


    }
}
