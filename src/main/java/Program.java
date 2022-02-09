import Model.CSVReader;
import Model.EmployeeCsvDataValidator;
import Model.EmployeeRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

public class Program {
    public static void main(String[] args) {

        EmployeeCsvDataValidator dataValidator = new EmployeeCsvDataValidator();
        dataValidator.setData(CSVReader.readCsvFile("EmployeeRecords.csv"));
        dataValidator.spilitData();
        ArrayList<String[]> corruptedData =  dataValidator.getCorrupted();
        ArrayList<String[]> validData =  dataValidator.getValid();

        ArrayList<EmployeeRecord> employeeRecords = new ArrayList<>(
                validData.stream().map(recordString ->{
                    EmployeeRecord record = new EmployeeRecord();
                    record.employer_ID = Integer.parseInt(recordString[0]);
                    record.firstName = recordString[2];
                    record.lastName = recordString[3];

                    return record;
        }).toList());

        System.out.println(employeeRecords);
    }

}
