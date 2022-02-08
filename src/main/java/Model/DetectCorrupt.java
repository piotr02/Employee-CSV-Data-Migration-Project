package Model;

import java.util.Arrays;

public class DetectCorrupt {

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(
                corruptReader("EmployeeRecords.csv")
        ));}



    public static String[][] corruptReader(String filename) {

        CSVReader fileReader = new CSVReader();
        String[][] fileArray = fileReader.readCsvFile(filename);

        // if ID includes anything but numbers -> Corrupt
        // if name prefix not in enum?
        // If Middle initial not 1 character long
        // If Gender not in M, F (or O?)
        // If date of birth/date of join don't adhere to date format
        // If Salary includes anything but numbers


        String[][] corruptArray = new String[0][];


        return fileArray;
    }


}
